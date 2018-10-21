var clientes;
function buscar(){
    var nombre=document.getElementById("buscar").value;
    cargarClientes();
    var resp = 0;
    for(var i=0; i<clientes.length; i++){
        if(clientes[i].id==nombre || clientes[i].persona.nombre===nombre || clientes[i].persona.apellidoPaterno===nombre
                || clientes[i].persona.apellidoMaterno===nombre || clientes[i].numeroCliente===nombre
                || clientes[i].persona.domicilio===nombre || clientes[i].persona.rfc===nombre
                || clientes[i].correo===nombre
                || clientes[i].persona.telefono==nombre || clientes[i].persona.genero===nombre
                || clientes[i].usuario.rol===nombre
                || clientes[i].estatus){
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
        myh11.textContent= clientes[i].correo;
        myh9.textContent= clientes[i].persona.telefono;
        myh10.textContent= clientes[i].persona.genero;
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
        article.appendChild(myh11);
        article.appendChild(myh9);
        article.appendChild(myh10);
        article.appendChild(myh13);
        resp = 1;
        }
    }//fin for
    if (resp == 0){
            alert("Producto no encontrado.");
        }
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