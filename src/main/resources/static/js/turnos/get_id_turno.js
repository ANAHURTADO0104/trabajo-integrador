
window.addEventListener('load', function () {
    const formulario = document.querySelector('#search_turno');
    formulario.addEventListener('submit', function (event) {
      event.preventDefault();
      document.querySelector('#response').style.display = "none";

      var table = document.getElementById("turnoTable");
      if(table.rows.length > 1){
        table.deleteRow(1);
      }

      const url = 'http://localhost:8080/turno';
      const settings = {
        method: 'GET'
      }

      fetch(`${url}?id=${document.querySelector('#id').value}`,settings)
      .then(response => response.json())
      .then(data => {
          if(data.status){
              errorMessage(data.message)
          }else{
            var turnoRow =table.insertRow();
            let tr_id = 'tr_' + data.id;
            turnoRow.id = tr_id;
            turnoRow.innerHTML =
                    '<td class=\"td_id\">' + data.id + '</td>' +
                    '<td class=\"td_fecha\">' + (data.fecha ? (new Date(data.fecha).toLocaleString()) : "---") + '</td>' +
                    '<td class=\"td_odontologo\">' + data.odontologo.nombre.toUpperCase() + ' ' + data.odontologo.apellido.toUpperCase() + '</td>' +
                    '<td class=\"td_paciente\">' + data.paciente.nombre.toUpperCase() + ' ' + data.paciente.apellido.toUpperCase() + '</td>';
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
        document.querySelector('#id').value = "";
    }
});