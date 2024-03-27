
window.addEventListener('load', function () {
      const url = 'http://localhost:8080/odontologo';
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
                     for(dentist of data){
                        var table = document.getElementById("dentistTable");
                        var dentistRow =table.insertRow();
                        let tr_id = 'tr_' + dentist.matricula;
                        dentistRow.id = tr_id;
                        dentistRow.innerHTML =
                                '<td class=\"td_matricula\">' + dentist.matricula + '</td>' +
                                '<td class=\"td_nombre\">' + dentist.nombre.toUpperCase() + '</td>' +
                                '<td class=\"td_apellido\">' + dentist.apellido.toUpperCase() + '</td>';
                    };
                 }else{
                    errorMessage("Actualmente no tienes odontólogos registrados")
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