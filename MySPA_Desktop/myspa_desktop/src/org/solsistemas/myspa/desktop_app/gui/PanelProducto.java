/*
 * 
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
import org.solsistemas.myspa.controller.ControllerProducto;
import org.solsistemas.myspa.desktop_app.gui.components.TableAdapterProducto;
import org.solsistemas.myspa.model.Producto;

/**
 *
 * @author Vanessa
 */
public class PanelProducto {
    
    Stage window;
    
    @FXML AnchorPane panelRaiz;
    
    @FXML TextField txtId;
    @FXML TextField txtNombre;
    @FXML TextField txtPrecio;
    @FXML TextField txtMarca;
    @FXML TextField txtFiltro;
    @FXML CheckBox chbActivo;
    @FXML CheckBox chbInactivo;
    
    
    @FXML Button btnGuardar;
    @FXML Button btnEliminar;
    @FXML Button btnNuevo;
    @FXML Button btnConsultar;
    @FXML Button btnBuscar;
    
    @FXML TableView<Producto> tblProductos;
    
    FXMLLoader fxmll;
    
    ControllerProducto cp;
    
    public PanelProducto(Stage ventanaApp){
        window = ventanaApp;
        
        fxmll = new FXMLLoader(System.class.getResource("/org/solsistemas/myspa/desktop_app/gui/fxml/panel_producto.fxml"));
        fxmll.setController(this);
    }
    
    public void inicializar() throws Exception{
        //Primero instanciamos el controlador de productos:
        cp = new ControllerProducto();
        //Después cargamos el archivo fxml:
        fxmll.load();
        TableAdapterProducto.adapt(tblProductos);
        //Agregamos oyentes:
        agregarOyentes();
    }
    
    private void guardarProducto(){
        
        int idGenerado = 0;
        Producto p = new Producto();
        
        try{
            //p.setEstatus(chbActivo.isSelected() ? 1 : 0);
           
            

            //Si la caja de texto NO esta vacía:
            if(!txtId.getText().isEmpty())
                p.setId(Integer.valueOf(txtId.getText()));
            
            
            p.setNombre(txtNombre.getText());
            p.setMarca(txtMarca.getText());
            p.setPrecioUso(Float.valueOf(txtPrecio.getText()));
            
            if(p.getId() == 0){
                idGenerado = cp.insert(p);
                txtId.setText("" + idGenerado);
                tblProductos.getItems().add(p);
            }else
                if(chbActivo.isSelected())
                    p.setEstatus(1);
                else
                    p.setEstatus(0);
                cp.update(p);
            mostrarMensaje( "Movimiento realizado.", 
                            "Datos del producto guardados correctamente.", 
                            Alert.AlertType.CONFIRMATION);
            
        }catch(Exception e){
            e.printStackTrace();
            mostrarMensaje( "Error al persistir datos.", 
                            "Ocurrió el siguiente error: " + e.toString(),
                            Alert.AlertType.ERROR);
        }    
    }
    
    private void consultarProductos() {
        
        List<Producto> listaProductos = null;
        ObservableList<Producto> listaObservableProductos = null;

        try{
            //Consultamos los productos a través del
            //ControllerProducto:
                    if(chbInactivo.isSelected()){
                    listaProductos = cp.getAll("",0);
                    }else{
                   listaProductos = cp.getAll("",1);
                    }            
            //Convertimos nuestra lista de productos en una 
            //lista Observable de productos:
            listaObservableProductos = FXCollections.observableArrayList(listaProductos);
            //Ponemos la lista observable dentro del table view
            tblProductos.setItems(listaObservableProductos);
        }catch(Exception e){        
            e.printStackTrace();
            mostrarMensaje( "Error", 
                            "Ocurrio el siguiente error: " + e.toString(), 
                            Alert.AlertType.ERROR);
        }    
    }
    
    private void mostrarDetalleProducto() {
        
        Producto p = tblProductos.getSelectionModel().getSelectedItem();
        
        if(p != null){
            
            txtId.setText("" + p.getId());
            txtMarca.setText("" + p.getMarca());
            txtPrecio.setText("" + p.getPrecioUso());
            txtNombre.setText("" + p.getNombre());
            if(p.getEstatus() == 1)
                chbActivo.setSelected(true);
            else
                chbActivo.setSelected(false);
        } 
    }
    
    private void eliminarProducto() {
        
        Producto p = new Producto();
        
        try{
            if(! txtId.getText().isEmpty()){
                p.setId(Integer.valueOf(txtId.getText()));
                cp.delete(p.getId());
                p = tblProductos.getSelectionModel().getSelectedItem();
                tblProductos.getItems().remove(p);
                limpiarPanel();
                mostrarMensaje( "Movimiento realizado.", 
                            "Datos de producto eliminados correctamente.", 
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
        txtMarca.setText("");
        txtId.setText("");
        txtPrecio.setText("");
        chbActivo.setSelected(false);
        
    }
    
    private void buscarProducto(){
        
        Producto p = new Producto();
        ObservableList<Producto> listaObservableProductos = null;
        
        try{
            int i = Integer.valueOf(txtFiltro.getText());
            p = cp.findById(i);
            tblProductos.getItems().clear();
            listaObservableProductos = FXCollections.observableArrayList(p);
            //Ponemos la lista observable dentro del table view
            tblProductos.setItems(listaObservableProductos);
            
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

    private void agregarOyentes(){
        btnGuardar.setOnAction(evt -> { guardarProducto(); });
        btnEliminar.setOnAction(evt -> { eliminarProducto(); });
        btnNuevo.setOnAction(evt -> { limpiarPanel(); });
        btnConsultar.setOnAction(evt -> { consultarProductos(); });
        btnBuscar.setOnAction(evt -> { buscarProducto(); }); 
        
        //Agregamos el evento a la tabla para que responda a la 
        //Seleccion de registros que haga el usuario:
        tblProductos.getSelectionModel().selectedItemProperty().addListener(evt -> {mostrarDetalleProducto();} );
    }
}

    