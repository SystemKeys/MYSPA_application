
function inicializar() {
    if (localStorage.getItem('MYSPA_CREDENCIAL') === null)
        window.location = '../index.html';
    var cliente = JSON.parse(localStorage.getItem('MYSPA_CREDENCIAL'));
    $('#spnEmpleadoNombre').html(cliente.persona.nombre + ' ' + cliente.persona.apellidoPaterno);
}

function cargarModuloReservacion(){
    $.ajax(
            {type: "GET",
                url: "reservacion/catalogo.html",
                async: true
            }
    ).done(
            function (data) {
                $('#divMainContainer').html(data);
                actualizarTablaReservacion();
            }
    );
}

function actualizarTablaReservacion() {
    var cliente = JSON.parse(localStorage.getItem('MYSPA_CREDENCIAL'));
    $.ajax({
        type: "POST",
        url: "../rsreservacion/getAllReservacionCliente",
        async: true,
        data:{
                idCliente: cliente.id
            }
    }).done(
            function (reservaciones)
            {
                var str = '';
                for (var i = 0; i < reservaciones.length; i++)
                 
                    str += '<tr>' +
                            '<td>' + reservaciones[i].id + '</td>' +
                            '<td>' + reservaciones[i].fechaHoraInicio + '</td>' +
                            '<td>' + reservaciones[i].fechaHoraFin + '</td>' +
                            '<td>' + reservaciones[i].sala.nombre + '</td>' +
                            '<td>' + reservaciones[i].sala.sucursal.nombre + '</td>' +                            
                            '<td>' + "Activa" + '</td>' +
                            
                            '</tr>';
                $('#tbReservaciones').html(str);
                $('#tbReservaciones').find('tr').click(function ()
                {
                    //this en esta funcion es el renglon
                    //seleccionado por el usuario                                                            
                    
                    $('#txtIdReservacion').val(reservaciones[$(this).index()].id);
                    $('#txtFechaHoraInicio').val(reservaciones[$(this).index()].fechaHoraInicio);
                    $('#txtFechaHoraFin').val(reservaciones[$(this).index()].fechaHoraFin);
                    $('#txtIdClienteReservacion').val(reservaciones[$(this).index()].cliente.id);
                    $('#cmbSala').val(reservaciones[$(this).index()].sala.nombre);
                    $('#chbEstatusReservacion').val(reservaciones[$(this).index()].estatus);
                    $('#divModalReservacionDetalle').modal();
                });

            }
    );
}


function cargarSalaReservacion() {

    $(document).ready(function () {
        $('#btnCargarSala').click(function () {
            $('#divModalSalaReservaciones').modal();
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
                                    '<td>' + salas[i].sucursal.nombre + '</td>' +
                                    '</tr>';
                        $('#tbSalasReservacion').html(str);
                        $('#tbSalasReservacion').find('tr').click(function ()
                        {
                            $('#txtIdSalaReservacion').val(salas[$(this).index()].id);
                             if($('#txtIdSalaReservacion').val.length > 0){
                                swal('Sala Selecionada , Ya puede cerrar esta ventana', '', 'success');
                            }else{
                                swal('No se selecciono la Sala', '', 'warning');
                   }
                        });
                    }
            );
        });
    });
}
function cargarHorariosReservacion() {
    
    $(document).ready(function () {
        $('#btnCargarHorarios').click(function () {
            $('#divModalHorariosReservaciones').modal();
            $.ajax({
                type: "POST",
                url: "../rshorario/getAllHorarioWithoutUsed",
                async: true,
                data: {
                    idSala: $('#txtIdSalaReservacion').val(),
                    fecha: $('#txtFecha').val()
                }               
            }).done(
                    function (horarios)
                    {
                        var str = '';
                        for (var i = 0; i < horarios.length; i++)
                            str += '<tr>' +
                                    '<td>' + horarios[i].id + '</td>' +
                                    '<td>' + horarios[i].horaInicio + '</td>' +
                                    '<td>' + horarios[i].horaFin + '</td>' +
                                    '</tr>';
                        $('#tbHorariosReservacion').html(str);
                        $('#tbHorariosReservacion').find('tr').click(function ()
                        {
                            $('#txtHorarioInicio').val(horarios[$(this).index()].horaInicio);
                            $('#txtHorarioFin').val(horarios[$(this).index()].horaFin);
                            $('#txtHorarioId').val(horarios[$(this).index()].id);
                             if($('#txtHorarioId').val.length > 0){
                                swal('Horario Selecionado, Ya puede cerrar esta ventana', '', 'success');
                             }else{
                                swal('No se selecciono el Horario', '', 'warning');
                   }
                        });
                    }
            );
        });
    });
}

function guardarReservacion(){    
    var rutaREST = null;
    var idReservacion = 0;
    var nombre = "";
    var cliente = JSON.parse(localStorage.getItem('MYSPA_CREDENCIAL'));
    var fechaInicio = $('#txtFecha').val() + ' ' + $('#txtHoraInicio').val();
    var fechaFin = $('#txtFecha').val() + ' ' + $('#txtHoraFin').val();
    if ($('#txtIdReservacion').val().length > 0) {
        rutaREST = '../rsreservacion/updateReservacion';
        idReservacion = $('#txtIdReservacion').val();
    } else {
        rutaREST = '../rsreservacion/insertReservacion';
    }    
        $.ajax({
            type: "POST",
            asyn: true,
            url: rutaREST,
            data: {
                idReservacion: idReservacion,            
                fechaHoraInicio: fechaInicio,
                fechaHoraFin: fechaFin,
                idCliente: cliente.id,
                idSala: $('#txtIdSalaReservacion').val(),
                idHorario: $('#txtHorarioId').val()
            //estatus : $('#chbEstatusProducto').prop('checked') ? 1 : 0                
            }
        }).done(function (data)
        {
        if (data.error != null) {
            swal("Error", data.error, 'warning');
            return;
        }
        actualizarTablaReservacion();
        $('#txtIdReservacion').val(data.result);
        swal('Realizado', 'Hecho', 'success');
    }
    );
            } 

    
    


