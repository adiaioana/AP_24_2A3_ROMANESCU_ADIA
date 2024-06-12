document.addEventListener("DOMContentLoaded", function() {
    const form = document.getElementById('locationForm');

    form.addEventListener('submit', function(event) {
        event.preventDefault(); // Prevent default form submission behavior

        // Gather input values
        const depots = document.getElementById('depots').value;
        const clients = document.getElementById('clients').value;
        const vehicles = document.getElementById('vehicles').value;

        // Prepare data object
        const data = {
            depots: depots,
            clients: clients,
            vehicles: vehicles
        };

        // Call function to send data to backend
        sendDataToBackend(data);
        window.location.href = "/graph";
    });
});

function sendDataToBackend(data) {
    // Make an HTTP POST request to your backend API
    fetch('/api/location', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            fetch('/api/graph', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: response
            })
            return response.json();
        })
        .then(data => {
            // Handle response from backend
            console.log('Response from backend:', data);
        })
        .catch(error => {
            console.error('Error:', error);
        });
}
