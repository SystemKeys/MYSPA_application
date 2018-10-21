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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.solsistemas.myspa.controller.ControllerCliente;
import org.solsistemas.myspa.desktop_app.gui.components.TableAdapterCliente;
import org.solsistemas.myspa.model.Cliente;
import org.solsistemas.myspa.model.Persona;
import org.solsistemas.myspa.model.Usuario;

/**
 *
 * @author diegg_
 */
public class PanelCliente {
    //Referencia a la ventana de la aplicación:
    Stage window;
    
    //El Panel Raiz:
    @FXML AnchorPane panelRaiz;
    
    //Controles de datos personales:
    @FXML TextField txtPersonaId;
    @FXML TextField txtPersonaNombre;
    @FXML TextField txtPersonaApellidoPaterno;
    @FXML TextField txtPersonaApellidoMaterno;
    @FXML TextField txtPersonaRFC;
    @FXML TextField txtPersonaTelefono;
    @FXML TextArea  txtaPersonaDomicilio;
    @FXML ComboBox<String> cmbPersonaGenero;
    
    //Controles de datos de Empleado:
    @FXML TextField txtClienteId;
    @FXML TextField txtClienteNumero;
    @FXML TextField txtClienteCorreo;
    @FXML CheckBox  chbClienteActivo;
      
    //Controles de datos de Usuario:
    @FXML TextField txtUsuarioId;
    @FXML TextField txtUsuarioNombre;
    @FXML PasswordField txtpUsuarioContrasenia;
    @FXML ComboBox<String> cmbUsuarioRol;
    
    //Botones de acciones:
    @FXML Button btnCargarFotografia;
    @FXML Button btnGuardar;
    @FXML Button btnEliminar;
    @FXML Button btnLimpiar;
    @FXML Button btnConsultar;   
    @FXML CheckBox chbInactivo;
    
    //Controles de Filtro:
    @FXML TextField txtFiltro;
    @FXML Button btnFiltro;
    
    //Tabla de Clientes:
    @FXML TableView<Cliente> tblClientes;
    
    //El objeto con el que cargaremos el archivo FXML:
    FXMLLoader fxmll;
    
    //El objeto que nos ayudará a consultar y persistir datos de clientes
    //en la BD:
    ControllerCliente cc;
    
    public PanelCliente(Stage ventanaApp) {
        window = ventanaApp;
        
        //Le indicamos al FXLLoader donde está el recurso que cargará:
        fxmll = new FXMLLoader(System.class.getResource("/org/solsistemas/myspa/desktop_app/gui/fxml/panel_cliente.fxml"));
        
        //Le indicamos al FXMLLoader que esta clase será su clase controladora:
        fxmll.setController(this);
    }
    /**
     * Devuelve el panel raiz de este componente.
     * 
     * El panel raiz es el componente visual que contiene todos los controles
     * visuales que se requieren para el módulo de control de clientes.
     * @return 
     */
    public AnchorPane getPanelRaiz() {
        return panelRaiz;
    }
    
    /**
     * Este método inicializa el módulo de empleados.
     * @throws Exception 
     */
    public void inicializar() throws Exception {       
        //Cargamos el archivo FXML:
        fxmll.load();
        
        //Instanciamos el controlador:
        cc = new ControllerCliente();       
        
        //Agregamos los oyentes de los controles:
         agregarOyentes();
        
         actualizarTabla();
    }

