/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function login(){
    //petición asincrona al servidor
    $.ajax({
              type : "GET",
              url : "rslogin/login",
              data : {
                        usuario : $('#txtUsuario').val(), //reemplaza el document get element
                        contrasenia : $('#txtContrasenia').val()
                      },
              async : true
            }).done(
                        function(data){                                                        
                            if(data.error != null){
                                swal('Cuidado', data.error, 'warning');
                                return;                                
                            }else if(data.class != null && data.class.toLowerCase().includes('cliente')){                                
                               window.location = 'cliente/index.html';
                               return;
                              }else if(data.class != null && data.class.toLowerCase().includes('empleado')){
                            //}else if(data.class == "org.solsistemas.myspa.model.Empleado"){
                                window.location = 'empleado/index.html';
                                return;
                            }
                        }
                    );
    
    //window.location = 'empleado/index.html';
    
}
