    var requestURL = 'http://localhost:8084/MySpa/json/horario.json';
    var request = new XMLHttpRequest();
    request.open('GET', requestURL);
    request.responseType = 'json';
    request.send();
    request.onload = function() {
      var horarios = request.response;
      showHorarios(horarios);
    };

    //esta funcion llena los article
    function showHorarios(jsonObj) {
        //se toman los miembros del squad, que se encuentran
        //en un arreglo colocado en el atributo members del json
        var horarios = jsonObj["horarios"];
        for(var i = 0; i < horarios.length; i++) {
          //se crean elementos html para hacer el vaciado de datos
        var article = document.querySelector('table');
        var q=document.createElement('tr');
        var myh4=document.createElement('td');
        
        
        //se toman los valores del objeto json, en cada posición, y atributo
        q.textContent="";
        myh4.textContent= horarios[i].horaInicio;
        
        //se agregan los elementos creados al section
        article.appendChild(q);
        article.appendChild(myh4);
      }
    }