let progress = 0;
let operationRunning = false;
let operationTimer = null;

function startOperation() {

    if (operationRunning) {
        return;
    }

    operationRunning = true;

    document.getElementById("startButton").disabled = true;
    document.getElementById("stopButton").disabled = false;

    document.getElementById("operationState").textContent = "RUNNING";

    document.getElementById("machineStatus").className =
        "machine-status running";

    document.getElementById("machineStatus").innerHTML =
        "<span></span> RUNNING";

    document.getElementById("message").textContent =
        "Machining operation is running...";

    document.getElementById("spindle").textContent =
        "2500 RPM";

    document.getElementById("feed").textContent =
        "500 mm/min";

    operationTimer = setInterval(() => {

        progress++;

        document.getElementById("progressValue").textContent =
            progress + "%";

        document.getElementById("progressFill").style.width =
            progress + "%";

        if (progress >= 100) {
            completeOperation();
        }

    }, 100);
}


function stopOperation() {

    if (!operationRunning) {
        return;
    }

    clearInterval(operationTimer);

    operationRunning = false;

    document.getElementById("startButton").disabled = false;
    document.getElementById("stopButton").disabled = true;

    document.getElementById("operationState").textContent =
        "STOPPED";

    document.getElementById("machineStatus").className =
        "machine-status";

    document.getElementById("machineStatus").innerHTML =
        "<span></span> STOPPED";

    document.getElementById("message").textContent =
        "Operation stopped by operator.";

    document.getElementById("spindle").textContent =
        "0 RPM";

    document.getElementById("feed").textContent =
        "0 mm/min";
}


function completeOperation() {

    clearInterval(operationTimer);

    operationRunning = false;

    document.getElementById("startButton").disabled = true;
    document.getElementById("stopButton").disabled = true;

    document.getElementById("operationState").textContent =
        "COMPLETED";

    document.getElementById("machineStatus").className =
        "machine-status ready";

    document.getElementById("machineStatus").innerHTML =
        "<span></span> COMPLETED";

    document.getElementById("message").textContent =
        "Machining operation completed successfully.";

    document.getElementById("spindle").textContent =
        "0 RPM";

    document.getElementById("feed").textContent =
        "0 mm/min";
}