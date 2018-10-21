/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solsistemas.myspa.desktop_app.gui;

import java.io.File;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;

import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.solsistemas.myspa.controller.ControllerSala;
import org.solsistemas.myspa.controller.ControllerSucursal;
import org.solsistemas.myspa.desktop_app.gui.components.TableAdapterSala;
import org.solsistemas.myspa.model.Sala;
import org.solsistemas.myspa.model.Sucursal;

/**
 *
 * @author lider
 */
public class PanelSala {
    
    Stage window;
    
    @FXML TextField txtId;
    @FXML TextField txtNombre;
    @FXML TextField txtDescripcion;
    @FXML TextField txtRef;
    @FXML ComboBox<Sucursal> cmbSucursal;
    @FXML ImageView imgvSala;
    @FXML CheckBox chbActivo;
    @FXML CheckBox chbInactivo;
    
    @FXML Button btnGuardar;
    @FXML Button btnNuevo;
    @FXML Button btnConsultar;
    @FXML Button btnEliminar;
    @FXML Button btnBuscar;
    @FXML Button btnCargarFotografia;
    
    @FXML TableView<Sala> tblSalas;
    
    @FXML AnchorPane panelRaiz;
    
    FileChooser fchFotografia;
    
    FXMLLoader fxmll;
    
    ControllerSala cs;
    
    public PanelSala(Stage ventanaApp){
        window = ventanaApp;
        
        fxmll = new FXMLLoader(System.class.getResource("/org/solsistemas/myspa/desktop_app/gui/fxml/panel_sala.fxml"));
        fxmll.setController(this);
    }
    
    public void inicializar() throws Exception {
        //Primero instanciamos el controlador de salas:
        cs = new ControllerSala();
        //Creamos el FileChooser:
        fchFotografia = new FileChooser();
        fchFotografia.setTitle("Seleccione la fotografía de la Sala...");
        //Después cargamos el archivo fxml:
        fxmll.load();
        agregarOpcionesSucursales();
        TableAdapterSala.adapt(tblSalas);
        //Agregamos oyentes:
        agregarOyentes();
    }
    
