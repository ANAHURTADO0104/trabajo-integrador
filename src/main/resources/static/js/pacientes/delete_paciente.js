
window.addEventListener('load', function () {
    const formulario = document.querySelector('#delete_paciente');
    formulario.addEventListener('submit', function (event) {
      event.preventDefault();
      document.querySelector('#response').style.display = "none";
      const url = 'http://localhost:8080/paciente';
      const settings = {
        method: 'DELETE'
      }

      fetch(`${url}?dni=${document.querySelector('#dni').value}`,settings)
      .then(response => response.json())
      .then(data => {
        if(data.status){
            errorMessage(data.message)
        }else{
             let successAlert = '<div class="alert alert-success alert-dismissible">' +
                 '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                 '<strong></strong> Paciente eliminado </div>'
             document.querySelector('#response').innerHTML = successAlert;
             document.querySelector('#response').style.display = "block";
             resetUploadForm();
        }
      })
      .catch(error => {
          errorMessage("Intente nuevamente")
      })
    })
    function errorMessage (message) {
        let errorAlert = '<div class="alert alert-danger alert-dismissible">' +
            '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
            '<strong>'+message+'</strong> </div>'
        document.querySelector('#response').innerHTML = errorAlert;
        document.querySelector('#response').style.display = "block";
        resetUploadForm();
    }

    function resetUploadForm(){
        document.querySelector('#dni').value = "";
    }
});