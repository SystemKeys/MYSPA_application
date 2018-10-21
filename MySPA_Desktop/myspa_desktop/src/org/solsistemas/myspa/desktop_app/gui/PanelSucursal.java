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
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.solsistemas.myspa.controller.ControllerSucursal;
import org.solsistemas.myspa.model.Sucursal;
import org.solsistemas.myspa.desktop_app.gui.components.TableAdapterSucursal;


/**
 *
 * @author Vanessa
 */
public class PanelSucursal {
    
    Stage window;
    
    @FXML AnchorPane panelRaiz;
    
    @FXML TextField txtId;
    @FXML TextField txtNombre;
    @FXML TextField txtLatitud;
    @FXML TextField txtLongitud;
    @FXML TextField txtDomicilio;
    @FXML TextField txtRef;
    @FXML CheckBox chbActivo;
    @FXML CheckBox chbInactivo;
    
    @FXML Button btnGuardar;
    @FXML Button btnEliminar;
    @FXML Button btnNuevo;
    @FXML Button btnConsultar;
    @FXML Button btnBuscar;
    
    @FXML TableView<Sucursal> tblSucursales;
    
    FXMLLoader fxmll;
    
    ControllerSucursal cs;
    
    public PanelSucursal(Stage ventanaApp){
        window = ventanaApp;
        
        fxmll = new FXMLLoader(System.class.getResource("/org/solsistemas/myspa/desktop_app/gui/fxml/panel_sucursal.fxml"));
        fxmll.setController(this);
    }
    
    public void inicializar() throws Exception{
        //Primero instanciamos el controlador de sucursales:
        cs = new ControllerSucursal();
        //Después cargamos el archivo fxml:
        fxmll.load();
        TableAdapterSucursal.adapt(tblSucursales);
        //Agregamos oyentes:
        agregarOyentes();
    }
    
    private void guardarSucursal(){
        
        int idGenerado = 0;
        Sucursal s = new Sucursal();
        
        try{
            //p.setEstatus(chbActivo.isSelected() ? 1 : 0);
            if(chbActivo.isSelected())
                s.setEstatus(1);
            else
                s.setEstatus(0);
            

            //Si la caja de texto NO esta vacía:
            if(!txtId.getText().isEmpty())
                s.setId(Integer.valueOf(txtId.getText()));
            

            s.setNombre(txtNombre.getText());
            s.setDomicilio(txtDomicilio.getText());
            s.setLatitud(Double.valueOf(txtLatitud.getText()));
            s.setLongitud(Double.valueOf(txtLongitud.getText()));
            
            if(s.getId() == 0){
                idGenerado = cs.insert(s);
                txtId.setText("" + idGenerado);
                tblSucursales.getItems().add(s);
            }else
                cs.update(s);
            mostrarMensaje( "Movimiento realizado.", 
                            "Datos de la sucursal guardados correctamente.", 
                            Alert.AlertType.CONFIRMATION);
            
        }catch(Exception e){
            e.printStackTrace();
            mostrarMensaje( "Error al persistir datos.", 
                            "Ocurrió el siguiente error: " + e.toString(),
                            Alert.AlertType.ERROR);
        }    
    }
    
    private void consultarSucursales(){
        
        List<Sucursal> listaSucursales = null;
        ObservableList<Sucursal> listaObservableSucursales = null;

        try{
            //Consultamos las sucursales a través del
            //ControllerSucursal:
             if(chbInactivo.isSelected()){
                    listaSucursales = cs.getAll("",0);
                    }else{
                   listaSucursales = cs.getAll("",1);
                    }    
            
            //Convertimos nuestra lista de sucursales en una 
            //lista Observable de sucursales:
            listaObservableSucursales = FXCollections.observableArrayList(listaSucursales);
            //Ponemos la lista observable dentro del table view
            tblSucursales.setItems(listaObservableSucursales);
            
        }catch(Exception e){        
            e.printStackTrace();
            mostrarMensaje( "Error", 
                            "Ocurrio el siguiente error: " + e.toString(), 
                            Alert.AlertType.ERROR);
        }    
    }
    
    private void mostrarDetalleSucursal(){
        
        Sucursal s = tblSucursales.getSelectionModel().getSelectedItem();
        
        if(s != null){
            
            txtId.setText("" + s.getId());
            txtNombre.setText("" + s.getNombre());
            txtDomicilio.setText("" + s.getDomicilio());
            txtLongitud.setText("" +s.getLongitud());
            txtLatitud.setText("" +s.getLatitud());
            if(s.getEstatus() == 1){
                
                chbActivo.setSelected(true);
            }else{
                
                chbActivo.setSelected(false);
            }
        } 
    }
    
    private void eliminarSucursal(){
        
        Sucursal s = new Sucursal();
        
        try{
            if(! txtId.getText().isEmpty()){
                s.setId(Integer.valueOf(txtId.getText()));
                s.setNombre(txtNombre.getText());
                s.setDomicilio(txtDomicilio.getText());
                s.setLatitud(Double.valueOf(txtLatitud.getText()));
                s.setLongitud(Double.valueOf(txtLongitud.getText()));
                
                if(chbActivo.isSelected()){
                    s.setEstatus(1);
                }else{
                    s.setEstatus(0);
                }
                
                cs.delete(s.getId());
                s = tblSucursales.getSelectionModel().getSelectedItem();
                tblSucursales.getItems().remove(s);
                limpiarPanel();
                
                mostrarMensaje( "Movimiento realizado.", 
                            "Datos de sucursal eliminados correctamente.", 
                            Alert.AlertType.CONFIRMATION);
            }
        }catch(Exception e){
            e.printStackTrace();
            mostrarMensaje( "Error al eliminar datos.", 
                            "Ocurrió el siguiente error: " + e.toString(),
                            Alert.AlertType.ERROR);
        }
    
    }
    
    private void limpiarPanel(){
        
        txtNombre.setText("");
        txtDomicilio.setText("");
        txtId.setText("");
        txtLongitud.setText("");
        txtLatitud.setText("");
        chbActivo.setSelected(false);
        
    }
    
    private void buscarSucursal(){
        
        Sucursal s = new Sucursal();
        ObservableList<Sucursal> listaObservableSucursales = null;
        
        try{
            int i = Integer.valueOf(txtRef.getText());
            s = cs.findById(i);
            tblSucursales.getItems().clear();
            listaObservableSucursales = FXCollections.observableArrayList(s);
            //Ponemos la lista observable dentro del table view
            tblSucursales.setItems(listaObservableSucursales);
            
        }catch(Exception e){
            e.printStackTrace();
            mostrarMensaje( "Error al buscar datos.", 
                            "Ocurrió el siguiente error: " + e.toString(),
                            Alert.AlertType.ERROR);
        }
    }

    /**
     * Este método es para mostrar un mensaje modal.
     * @param titulo        El título de la ventana.
     * @param mensaje       El contenido del mensaje.
     * @param tipoMensaje   El tipo de mensaje.
     */
    
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
    
    public void agregarOyentes(){
        btnGuardar.setOnAction(evt -> { guardarSucursal(); });
        btnEliminar.setOnAction(evt -> { eliminarSucursal(); });
        btnNuevo.setOnAction(evt -> { limpiarPanel(); });
        btnConsultar.setOnAction(evt -> { consultarSucursales(); });
        btnBuscar.setOnAction(evt -> { buscarSucursal(); });
        
        tblSucursales.getSelectionModel().selectedItemProperty().addListener(evt -> {mostrarDetalleSucursal();} );
    }
    
}
