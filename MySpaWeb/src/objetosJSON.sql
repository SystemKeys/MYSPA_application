USE myspa;

-- Colección de Empleados:
SELECT json_object(
					'persona',		json_object('idPersona',		idPersona,
												'nombre',			nombre,
												'apellidoPaterno', 	apellidoPaterno,
												'apellidoMaterno', 	apellidoMaterno,
                                                'genero',			genero,
												'domicilio',		domicilio,
												'telefono',			telefono
												'rfc',				rfc),
					'idEmpleado',		idEmpleado,
                    'numeroEmpleado',	numeroEmpleado,
					'puesto',			puesto,
                    'estatus',			estatus,
					'foto',				foto,
					'rutaFoto',			rutaFoto,
                    'usuario',			json_object('idUsuario', idUsuario,
													'nombreUsuario', nombreUsuario,
													'contrasenia', constrasenia,
                                                    'rol', rol))
FROM v_empleados
INTO OUTFILE 'C:\\ProgramData\\MySQL\\MySQL Server 5.7\\Uploads\\empleados.json';

-- Colección de productos:
SELECT json_object(	'idProducto',	idProducto,
					'nombre', 		nombre,
					'marca',		marca,
					'estatus',		estatus,
					'precioUso',	precioUso)
FROM producto
INTO OUTFILE 'C:\\ProgramData\\MySQL\\MySQL Server 5.7\\Uploads\\productos.json';

-- Colección de reservaciones:
SELECT json_object(	'idReservacion',	idReservacion,
					'fecha', 			fecha,
					'estatus',			estatus,
					'cliente',			json_object('idCliente',			idCliente,
													'nombreCliente',		nombreCliente,
													'apellidoPaterno',		apellidoPaterno,
                                                    'apellidoMaterno',		apellidoMaterno),
					'sala',				json_object('idSala',			idSala,
													'nombreSala',		nombreSala))
FROM v_reservacion
INTO OUTFILE 'C:\\ProgramData\\MySQL\\MySQL Server 5.7\\Uploads\\reservaciones.json';

-- Colección de 