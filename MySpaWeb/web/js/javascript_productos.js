function onloadProducto() {
    var requestURL = 'http://localhost:8084/MySpaWeb/datos/productos.json';
    var request = new XMLHttpRequest();
    request.open("GET", requestURL);
    request.responseType="json";
    request.send();
    request.onload = function() {
        var productos = request.response;
        mostrarProducto(productos);
    };

    function mostrarProducto(productos) {
        
        var table = document.getElementById("tableServicio");

        for (var i = 0; i < productos.length ;i++) {
            
            var tr = document.createElement('tr');
            var td = document.createElement('td');
            var td1 = document.createElement('td');
            var td2 = document.createElement('td');
            var td3 = document.createElement('td');
            var td4 = document.createElement('td');

            td.textContent = (productos[i].idProducto);
            td1.textContent = (productos[i].nombre);
            td2.textContent = (productos[i].marca);
            td3.textContent = (productos[i].precioUso);
            td4.textContent = (productos[i].estatus);

            tr.appendChild(td);
            tr.appendChild(td1);
            tr.appendChild(td2);
            tr.appendChild(td3);
            tr.appendChild(td4);
            table.appendChild(tr);
        }
    }
}

