function establecerUbicaciones(){
              var sucursales = ["Centro","Arbide","Villa Insurgentes"];
           
               for (var i =0; i < sucursales.length; i++) {
               document.getElementById("cmbSucursales").options[i]= new Option(sucursales[i]);
              }
              
              }

              function obtenerOpcion(){
                var lista = document.getElementById("cmbSucursales");
                var indiceSelect = lista.selectedIndex;  
                var optionSeleccionada = lista.options[indiceSelect];

             
                seleccionarSucursal(indiceSelect);
               
}
                function seleccionarSucursal(indice1){ 
                  
                  
                
                  var posCentro = {lat:21.1222315,lng:-101.6882629};
                  var posArbide = {lat: 21.1237224, lng: -101.6988892};
                  var posVillaInsurgentes = {lat:21.1427602,  lng: -101.6706908};
                 
                 if(indice1 == 0){
                   var titulo = "Sucursal Centro \n"
                               +"Belisario Domínguez 418, Centro, León, Guanajuato";
                  generarMapa(posCentro,titulo);
                 
                 }else if(indice1 == 1){
                  var titulo = "Sucursal Arbide \n"
                                +"Puerto Rico 208, Arbide, León, Gto.";
                  generarMapa(posArbide, titulo);

                 }else if (indice1 == 2){
                  var titulo ="Sucursal Villa Insurgentes \n"
                               +"Ignacio Pérez  #403, Col. Villa Insurgentes, León, Guanajuato";
                  generarMapa(posVillaInsurgentes, titulo);
                 }
               }
 
                    function generarMapa(ubicacion, titulo){
                             var objetoMapa = new google.maps.Map(
                                    //Operacion de generar el mapa y colocarlo
                                    document.getElementById("mapa"),
                                    {
                                    center : ubicacion,
                                    scrollwhell : true,
                                    mapTypeId : google.maps.MapTypeId.ROADMAP,
                                    zoom : 8
                     
                                  }

                                  );

                                //Colocar un marcador con la posición deseada
                                var objetoMarca =new google.maps.Marker(
                                   {
                                      map: objetoMapa,
                                      position: ubicacion,
                                   
                                      //icon: "../images/gas.png",
                                      title: titulo
                                            

                                   }

                                  );



}

                  
