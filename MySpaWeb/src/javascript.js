
var section = document.querySelector("section");
var article = document.querySelector("article");
var header = document.querySelector("header");

function onloadProducto() {
    var requestURL = 'http://localhost:8084/Productos/productos.json';
    var request = new XMLHttpRequest();
    request.open("GET", requestURL);
    request.responseType="json";
    request.send();
    request.onload = function() {
        var productos = request.response;
        mostrarProducto(productos);
    };

    function mostrarProducto(productos) {
        var h1 = document.createElement('h1');
        var section = document.querySelector("section");
        h1.textContent = "Productos";
        
        var table = document.querySelector("table");
        var tr = document.createElement('tr');
            var td = document.createElement('td');
            var td1 = document.createElement('td');
            var td2 = document.createElement('td');
            var td3 = document.createElement('td');
            var td4 = document.createElement('td');
            
            td.textContent = "Marca";
            td1.textContent = "Nombre";
            td2.textContent = "Estatus";
            td3.textContent = "Precio de Uso";
            td4.textContent = "idProducto";

            
            tr.appendChild(td);
            tr.appendChild(td1);
            tr.appendChild(td2);
            tr.appendChild(td3);
            tr.appendChild(td4);
            table.appendChild(tr);
            
        
        for (var i = 0; i < productos.length ;i++) {
            
            var tr = document.createElement('tr');
            var td = document.createElement('td');
            var td1 = document.createElement('td');
            var td2 = document.createElement('td');
            var td3 = document.createElement('td');
            var td4 = document.createElement('td');

            td.textContent = (productos[i].marca);
            td1.textContent = (productos[i].nombre);
            td2.textContent = (productos[i].estatus);
            td3.textContent = (productos[i].precioUso);
            td4.textContent = (productos[i].idProducto);

            tr.appendChild(td);
            tr.appendChild(td1);
            tr.appendChild(td2);
            tr.appendChild(td3);
            tr.appendChild(td4);
            table.appendChild(tr);

            section.appendChild(table);
        }
    }
}

