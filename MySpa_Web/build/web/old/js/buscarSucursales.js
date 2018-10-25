var buscarSucursal;
function buscar(){
    var nombre=document.getElementById("buscar").value;
    cargarSucursales();
    var resp = 0;
    for(var i=0; i<buscarSucursal.length; i++){
        if(buscarSucursal[i].nombre===nombre || buscarSucursal[i].domicilio===nombre
                || buscarSucursal[i].longitud==nombre || buscarSucursal[i].latitud==nombre
                || buscarSucursal[i].estatus==nombre){
            var article = document.querySelector('table');
        var q=document.createElement('tr');
        var w=document.createElement('tr');
        var e=document.createElement('tr');
        var myh1=document.createElement('td');
        var myh2=document.createElement('td');
        var myh3=document.createElement('td');
        var myh4=document.createElement('td');
        var myh5=document.createElement('td');
        
        //se toman los valores del objeto json, en cada posición, y atributo
        q.textContent="";
        w.textContent="";
        e.textContent="";
        myh1.textContent= buscarSucursal[i].nombre;
        myh2.textContent= buscarSucursal[i].domicilio;
        myh3.textContent= buscarSucursal[i].longitud;
        myh4.textContent= buscarSucursal[i].latitud;
        myh5.textContent= buscarSucursal[i].estatus;
        
        //se agregan los elementos creados al section
        article.appendChild(q);
        article.appendChild(w);
        article.appendChild(e);
        article.appendChild(myh1);
        article.appendChild(myh2);
        article.appendChild(myh3);
        article.appendChild(myh4);
        article.appendChild(myh5);
        resp = 1;
        }
    }//fin for
    if (resp == 0){
            alert("Producto no encontrado.");
        }
}

function cargarSucursales(){
    var requestURL="http://localhost:8084/MySpa/json/jsSucursal.json";
    var request=new XMLHttpRequest();
    request.open('POST',requestURL);
    request.responseType='json';
    request.send();
    request.onload=function(){
        buscarSucursal=request.response;
    };  
    
}