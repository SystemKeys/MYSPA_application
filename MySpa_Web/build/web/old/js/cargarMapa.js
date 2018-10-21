function verMapa(){
				var Centro={lat:21.12117, lng:-101.682636};
				var Arbide={lat:21.123116, lng:-101.696867};
				var Insurgentes={lat:21.142708, lng:-101.668407};
				var objMapa=new google.maps.Map
								(document.getElementById("seccionMapa"),
								{center:Centro,
								scrollwheel:true,
								mapTypeId: google.maps.MapTypeId.ROADMAP,//Tipo de mapa, en este caso es interactivo.
								zoom:13
								});
								
							var cen=new google.maps.Marker(
								{map:objMapa,
								position:Centro,
								title:'Zacatecas\nPetróleo y Gas\nLatitud: 21.7710273\nLongitud: -102.58357719999998',
								icon: '../imgs/ubicacion.jpg'}
								);
		
							var arb=new google.maps.Marker(
								{map:objMapa,
								position:Arbide,
								title:'Tampico\nPetróleo y Gas\nLatitud: 22.2331409\nLongitud: -97.8611234',
								icon: '../imgs/ubicacion.jpg'
								});
								
							var ins=new google.maps.Marker(
								{map:objMapa,
								position:Insurgentes,
								title:'Hermosillo\nPetróleo y Gas\nLatitud: 29.0730377\nLongitud: -110.95582869999998',
								icon: '../imgs/ubicacion.jpg'}
								);
			}