function guardar()   {
                    
			//traer datos del formulario
			var misDatos =
			{
					fecha:document.getElementById("fecha").value,
                                        cliente:document.getElementById("cliente").value,
					sala:document.getElementById("sala").value,
                                        estatus:document.getElementById("estatus").value
			};
                        
                        //seleccionamos el apartado donde se verá le numero unico
                        var section = document.querySelector('table');
                        
                        var tr = document.createElement('tr');
			tr.textContent= "";
			section.appendChild(tr);
                        
                        var p2 = document.createElement('td');
			p2.textContent= misDatos.fecha;
			section.appendChild(p2);
                        
                        var p4 = document.createElement('td');
			p4.textContent= misDatos.cliente;
			section.appendChild(p4);
                        
                        var p3 = document.createElement('td');
			p3.textContent= misDatos.sala;
			section.appendChild(p3);
                        
                        var p5 = document.createElement('td');
			p5.textContent= misDatos.estatus;
			section.appendChild(p5);
                    }
                    
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
    
    function showClientes(jsonObj) {
        //se toman los miembros del squad, que se encuentran
        //en un arreglo colocado en el atributo members del json
        var clientes = jsonObj["clientes"];
        for(var i = 0; i < clientes.length; i++) {
          //se crean elementos html para hacer el vaciado de datos
        var article = document.querySelector('select');
        var myh2=document.createElement('option');
        
        myh2.textContent= clientes[i].persona.nombre 
                    + " " + clientes[i].persona.apellidoPaterno 
                    + " " + clientes[i].persona.apellidoMaterno;
            
        article.appendChild(myh2);
      }
    }