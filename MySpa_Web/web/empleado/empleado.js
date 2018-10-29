/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function cargarModuloProducto(){
    $.ajax  (   
                {   type   :    "GET",
                    url    :   "producto/catalogo.html",
                    async  :    true
                }
            ).done(
                    function(data){
                      $('#divMainContainer').html(data);  
                      $('#tbProductos').find('tr').click(function()
                                                          {
                                                            alert('Renglon: ' + $(this).index());
                                                            }                              
                                                            );
                        actualizarTablaProductos();
                        }
                    );
                   
                    
}

function actualizarTablaProductos(){
    $.ajax( {
                                type   : "GET",
                                url    : "../rsproducto/getAllProducto",
                                async  : true
                              }).done(
                                        function(productos)
                                            {
                                                var str  = '';
                                                for (var i = 0; i < productos.length; i++)
                                                    str += '<tr>' + 
                                                                  '<td>' + productos[i].id + '</td>' +
                                                                  '<td>' + productos[i].nombre + '</td>' +
                                                                  '<td>' + productos[i].marca + '</td>' +
                                                                  '<td>' + productos[i].precioUso + '</td>' +
                                                            '</tr>';
                                                    $('#tbProductos').html(str);
                                            }
                                     );           
}
function guardarProducto(){
    
    var rutaREST = null;
    var idProducto = 0;
    
    if($('#txtIdProducto').val().length > 0){
        rutaREST = '../rsproducto/updateProducto';
        idProducto = $('#txtProductoId').val();
    }else{
        rutaREST = '../rsproducto/insertProducto';
    }
    
    $.ajax({
            type : "POST",
            asyn : true,
            url : rutaREST,
            data :{
                    id : idProducto,
                    nombre : $('#txtNombreProducto').val(),
                    marca : $('#txtMarcaProducto').val(),
                    precioUso : $('#txtPrecioUsoProducto').val(),
                    //estatus : $('#chbEstatusProducto').prop('checked') ? 1 : 0
                    }                        
            }).done(function (data)
                    {
                     if (data.error != null){
                         swal("Error", data.error, 'warning');
                         return;
                     }
                     actualizarTablaProductos();
                     $('#txtIdProducto').val(data.result);  
                     swal('Realizado', 'Hecho', 'success');
                    }                                
                    );
}

function limpiarCampos(){
    $('#txtIdProducto').val('');
    $('#txtNombreProducto').val('');
    $('#txtMarcaProducto').val('');
    $('#txtPrecioUsoProducto').val('');
    $('#chbEstatusProducto').prop('checked', true);
}

function cargarModuloSala() {
    $.ajax(
            {type: "GET",
                url: "sala/catalogoSala.html",
                async: true
            }
    ).done(
            function (data) {
                $('#divMainContainer').html(data);
                $('#tbSalas').find('tr').click(function () {
                    alert('Renglon:' + $(this).index());
                });
                actualizarTablaSalas();
            }
    );
}
function actualizarTablaSalas() {
    $.ajax({
        type: "GET",
        url: "../rssala/getAllSala",
        async: true
    }).done(
            function (salas)
            {
                var str = '';
                for (var i = 0; i < salas.length; i++)
                    str += '<tr>' +
                            '<td>' + salas[i].id + '</td>' +
                            '<td>' + salas[i].nombre + '</td>' +
                            '<td>' + salas[i].descripcion + '</td>' +
                            '<td>' + salas[i].sucursal.idSucursal + '</td>' +
                            '</tr>';
                $('#tbSalas').html(str);
            }
    );
}

