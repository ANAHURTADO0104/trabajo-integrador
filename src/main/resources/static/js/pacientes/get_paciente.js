
window.addEventListener('load', function () {
      const url = 'http://localhost:8080/paciente';
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
                     for(paciente of data){
                        var table = document.getElementById("pacienteTable");
                        var pacienteRow =table.insertRow();
                        let tr_id = 'tr_' + paciente.dni;
                        pacienteRow.id = tr_id;
                        pacienteRow.innerHTML =
                                '<td class=\"td_dni\">' + paciente.dni + '</td>' +
                                '<td class=\"td_nombre\">' + paciente.nombre.toUpperCase() + '</td>' +
                                '<td class=\"td_apellido\">' + paciente.apellido.toUpperCase() + '</td>' +
                                '<td class=\"td_domicilio\">' + paciente.domicilio.toUpperCase() + '</td>' +
                                '<td class=\"td_fecha_alta\">' + (paciente.fechaAlta ? (new Date(paciente.fechaAlta).toLocaleString()) : "---") + '</td>';
                    };
                 }else{
                    errorMessage("Actualmente no tienes pacientes registrados")
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