    private void agregarOyentes() {
        //Agregamos el oyente a los botones:
        
        btnConsultar.setOnAction(evt -> { actualizarTabla(); });
        btnGuardar.setOnAction(evt -> { guardarCliente(); });
        btnLimpiar.setOnAction(evt -> { limpiarCampos(); });
        btnEliminar.setOnAction(evt -> { eliminarCliente(); });
        btnFiltro.setOnAction(evt -> { buscarCliente(); });
        //Agregamos un oyente de selección a la tabla de empleados.
        //Este oyente se disparará cuando el usuario seleccione un renglón:
        tblClientes.getSelectionModel()
                    .selectedItemProperty()
                    .addListener((obs, anterior, nueva) -> { mostrarDetallesCliente(); });
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
    /**
     * Este método consulta los empleados en la BD a través del controlador 
     * y actualiza la tabla con la lista de objetos devuelta.
     * 
     * @throws Exception    Lanzará cualquier excepción que ocurra.
     */
    public void actualizarTabla() {
        //Declaramos una lista dinámica de Empleados:
        List<Cliente> listaClientes = null;        
        //Declaramos una lista observable dinámica:
        ObservableList<Cliente> listaObserbableClientes = null;
        
        try {
            //Le pedimos al controlador los empleados:
            if(chbInactivo.isSelected()){
            listaClientes = cc.getAll("",0);
            }else{
            listaClientes = cc.getAll("",1);
            }
            
            //Con la lista de empleados que ya tenemos, creamos una lista observable:
            listaObserbableClientes = FXCollections.observableArrayList(listaClientes);

            //Ponemos la lista obserbable dentro de la tabla:
            tblClientes.setItems(listaObserbableClientes);
        } 
        catch (Exception e)
        {
            e.printStackTrace();
            mostrarMensaje( "Error", 
                            "Ocurrio el siguiente error: " + e.toString(), 
                            Alert.AlertType.ERROR);
        }
        
        //Adaptamos la tabla para que tenga las columnas configuradas:
        TableAdapterCliente.adapt(tblClientes);
    }
    
    /**
     * Este método despliega los detalles de un empleado que haya sido 
     * seleccionado por el usuario en la tabla de empleados.
     */
    private void mostrarDetallesCliente() {
        Cliente c = null;
        
        //Preguntamos si hay un empleado seleccionado:
        if (tblClientes.getSelectionModel().getSelectedItem() != null) {
            //Obtenemos el objeto Empleado seleccionado:
            c = tblClientes.getSelectionModel().getSelectedItem();
            
            //Llenamos los controles que despliegan datos personales:
            txtPersonaApellidoMaterno.setText(c.getPersona().getApellidoMaterno());
            txtPersonaApellidoPaterno.setText(c.getPersona().getApellidoPaterno());
            txtPersonaId.setText("" + c.getPersona().getId());
            txtPersonaNombre.setText(c.getPersona().getNombre());
            txtPersonaRFC.setText(c.getPersona().getRfc());
            txtPersonaTelefono.setText(c.getPersona().getTelefono());
            txtaPersonaDomicilio.setText(c.getPersona().getDomicilio());
            cmbPersonaGenero.getSelectionModel().select(c.getPersona().getGenero());
            
            //Llenamos los controles que despliegan datos del empleado:
            txtClienteId.setText("" + c.getId());
            txtClienteNumero.setText(c.getNumeroUnico());
            txtClienteCorreo.setText(c.getCorreo());
            if(c.getEstatus() == 1){
                
                chbClienteActivo.setSelected(true);
            }else{
                
                chbClienteActivo.setSelected(false);
            }
            
            //Llenamos los datos de seguridad:
            txtUsuarioId.setText("" + c.getUsuario().getId());
            txtUsuarioNombre.setText(c.getUsuario().getNombreUsuario());
            txtpUsuarioContrasenia.setText(c.getUsuario().getContrasenia());
            cmbUsuarioRol.getSelectionModel().select(c.getUsuario().getRol());                       
        }
    }
    

    
    /**
     * Este método contiene las instrucciones necesarias para insertar o 
     * actualizar un registro de empleado.
     */
    
    private void guardarCliente() {
        //Creamos un nuevo objeto de tipo Persona:
        Persona p = new Persona();
        //Creamos un nuevo objeto de tipo Usuario:
        Usuario u = new Usuario();
        //Creamos un nuevo objeto de tipo Cliente:
        Cliente c = new Cliente();
        
        //Llenamos los datos personales:
        p.setApellidoMaterno(txtPersonaApellidoMaterno.getText());
        p.setApellidoPaterno(txtPersonaApellidoPaterno.getText());
        p.setDomicilio(txtaPersonaDomicilio.getText());
        p.setGenero(cmbPersonaGenero.getSelectionModel().getSelectedItem());
        p.setNombre(txtPersonaNombre.getText());
        p.setRfc(txtPersonaRFC.getText());
        p.setTelefono(txtPersonaTelefono.getText());
        
        if (txtPersonaId.getText().trim().length() > 0)   
            p.setId(Integer.valueOf(txtPersonaId.getText()));
        
        //Llenamos los datos de seguridad
        u.setContrasenia(txtpUsuarioContrasenia.getText());
        u.setNombreUsuario(txtUsuarioNombre.getText());
        u.setRol(cmbUsuarioRol.getSelectionModel().getSelectedItem());
        
        if (txtUsuarioId.getText().trim().length() > 0)
            u.setId(Integer.valueOf(txtUsuarioId.getText()));
         //aquí termina la condición
         
        if(txtClienteId.getText().trim().length() > 0)
            c.setId(Integer.valueOf(txtClienteId.getText()));
        
        c.setNumeroUnico(txtClienteNumero.getText());
        c.setCorreo(txtClienteCorreo.getText());       
        c.setEstatus(chbClienteActivo.isSelected() ? 1 : 0);
        c.setUsuario(u);
        c.setPersona(p);
        //Una vez que llenamos todos los datos de un empleado,
        //realizamos la inserción o actualización del registro:
        try {
            if (c.getId() == 0) {
                cc.insert(c);
                txtClienteId.setText("" + c.getId());
                txtClienteNumero.setText(c.getNumeroUnico());
                txtPersonaId.setText("" + c.getPersona().getId());
                txtUsuarioId.setText("" + c.getUsuario().getId());
                tblClientes.getItems().add(c);
            }
            else
                cc.update(c);
            
            mostrarMensaje( "Movimiento realizado.", 
                            "Datos de cliente guardados correctamente.", 
                            Alert.AlertType.CONFIRMATION);
        } 
        catch (Exception ex) {
            ex.printStackTrace();
            mostrarMensaje( "Error al persistir datos.", 
                            "Ocurrió el siguiente error: " + ex.toString(),
                            Alert.AlertType.ERROR);
        }
    }
    
    /**
     *  Este método limpia todos los controles con datos de un empleado.
    */
    
    private void limpiarCampos() {
        txtPersonaApellidoMaterno.setText("");
        txtPersonaApellidoPaterno.setText("");
        txtPersonaId.setText("");
        txtPersonaNombre.setText("");
        txtPersonaRFC.setText("");
        txtPersonaTelefono.setText("");
        txtaPersonaDomicilio.setText("");
        cmbPersonaGenero.getSelectionModel().clearSelection();
        
        txtClienteId.setText("");
        txtClienteNumero.setText("");
        txtClienteCorreo.setText("");
        
        txtUsuarioId.setText("");
        txtUsuarioNombre.setText("");
        txtpUsuarioContrasenia.setText("");
        cmbUsuarioRol.getSelectionModel().clearSelection();                        
        chbClienteActivo.setSelected(true);
    }
    
    /**
     * Este método es para mostrar un mensaje modal.
     * @param titulo        El título de la ventana.
     * @param mensaje       El contenido del mensaje.
     * @param tipoMensaje   El tipo de mensaje.
     */
    
    
    
    private void eliminarCliente(){
      
        Cliente c = new Cliente();
        
        try{
            if(! txtClienteId.getText().isEmpty()){
                c.setId(Integer.valueOf(txtClienteId.getText()));
               
                
                cc.delete(c.getId());
                c = tblClientes.getSelectionModel().getSelectedItem();
                tblClientes.getItems().remove(c);
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


    private void buscarCliente(){
            Cliente c = new Cliente();
        ObservableList<Cliente> listaObservableProductos = null;
        
        try{
            int i = Integer.valueOf(txtFiltro.getText());
            c = cc.findById(i);
            tblClientes.getItems().clear();
            listaObservableProductos = FXCollections.observableArrayList(c);
            //Ponemos la lista observable dentro del table view
            tblClientes.setItems(listaObservableProductos);
            
        }catch(Exception x){
            x.printStackTrace();
            mostrarMensaje( "Error al buscar datos.", 
                            "Ocurrió el siguiente error: " + x.toString(),
                            Alert.AlertType.ERROR);
        }
    }
}
