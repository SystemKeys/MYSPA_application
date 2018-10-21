function guardar()   {
                    
			//traer datos del formulario
			var misDatos =
			{
					nombre:document.getElementById("nombre").value,
                                        domicilio:document.getElementById("domicilio").value,
					longitud:document.getElementById("longitud").value,
                                        latitud:document.getElementById("latitud").value,
                                        estatus:document.getElementById("estatus").value
			};
                        
                        //seleccionamos el apartado donde se verá le numero unico
                        var section = document.querySelector('table');
                        
                        var tr = document.createElement('tr');
			tr.textContent= "";
			section.appendChild(tr);
                        
                        var p2 = document.createElement('td');
			p2.textContent= misDatos.nombre;
			section.appendChild(p2);
                        
                        var p4 = document.createElement('td');
			p4.textContent= misDatos.domicilio;
			section.appendChild(p4);
                        
                        var p3 = document.createElement('td');
			p3.textContent= misDatos.longitud;
			section.appendChild(p3);
                        
                        var p5 = document.createElement('td');
			p5.textContent= misDatos.latitud;
			section.appendChild(p5);
                        
                        var p6 = document.createElement('td');
			p6.textContent= misDatos.estatus;
			section.appendChild(p6);
                    }