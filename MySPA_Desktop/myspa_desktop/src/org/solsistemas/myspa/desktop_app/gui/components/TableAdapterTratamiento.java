/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solsistemas.myspa.desktop_app.gui.components;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.solsistemas.myspa.model.Tratamiento;

/**
 *
 * @author ramir
 */
public class TableAdapterTratamiento {
     public static void adapt(TableView<Tratamiento> tbl)
    {
      //Creamos las columnas y establecemos la cabezera que mostrarán.
        TableColumn<Tratamiento,Integer> tcTratamientoId=new TableColumn<>("Cve. Tratamiento");
        
          //Creamos las columnas y establecemos la cabezera que mostrarán¡.
        TableColumn<Tratamiento,String> tcTratamientoNombre=new TableColumn<>("Nombre");
        
          //Creamos las columnas y establecemos la cabezera que mostrarán¡.
        TableColumn<Tratamiento,String> tcTratamientoDescripcion=new TableColumn<>("Descripcion");
        
          //Creamos las columnas y establecemos la cabezera que mostrarán¡.
        TableColumn<Tratamiento,Integer> tcTratamientoStatus=new TableColumn<>("Estatus");
        
        
        
        
        //Ahora le diremos, a cada columna como debera mostrarse y cual serán
        // el atributo que se mostrarán de un objeto de tipo Producto, a traves
        // de un cell factory y CallBack.
        
            tcTratamientoId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tcTratamientoNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
         tcTratamientoDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        tcTratamientoStatus.setCellValueFactory(new PropertyValueFactory<>("estatus"));
     
        
        //Una vez configuradas las columnas, las pondremos en la tabla,
        //pero antes, quítaremos cualquier columna que tenga la tabla.
        
        tbl.getColumns().clear();
        
        //Agregamos las columnas en el orden que deseamos que aparezcan.
        tbl.getColumns().addAll(
                                tcTratamientoId,
                                tcTratamientoNombre, 
                                tcTratamientoStatus,  
                                tcTratamientoDescripcion);       
        
    }
}

