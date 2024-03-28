window.addEventListener('load', function () {
    const formulario = document.querySelector('#add_new_paciente');

    formulario.addEventListener('submit', function (event) {
        event.preventDefault();
        document.querySelector('#response').style.display = "none";
        const formData = {
            nombre: document.querySelector('#nombre').value,
            apellido: document.querySelector('#apellido').value,
            dni: document.querySelector('#dni').value,
            domicilio: document.querySelector('#domicilio').value
        };
        const url = 'http://localhost:8080/paciente';
        const settings = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(formData)
        }

        fetch(url, settings)
            .then(response => response.json())
            .then(data => {
                console.log(data)
                if(data.status){
                    errorMessage(data.message)
                }else{
                     let successAlert = '<div class="alert alert-success alert-dismissible">' +
                         '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                         '<strong></strong>Paciente agregado</div>'
                     document.querySelector('#response').innerHTML = successAlert;
                     document.querySelector('#response').style.display = "block";
                     resetUploadForm();
                }
            })
            .catch(error => {
                errorMessage("Intente nuevamente")
            })
    });

    function errorMessage (message) {
        let errorAlert = '<div class="alert alert-danger alert-dismissible">' +
            '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
            '<strong>'+message+'</strong> </div>'
        document.querySelector('#response').innerHTML = errorAlert;
        document.querySelector('#response').style.display = "block";
        resetUploadForm();
    }

    function resetUploadForm(){
        document.querySelector('#nombre').value = "";
        document.querySelector('#apellido').value = "";
        document.querySelector('#dni').value = "";
        document.querySelector('#domicilio').value = "";
    }
});