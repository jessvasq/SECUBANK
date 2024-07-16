const navHeader = document.getElementById("navbar");

// DOMContentLoaded loads the header as soon as the HTML is loaded, js script inserts the header before the user interacts with the page
document.addEventListener("DOMContentLoaded", loadNav());

function loadNav(){
    fetch("navBar.html")
        .then(response => response.text())
        .then(data => {
            navHeader.innerHTML = data;
        })
}


const footer = document.getElementById("footer");

document.addEventListener("DOMContentLoaded", loadFooter());

function loadFooter() {
    fetch("footer.html")
        .then(response => response.text())
        .then(data => {
            footer.innerHTML = data;
        })
}