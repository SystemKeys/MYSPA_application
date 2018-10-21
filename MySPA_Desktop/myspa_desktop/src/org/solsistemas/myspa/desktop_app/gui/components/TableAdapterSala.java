/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solsistemas.myspa.desktop_app.gui.components;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.solsistemas.myspa.model.Sala;

/**
 *
 * @author lider
 */
public class TableAdapterSala {
     /**
     * En este método creamos y configuramos las columnas de la
     * tabla que mostrará datos de las sucursales objetos de 
     * tipo {@link org.solsistema.myspa.model.Sala}.
     * @param tbl 
     */
    public static void adapt(TableView<Sala> tbl){
        
        //Creamos las columnas y estbalecemos el texto de cabecera que mostrará:
        TableColumn<Sala, Integer> tcSalaId = new TableColumn<>("Cve. Sala");
        TableColumn<Sala, String>  tcSalaNombre = new TableColumn<>("Nombre");
        TableColumn<Sala, String>  tcSalaDescripcion = new TableColumn<>("Descripcion");
        TableColumn<Sala, String>  tcSalaFoto = new TableColumn<>("Foto");
        TableColumn<Sala, String>  tcSalaRutaFoto = new TableColumn<>("Ruta Foto");
        TableColumn<Sala, Integer> tcSalaStatus = new TableColumn<>("Estatus");
        TableColumn<Sala, Integer> tcSalaSucursal = new TableColumn<>("Sucursal");
        
        //Ahora, le diremos a cada columna como deberá mostrarse y cual será
        //el atributo que mostrará de un objeto de tipo Sala, a través de
        //un CellFactory y un CallBack.
        
        //Para propiedades directas del objeto podemos utilizar:
        tcSalaId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tcSalaNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        tcSalaDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        tcSalaFoto.setCellValueFactory(new PropertyValueFactory<>("foto"));
        tcSalaRutaFoto.setCellValueFactory(new PropertyValueFactory<>("rutaFoto"));
        tcSalaSucursal.setCellValueFactory(new PropertyValueFactory<>("sucursal"));
        tcSalaStatus.setCellValueFactory(new PropertyValueFactory<>("estatus"));
        
        //Una vez configuradas las columnas, las pondremos en la tabla,
        //pero antes, quítaremos cualquier columna que pudiera tener:
        tbl.getColumns().clear();
        
        //Agregamos las columnas a la tabla, en el orden
        //que deseamos que aparezcan:
        tbl.getColumns().addAll(tcSalaId, tcSalaNombre, tcSalaDescripcion, tcSalaFoto, tcSalaRutaFoto, tcSalaSucursal, tcSalaStatus);
    }
}
