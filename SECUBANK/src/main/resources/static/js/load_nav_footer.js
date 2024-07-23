// const navHeader = document.getElementById("navbar");
//
// // DOMContentLoaded loads the header as soon as the HTML is loaded, js script inserts the header before the user interacts with the page
// document.addEventListener("DOMContentLoaded", loadNav);
//
// function loadNav(){
//     fetch("navBar.html")
//         .then(response => response.text())
//         .then(data => {
//             navHeader.innerHTML = data;
//         })
// }
//
//
// const footer = document.getElementById("footer");
//
// document.addEventListener("DOMContentLoaded", loadFooter);
//
// function loadFooter() {
//     fetch("footer.html")
//         .then(response => response.text())
//         .then(data => {
//             footer.innerHTML = data;
//         })
// }


const navHeader = document.getElementById("navbar");

// DOMContentLoaded loads the header as soon as the HTML is loaded, js script inserts the header before the user interacts with the page
document.addEventListener("DOMContentLoaded", loadNav);

function loadNav(){
    fetch("/secubank/nav")
        .then(response => response.text())
        .then(data => {
            navHeader.innerHTML = data;
        })
}


const footer = document.getElementById("footer");

document.addEventListener("DOMContentLoaded", () => {
    loadFooter();
});

function loadFooter() {
    fetch("/secubank/footer")
        .then(response => {
            if (!response.ok) {
                throw new Error(`HTTP error! Status: ${response.status}`);
            }
            return response.text();
        })
        .then(data => {
            // Ensure the footer element is still available
            if (footer) {
                footer.innerHTML = data;
            } else {
                console.error("Footer element not found.");
            }
        })
        .catch(error => {
            console.error("Error loading footer:", error);
        });
}


const notificationSection = document.getElementById("section2");

document.addEventListener("DOMContentLoaded", () => {
    loadNotifications();
});

function loadNotifications(){
    fetch("/secubank/notifications")
        .then(response => response.text())
        .then(data => {
            notificationSection.innerHTML = data;
        })
        .catch(err => console.log('Error loading notifications:', err))
}
//
//
// const transferSection = document.getElementById("transferContainer");
//
// document.addEventListener("DOMContentLoaded", () => {
//     loadTransferForm();
// });
//
// function loadTransferForm(){
//     fetch("/secubank/transferByEmail")
//         .then(response => response.text())
//         .then(data => {
//             transferSection.innerHTML = data;
//         })
//         .catch(err => console.log('Error loading transfer form:', err))
// }