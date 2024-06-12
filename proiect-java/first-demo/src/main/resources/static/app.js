document.addEventListener("DOMContentLoaded", function() {
    fetch('http://localhost:8080/api/hello')
        .then(response => response.text())
        .then(data => {
            document.getElementById('app').innerText = data;
        })
        .catch(error => console.error('Error:', error));
});