function guardarSala() {
    var rutaREST = null;
    var idSala = 0;
    if ($('#txtSalaId').val().length > 0) {
        rutaREST = '../rssala/updateSala';
        idSala = $('#txtSalaId').val();
    } else {
        rutaREST = '../rssala/insertSala';
    }
    $.ajax({
        type: "POST",
        async: true,
        url: rutaREST,
        data: {
            id: idSala,
            nombre: $('#txtSalaNombre').val(),
            descripcion: $('#txtSalaDescripcion').val(),
            foto: $('#txtFoto').val(),
            rutaFoto: $('#txtrutaFoto').val(),
            idSucursal: $('#cmbSucursal').val(),
            // estatus:$('#chbSalaEstatus').prop('checked')?1:0
        }
    }).done(function (data) {
        if (data.error != null)
        {
            swal('Error', data.error, 'warning');
            return;
        }
        actualizarTablaSalas();
        $('#txtSalaId').val(data.result);
        swal('Movimiento realizado', '', 'success');
    });
}
function eliminarSala(){
//    var rutaREST=null;
//    var idSala=0;
//     if ($('#txtSalaId').val().length > 0) {
//        rutaREST = '../rssala/updateSala';
//        idSala = $('#txtSalaId').val();
//    } else {
//        rutaREST = '../rssala/deleteSala';
//    }
    $.ajax({
        type: "POST",
        async: true,
        url: "../rssala/deleteSala",
        data: {
            id:$('#txtSalaId').val() 
//            nombre: $('#txtSalaNombre').val(),
//            descripcion: $('#txtSalaDescripcion').val(),
//            foto: $('#txtFoto').val(),
//            rutaFoto: $('#txtrutaFoto').val(),
//            idSucursal: $('#cmbSucursal').val(),
            // estatus:$('#chbSalaEstatus').prop('checked')?1:0
        }
    }).done(function (data) {
        if (data.error != null)
        {
            swal('Error', data.error, 'warning');
            return;
        }
        actualizarTablaSalas();
        swal('Movimiento realizado', '', 'success');
    });
}
function actualizarSala(){
   
    $.ajax({
        type: "POST",
        async: true,
        url: "../rssala/updateSala",
        data: {
            id: $('#txtSalaId').val(),
            nombre: $('#txtSalaNombre').val(),
            descripcion: $('#txtSalaDescripcion').val(),
            foto: $('#txtFoto').val(),
            rutaFoto: $('#txtrutaFoto').val(),
            idSucursal: $('#cmbSucursal').val(),
          estatus:$('#chbSalaEstatus').prop('checked')?1:0
        }
    }).done(function (data) {
        if (data.error != null)
        {
            swal('Error', data.error, 'warning');
            return;
        }
        actualizarTablaSalas();

    swal('Movimiento realizado', '', 'success');
    });
} 
    
function limpiarCampos() {
    $('#txtSalaId').val('');
    $('#txtSalaNombre').val('');
    $('#txtSalaDescripcion').val('');
    $('#txtFoto').val('');
    $('#txtRutaFoto').val('');
    $('#chbSalaEstatus').prop('checked', false);
}
function cargarModuloSucursal(){
    $.ajax  (   
                {   type   :    "GET",
                    url    :   "sucursal/catalogo.html",
                    async  :    true
                }
            ).done(
                    function(data){
                      $('#divMainContainer').html(data); 
                      $('#tbSucursales').find('tr').click(function(){
                          alert('Renglon:'+$(this).index());
                                                                    });
                      actualizarTablaSucursal();
                  }
                  );
      }
function actualizarTablaSucursal(){
                          $.ajax( {
                                type   : "GET",
                                url    : "../rssucursal/getAllSucursal",
                                async  : true
                              }).done(
                                        function(sucursales)
                                            {
                                                var str  = '';
                                                for (var i = 0; i < sucursales.length; i++)
                                                    str += '<tr>' + 
                                                                  '<td>' + sucursales[i].id + '</td>' +
                                                                  '<td>' + sucursales[i].nombre + '</td>' +
                                                                  '<td>' + sucursales[i].latitud + '</td>' +
                                                                  '<td>' + sucursales[i].longitud + '</td>' +
                                                                  '<td>' + sucursales[i].domicilio+ '<td>'
                                                            '</tr>';
                                                    $('#tbSucursales').html(str);
                                            }
                                     );
                          }

                   
                  
function guardarSucursal(){
 var rutaREST=null;
 var idSucursal=0;
 if($('#txtSucursalId').val().length>0){
     rutaREST='../rssucursal/updateSucursal';
     idSucursal=$('#txtSucursalId').val();
 }
 else{
     rutaREST='../rssucursal/insertSucursal';
 }
    $.ajax({
                type:"POST",
                aync:true,
                url:rutaREST,
                data:{
                    id: idSucursal,
                    nombre: $('#txtSucursalNombre').val(),
                    latitud: $('#txtSucursalLatitud').val(),
                    longitud: $('#txtSucursalLongitud').val(),
                    domicilio: $('#txtSucursalDomicilio').val(),
                    estatus:$('#chbSucursalEstatus').prop('checked')?1:0
                }
            }).done(function(data){
               if(data.error!=null)
               {
                  swal('Error',data.error,'warning');
                  return;
               }
              actualizarTablaSucursal();
               $('#txtSucursalId').val(data.result);
                swal('Movimiento realizado','','success');
            });
}



