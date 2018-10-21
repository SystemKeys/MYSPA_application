/*
 * 
 */
package org.solsistemas.myspa.desktop_app.gui.components;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.solsistemas.myspa.model.Producto;

/**
 * Esta clase contiene los métodos necesarios para 
 * definir y configurar las columnas que contendrán 
 * las tablas (objetos de tipo <code>TableView</code>)
 * que desplegarán datos de los productos (objetos de 
 * tipo {@link org.solsistema.myspa.model.Producto}).
 * @author Vanessa
 */
public class TableAdapterProducto {
    
    /**
     * En este método creamos y configuramos las columnas de la
     * tabla que mostrará datos de los productos objetos de 
     * tipo {@link org.solsistema.myspa.model.Producto}.
     * @param tbl 
     */
    public static void adapt(TableView<Producto> tbl){
        
        //Creamos las columnas y estbalecemos el texto de cabecera que mostrará:
        TableColumn<Producto, Integer> tcProductoId = new TableColumn<>("Cve. Producto");
        TableColumn<Producto, String>  tcProductoNombre = new TableColumn<>("Nombre");
        TableColumn<Producto, String>  tcProductoMarca = new TableColumn<>("Marca");
        TableColumn<Producto, Integer> tcProductoStatus = new TableColumn<>("Estatus");
        TableColumn<Producto, Float>   tcProductoPrecioUso = new TableColumn<>("Precio de uso");
        
        //Ahora, le diremos a cada columna como deberá mostrarse y cual será
        //el atributo que mostrará de un objeto de tipo Producto, a través de
        //un CellFactory y un CallBack.
        
        //Para propiedades directas del objeto podemos utilizar:
        tcProductoId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tcProductoNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        tcProductoMarca.setCellValueFactory(new PropertyValueFactory<>("marca"));
        tcProductoStatus.setCellValueFactory(new PropertyValueFactory<>("estatus"));
        tcProductoPrecioUso.setCellValueFactory(new PropertyValueFactory<>("precioUso"));
        
        //Una vez configuradas las columnas, las pondremos en la tabla,
        //pero antes, quítaremos cualquier columna que pudiera tener:
        tbl.getColumns().clear();
        
        //Agregamos las columnas a la tabla, en el orden
        //que deseamos que aparezcan:
        tbl.getColumns().addAll(tcProductoId, tcProductoNombre, tcProductoMarca, tcProductoPrecioUso, tcProductoStatus);
    }
    
}
