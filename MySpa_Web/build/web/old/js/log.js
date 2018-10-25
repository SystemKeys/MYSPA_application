var empleados;
var clientes;
function validar(){
    var usuario=document.getElementById("txtNombre").value;
    var password=document.getElementById("txtPassword").value;
    
    
    cargarEmpleados ();
    cargarClientes ();
    //mensaje para mandar al usuario si es correcto su mensaje
    //para verificar si los carga
    var respuesta;
    //arreglo para cargar el empleado cada uno 
    // va validar los atributos que se tiene en el arrreglo json
    
    
    for(var i=0; i<empleados.length; i++){
        if(empleados[i].usuario.nombreUsuario==usuario &&  empleados[i].usuario.contrasenia==password){
            respuesta="empleado";
        }else{
            for(var i=0; i<clientes.length; i++){
                if(clientes[i].usuario.nombreUsuario==usuario &&  clientes[i].usuario.contrasenia==password){
                    respuesta="cliente";
                }else{
            
                }//fin else
            }//fin for
        }//fin else
    }//fin for

    if(respuesta=="cliente"){
            window.location="../html_cliente/myspa_cliente.html";
        }else{
            if(respuesta=="empleado"){
                window.location="../html_empleado/myspa_empleado.html";
            }
            else{
                alert("Datos Incorrectos");
            }
        }    
}

function cargarEmpleados(){
    var requestURL="http://localhost:8084/MySpa/json/jsempleado.json";
    var request=new XMLHttpRequest();
    request.open('POST',requestURL);
    request.responseType='json';
    request.send();
    request.onload=function(){
        empleados=request.response;
    };  
    
}

function cargarClientes(){
    var requestURL="http://localhost:8084/MySpa/json/jscliente.json";
    var request=new XMLHttpRequest();
    request.open('POST',requestURL);
    request.responseType='json';
    request.send();
    request.onload=function(){
        clientes=request.response;
    };  
    
}