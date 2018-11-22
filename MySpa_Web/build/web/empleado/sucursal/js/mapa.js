var watchId;
var mapa = null;
var mapaMarcador = null;	

function mostrarMapa(){
    if (navigator.geolocation) {
	watchId = navigator.geolocation.watchPosition(mostrarPosicion, mostrarErrores, opciones);	
} else {
	alert("Tu navegador no soporta la geolocalización, actualiza tu navegador.");
}
}

function mostrarPosicion(posicion) {
	var latitud = document.getElementById("txtSucursalLatitud").value;
	var longitud = document.getElementById("txtSucursalLongitud").value;
	var precision = posicion.coords.accuracy;

	var miPosicion = new google.maps.LatLng(latitud, longitud);

	// Se comprueba si el mapa se ha cargado ya 
	if (mapa === null) {
		// Crea el mapa y lo pone en el elemento del DOM con ID mapa
		var configuracion = {center: miPosicion, zoom: 18, mapTypeId: google.maps.MapTypeId.HYBRID};
		mapa = new google.maps.Map(document.getElementById("mapa"), configuracion);

		// Crea el marcador en la posicion actual
		mapaMarcador = new google.maps.Marker({position: miPosicion, title:"Esta es tu posición"});
		mapaMarcador.setMap(mapa);
	} else {
		// Centra el mapa en la posicion actual
		mapa.panTo(miPosicion);
		// Pone el marcador para indicar la posicion
		mapaMarcador.setPosition(miPosicion);
	}
}

function mostrarErrores(error) {
	switch (error.code) {
 		case error.PERMISSION_DENIED:
                    swal('Permiso denegado por el usuario'); 
                    break;
   		case error.POSITION_UNAVAILABLE:
                    swal('Posición no disponible');
                    break; 
     	case error.TIMEOUT:
      		swal('Tiempo de espera agotado');
       		break;
        default:
         	swal('Error de Geolocalización desconocido :' + error.code);
	}
}

var opciones = {
	enableHighAccuracy: true,
	timeout: 10000,
	maximumAge: 1000
};

function detener() {
	navigator.geolocation.clearWatch(watchId);
}


