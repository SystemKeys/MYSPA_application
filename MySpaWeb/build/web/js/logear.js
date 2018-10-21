/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var empleados;
var cliente;

function uploadUsuario(){
    var requestURL='http://localhost:8084/MySpaWeb/datos/empleados.json';
    var requestURL1='http://localhost:8084/MySpaWeb/datos/cliente.json';
    
    var request = new XMLHttpRequest();
    var request1 = new XMLHttpRequest();
    
    request.open("GET", requestURL);
    request1.open("GET", requestURL1);
    
    request.responseType="json";
    request1.responseType="json";
    
    request.send();
    request1.send();
    
    request.onload = function(){
        empleados = request.response;
       };
   
    request1.onload = function(){
        cliente = request1.response;
       };
}

function iniciarSesion(){
    var user = document.getElementById("txtUsuario").value.toString();
    var pass = document.getElementById("txtContrasenia").value.toString();
    var verificar = false;
    var rol="";
    var nombre="";
    var apeP="";
    var apeM="";
    var cadena="";
    for ( var i=0; i<empleados.length;i++ ){
        var usuarios = empleados[i].usuario;
        var personas = empleados[i].persona;
        if (user == usuarios.nombreUsuario && pass == usuarios.contrasenia){
            verificar=true;
            rol = usuarios.rol.toLowerCase();
            nombre = personas.nombre.toUpperCase();
            apeP = personas.apellidoPaterno.toUpperCase();
            apeM = personas.apellidoMaterno.toUpperCase();
            break;
        }
    }
     for(var j=0; j<cliente.length; j++){
        var usuarios1 = cliente[j].usuario;
        var personas2= cliente[j].persona;

            if (user == usuarios1.nombreUsuario && pass == usuarios1.contrasenia){
                verificar=true;
                rol = usuarios.rol.toLowerCase();
                nombre = personas2.nombre.toUpperCase();
                apeP = personas2.apellidoPaterno.toUpperCase();
                apeM = personas2.apellidoMaterno.toUpperCase();
                break;
        }
      }
    
    var nuevoNombre = quitarAcentos(nombre);
    var nuevoapeP = quitarAcentos(apeP);
    var nuevoapeM = quitarAcentos(apeM);
    
    cadena = nuevoNombre+" "+nuevoapeP+" "+nuevoapeM;
    
    enviarDatos(cadena, verificar, rol);
}   

function quitarAcentos(cadena){
    var nuevaCadena="";
    var validar = false;
    for(var i=0; i<cadena.length; i++){
        if(cadena.charAt(i) == 'Á' ||
           cadena.charAt(i) == 'É' ||
           cadena.charAt(i) == 'Í' ||
           cadena.charAt(i) == 'Ó' ||
           cadena.charAt(i) == 'Ú' ||
           cadena.charAt(i)== 'Ñ'){
            validar = true;
            break;
        }
    }
    if(validar == true){
        for (var i=0; i<cadena.length; i++){

            switch(cadena.charAt(i)){
                case 'Á':
                    nuevaCadena = cadena.replace('Á', "A");
                break;
                case 'É':
                    nuevaCadena = cadena.replace('É', "E");
                break;
                case 'Í':
                    nuevaCadena = cadena.replace('Í', "I");
                break;
                case 'Ó':
                    nuevaCadena = cadena.replace('Ó', "O");
                break;
                case 'Ú':
                    nuevaCadena = cadena.replace('Ú', "U");
                break;    
                case 'Ñ':
                    nuevaCadena = cadena.replace('Ñ', "N");
                break;  
            }
        }
    }else{
        nuevaCadena=cadena;
    }
    return nuevaCadena;
}

function enviarDatos(cadena, verificar, rol){
    if ( verificar ){
        switch(rol){
            //preguntar cual html correspondera a cada una
            case "administrador":
                window.location.assign("Servicio.html?datosP="+cadena);
   
                break;

            case "recepcionista":
                window.location.assign("Reservacion.html?datosP="+cadena);
                break;

            case "cliente":
                window.location.assign("tratamientos_servicio.html?datosP="+cadena);
                break;
            case "tecnico":
            window,location.assign("Index.html?datosP="+cadena);
            break;
        }
    }    
    }


