/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function datosCliente(){
    var cliente
    var requestURL='http://localhost:8084/MySpaWeb/datos/consulta_cliente.json';
    var request = new XMLHttpRequest();
    request.open("GET", requestURL);
    request.responseType="json";
    request.send();   
    request.onload = function(){
                    cliente = request.response;
                    mostrarClientes(cliente);
                    };
     
}
function mostrarClientes(cliente){
    
    var repositorio = document.getElementById("datosCliente");
    var tablaClientes = document.getElementById("tableServicio");
    
     for ( var i=0; i<cliente.length; i++ ){
        
        var tdIdCliente = document.createElement("td");
        var tdEstatus = document.createElement("td");
        var tdNombre = document.createElement("td");
        var tdApeP = document.createElement("td");
        var tdApeM = document.createElement("td");
        var tdRfc = document.createElement("td");
        var tdGenero = document.createElement("td");
        var tdDomicilio = document.createElement("td");
        var tdTelefono = document.createElement("td");
        var tdIdUsuario = document.createElement("td");
        var tdNomUsuario = document.createElement("td");
        var tdContrasenia = document.createElement("td");
        var tdCorreo = document.createElement("td");
        var tr = document.createElement("tr");
        
        //Guardamos los datos de persona en una variable
        var persona = cliente[i].persona;
        var usuario = cliente[i].usuario;
        
        tdIdCliente.textContent=cliente[i].idCliente;
        tdNombre.textContent=persona.nombre;
        tdApeP.textContent=persona.apellidoPaterno;
        tdApeM.textContent=persona.apellidoMaterno;
        tdGenero.textContent=persona.genero;
        tdDomicilio.textContent=persona.domicilio;
        tdTelefono.textContent=persona.telefono;
        tdRfc.textContent=persona.rfc;
        tdCorreo.textContent=cliente[i].correo;
        tdIdUsuario.textContent=usuario.idUsuario;
        tdNomUsuario.textContent=usuario.nombreUsuario;
        tdContrasenia.textContent=usuario.contrasenia;
        tdEstatus.textContent=cliente[i].estatus;        
        
        tr.appendChild(tdIdCliente);
        tr.appendChild(tdNombre);
        tr.appendChild(tdApeP);
        tr.appendChild(tdApeM);
        tr.appendChild(tdGenero);
        tr.appendChild(tdDomicilio);
        tr.appendChild(tdTelefono);
        tr.appendChild(tdRfc);
        tr.appendChild(tdCorreo);
        tr.appendChild(tdIdUsuario);
        tr.appendChild(tdNomUsuario);
        tr.appendChild(tdContrasenia);
        tr.appendChild(tdEstatus);


        
        
        tablaClientes.appendChild(tr);
    } 
    repositorio.appendChild(tablaClientes);
}
     
     
 
