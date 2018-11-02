/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solsistemas.myspa.desktop_app.gui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import org.solsistemas.myspa.controller.ControllerCliente;
import org.solsistemas.myspa.controller.ControllerHorario;
import org.solsistemas.myspa.controller.ControllerReservacion;
import org.solsistemas.myspa.controller.ControllerSala;
import org.solsistemas.myspa.controller.ControllerSalaHorario;
import org.solsistemas.myspa.model.Cliente;
import org.solsistemas.myspa.model.Reservacion;
import org.solsistemas.myspa.model.Sala;
import org.solsistemas.myspa.desktop_app.gui.components.TableAdapterReservacion;
import org.solsistemas.myspa.model.Horario;
import org.solsistemas.myspa.model.Persona;
import org.solsistemas.myspa.model.Usuario;

/**
 *
 * @author Vanessa
 */
public class PanelReservacion {
    
    Stage window;
    
    @FXML AnchorPane panelRaiz;
    
    @FXML TextField txtId;
    @FXML TextField txtFiltro;
    @FXML ComboBox cmbEstatus;
    @FXML DatePicker dteFecha;
    @FXML ComboBox<Horario> cmbHorario;
   
    @FXML CheckBox chbInactivo;
    @FXML Button btnGuardar;
    @FXML Button btnEliminar;
    @FXML Button btnNuevo;
    @FXML Button btnConsultar;
    @FXML Button btnBuscar;
    @FXML Button btnConsultarHorarios;    
    @FXML ComboBox<Cliente> cmbCliente;
    @FXML ComboBox<Sala> cmbSala;
    
    @FXML TableView<Reservacion> tblReservaciones;
    
    FXMLLoader fxmll;
    
    ControllerReservacion cr;
    ControllerSalaHorario csh;
    public PanelReservacion(Stage ventanaApp){
        window = ventanaApp;
        
        fxmll = new FXMLLoader(System.class.getResource("/org/solsistemas/myspa/desktop_app/gui/fxml/panel_reservacion.fxml"));
        fxmll.setController(this);
    }
    
    public void inicializar() throws Exception{
        //Primero instanciamos el controlador de reservaciones:
        cr = new ControllerReservacion();
        csh = new ControllerSalaHorario();
        //Después cargamos el archivo fxml:
        fxmll.load();
        TableAdapterReservacion.adapt(tblReservaciones);
         //Agregamos oyentes:
        agregarOyentes();
        cambiarFormatoFecha();
        agregarOpcionesSalas();
        agregarOpcionesClientes();
         
        ObservableList<String> estatusReservacion = FXCollections.observableArrayList("0","1","2");       
         cmbEstatus.setItems(estatusReservacion);
         cmbHorario.setDisable(true);
      
    }

    private void agregarOyentes(){
           btnConsultar.setOnAction(evt -> { actualizarTabla(); });
           btnGuardar.setOnAction(evt -> { guardarReservacion(); });
            btnNuevo.setOnAction(evt -> { limpiarCampos(); });
            btnEliminar.setOnAction(evt -> { eliminarReservacion(); });
            btnBuscar.setOnAction(evt -> { buscarReservacion(); });
            btnConsultarHorarios.setOnAction(evt -> {agregarHorario();});
            //Agregamos un oyente de selección a la tabla de reservaciones.
            //Este oyente se disparará cuando el usuario seleccione un renglón:
            tblReservaciones.getSelectionModel()
                        .selectedItemProperty()
                        .addListener((obs, anterior, nueva) -> { mostrarDetallesReservacion(); });           
    }
    
