var section = document.querySelector('section');
    var requestURL = 'http://localhost:8084/MySpa/json/horario_sala.json';
    var request = new XMLHttpRequest();
    request.open('GET', requestURL);
    request.responseType = 'json';
    request.send();
    request.onload = function() {
      var salas = request.response;
      showSalas(salas);
    };
    
    //esta funcion llena los article
    function showSalas(jsonObj) {
        //se toman los miembros del squad, que se encuentran
        //en un arreglo colocado en el atributo members del json
        var salas = jsonObj["salas"];
        for(var i = 0; i < salas.length; i++) {
          //se crean elementos html para hacer el vaciado de datos
        var article = document.querySelector('table');
        var q=document.createElement('tr');
        var w=document.createElement('td');
        var myh=document.createElement('td');
        var myh1=document.createElement('td');
        var myh2=document.createElement('td');
        var myh3=document.createElement('td');
        
        //se toman los valores del objeto json, en cada posición, y atributo
        q.textContent="";
        w.textContent="";
        myh2.textContent= salas[i].id;
        myh.textContent= salas[i].nombre;
        myh3.textContent= salas[i].descripción;
        if(salas[i].horario.horaInicio1 === undefined){
            salas[i].horario.horaInicio1 = "";
            salas[i].horario.horaFin1 = "";
        }
        if(salas[i].horario.horaInicio2 === undefined){
            salas[i].horario.horaInicio2 = "";
            salas[i].horario.horaFin2 = "";
        }
        if(salas[i].horario.horaInicio3 === undefined){
            salas[i].horario.horaInicio3 = "";
            salas[i].horario.horaFin3 = "";
        }
        if(salas[i].horario.horaInicio4 === undefined){
            salas[i].horario.horaInicio4 = "";
            salas[i].horario.horaFin4 = "";
        }
        if(salas[i].horario.horaInicio5 === undefined){
            salas[i].horario.horaInicio5 = "";
            salas[i].horario.horaFin5 = "";
        }
        if(salas[i].horario.horaInicio6 === undefined){
            salas[i].horario.horaInicio6 = "";
            salas[i].horario.horaFin6 = "";
        }
        if(salas[i].horario.horaInicio7 === undefined){
            salas[i].horario.horaInicio7 = "";
            salas[i].horario.horaFin7 = "";
        }
        if(salas[i].horario.horaInicio8 === undefined){
            salas[i].horario.horaInicio8 = "";
            salas[i].horario.horaFin8 = "";
        }
        if(salas[i].horario.horaInicio9 === undefined){
            salas[i].horario.horaInicio9 = "";
            salas[i].horario.horaFin9 = "";
        }
        if(salas[i].horario.horaInicio10 === undefined){
            salas[i].horario.horaInicio10 = "";
            salas[i].horario.horaFin10 = "";
        }
        myh1.textContent= salas[i].horario.horaInicio1 + " - " + salas[i].horario.horaFin1
                + "\r\n" + salas[i].horario.horaInicio2 + " - " + salas[i].horario.horaFin2
                + "\r\n" + salas[i].horario.horaInicio3 + " - " + salas[i].horario.horaFin3
                + "\r\n" + salas[i].horario.horaInicio4 + " - " + salas[i].horario.horaFin4
                + "\r\n" + salas[i].horario.horaInicio5 + " - " + salas[i].horario.horaFin5
                + "\r\n" + salas[i].horario.horaInicio6 + " - " + salas[i].horario.horaFin6
                + "\r\n" + salas[i].horario.horaInicio7 + " - " + salas[i].horario.horaFin7
                + "\r\n" + salas[i].horario.horaInicio8 + " - " + salas[i].horario.horaFin8
                + "\r\n" + salas[i].horario.horaInicio9 + " - " + salas[i].horario.horaFin9
                + "\r\n" + salas[i].horario.horaInicio10 + " - " + salas[i].horario.horaFin10
        ;
        
        
        //se agregan los elementos creados al section
        article.appendChild(q);
        article.appendChild(myh2);
        article.appendChild(myh);
        article.appendChild(myh1);
      }
      
    }