function eliminarSucursal(){
   
   $.ajax({
                type:"POST",
                aync:true,
                url: '../rssucursal/deleteSucursal',
                data:{
                    idSucursal: $('#txtSucursalId').val()
                    
                      }
            }).done(function(data){
               if(data.error!=null)
               {
                  swal('Error',data.error,'warning');
                  return;
               }
              actualizarTablaSucursal();
               $('#txtSucursalId').val(data.result);
                swal('Movimiento realizado','','success');
            });
}

function actualizarSucursal(){
    $.ajax({
                type:"POST",
                aync:true,
                url:'../rssucursal/updateSucursal',
                data:{
                    
                    nombre: $('#txtSucursalNombre').val(),
                    domicilio: $('#txtSucursalDomicilio').val(),
                    latitud: $('#txtSucursalLatitud').val(),
                    longitud: $('#txtSucursalLongitud').val(),
                    estatus:$('#chbSucursalEstatus').prop('checked')?1:0,
                    idSucursal: $('#txtSucursalId').val()
                }
            }).done(function(data){
               if(data.error!=null)
               {
                  swal('Error',data.error,'warning');
                  return;
               }
              actualizarTablaSucursal();
               $('#txtSucursalId').val(data.result);
                swal('Movimiento realizado','','success');
            });
}
          
function cargarModuloTratamiento() {
    $.ajax(
            {type: "GET",
                url: "tratamiento/catalogo.html",
                async: true
            }
    ).done(
            function (data) {
                $('#divMainContainer').html(data);

                actualizarTablaTratamiento();
            }
    );


}

function guardarTratamiento() {

    var rutaREST = null;
    var idTratamiento = 0;

    if ($('#txtIdTratamiento').val().length > 0) {
        rutaREST = '../rstratamiento/updateTratamiento';
        idTratamiento = $('#txtIdTratamiento').val();
    } else {
        rutaREST = '../rstratamiento/insertTratamiento';
    }

    $.ajax({
        type: "POST",
        asyn: true,
        url: rutaREST,
        data: {
            id: idTratamiento,
            nombre: $('#txtNombreTratamiento').val(),
            descripcion: $('#txtDescripcionTratamiento').val()
            //estatus : $('#chbEstatusProducto').prop('checked') ? 1 : 0
        }
    }).done(function (data)
    {
        if (data.error != null) {
            swal("Error", data.error, 'warning');
            return;
        }
        actualizarTablaTratamiento();
        $('#txtIdTratamiento').val(data.result);
        swal('Realizado', 'Hecho', 'success');
    }
    );
}

function actualizarTablaTratamiento() {
    $.ajax({
        type: "GET",
        url: "../rstratamiento/getAllTratamiento",
        async: true
    }).done(
            function (tratamientos)
            {
                var str = '';
                for (var i = 0; i < tratamientos.length; i++)
                    str += '<tr>' +
                            '<td>' + tratamientos[i].id + '</td>' +
                            '<td>' + tratamientos[i].nombre + '</td>' +
                            '<td>' + tratamientos[i].descripcion + '</td>' +
                            '<td>' + tratamientos[i].estatus + '</td>' +
                            '</tr>';
                $('#tbTratamiento').html(str);
                $('#tbTratamiento').find('tr').click(function ()
                {
                    alert('Renglon: ' + $(this).index());
                }
                );
            }
    );
}



function eliminarTratamiento(){
   
   $.ajax({
                type:"POST",
                aync:true,
                url: '../rstratamiento/deleteTratamiento',
                data:{
                    idTratamiento: $('#txtIdTratamiento').val()
                    
                      }
            }).done(function(data){
               if(data.error!=null)
               {
                  swal('Error',data.error,'warning');
                  return;
               }
              actualizarTablaTratamiento();
               $('#txtIdTratamiento').val(data.result);
                swal('Movimiento realizado','','success');
            });
}

function limpiarCamposTratamiento() {
    $('#txtIdTratamiento').val('');
    $('#txtNombreTratamiento').val('');
    $('#txtDescripcionTratamiento').val('');
    $('#chbEstatusTratamiento').prop('checked', true);
}