API_URL = "https://swapi.dev/api/people/1/"
fetch(API_URL)
    .then(response => response.json())
    .then(data => {
        document.getElementById("idName").innerHTML = data.name
        document.getElementById("idGender").innerHTML = data.gender
        data.vehicles.filter((vehicle,id)=>id===0)
            .map(vehicle=>document.getElementById("idVehicles").innerHTML = vehicle);
    })
    .catch(error => console.log(error))