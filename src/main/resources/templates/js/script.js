
const form = document.getElementById("patient-form");
document.getElementById("myForm").addEventListener("submit", function (event) {
    event.preventDefault();

    const username = document.getElementById("name").value;
    const password = document.getElementById("password").value;

    const config = {
        headers: {
            'Content-Type': 'application/json'
        }
    };
    const data = {
        username: username,
        password: password
    };

    let req = axios.get("/login", data, config)
    .then(function (response) {
        console.log("POST request successful:", response.data);
    })
    .catch(function (error) {
        console.error("POST request failed:", error);
    });
    alert(req)
});

//
//
// form.addEventListener("submit", function(event) {
//     event.preventDefault();
//     const formData = {
//         name: document.getElementById("name").value,
//         surname: document.getElementById("surname").value,
//         gender: document.querySelector('input[name="gender"]:checked').value,
//         age: document.getElementById("age").value,
//         passport: document.getElementById("passport").value,
//         palata_raqami: document.getElementById("palata_raqami").value,
//         palata_joy_raqami: document.getElementById("palata_joy_raqami").value,
//         palata_yotkizilgan_sana: document.getElementById("palata_yotkizilgan_sana").value,
//         kasallik: document.getElementById("kasallik").value,
//         tashxis: document.getElementById("tashxis").value,
//     };
//     const jsonData = JSON.stringify(formData);
//     console.log(jsonData);
// });
