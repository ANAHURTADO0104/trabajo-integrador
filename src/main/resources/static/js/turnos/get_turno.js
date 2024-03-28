
window.addEventListener('load', function () {
      const url = 'http://localhost:8080/turno';
      const settings = {
        method: 'GET'
      }

      fetch(url,settings)
      .then(response => response.json())
      .then(data => {
          if(data.status){
                errorMessage(data.message)
            }else{
                 if(data.length> 0){
                     for(turno of data){
                        var table = document.getElementById("turnoTable");
                        var turnoRow =table.insertRow();
                        let tr_id = 'tr_' + turno.id;
                        turnoRow.id = tr_id;
                        turnoRow.innerHTML =
                                '<td class=\"td_id\">' + turno.id + '</td>' +
                                '<td class=\"td_fecha\">' + (turno.fecha ? (new Date(turno.fecha).toLocaleString()) : "---") + '</td>' +
                                '<td class=\"td_odontologo\">' + turno.odontologo.nombre.toUpperCase() + ' ' + turno.odontologo.apellido.toUpperCase() + '</td>' +
                                '<td class=\"td_paciente\">' + turno.paciente.nombre.toUpperCase() + ' ' + turno.paciente.apellido.toUpperCase() + '</td>';
                    };
                 }else{
                    errorMessage("Actualmente no existen turnos registrados")
                 }
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
}