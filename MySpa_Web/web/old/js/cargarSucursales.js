
    var section = document.querySelector('section');
    var requestURL = 'http://localhost:8084/MySpa/json/sucursal.json';
    var request = new XMLHttpRequest();
    request.open('GET', requestURL);
    request.responseType = 'json';
    request.send();
    request.onload = function() {
      var sucursales = request.response;
      showSucursales(sucursales);
    };
    
    //esta funcion llena los article
    function showSucursales(jsonObj) {
        //se toman los miembros del squad, que se encuentran
        //en un arreglo colocado en el atributo members del json
        var sucursales = jsonObj["sucursales"];
        for(var i = 0; i < sucursales.length; i++) {
          //se crean elementos html para hacer el vaciado de datos
        var article = document.querySelector('table');
        var q=document.createElement('tr');
        var w=document.createElement('tr');
        var e=document.createElement('tr');
        var myh1=document.createElement('td');
        var myh2=document.createElement('td');
        var myh3=document.createElement('td');
        var myh4=document.createElement('td');
        var myh5=document.createElement('td');
        
        //se toman los valores del objeto json, en cada posición, y atributo
        q.textContent="";
        w.textContent="";
        e.textContent="";
        myh1.textContent= sucursales[i].nombre;
        myh2.textContent= sucursales[i].domicilio;
        myh3.textContent= sucursales[i].longitud;
        myh4.textContent= sucursales[i].latitud;
        myh5.textContent= sucursales[i].estatus;
        
        //se agregan los elementos creados al section
        article.appendChild(q);
        article.appendChild(w);
        article.appendChild(e);
        article.appendChild(myh1);
        article.appendChild(myh2);
        article.appendChild(myh3);
        article.appendChild(myh4);
        article.appendChild(myh5);
      }
    }