
window.addEventListener('load', function () {
    const formulario = document.querySelector('#search_paciente');
    formulario.addEventListener('submit', function (event) {
      event.preventDefault();
      document.querySelector('#response').style.display = "none";

      var table = document.getElementById("pacienteTable");
      if(table.rows.length > 1){
        table.deleteRow(1);
      }

      const url = 'http://localhost:8080/paciente';
      const settings = {
        method: 'GET'
      }

      fetch(`${url}?dni=${document.querySelector('#dni').value}`,settings)
      .then(response => response.json())
      .then(data => {
          if(data.status){
              errorMessage(data.message)
          }else{
            var pacienteRow =table.insertRow();
            let tr_id = 'tr_' + data.dni;
            pacienteRow.id = tr_id;
            pacienteRow.innerHTML =
                    '<td class=\"td_dni\">' + data.dni + '</td>' +
                    '<td class=\"td_nombre\">' + data.nombre.toUpperCase() + '</td>' +
                    '<td class=\"td_apellido\">' + data.apellido.toUpperCase() + '</td>' +
                    '<td class=\"td_domicilio\">' + data.domicilio.toUpperCase() + '</td>' +
                    '<td class=\"td_fecha_alta\">' + (data.fechaAlta ? (new Date(data.fechaAlta).toLocaleString()) : "---") + '</td>';
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