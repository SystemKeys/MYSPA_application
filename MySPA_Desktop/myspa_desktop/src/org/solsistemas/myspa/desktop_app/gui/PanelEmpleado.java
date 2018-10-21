/*
 *  Version:        1.0
 *  Date:           04/05/2018 14:27:00
 *  Author:         Miguel Angel Gil Rios
 *  Email:          angel.grios@gmail.com
 *  Comments:       Esta clase provee de funcionalidad al panel que
                    permite llevar el control de los empleados.
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.solsistemas.myspa.controller.ControllerEmpleado;
import org.solsistemas.myspa.desktop_app.gui.components.TableAdapterEmpleado;
import org.solsistemas.myspa.model.Empleado;
import org.solsistemas.myspa.model.Persona;
import org.solsistemas.myspa.model.Usuario;

/**
 *  
 * @author LiveGrios
 */
public class PanelEmpleado
{
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
    @FXML ImageView imgvPersonaFotografia;
    @FXML ComboBox<String> cmbPersonaGenero;
    
    //Controles de datos de Empleado:
    @FXML TextField txtEmpleadoId;
    @FXML TextField txtEmpleadoNumero;
    @FXML TextField txtEmpleadoPuesto;
    @FXML CheckBox  chbEmpleadoActivo;
      
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
    
    //Tabla de Empleados:
    @FXML TableView<Empleado> tblEmpleados;
    
    //Un FileChooser para que el usuario seleccione la fotografía del Empleado:
    FileChooser fchFotografia;
    
    //El objeto con el que cargaremos el archivo FXML:
    FXMLLoader fxmll;
    
    //El objeto que nos ayudará a consultar y persistir datos de empleados
    //en la BD:
    ControllerEmpleado ce;
    
    public PanelEmpleado(Stage ventanaApp){
        window = ventanaApp;
        
        //Le indicamos al FXLLoader donde está el recurso que cargará:
        fxmll = new FXMLLoader(System.class.getResource("/org/solsistemas/myspa/desktop_app/gui/fxml/panel_empleado.fxml"));
        
        //Le indicamos al FXMLLoader que esta clase será su clase controladora:
        fxmll.setController(this);
    }
    
    /**
     * Devuelve el panel raiz de este componente.
     * 
     * El panel raiz es el componente visual que contiene todos los controles
     * visuales que se requieren para el módulo de control de empleados.
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
        ce = new ControllerEmpleado();
        
        //Creamos el FileChooser:
        fchFotografia = new FileChooser();
        fchFotografia.setTitle("Seleccione la fotografía del Empleado...");        
        
        //Agregamos los oyentes de los controles:
        agregarOyentes();
        
        actualizarTabla();
    }
    
    /**
     * En este método se programan los eventos de los diferentes controles
     * del panel de Empleados.
     */
    private void agregarOyentes() {
        //Agregamos el oyente a los botones:
        btnCargarFotografia.setOnAction(evt -> { cargarFotografiaEmpleado(); });
        btnConsultar.setOnAction(evt -> { actualizarTabla(); });
        btnGuardar.setOnAction(evt -> { guardarEmpleado(); });
        btnLimpiar.setOnAction(evt -> { limpiarCampos(); });
        btnEliminar.setOnAction(evt -> { eliminarEmpleado(); });
        btnFiltro.setOnAction(evt -> { buscarEmpleado(); });
        //Agregamos un oyente de selección a la tabla de empleados.
        //Este oyente se disparará cuando el usuario seleccione un renglón:
        tblEmpleados.getSelectionModel()
                    .selectedItemProperty()
                    .addListener((obs, anterior, nueva) -> { mostrarDetallesEmpleado(); });
    }
    
    /**
     * Este método consulta los empleados en la BD a través del controlador 
     * y actualiza la tabla con la lista de objetos devuelta.
     * 
     * @throws Exception    Lanzará cualquier excepción que ocurra.
     */
    public void actualizarTabla() {
        //Declaramos una lista dinámica de Empleados:
        List<Empleado> listaEmpleados = null;        
        //Declaramos una lista observable dinámica:
        ObservableList<Empleado> listaObserbableEmpleados = null;
        
        try
        {
            //Le pedimos al controlador los empleados:
            if(chbInactivo.isSelected()){
             listaEmpleados = ce.getAll("",0);
            }else{
             listaEmpleados = ce.getAll("",1);
            }
           

            //Con la lista de empleados que ya tenemos, creamos una lista observable:
            listaObserbableEmpleados = FXCollections.observableArrayList(listaEmpleados);

            //Ponemos la lista obserbable dentro de la tabla:
            tblEmpleados.setItems(listaObserbableEmpleados);
        } 
        catch (Exception e)
        {
            e.printStackTrace();
            mostrarMensaje( "Error", 
                            "Ocurrio el siguiente error: " + e.toString(), 
                            Alert.AlertType.ERROR);
        }
        
        //Adaptamos la tabla para que tenga las columnas configuradas:
        TableAdapterEmpleado.adapt(tblEmpleados);
    }
    
