/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function recibirNombreUsuario() {
    
    var datoPersonal;
    var numerico;
    
    var cadenaURL = location.search.substring(1, location.search.length);
    var arrVariable = cadenaURL.split("&");
    
    for(var i=0; i<arrVariable.length; i++){
        var varActual = arrVariable[i].split("=");
        if (isNaN(parseFloat(varActual[1]))){
            datoPersonal = varActual[1];
        }else{
            numerico = parseFloat(varActual[1]);
        }
    }
 
        return   datoPersonal.replace(/%20/g, " ");
    
}


function mostrarDatos() {
    
    var nom = recibirNombreUsuario();
    var p = document.getElementById("datos");
    var h1 = document.createElement("h1");
    
    h1.textContent = "Hola de Nuevo " + nom;
    
    p.appendChild(h1);
}