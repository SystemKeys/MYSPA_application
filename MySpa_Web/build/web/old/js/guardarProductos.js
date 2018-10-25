function guardar()   {
                    
			//traer datos del formulario
			var misDatos =
			{
					nombre:document.getElementById("nombre").value,
					marca:document.getElementById("marca").value,
                                        precio:document.getElementById("precio").value,
                                        estatus:document.getElementById("estatus").value
			};
                        
                        //seleccionamos el apartado donde se verá le numero unico
                        var section = document.querySelector('table');
                        
                        var p1 = document.createElement('tr');
			p1.textContent= "";
			section.appendChild(p1);
                        
                        var p2 = document.createElement('td');
			p2.textContent= misDatos.nombre;
			section.appendChild(p2);
                        
                        var p3 = document.createElement('td');
			p3.textContent= misDatos.marca;
			section.appendChild(p3);
                        
                        var p5 = document.createElement('td');
			p5.textContent= misDatos.precio;
			section.appendChild(p5);
                        
                        var p6 = document.createElement('td');
			p6.textContent= misDatos.estatus;
			section.appendChild(p6);
                    }