    /**
     * Este método despliega los detalles de un empleado que haya sido 
     * seleccionado por el usuario en la tabla de empleados.
     */
    private void mostrarDetallesEmpleado() {
        Empleado e = null;
        
        //Preguntamos si hay un empleado seleccionado:
        if (tblEmpleados.getSelectionModel().getSelectedItem() != null)
        {
            //Obtenemos el objeto Empleado seleccionado:
            e = tblEmpleados.getSelectionModel().getSelectedItem();
            
            //Llenamos los controles que despliegan datos personales:
            txtPersonaApellidoMaterno.setText(e.getPersona().getApellidoMaterno());
            txtPersonaApellidoPaterno.setText(e.getPersona().getApellidoPaterno());
            txtPersonaId.setText("" + e.getPersona().getId());
            txtPersonaNombre.setText(e.getPersona().getNombre());
            txtPersonaRFC.setText(e.getPersona().getRfc());
            txtPersonaTelefono.setText(e.getPersona().getTelefono());
            txtaPersonaDomicilio.setText(e.getPersona().getDomicilio());
            cmbPersonaGenero.getSelectionModel().select(e.getPersona().getGenero());
            
            //Llenamos los controles que despliegan datos del empleado:
            txtEmpleadoId.setText("" + e.getId());
            txtEmpleadoNumero.setText(e.getNumeroEmpleado());
            txtEmpleadoPuesto.setText(e.getPuesto());
            if(e.getStatus() == 1){
                chbEmpleadoActivo.setSelected(true);
            }else{
                chbEmpleadoActivo.setSelected(false);
            }
            
            //Llenamos los datos de seguridad:
            txtUsuarioId.setText("" + e.getUsuario().getId());
            txtUsuarioNombre.setText(e.getUsuario().getNombreUsuario());
            txtpUsuarioContrasenia.setText(e.getUsuario().getContrasenia());
            cmbUsuarioRol.getSelectionModel().select(e.getUsuario().getRol());                       
        }
    }
    
    /**
     * Este método muestra un dialogo para cargar la fotografía del empleado
     * y depslegarla en el ImageView correspondiente.
     */
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
                imgvPersonaFotografia.setImage(img);
            }
        } 
        catch (Exception e)
        {
            e.printStackTrace();
            mostrarMensaje("Error al cargar fotografía.", e.toString(), Alert.AlertType.ERROR);
        }
    }
    
    /**
     * Este método contiene las instrucciones necesarias para insertar o 
     * actualizar un registro de empleado.
     */
    private void guardarEmpleado(){
        //Creamos un nuevo objeto de tipo Persona:
        Persona p = new Persona();
        //Creamos un nuevo objeto de tipo Usuario:
        Usuario u = new Usuario();
        //Creamos un nuevo objeto de tipo Empleado:
        Empleado e = new Empleado();
        
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
        
        if(txtEmpleadoId.getText().trim().length() > 0)
            e.setId(Integer.valueOf(txtEmpleadoId.getText()));
        
        e.setNumeroEmpleado(txtEmpleadoNumero.getText());
        e.setPuesto(txtEmpleadoPuesto.getText());
        e.setFoto("");
        e.setRutaFoto("");
        //e.setStatus(chbEmpleadoActivo.isSelected() ? 1 : 0);
        if(chbEmpleadoActivo.isSelected())
            e.setStatus(1);
        else
            e.setStatus(0);
        
        e.setUsuario(u);
        e.setPersona(p);
        //Una vez que llenamos todos los datos de un empleado,
        //realizamos la inserción o actualización del registro:
        try {
            if (e.getId() == 0) {
                ce.insert(e);
                txtEmpleadoId.setText("" + e.getId());
                txtEmpleadoNumero.setText(e.getNumeroEmpleado());
                txtPersonaId.setText("" + e.getPersona().getId());
                txtUsuarioId.setText("" + e.getUsuario().getId());
                tblEmpleados.getItems().add(e);
            }
            else
                ce.update(e);
            
            mostrarMensaje( "Movimiento realizado.", 
                            "Datos de empleado guardados correctamente.", 
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
    private void limpiarCampos(){
        txtPersonaApellidoMaterno.setText("");
        txtPersonaApellidoPaterno.setText("");
        txtPersonaId.setText("");
        txtPersonaNombre.setText("");
        txtPersonaRFC.setText("");
        txtPersonaTelefono.setText("");
        txtaPersonaDomicilio.setText("");
        cmbPersonaGenero.getSelectionModel().clearSelection();
        
        txtEmpleadoId.setText("");
        txtEmpleadoNumero.setText("");
        txtEmpleadoPuesto.setText("");
        
        txtUsuarioId.setText("");
        txtUsuarioNombre.setText("");
        txtpUsuarioContrasenia.setText("");
        cmbUsuarioRol.getSelectionModel().clearSelection();
        
        imgvPersonaFotografia.setImage(null);
        
        chbEmpleadoActivo.setSelected(true);
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
    
    private void eliminarEmpleado() {
        
        Empleado e = new Empleado();
        try{
            if(! txtEmpleadoId.getText().isEmpty()){
                e.setId(Integer.valueOf(txtEmpleadoId.getText()));
                
                ce.delete(e.getId());
                e = tblEmpleados.getSelectionModel().getSelectedItem();
                tblEmpleados.getItems().remove(e);
                limpiarCampos();
                //tblProductos.getItems().remove(p.getId());
                mostrarMensaje( "Movimiento realizado.", 
                            "Datos de empleado eliminados correctamente.", 
                            Alert.AlertType.CONFIRMATION);
            }
        }catch(Exception x){
            x.printStackTrace();
            mostrarMensaje( "Error al eliminar datos.", 
                            "Ocurrió el siguiente error: " + x.toString(),
                            Alert.AlertType.ERROR);
        }
    }

    private void buscarEmpleado(){
        Empleado e = new Empleado();
        ObservableList<Empleado> listaObservableProductos = null;
        
        try {
            int i = Integer.valueOf(txtFiltro.getText());
            e = ce.findById(i);
            tblEmpleados.getItems().clear();
            listaObservableProductos = FXCollections.observableArrayList(e);
            //Ponemos la lista observable dentro del table view
            tblEmpleados.setItems(listaObservableProductos);
            
        }catch(Exception x) {
            x.printStackTrace();
            mostrarMensaje( "Error al buscar datos.", 
                            "Ocurrió el siguiente error: " + x.toString(),
                            Alert.AlertType.ERROR);
        }
}
}