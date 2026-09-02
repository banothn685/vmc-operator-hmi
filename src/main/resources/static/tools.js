const operationId = 1;

let requiredTools = [];
let confirmedTools = 0;

function loadTools() {

    fetch(`/api/operations/${operationId}/tools`)
        .then(response => {

            if (!response.ok) {
                throw new Error("Failed to load required tools");
            }

            return response.json();
        })
        .then(operationTools => {

            requiredTools = operationTools;

            const toolList =
                document.getElementById("toolList");

            toolList.innerHTML = "";

            confirmedTools = 0;

            operationTools.forEach(operationTool => {

                const tool = operationTool.tool;

                if (!tool) {
                    return;
                }

                const item =
                    document.createElement("div");

                item.classList.add("tool-item");

                const isConfirmed =
                    tool.status === "CONFIRMED";

                if (isConfirmed) {
                    item.classList.add("confirmed");
                    confirmedTools++;
                }

                item.innerHTML = `
                    <div>
                        <strong>${tool.toolNumber}</strong>
                        <span>${tool.toolName}</span>
                    </div>

                    <button
                        onclick="confirmTool(this, ${tool.id})"
                        ${isConfirmed ? "disabled" : ""}>
                        ${isConfirmed ? "✓ CONFIRMED" : "CONFIRM"}
                    </button>
                `;

                toolList.appendChild(item);
            });

            updateStatus();
        })
		.catch(error => {

		    console.error(
		        "Error loading required tools:",
		        error
		    );

		    document.getElementById("toolList").innerHTML =
		        "<p>Failed to load required tools.</p><p>" +
		        error.message +
		        "</p>";
		});
}

function confirmTool(button, toolId) {

    fetch(`/api/tools/${toolId}/status?status=CONFIRMED`, {
        method: "PUT"
    })
        .then(response => {

            if (!response.ok) {
                throw new Error("Failed to confirm tool");
            }

            return response.json();
        })
        .then(tool => {

            const item =
                button.parentElement;

            item.classList.add("confirmed");

            button.textContent =
                "✓ CONFIRMED";

            button.disabled = true;

            confirmedTools++;

            updateStatus();
        })
        .catch(error => {

            console.error(
                "Error confirming tool:",
                error
            );
        });
}

function updateStatus() {

    const status =
        document.getElementById("toolStatus");

    const nextButton =
        document.getElementById("nextButton");

    const totalTools =
        requiredTools.length;

    status.textContent =
        confirmedTools +
        " of " +
        totalTools +
        " tools confirmed";

    if (confirmedTools === totalTools && totalTools > 0) {

        status.textContent =
            "All required tools confirmed ✓";

        nextButton.disabled = false;

    } else {

        nextButton.disabled = true;
    }
}

function goNext() {

    if (
        confirmedTools === requiredTools.length &&
        requiredTools.length > 0
    ) {

        window.location.href =
            "/workpiece";
    }
}

loadTools();