    public void cambiarFormatoFecha(){
            String pattern = "yyyy-MM-dd";

            dteFecha.setPromptText(pattern.toLowerCase());

            dteFecha.setConverter(new StringConverter<LocalDate>() {
             DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);

             @Override 
             public String toString(LocalDate date) {
                 if (date != null) {
                     return dateFormatter.format(date);
                 } else {
                     return "";
                 }
             }

             @Override 
             public LocalDate fromString(String string) {
                 if (string != null && !string.isEmpty()) {
                     return LocalDate.parse(string, dateFormatter);
                 } else {
                     return null;
                 }
             }
         });
}
    private void agregarHorario(){
        String fecha = "";
        int salaId = 0;
        ControllerHorario ho = new ControllerHorario();        
        ObservableList<Horario> listaHorarios = null;
        List<Horario> listaHorario = null;
        
        try{
            salaId = cmbSala.getSelectionModel().getSelectedItem().getId();
            fecha = "" + dteFecha.getValue() + "%";
            listaHorario = ho.getAllWithoutUsed(salaId, fecha);
            listaHorarios = FXCollections.observableList(listaHorario);
            cmbHorario.setItems(listaHorarios);
            cmbHorario.setDisable(false);
        }catch(Exception e){
            e.printStackTrace();
             mostrarMensaje( "Error al cargar datos.", 
                            "Ocurrió el siguiente error: " + e.toString(),
                            Alert.AlertType.ERROR);
        }
    }
    //Agregamos las salas al comboBox
      private void agregarOpcionesSalas(){
        ControllerSala sa = new ControllerSala();
        ObservableList<Sala> listaSalas = null;
        List<Sala> listaSala = null;
        
        try{
            
            listaSala = sa.getAll("",1);
            listaSalas = FXCollections.observableArrayList(listaSala);
            cmbSala.setItems(listaSalas);
            
        }catch(Exception e){
            e.printStackTrace();
            mostrarMensaje( "Error al cargar datos.", 
                            "Ocurrió el siguiente error: " + e.toString(),
                            Alert.AlertType.ERROR);
        }
    }

       private void agregarOpcionesClientes(){
        ControllerCliente cc = new ControllerCliente();
        ObservableList<Cliente> listaClientes = null;
        List<Cliente> listaCliente = null;
        
        try{
            
            listaCliente = cc.getAll("",1);
            listaClientes = FXCollections.observableArrayList(listaCliente);
            cmbCliente.setItems(listaClientes);
            
        }catch(Exception e){
            e.printStackTrace();
            mostrarMensaje( "Error al cargar datos.", 
                            "Ocurrió el siguiente error: " + e.toString(),
                            Alert.AlertType.ERROR);
        }
    }
      
    private void mostrarMensaje(String titulo, String mensaje, Alert.AlertType tipoMensaje) {
            //Creamos una nueva alerta:
            Alert msg = new Alert(tipoMensaje);

            //Establecemos el título de la ventana del mensaje:
            msg.setTitle(titulo);

            //Establecemos el contenido de la ventana del mensaje:
            msg.setContentText(mensaje);

            //Establecemos el tipo de la ventana del mensaje como MODAL.
            //Esto significa que el programa no avanza hasta que el usuario realiza
            //una acción que cierra la ventana:
            msg.initModality(Modality.WINDOW_MODAL);

            //Establecemos la ventana "Padre" de la ventana del mensaje:
            msg.initOwner(window);

            //Mostramos la ventana del mensaje y esperamos:
            msg.showAndWait();
        }
    public void actualizarTabla() {
           
            //Declaramos una lista dinámica de Resevaciones:
            List<Reservacion> listaReservaciones = null;        
            //Declaramos una lista observable dinámica:
            ObservableList<Reservacion> listaObserbableReservaciones = null;

            try {
                //Le pedimos al controlador las reservaciones:
                 if(chbInactivo.isSelected()){
                    listaReservaciones = cr.getAll("",0);
                    }else{
                    listaReservaciones = cr.getAll("",1);
                    }
               
              
                //Con la lista de reservaciones que ya tenemos, creamos una lista observable:
                listaObserbableReservaciones = FXCollections.observableArrayList(listaReservaciones);

                //Ponemos la lista obserbable dentro de la tabla:
                tblReservaciones.setItems(listaObserbableReservaciones);
            } 
            catch (Exception e)
            {
                e.printStackTrace();
                mostrarMensaje( "Error", 
                                "Ocurrio el siguiente error: " + e.toString(), 
                                Alert.AlertType.ERROR);
            }

            //Adaptamos la tabla para que tenga las columnas configuradas:
            TableAdapterReservacion.adapt(tblReservaciones);
        }
        /**
       * Este método despliega los detalles de una reservacion que haya sido 
       * seleccionado por el usuario en la tabla de reservaciones.
       */
        private void mostrarDetallesReservacion() {
          Reservacion r = null;

          //Preguntamos si hay una reservacion seleccionada:
          if (tblReservaciones.getSelectionModel().getSelectedItem() != null) {
              //Obtenemos el objeto reservacion seleccionado:
              r = tblReservaciones.getSelectionModel().getSelectedItem();
                 
            
              txtId.setText("" + r.getId());
              //dteFecha.setValue(java.sql.Date.valueOf());
              
              cmbCliente.getSelectionModel().select(r.getCliente());
              cmbSala.getSelectionModel().select(r.getSala());
              //cmbEstatus.getValue();
               cmbEstatus.getSelectionModel().select(1);
                 mostrarMensaje( "Recordatorio!", 
                            "Si vas a actualizar Datos recuerda llenar su Fecha y Horarios ",
                            Alert.AlertType.INFORMATION);
            //NO SE PUEDE PORQUE NO HAY NADA EN LA BD QUE TENGA LOS VALORES QUE LEDI AL COMBOBOX
            //  cmbHoraInicio.getSelectionModel().getSelectedItem().toString();
            //  cmbHoraFin.getSelectionModel().getSelectedItem().toString();
             
             
          }
      }
       
    private void guardarReservacion() {
        //Creamos una variable para guardar el horario selecionado
        Horario h = new Horario();
        String horarioInicio;
        String horarioFin;                 
        //Creamos un nuevo objeto de tipo Reservacion:
       Reservacion r = new Reservacion();
        
       horarioInicio = cmbHorario.getSelectionModel().getSelectedItem().getHoraInicio();
       horarioFin  =   cmbHorario.getSelectionModel().getSelectedItem().getHoraInicio();
     
                 horarioInicio = horarioInicio.substring(0, 5)+":00";
                
  
                r.setFechaHoraInicio(dteFecha.getValue()+" "+horarioInicio);
                r.setFechaHoraFin(dteFecha.getValue()+" "+horarioFin);
               
        r.setSala(cmbSala.getValue());
        r.setCliente(cmbCliente.getValue());
        r.setEstatus(cmbEstatus.getSelectionModel().getSelectedIndex());
        
  
        if (txtId.getText().trim().length() > 0)   
            r.setId(Integer.valueOf(txtId.getText()));
        //guardamos el id de horario para llenar latabla sala:horario
        h.setId(cmbHorario.getSelectionModel().getSelectedItem().getId());
        //Una vez que llenamos todos los datos de un empleado,
        //realizamos la inserción o actualización del registro:
        try {
            if (r.getId() == 0) {
                csh.insert(r.getSala(), h);
                cr.insert(r);
                txtId.setText("" + r.getId());         
                cmbHorario.getItems().clear();
            }
            else                                         
                mostrarMensaje( "No se puede Modificar una reservación.", 
                            "Datos de cliente guardados correctamente.", 
                            Alert.AlertType.INFORMATION);
        } 
        catch (Exception ex) {
            ex.printStackTrace();
            mostrarMensaje( "Error al persistir datos.", 
                            "Ocurrió el siguiente error: " + ex.toString(),
                            Alert.AlertType.ERROR);
        }
    }
