var buscarSala;
function buscar(){
    var nombre=document.getElementById("buscar").value;
    cargarSalas();
    var resp = 0;
    for(var i=0; i<buscarSala.length; i++){
        if(buscarSala[i].nombre===nombre || buscarSala[i].id==nombre
                || buscarSala[i].descripción===nombre || buscarSala[i].sucursal===nombre
                || buscarSala[i].estatus==nombre
                || buscarSala[i].horaInicio1===nombre || buscarSala[i].horaFin1===nombre
                || buscarSala[i].horaInicio2===nombre || buscarSala[i].horaFin2===nombre
                || buscarSala[i].horaInicio3===nombre || buscarSala[i].horaFin3===nombre
                || buscarSala[i].horaInicio4===nombre || buscarSala[i].horaFin4===nombre
                || buscarSala[i].horaInicio5===nombre || buscarSala[i].horaFin5===nombre){
            var article = document.querySelector('table');
        var q=document.createElement('tr');
        var w=document.createElement('tr');
        var e=document.createElement('tr');
        var myh4=document.createElement('td');
        var myh1=document.createElement('td');
        var myh2=document.createElement('td');
        var myh3=document.createElement('td');
        var myh5=document.createElement('td');
        var myh6=document.createElement('td');
        var myh7=document.createElement('td');
        var myh8=document.createElement('td');
        var myh9=document.createElement('td');
        var myh10=document.createElement('td');
        var myh11=document.createElement('td');
        
        //se toman los valores del objeto json, en cada posición, y atributo
        q.textContent="";
        w.textContent="";
        e.textContent="";
         myh4.textContent= buscarSala[i].id;
        myh1.textContent= buscarSala[i].nombre;
        myh2.textContent= buscarSala[i].descripción;
        myh3.textContent= buscarSala[i].sucursal;
        myh5.textContent= buscarSala[i].estatus;
        myh6.textContent= buscarSala[i].horaInicio1 + " - " + buscarSala[i].horaFin1;
        myh8.textContent= buscarSala[i].horaInicio2 + " - " + buscarSala[i].horaFin2;
        myh9.textContent= buscarSala[i].horaInicio3 + " - " + buscarSala[i].horaFin3;
        myh10.textContent= buscarSala[i].horaInicio4 + " - " + buscarSala[i].horaFin4;
        myh11.textContent= buscarSala[i].horaInicio5 + " - " + buscarSala[i].horaFin5;
        
        //se agregan los elementos creados al section
        article.appendChild(q);
        article.appendChild(w);
        article.appendChild(e);
        article.appendChild(myh4);
        article.appendChild(myh1);
        article.appendChild(myh2);
        article.appendChild(myh3);
        article.appendChild(myh5);
        article.appendChild(myh6);
        article.appendChild(myh8);
        article.appendChild(myh9);
        article.appendChild(myh10);
        article.appendChild(myh11);
        resp = 1;
        }
    }//fin for
    if (resp == 0){
            alert("Producto no encontrado.");
        }
}

function cargarSalas(){
    var requestURL="http://localhost:8084/MySpa/json/salas.json";
    var request=new XMLHttpRequest();
    request.open('POST',requestURL);
    request.responseType='json';
    request.send();
    request.onload=function(){
        buscarSala=request.response;
    };  
    
}