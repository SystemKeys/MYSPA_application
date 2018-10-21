/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solsistemas.myspa.desktop_app.gui.components;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.solsistemas.myspa.model.Sucursal;

/**
 *
 * @author lider
 */
public class TableAdapterSucursal {
    /**
     * En este método creamos y configuramos las columnas de la
     * tabla que mostrará datos de las sucursales objetos de 
     * tipo {@link org.solsistema.myspa.model.Sucursal}.
     * @param tbl 
     */
    public static void adapt(TableView<Sucursal> tbl){
        
        //Creamos las columnas y estbalecemos el texto de cabecera que mostrará:
        TableColumn<Sucursal, Integer> tcSucursalId = new TableColumn<>("Cve. Sucursal");
        TableColumn<Sucursal, String>  tcSucursalNombre = new TableColumn<>("Nombre");
        TableColumn<Sucursal, String>  tcSucursalDomicilio = new TableColumn<>("Domicilio");
        TableColumn<Sucursal, Double>  tcSucursalLongitud = new TableColumn<>("Longitud");
        TableColumn<Sucursal, Double>  tcSucursalLatitud = new TableColumn<>("Latitud");
        TableColumn<Sucursal, Integer> tcSucursalStatus = new TableColumn<>("Estatus");
        
        //Ahora, le diremos a cada columna como deberá mostrarse y cual será
        //el atributo que mostrará de un objeto de tipo Producto, a través de
        //un CellFactory y un CallBack.
        
        //Para propiedades directas del objeto podemos utilizar:
        tcSucursalId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tcSucursalNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        tcSucursalDomicilio.setCellValueFactory(new PropertyValueFactory<>("domicilio"));
        tcSucursalLongitud.setCellValueFactory(new PropertyValueFactory<>("longitud"));
        tcSucursalLatitud.setCellValueFactory(new PropertyValueFactory<>("latitud"));
        tcSucursalStatus.setCellValueFactory(new PropertyValueFactory<>("estatus"));
        
        //Una vez configuradas las columnas, las pondremos en la tabla,
        //pero antes, quítaremos cualquier columna que pudiera tener:
        tbl.getColumns().clear();
        
        //Agregamos las columnas a la tabla, en el orden
        //que deseamos que aparezcan:
        tbl.getColumns().addAll(tcSucursalId, tcSucursalNombre, tcSucursalDomicilio, tcSucursalLongitud, tcSucursalLatitud, tcSucursalStatus);
    }
}
