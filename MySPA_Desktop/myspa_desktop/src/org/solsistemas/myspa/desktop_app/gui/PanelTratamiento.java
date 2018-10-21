/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solsistemas.myspa.desktop_app.gui;

import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.solsistemas.myspa.controller.ControllerTratamiento;
import org.solsistemas.myspa.desktop_app.gui.components.TableAdapterTratamiento;
import org.solsistemas.myspa.model.Tratamiento;

/**
 *
 * @author ramir
 */
public class PanelTratamiento {
     Stage window;  
     @FXML AnchorPane panelRaiz;
     @FXML TextField  txtTratamientoId;
     @FXML TextField  txtTratamientoNombre;
     @FXML TextArea  txtpTratamientoDescripcion;
     @FXML TextField  txtBuscar;
     @FXML CheckBox   chbActivo;
     
     @FXML Button btnNuevo;
     @FXML Button btnGuardar;
     @FXML Button btnEliminar;
     @FXML Button btnConsultar;
     @FXML Button btnBuscar;
     
     @FXML TableView<Tratamiento> tblTratamientos;
     
     FXMLLoader fxmll;
    
      ControllerTratamiento ct;

      public PanelTratamiento(Stage ventanaApp){
           window = ventanaApp;
           fxmll= new FXMLLoader(System.class.getResource("/org/solsistemas/myspa/desktop_app/gui/fxml/panel_tratamiento.fxml"));
           fxmll.setController(this);
      }
      
      public void inicializar() throws Exception
      {
          //Primero inicalizamos el controlador de tratamiento:
           ct= new ControllerTratamiento();
          
          //despues cargamos el archivo fxml
          fxmll.load();
          
          //Agregamos Oyentes
          
          agregarOyentes();
          consultarTratamientos();
         
          
         } 
      private void agregarOyentes(){
          btnGuardar.setOnAction(evt ->{guardarTratamiento();});          
          btnNuevo.setOnAction(evt ->{registroNuevo();});
          btnConsultar.setOnAction(evt ->{consultarTratamientos();});
          tblTratamientos.getSelectionModel().selectedItemProperty().addListener(evt ->{mostrarDetalleTratamiento();});
          btnEliminar.setOnAction(evt ->{eliminarTratamiento();});
      }

      
      private void registroNuevo(){
          txtTratamientoId.setText("");
             txtTratamientoNombre.setText("");
             txtpTratamientoDescripcion.setText("");
             chbActivo.setSelected(false);
          
      }
       private void guardarTratamiento(){
           
             int idGenerado = 0;
                 Tratamiento t = new Tratamiento();
           
             try {
              
           if (chbActivo.isSelected()) 
               t.setEstatus(1);
               
           else
               t.setEstatus(0);
           
           
           //Preguntamos si la caja de texto no esta vacia:
           if (!txtTratamientoId.getText().isEmpty()) 
               t.setId(Integer.valueOf(txtTratamientoId.getText()));
           
           t.setDescripcion(txtpTratamientoDescripcion.getText());
           t.setNombre(txtTratamientoNombre.getText());

           
               if (t.getId()== 0) {
                   
                   
                  idGenerado = ct.insert(t);
                  txtTratamientoId.setText(""+idGenerado);
                  consultarTratamientos();
                 
                  
               }else
                   ct.update(t);
               consultarTratamientos();
               mostrarMensaje( "Movimiento realizado.", 
                            "Datos del tratamiento guardados correctamente.", 
                            Alert.AlertType.CONFIRMATION);
           } catch (Exception e) {
             e.printStackTrace();
              mostrarMensaje( "Error al persistir datos.", 
                            "OcurriÃ³ el siguiente error: " + e.toString(),
                            Alert.AlertType.ERROR);
           }
        
          
      
           
      }
       private void consultarTratamientos(){
           List<Tratamiento> listaTratamiento = null;
           ObservableList<Tratamiento> listaObservableTratamientos = null;
           
           try{
               listaTratamiento = ct.getAll("");
               
               
               listaObservableTratamientos = FXCollections.observableArrayList(listaTratamiento);
               
               tblTratamientos.setItems(listaObservableTratamientos);
              
               
           }catch (Exception e){
               e.printStackTrace();
               mostrarMensaje( "Error", 
                            "Ocurrio el siguiente error: " + e.toString(), 
                            Alert.AlertType.ERROR);
           }
           TableAdapterTratamiento.adapt(tblTratamientos);
       }
    
       
       private void eliminarTratamiento(){
              Tratamiento t = new Tratamiento();
           t.setId(Integer.parseInt(txtTratamientoId.getText()));
         try {
             ct.delete(t.getId());
             consultarTratamientos();
         } catch (Exception e) {
             e.printStackTrace();
         }
           

           
       }
       private void mostrarDetalleTratamiento(){
           Tratamiento t = tblTratamientos.getSelectionModel().getSelectedItem();
          if (t != null){
             txtTratamientoId.setText("" + t.getId());
             txtTratamientoNombre.setText("" + t.getNombre());
             txtpTratamientoDescripcion.setText("" + t.getDescripcion());
             
          if (t.getEstatus() == 1)
              chbActivo.setSelected(true);
          else 
               chbActivo.setSelected(false);
          }
          
       }
        /**
     * Este mÃ©todo es para mostrar un mensaje modal.
     * @param titulo        El tÃ­tulo de la ventana.
     * @param mensaje       El contenido del mensaje.
     * @param tipoMensaje   El tipo de mensaje.
     */
    private void mostrarMensaje(String titulo, String mensaje, Alert.AlertType tipoMensaje)
    {
        //Creamos una nueva alerta:
        Alert msg = new Alert(tipoMensaje);
        
        //Establecemos el tÃ­tulo de la ventana del mensaje:
        msg.setTitle(titulo);
        
        //Establecemos el contenido de la ventana del mensaje:
        msg.setContentText(mensaje);
        
        //Establecemos el tipo de la ventana del mensaje como MODAL.
        //Esto significa que el programa no avanza hasta que el usuario realiza
        //una acciÃ³n que cierra la ventana:
        msg.initModality(Modality.WINDOW_MODAL);
        
        //Establecemos la ventana "Padre" de la ventana del mensaje:
        msg.initOwner(window);
        
        //Mostramos la ventana del mensaje y esperamos:
        msg.showAndWait();
    }
}

