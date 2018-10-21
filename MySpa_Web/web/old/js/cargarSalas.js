var section = document.querySelector('section');
    var requestURL = 'http://localhost:8084/MySpa/json/jssala.json';
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
        var w=document.createElement('tr');
        var e=document.createElement('tr');
        var myh4=document.createElement('td');
        var myh1=document.createElement('td');
        var myh2=document.createElement('td');
        var myh3=document.createElement('td');
        var myh5=document.createElement('td');
        
        //se toman los valores del objeto json, en cada posición, y atributo
        q.textContent="";
        w.textContent="";
        e.textContent="";
        myh4.textContent= salas[i].id;
        myh1.textContent= salas[i].nombre;
        myh2.textContent= salas[i].descripción;
        myh3.textContent= salas[i].sucursal;
        myh5.textContent= salas[i].estatus;
        
        //se agregan los elementos creados al section
        article.appendChild(q);
        article.appendChild(w);
        article.appendChild(e);
        article.appendChild(myh4);
        article.appendChild(myh1);
        article.appendChild(myh2);
        article.appendChild(myh3);
        article.appendChild(myh5);
      }
    }
