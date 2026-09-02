// workpiece.js

// Define all setup items
let setupItems = [
    { id: "FIXTURE", confirmed: false },
    { id: "ORIENTATION", confirmed: false },
    { id: "CLAMPING", confirmed: false },
    { id: "MATERIAL", confirmed: false },
    { id: "WORK_OFFSET", confirmed: false }
];

let confirmedCount = 0;

// Confirm a setup item
function confirmSetup(button, setupId) {
    const item = setupItems.find(i => i.id === setupId);

    if (!item || item.confirmed) return;

    item.confirmed = true;
    confirmedCount++;

    // Update button
    button.textContent = "✓ CONFIRMED";
    button.disabled = true;

    // Highlight confirmed item
    button.parentElement.classList.add("confirmed");

    updateStatus();
}

// Update status text and NEXT button
function updateStatus() {
    const status = document.getElementById("setupStatus");
    const nextButton = document.getElementById("nextButton");

    status.textContent = `${confirmedCount} of ${setupItems.length} setup items confirmed`;

    // Enable NEXT only when all confirmed
    nextButton.disabled = !(confirmedCount === setupItems.length);
}

// Go to next page
function goNext() {
    if (confirmedCount === setupItems.length) {
        window.location.href = "/readyreview";
    }
}