    private void guardarSala() {
        
        int idGenerado = 0;
        Sala s = new Sala();
        
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
            s.setDescripcion(txtDescripcion.getText());
            s.setFoto("");
            s.setRutaFoto("");
            s.setSucursal(cmbSucursal.getValue());
            
            if(s.getId() == 0){
                idGenerado = cs.insert(s);
                txtId.setText("" + idGenerado);
                tblSalas.getItems().add(s);
            }else
                cs.update(s);
            mostrarMensaje( "Movimiento realizado.", 
                            "Datos de la sala guardados correctamente.", 
                            Alert.AlertType.CONFIRMATION);
            
        }catch(Exception e){
            e.printStackTrace();
            mostrarMensaje( "Error al persistir datos.", 
                            "Ocurrió el siguiente error: " + e.toString(),
                            Alert.AlertType.ERROR);
        }    
    }
    
    private void cargarFotografiaEmpleado() {
        File f = null; 
        Image img = null;
        
        try
        {
            //Mostramos el díalogo para que el usuario seleccione el archivo
            //de imagen:
            f = fchFotografia.showOpenDialog(window);
            
            //Evaluamos si el usuario seleccionó una Imagen:
            if (f != null)
            {
                img = new Image(f.toURI().toURL().toString());
                imgvSala.setImage(img);
            }
        } 
        catch (Exception e)
        {
            e.printStackTrace();
            mostrarMensaje("Error al cargar fotografía.", e.toString(), Alert.AlertType.ERROR);
        }
    }
    
    private void consultarSalas() {
        
        List<Sala> listaSalas = null;
        ObservableList<Sala> listaObservableSalas = null;

        try{
            //Consultamos las sucursales a través del
            //ControllerSucursal:
             if(chbInactivo.isSelected()){
                      listaSalas = cs.getAll("",0);
                    }else{
                    listaSalas = cs.getAll("",1);
                    }    
           
            //Convertimos nuestra lista de sucursales en una 
            //lista Observable de sucursales:
            listaObservableSalas = FXCollections.observableArrayList(listaSalas);
            //Ponemos la lista observable dentro del table view
            tblSalas.setItems(listaObservableSalas);
        }catch(Exception e){        
            e.printStackTrace();
            mostrarMensaje( "Error", 
                            "Ocurrio el siguiente error: " + e.toString(), 
                            Alert.AlertType.ERROR);
        }    
    }
    
    private void mostrarDetalleSala() {
        
        Sala s = tblSalas.getSelectionModel().getSelectedItem();
        
        if(s != null){
            
            txtId.setText("" + s.getId());
            txtNombre.setText("" + s.getNombre());
            txtDescripcion.setText("" + s.getDescripcion());
            cmbSucursal.getSelectionModel().select(s.getSucursal());
            if(s.getEstatus() == 1){
                
                chbActivo.setSelected(true);
            }else{
                
                chbActivo.setSelected(false);
            }
        } 
    }
    
    private void eliminarSala() {
        
        Sala s = new Sala();
        
        try{
            if(! txtId.getText().isEmpty()){
                s.setId(Integer.valueOf(txtId.getText()));
                s.setNombre(txtNombre.getText());
                s.setDescripcion(txtDescripcion.getText());
                s.setFoto("");
                s.setRutaFoto("");
                
                //s.setSucursal;
                
                if(chbActivo.isSelected()){
                    s.setEstatus(1);
                }else{
                    s.setEstatus(0);
                }
                
                cs.delete(s.getId());
                s = tblSalas.getSelectionModel().getSelectedItem();
                tblSalas.getItems().remove(s);
                limpiarPanel();
                mostrarMensaje( "Movimiento realizado.", 
                            "Datos de sala eliminados correctamente.", 
                            Alert.AlertType.CONFIRMATION);
            }
        }catch(Exception e){
            e.printStackTrace();
            mostrarMensaje( "Error al eliminar datos.", 
                            "Ocurrió el siguiente error: " + e.toString(),
                            Alert.AlertType.ERROR);
        }
    
    }
    
    private void limpiarPanel() {
        
        txtNombre.setText("");
        txtDescripcion.setText("");
        txtId.setText("");
        imgvSala.setImage(null);
        chbActivo.setSelected(false);
        cmbSucursal.getSelectionModel().clearSelection();
    }
    
    private void buscarSala() {
        
        Sala s = new Sala();
        ObservableList<Sala> listaObservableSalas = null;
        
        try{
            int i = Integer.valueOf(txtRef.getText());
            s = cs.findById(i);
            tblSalas.getItems().clear();
            listaObservableSalas = FXCollections.observableArrayList(s);
            //Ponemos la lista observable dentro del table view
            tblSalas.setItems(listaObservableSalas);
            
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
    private void mostrarMensaje(String titulo, String mensaje, Alert.AlertType tipoMensaje){
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
        btnGuardar.setOnAction(evt -> { guardarSala(); });
        btnEliminar.setOnAction(evt -> { eliminarSala(); });
        btnNuevo.setOnAction(evt -> { limpiarPanel(); });
        btnConsultar.setOnAction(evt -> { consultarSalas(); });
        btnBuscar.setOnAction(evt -> { buscarSala(); });
        
        tblSalas.getSelectionModel().selectedItemProperty().addListener(evt -> {mostrarDetalleSala();} );
        cmbSucursal.setOnMouseClicked(evt -> { agregarOpcionesSucursales(); } );
    }
    
    private void agregarOpcionesSucursales(){
        ControllerSucursal csu = new ControllerSucursal();
        ObservableList<Sucursal> listaSucursales = null;
        List<Sucursal> listaSuc = null;
        
        try{
            
            listaSuc = csu.getAll("",1);
            listaSucursales = FXCollections.observableArrayList(listaSuc);
            cmbSucursal.setItems(listaSucursales);
            
        }catch(Exception e){
            e.printStackTrace();
            mostrarMensaje( "Error al cargar datos.", 
                            "Ocurrió el siguiente error: " + e.toString(),
                            Alert.AlertType.ERROR);
        }
    }
    
}
