var section = document.querySelector('section');    
    var requestURL = 'http://localhost:8084/MySpa/json/jsproducto.json';
    var request = new XMLHttpRequest();
    request.open('GET', requestURL);
    request.responseType = 'json';
    request.send();
    request.onload = function() {
      var productos = request.response;
      showProductos(productos);
    };
    function showProductos(jsonObj) {
        var productos = jsonObj["productos"];
      for(var i = 0; i < productos.length; i++) {
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
        myh1.textContent= productos[i].nombre;
        myh3.textContent= productos[i].marca;
        myh4.textContent= "$" + productos[i].precio;
        
        //se agregan los elementos creados al section
        article.appendChild(q);
        article.appendChild(w);
        article.appendChild(e);
        article.appendChild(myh1);
        article.appendChild(myh3);
        article.appendChild(myh4);
      }
    }
    
var buscarProducto;
function buscar(){
    var nombre=document.getElementById("buscar").value;
    
    
    cargarProductos();
    for(var i=0; i<buscarProducto.length; i++){
        if(buscarProducto[i].nombre==nombre){
            alert();
        }
    }//fin for

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