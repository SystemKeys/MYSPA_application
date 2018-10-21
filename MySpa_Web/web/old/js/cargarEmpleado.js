//Este es el de consultarEmpleado.
    var section = document.querySelector('section');
    var requestURL = 'http://localhost:8084/MySpa/json/empleado.json';
    var request = new XMLHttpRequest();
    request.open('GET', requestURL);
    request.responseType = 'json';
    request.send();
    request.onload = function() {
      var empleados = request.response;
      showEmpleados(empleados);
    };
    
    //esta funcion llena los article
    function showEmpleados(jsonObj) {
        //se toman los miembros del squad, que se encuentran
        //en un arreglo colocado en el atributo members del json
        var empleados = jsonObj["empleados"];
        for(var i = 0; i < empleados.length; i++) {
          //se crean elementos html para hacer el vaciado de datos
        var article = document.querySelector('table');
        var q=document.createElement('tr');
        var w=document.createElement('tr');
        var e=document.createElement('tr');
        var myh1=document.createElement('td');
        var myh2=document.createElement('td');
        var myh4=document.createElement('td');
        var myh5=document.createElement('td');
        var myh6=document.createElement('td');
        var myh7=document.createElement('td');
        var myh8=document.createElement('td');
        var myh9=document.createElement('td');
        var myh10=document.createElement('td');
        var myh11=document.createElement('td');
        var myh12=document.createElement('td');
        var myh13=document.createElement('td');
        
        //se toman los valores del objeto json, en cada posición, y atributo
        q.textContent="";
        w.textContent="";
        e.textContent="";
        myh1.textContent= empleados[i].id;
        myh2.textContent= empleados[i].persona.nombre 
                    + " " + empleados[i].persona.apellidoPaterno 
                    + " " + empleados[i].persona.apellidoMaterno;
        myh4.textContent= empleados[i].numeroEmpleado;
        myh7.textContent= empleados[i].persona.domicilio;
        myh8.textContent= empleados[i].persona.rfc;
        myh9.textContent= empleados[i].persona.telefono;
        myh10.textContent= empleados[i].persona.genero;
        myh11.textContent= empleados[i].puesto;
        myh12.textContent= empleados[i].usuario.rol;
        myh13.textContent= empleados[i].estatus;
        
        //se agregan los elementos creados al section
        article.appendChild(q);
        article.appendChild(w);
        article.appendChild(e);
        article.appendChild(myh1);
        article.appendChild(myh2);
        article.appendChild(myh4);
        article.appendChild(myh7);
        article.appendChild(myh8);
        article.appendChild(myh9);
        article.appendChild(myh10);
        article.appendChild(myh11);
        article.appendChild(myh12);
        article.appendChild(myh13);
      }
    }