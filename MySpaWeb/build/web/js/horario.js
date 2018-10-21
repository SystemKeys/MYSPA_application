var horario=["9:00","10:00","11:00","12:00","13:00","14:00","15:00","16:00","17:00","18:00","19:00"];
function cargarHorario(){
	var lista=document.getElementById("cmbHorario");
	for (var i =0; i<= horario.length ; i++) {
		document.getElementById("cmbHorario").options[i]= new Option(horario[i]);
	}
}