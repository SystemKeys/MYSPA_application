
function guardar()   {
                    
			//traer datos del formulario
			var misDatos =
			{
                                fecha:document.getElementById("fecha").value,
                                reservacion:document.getElementById("reservacion").value,
                                tratamiento:document.getElementById("tratamientos").value,
                                empleados:document.getElementById("empleados").value
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
			p4.textContent= misDatos.reservacion;
			section.appendChild(p4);
                        
                        var p3 = document.createElement('td');
			p3.textContent= misDatos.tratamiento;
			section.appendChild(p3);
                        
                        var p5 = document.createElement('td');
			p5.textContent= misDatos.empleados;
			section.appendChild(p5);
                    }