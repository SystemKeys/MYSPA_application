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

