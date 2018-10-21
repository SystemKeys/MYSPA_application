/*
 * Version:     1.0
 * Date:        18/06/2018 23:16:00
 * Author:      Jocelyn Vanessa Ortega Torres
 * Email:       ortegatorresjocelynvanessa@gmail.com
 * Comments:    Esta clase contiene los métodos necesarios para mantener el
 *              manejo de la interfaz visual.
 */
package org.solsistemas.myspa.desktop_app.gui;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class WindowMain extends Application{
    
    @FXML Button btnCargarModuloEmpleado;
    @FXML Button btnCargarModuloProducto;
    @FXML Button btnCargarModuloSucursal;
    @FXML Button btnCargarModuloSala;
    @FXML BorderPane pnlContenedorPrincipal;
    @FXML Button btnCargarModuloEmpleados;
    @FXML Button  btnCargarModuloCliente;
    @FXML Button btnCargarModuloReservacion;
    @FXML Button btnCargarModuloTratamiento;
    FXMLLoader fxmll;
    Scene scene;
    Stage window;
    PanelProducto panelProducto;
    PanelSucursal panelSucursal;
    PanelSala panelSala;
    PanelEmpleado panelEmpleado;
    PanelCliente panelCliente;
    PanelReservacion panelReservacion;
    PanelTratamiento panelTratamiento;
    
    public WindowMain(){
        fxmll = new FXMLLoader (System.class.getResource("/org/solsistemas/myspa/desktop_app/gui/fxml/window_main.fxml"));
        fxmll.setController(this);
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        fxmll.load();
        //Creamos una nueva escena utilizando como contenedor principal la raiz (El panel de mas alto nivel) de la GUI
        scene = new Scene(fxmll.getRoot());
     
        agregarOyentes();
        //Guardamos la ventana que nos pasa la JVM
        window = primaryStage;

        //Creamos los módulos de la aplicación
        panelProducto = new PanelProducto(primaryStage);
        panelSucursal = new PanelSucursal(primaryStage);
        panelSala = new PanelSala(primaryStage);
        panelEmpleado = new PanelEmpleado(primaryStage); 
        panelCliente = new PanelCliente (primaryStage);
        panelReservacion = new PanelReservacion (primaryStage);
        panelTratamiento = new PanelTratamiento (primaryStage);
        //Inicializamos los módulos que hemos creado
        try{
            panelProducto.inicializar();
            panelSucursal.inicializar();
            panelSala.inicializar();
            panelEmpleado.inicializar();
            panelCliente.inicializar();
            panelReservacion.inicializar();
            panelTratamiento.inicializar();
        }
        catch(Exception e){
            //Si ocurre un error, imprimimos la excepción y
            //nos salimos de la aplicación
            e.printStackTrace();
            System.exit(0);
        }
          
        //Establecemos las propiedades de la ventana       
        window.setScene(scene);
        window.setTitle("MySPA");
        
        //Mostramos la ventama
        window.show();
    }
    
    private void agregarOyentes(){
        //btnCargarModuloEmpleado.setOnAction(evt -> {/* */ });
        btnCargarModuloProducto.setOnAction(evt -> {cargarModulo(panelProducto.panelRaiz); });
        btnCargarModuloSucursal.setOnAction(evt -> {cargarModulo(panelSucursal.panelRaiz); });
        btnCargarModuloSala.setOnAction(evt -> {cargarModulo(panelSala.panelRaiz); });
        btnCargarModuloEmpleados.setOnAction(evt -> {cargarModulo(panelEmpleado.panelRaiz); });
        btnCargarModuloCliente.setOnAction(evt -> {cargarModulo(panelCliente.panelRaiz); });
        btnCargarModuloReservacion.setOnAction(evt -> {cargarModulo(panelReservacion.panelRaiz); });        
        btnCargarModuloTratamiento.setOnAction(evt -> {cargarModulo(panelTratamiento.panelRaiz); });
        
    }
    
    private void cargarModulo(Node n){
        pnlContenedorPrincipal.setCenter(n);
    }
}
