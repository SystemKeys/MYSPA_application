var empelados;
function buscar(){
    var nombre=document.getElementById("buscar").value;
    cargarEmpleados();
    var resp = 0;
    for(var i=0; i<empelados.length; i++){
        if(empelados[i].id==nombre || empelados[i].persona.nombre ===nombre || empelados[i].persona.apellidoPaterno===nombre
                || empelados[i].persona.apellidoMaterno==nombre || empelados[i].numeroEmpleado==nombre
                || empelados[i].persona.domicilio==nombre || empelados[i].persona.rfc==nombre
                || empelados[i].persona.telefono==nombre || empelados[i].persona.genero==nombre
                || empelados[i].puesto==nombre || empelados[i].usuario.rol==nombre
                || empelados[i].estatus){
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
        var myh12=document.createElement('td');
        var myh13=document.createElement('td');
        
        //se toman los valores del objeto json, en cada posición, y atributo
        q.textContent="";
        w.textContent="";
        e.textContent="";
        myh1.textContent= empelados[i].id;
        myh2.textContent= empelados[i].persona.nombre 
                    + " " + empelados[i].persona.apellidoPaterno 
                    + " " + empelados[i].persona.apellidoMaterno;
        myh4.textContent= empelados[i].numeroEmpleado;
        myh7.textContent= empelados[i].persona.domicilio;
        myh8.textContent= empelados[i].persona.rfc;
        myh9.textContent= empelados[i].persona.telefono;
        myh10.textContent= empelados[i].persona.genero;
        myh11.textContent= empelados[i].puesto;
        myh12.textContent= empelados[i].usuario.rol;
        myh13.textContent= empelados[i].estatus;
        
        //se agregan los elementos creados al section
        article.appendChild(q);
        article.appendChild(w);
        article.appendChild(e);
        article.appendChild(myh1);
        article.appendChild(myh2);
        article.appendChild(myh4);
        article.appendChild(myh7);
        article.appendChild(myh8);
        article.appendChild(myh9);
        article.appendChild(myh10);
        article.appendChild(myh11);
        article.appendChild(myh12);
        article.appendChild(myh13);
        resp = 1;
        }
    }//fin for
    if (resp == 0){
            alert("Producto no encontrado.");
        }
}

function cargarEmpleados(){
    var requestURL="http://localhost:8084/MySpa/json/jsempleado.json";
    var request=new XMLHttpRequest();
    request.open('POST',requestURL);
    request.responseType='json';
    request.send();
    request.onload=function(){
        empelados=request.response;
    };  
    
}