private void limpiarCampos() {
        
        txtId.setText("");
        cmbCliente.getSelectionModel().clearSelection();
        cmbCliente.setValue(null);
        dteFecha.getEditor().clear();
        dteFecha.setValue(null);
        cmbSala.getSelectionModel().clearSelection();
        cmbSala.setValue(null);
        cmbHorario.getSelectionModel().clearSelection();    
        cmbHorario.setValue(null);      
        cmbEstatus.getSelectionModel().clearSelection();   
        cmbEstatus.getSelectionModel().selectFirst();
        cmbHorario.getItems().clear();

    }
    private void eliminarReservacion(){
      
        Reservacion r = new Reservacion();
        
        try{
            if(! txtId.getText().isEmpty()){
                r.setId(Integer.valueOf(txtId.getText()));
               
                
                cr.delete(r.getId());
                r = tblReservaciones.getSelectionModel().getSelectedItem();
                tblReservaciones.getItems().remove(r);
                limpiarCampos();
                mostrarMensaje( "Movimiento realizado.", 
                            "Datos de cliente eliminados correctamente.", 
                            Alert.AlertType.CONFIRMATION);
            }
        }catch(Exception x){
            x.printStackTrace();
            mostrarMensaje( "Error al eliminar datos.", 
                            "Ocurrió el siguiente error: " + x.toString(),
                            Alert.AlertType.ERROR);
        }
    }
        private void buscarReservacion(){
            Reservacion r = new Reservacion();
        ObservableList<Reservacion> listaObservableReservaciones = null;
        
        try{
            int i = Integer.valueOf(txtFiltro.getText());
            r = cr.findById(i);
            tblReservaciones.getItems().clear();
            listaObservableReservaciones = FXCollections.observableArrayList(r);
            //Ponemos la lista observable dentro del table view
            tblReservaciones.setItems(listaObservableReservaciones);
            
        }catch(Exception x){
            x.printStackTrace();
            mostrarMensaje( "Error al buscar datos.", 
                            "Ocurrió el siguiente error: " + x.toString(),
                            Alert.AlertType.ERROR);
        
    }
    }














    }
