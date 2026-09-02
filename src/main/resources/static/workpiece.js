let confirmedItems = 0;

const totalItems = 5;

function loadWorkpiece() {

    fetch("/api/workpieces/1")

        .then(response => {

            if (!response.ok) {
                throw new Error("Failed to load workpiece");
            }

            return response.json();
        })

        .then(workpiece => {

            document.getElementById("material").textContent =
                workpiece.material;

            document.getElementById("fixture").textContent =
                workpiece.fixture;

            document.getElementById("orientation").textContent =
                workpiece.orientation;

            document.getElementById("workOffset").textContent =
                workpiece.workOffset;

            const items =
                document.querySelectorAll(".setup-item");

            const statuses = [
                workpiece.fixtureStatus,
                workpiece.orientationStatus,
                workpiece.clampingStatus,
                workpiece.materialStatus,
                workpiece.workOffsetStatus
            ];

            items.forEach((item, index) => {

                const button =
                    item.querySelector("button");

                if (statuses[index] === "CONFIRMED") {

                    item.classList.add("confirmed");

                    button.textContent =
                        "✓ CONFIRMED";

                    button.disabled = true;

                    confirmedItems++;
                }
            });

            updateStatus();
        })

        .catch(error => {

            console.error(
                "Error loading workpiece:",
                error
            );
        });
}

function confirmSetup(button, setupType) {

    const item =
        button.parentElement;

    if (item.classList.contains("confirmed")) {
        return;
    }

    fetch(
        `/api/workpieces/1/setup/${setupType}?status=CONFIRMED`,
        {
            method: "PUT"
        }
    )

        .then(response => {

            if (!response.ok) {
                throw new Error(
                    "Failed to update workpiece setup"
                );
            }

            return response.json();
        })

        .then(workpiece => {

            item.classList.add("confirmed");

            button.textContent =
                "✓ CONFIRMED";

            button.disabled = true;

            confirmedItems++;

            updateStatus();

            console.log(
                "Setup confirmed:",
                setupType
            );

            console.log(
                "Workpiece status:",
                workpiece.status
            );
        })

        .catch(error => {

            console.error(
                "Workpiece update error:",
                error
            );
        });
}

function updateStatus() {

    const status =
        document.getElementById("setupStatus");

    const nextButton =
        document.getElementById("nextButton");

    status.textContent =
        confirmedItems +
        " of " +
        totalItems +
        " setup items confirmed";

    if (confirmedItems === totalItems) {

        status.textContent =
            "Workpiece setup completed ✓";

        nextButton.disabled = false;

    } else {

        nextButton.disabled = true;
    }
}

function goNext() {

    if (confirmedItems === totalItems) {

        window.location.href =
            "/ready-review";
    }
}

loadWorkpiece();