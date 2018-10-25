/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function login() {
    //petisión asicrona con url
    $.ajax({
                type   : "GET",
                url    : "rslogin/login", 
                data   : {
                            usuario : $('#txtUsername').val(),
                            contrasenia : $('#txtPassword').val()
                       },
                async  : true
            }).done(
                        function (data)
                        {
                            if (data.error != null)
                            {
                                swal('Error', data.error, 'error');
                                return;
                            }
                            else if (data.class.toLowerCase().includes('empleado')){
                                window.location='empleado/index.html';
                            }
                            else if (data.class.toLowerCase().includes('cliente')){
                                window.location='cliente/index.html';
                            }
                        }
                    );
    
    
//    window.location = 'empleado/index.html';
}