var buscarProducto;
function buscar(){
    var nombre=document.getElementById("buscar").value;
    cargarProductos();
    var resp = 0;
    for(var i=0; i<buscarProducto.length; i++){
        if(buscarProducto[i].nombre===nombre || buscarProducto[i].marca===nombre || buscarProducto[i].precio==nombre){
            var article = document.querySelector('table');
        var q=document.createElement('tr');
        var w=document.createElement('tr');
        var e=document.createElement('tr');
        var myh1=document.createElement('td');
        var myh3=document.createElement('td');
        var myh4=document.createElement('td');
        
        //se toman los valores del objeto json, en cada posición, y atributo
        q.textContent="";
        w.textContent="";
        e.textContent="";
        myh1.textContent= buscarProducto[i].nombre;
        myh3.textContent= buscarProducto[i].marca;
        myh4.textContent= "$" + buscarProducto[i].precio;
        
        //se agregan los elementos creados al section
        article.appendChild(q);
        article.appendChild(w);
        article.appendChild(e);
        article.appendChild(myh1);
        article.appendChild(myh3);
        article.appendChild(myh4);
        resp = 1;
        }
    }//fin for
    if (resp == 0){
            alert("Producto no encontrado.");
        }
}

function cargarProductos(){
    var requestURL="http://localhost:8084/MySpa/json/producto.json";
    var request=new XMLHttpRequest();
    request.open('POST',requestURL);
    request.responseType='json';
    request.send();
    request.onload=function(){
        buscarProducto=request.response;
    };  
    
}