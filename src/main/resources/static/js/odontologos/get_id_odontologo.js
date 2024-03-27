
window.addEventListener('load', function () {
    const formulario = document.querySelector('#search_dentist');
    formulario.addEventListener('submit', function (event) {
      event.preventDefault();
      document.querySelector('#response').style.display = "none";

      var table = document.getElementById("dentistTable");
      if(table.rows.length > 1){
        table.deleteRow(1);
      }

      const url = 'http://localhost:8080/odontologo';
      const settings = {
        method: 'GET'
      }

      fetch(`${url}?matricula=${document.querySelector('#matricula').value}`,settings)
      .then(response => response.json())
      .then(data => {
          if(data.status){
              errorMessage(data.message)
          }else{
            var dentistRow =table.insertRow();
            let tr_id = 'tr_' + data.matricula;
            dentistRow.id = tr_id;
            dentistRow.innerHTML =
                    '<td class=\"td_matricula\">' + data.matricula + '</td>' +
                    '<td class=\"td_nombre\">' + data.nombre.toUpperCase() + '</td>' +
                    '<td class=\"td_apellido\">' + data.apellido.toUpperCase() + '</td>';
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
        document.querySelector('#matricula').value = "";
    }
});