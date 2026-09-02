const operationId = 1;

function loadReadyReview() {

    const readyMessage =
        document.getElementById("readyMessage");

    const readyDescription =
        document.getElementById("readyDescription");

    const startButton =
        document.getElementById("startButton");

    readyMessage.textContent =
        "CHECKING SYSTEM...";

    readyDescription.textContent =
        "Checking machine, tools and workpiece status...";

    Promise.all([

        fetch("/api/machine-checks")
            .then(response => {

                if (!response.ok) {
                    throw new Error(
                        "Machine Checks API failed: " +
                        response.status
                    );
                }

                return response.json();
            }),

        fetch(`/api/operations/${operationId}/tools`)
            .then(response => {

                if (!response.ok) {
                    throw new Error(
                        "Required Tools API failed: " +
                        response.status
                    );
                }

                return response.json();
            }),

        fetch("/api/workpieces/1")
            .then(response => {

                if (!response.ok) {
                    throw new Error(
                        "Workpiece API failed: " +
                        response.status
                    );
                }

                return response.json();
            })

    ])

    .then(([checks, operationTools, workpiece]) => {

        const allChecksConfirmed =
            Array.isArray(checks) &&
            checks.length > 0 &&
            checks.every(
                check => check.status === "CONFIRMED"
            );

			const allToolsConfirmed =
			    Array.isArray(operationTools) &&
			    operationTools.length > 0 &&
			    operationTools.every(
			        operationTool =>
			            operationTool.tool &&
			            operationTool.tool.status === "CONFIRMED"
			    );

        const workpieceReady =
            workpiece &&
            workpiece.status === "CONFIRMED";

        updateItem(
            "machineCheck",
            allChecksConfirmed,
            "Machine Checks"
        );

        updateItem(
            "toolCheck",
            allToolsConfirmed,
            "Required Tools"
        );

        updateItem(
            "workpieceCheck",
            workpieceReady,
            "Workpiece Setup"
        );

        const allReady =
            allChecksConfirmed &&
            allToolsConfirmed &&
            workpieceReady;

        if (allReady) {

            readyMessage.textContent =
                "SYSTEM READY ✓";

            readyDescription.textContent =
                "All startup requirements have been confirmed.";

            startButton.disabled = false;

        } else {

            readyMessage.textContent =
                "SYSTEM NOT READY";

            readyDescription.textContent =
                "Complete all startup requirements before starting.";

            startButton.disabled = true;
        }

    })

    .catch(error => {

        console.error(
            "Ready Review Error:",
            error
        );

        readyMessage.textContent =
            "SYSTEM CHECK FAILED";

        readyDescription.textContent =
            error.message;

        startButton.disabled = true;
    });
}

function updateItem(id, confirmed, label) {

    const item =
        document.getElementById(id);

    if (!item) {
        return;
    }

    if (confirmed) {

        item.classList.add("completed");

        item.innerHTML = `
            <div class="icon">✓</div>

            <div>
                <strong>${label}</strong>
                <span>All requirements confirmed</span>
            </div>

            <b>COMPLETE</b>
        `;

    } else {

        item.classList.remove("completed");

        item.innerHTML = `
            <div class="icon">✗</div>

            <div>
                <strong>${label}</strong>
                <span>Requirements are not confirmed</span>
            </div>

            <b>NOT COMPLETE</b>
        `;
    }
}

function goBack() {

    window.location.href =
        "/workpiece";
}

function startOperation() {

    window.location.href =
        "/operation";
}

loadReadyReview();