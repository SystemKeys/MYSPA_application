//Este es el de consultarEmpleado.
    var section = document.querySelector('section');
    var requestURL = 'http://localhost:8084/MySpa/json/clientes.json';
    var request = new XMLHttpRequest();
    request.open('GET', requestURL);
    request.responseType = 'json';
    request.send();
    request.onload = function() {
      var clientes = request.response;
      showClientes(clientes);
    };
    
    //esta funcion llena los article
    function showClientes(jsonObj) {
        //se toman los miembros del squad, que se encuentran
        //en un arreglo colocado en el atributo members del json
        var clientes = jsonObj["clientes"];
        for(var i = 0; i < clientes.length; i++) {
          //se crean elementos html para hacer el vaciado de datos
        var article = document.querySelector('table');
        var q=document.createElement('tr');
        var w=document.createElement('tr');
        var e=document.createElement('tr');
        var myh1=document.createElement('td');
        var myh2=document.createElement('td');
        var myh4=document.createElement('td');
        var myh7=document.createElement('td');
        var myh8=document.createElement('td');
        var myh9=document.createElement('td');
        var myh5=document.createElement('td');
        var myh10=document.createElement('td');
        var myh6=document.createElement('td');
        var myh11=document.createElement('td');
        var myh12=document.createElement('td');
        var myh13=document.createElement('td');
        
        //se toman los valores del objeto json, en cada posición, y atributo
        q.textContent="";
        w.textContent="";
        e.textContent="";
        myh1.textContent= clientes[i].id;
        myh2.textContent= clientes[i].persona.nombre 
                    + " " + clientes[i].persona.apellidoPaterno 
                    + " " + clientes[i].persona.apellidoMaterno;
        myh4.textContent= clientes[i].numeroCliente;
        myh7.textContent= clientes[i].persona.domicilio;
        myh8.textContent= clientes[i].persona.rfc;
        myh9.textContent= clientes[i].persona.telefono;
        myh5.textContent= clientes[i].correo;
        myh10.textContent= clientes[i].persona.genero;
        myh12.textContent= clientes[i].usuario.rol;
        myh13.textContent= clientes[i].estatus;
        
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
        article.appendChild(myh5);
        article.appendChild(myh10);
        article.appendChild(myh12);
        article.appendChild(myh13);
      }
    }