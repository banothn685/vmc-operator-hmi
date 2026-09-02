let confirmedChecks = 0;

let totalChecks = 0;

function loadChecks() {

    fetch("/api/machine-checks")

        .then(response => response.json())

        .then(checks => {

            const checkList =
                document.getElementById("checkList");

            checkList.innerHTML = "";

            totalChecks = checks.length;

            confirmedChecks = 0;

            checks.forEach(check => {

                const item =
                    document.createElement("div");

                item.classList.add("check-item");

                const isConfirmed =
                    check.status === "CONFIRMED";

                if (isConfirmed) {

                    item.classList.add("confirmed");

                    confirmedChecks++;

                }

                item.innerHTML = `

                    <div>

                        <strong>${check.checkName}</strong>

                    </div>

                    <button
                        onclick="confirmCheck(this, ${check.id})"
                        ${isConfirmed ? "disabled" : ""}>

                        ${isConfirmed
                            ? "✓ CONFIRMED"
                            : "CONFIRM"}

                    </button>

                `;

                checkList.appendChild(item);

            });

            updateStatus();

        })

        .catch(error => {

            console.error(
                "Error loading machine checks:",
                error
            );

        });

}


function confirmCheck(button, id) {

    fetch(
        {
            method: "PUT"
        }
    )

        .then(response => {

            if (!response.ok) {

                throw new Error(
                    "Failed to update check"
                );

            }

            return response.json();

        })

        .then(check => {

            const item =
                button.parentElement;

            item.classList.add("confirmed");

            button.textContent =
                "✓ CONFIRMED";

            button.disabled = true;

            confirmedChecks++;

            updateStatus();

        })

        .catch(error => {

            console.error(
                "Error confirming check:",
                error
            );

        });

}

function updateStatus() {

    const status =
        document.getElementById("checkStatus");

    const nextButton =
        document.getElementById("nextButton");

    status.textContent =
        confirmedChecks +
        " of " +
        totalChecks +
        " checks confirmed";

    if (
        confirmedChecks === totalChecks &&
        totalChecks > 0
    ) {

        status.textContent =
            "All machine checks confirmed ✓";

        nextButton.disabled = false;

    } else {

        nextButton.disabled = true;

    }

}


function goNext() {

    if (
        confirmedChecks === totalChecks &&
        totalChecks > 0
    ) {

        window.location.href = "/tools";

    }

}
loadChecks();