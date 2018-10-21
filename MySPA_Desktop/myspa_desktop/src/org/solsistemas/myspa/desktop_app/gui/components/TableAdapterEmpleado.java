/*
 *  Version:        1.0
 *  Date:           04/05/2018 21:34:00
 *  Author:         Miguel Angel Gil Rios
 *  Email:          angel.grios@gmail.com
 *  Comments:       Esta clase contiene los métodos necesarios para crear
                    y configurar las columnas de las tablas que requieran
                    desplegar datos de empleados.
 */
package org.solsistemas.myspa.desktop_app.gui.components;

import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import org.solsistemas.myspa.model.Empleado;

/**
 *  Esta clase contiene los métodos necesarios para definir y configurar
 *  las columnas que contendrán las tablas 
 *  (objetos de tipo <code>TableView</code>) que desplegarán datos de los 
 *  empleados (objetos de tipo {@link org.utl.myspa.model.Empleado}.
 *  @author LiveGrios
 */
public class TableAdapterEmpleado
{
    /**
     * En este método creamos y configuramos las columnas que tendrá la 
     * tabla que mostrará datos de empleados (objetos de tipo
     * {@link org.utl.myspa.model.Empleado}.
     * @param tbl 
     */
    public static void adapt(TableView<Empleado> tbl)
    {
        //Creamos las columnas y establecemos el texto de cabecera que mostrarán:
        TableColumn<Empleado, Integer> tcEmpleadoId = new TableColumn<>("Cve. Empleado");
        TableColumn<Empleado, Integer> tcPersonaId = new TableColumn<>("Cve. Persona");         
        TableColumn<Empleado, String> tcPersonaNombre= new TableColumn<>("Nombre");
        TableColumn<Empleado, String> tcPersonaApellidoPaterno= new TableColumn<>("Apellido Paterno");
        TableColumn<Empleado, String> tcPersonaApellidoMaterno= new TableColumn<>("Apellido Materno");
        TableColumn<Empleado, String> tcPersonaGenero = new TableColumn<>("Género");
        TableColumn<Empleado, String> tcPersonaDomicilio = new TableColumn<>("Domicilio");
        TableColumn<Empleado, String> tcPersonaTelefono = new TableColumn<>("Teléfono");
        TableColumn<Empleado, String> tcPersonaRfc = new TableColumn<>("RFC");
        TableColumn<Empleado, Integer> tcUsuarioId = new TableColumn<>("Cve. Usuario");
        TableColumn<Empleado, String> tcUsuarioNombreUsuario = new TableColumn<>("Nombre de Usuario");
       // TableColumn<Empleado, String> tcUsuarioContrasenia = new TableColumn<>("Contraseña");
        TableColumn<Empleado, String> tcUsuarioRol = new TableColumn<>("Rol");
        TableColumn<Empleado, String> tcEmpleadoNumero = new TableColumn<>("Número Empleado");
        TableColumn<Empleado, String> tcEmpleadoPuesto = new TableColumn<>("Puesto");
        TableColumn<Empleado, Integer> tcEmpleadoEstatus = new TableColumn<>("Estatus");
          
        //Ahora, le diremos a cada columna como deberá mostrarse y cual será
        //el atributo que mostrará de un objeto de tipo Empleado, a través de
        //un CellFactory y un CallBack.
        
        //Para propiedades directas, del objeto podemos utilizar:
        //PROPIEDADES DE EMPLEADO
        tcEmpleadoId.setCellValueFactory(new PropertyValueFactory<Empleado, Integer>("id"));
        tcEmpleadoNumero.setCellValueFactory(new PropertyValueFactory<Empleado, String>("numeroEmpleado"));
        tcEmpleadoPuesto.setCellValueFactory(new PropertyValueFactory<Empleado, String>("puesto"));
        tcEmpleadoEstatus.setCellValueFactory(new PropertyValueFactory<Empleado, Integer>("status"));
        
        
        
        
        //Para propiedades anidadas, debemos utilizar:
        //DATOS HEREDADOS DE OTRAS TABLAS
        try{
        tcPersonaId.setCellFactory(new Callback<TableColumn<Empleado, Integer>, 
                                       TableCell<Empleado, Integer>>()
        {
            @Override
            public TableCell<Empleado, Integer> call(TableColumn<Empleado, 
                                                    Integer> param)
            {
                return new TableCell<Empleado, Integer>()
                {
                    //
                      
                    @Override
                    protected void updateItem(Integer item, boolean empty) 
                    {
                        //Pedimos el indice del elemento que quiere mostrar
                        //el TableView:
                        int indice = getIndex();
                        
                        Empleado e = null;
                        
                        //Inicializamos la celda con el valor que nos pasa el
                        //TableView:
                        super.updateItem(item, empty);
                        
                        //Preguntamos si el indice (posicion) del objeto
                        //está dentro del tamaño de la lista:
                        if (indice >= 0 && indice < tbl.getItems().size() ){
                        
                            //Obtenemos el empleado de la posición requerida:
                            e = tbl.getItems().get(indice);
                            
                            //Establecemos el valor de la celda:
                            
                            textProperty().set(" " + e.getPersona().getId());       
                            
                        }
                        else
                            setText(null);
                    }
                };
            }
        });
        
        tcPersonaNombre.setCellFactory(new Callback<TableColumn<Empleado, String>, 
                                       TableCell<Empleado, String>>()
        {
            @Override
            public TableCell<Empleado, String> call(TableColumn<Empleado, 
                                                    String> param)
            {
                return new TableCell<Empleado, String>()
                {
                    @Override
                    protected void updateItem(String item, boolean empty) 
                    {
                        //Pedimos el indice del elemento que quiere mostrar
                        //el TableView:
                        int indice = getIndex();
                        
                        Empleado e = null;
                        
                        //Inicializamos la celda con el valor que nos pasa el
                        //TableView:
                        super.updateItem(item, empty);
                        
                        //Preguntamos si el indice (posicion) del objeto
                        //está dentro del tamaño de la lista:
                        if (indice >= 0 && indice < tbl.getItems().size())
                        {
                            //Obtenemos el empleado de la posición requerida:
                            e = tbl.getItems().get(indice);
                            
                            //Establecemos el valor de la celda:
                             
                            textProperty().set(e.getPersona().getNombre());
                             
                        }
                        else
                            setText(null);
                    }
                };
            }
        });
        
        tcPersonaApellidoPaterno.setCellFactory(new Callback<TableColumn<Empleado, String>, 
                                       TableCell<Empleado, String>>()
        {
            @Override
            public TableCell<Empleado, String> call(TableColumn<Empleado, 
                                                    String> param)
            {
                return new TableCell<Empleado, String>()
                {
                    @Override
                    protected void updateItem(String item, boolean empty) 
                    {
                        //Pedimos el indice del elemento que quiere mostrar
                        //el TableView:
                        int indice = getIndex();
                        
                        Empleado e = null;
                        
                        //Inicializamos la celda con el valor que nos pasa el
                        //TableView:
                        super.updateItem(item, empty);
                        
                        //Preguntamos si el indice (posicion) del objeto
                        //está dentro del tamaño de la lista:
                        if (indice >= 0 && indice < tbl.getItems().size())
                        {
                            //Obtenemos el empleado de la posición requerida:
                            e = tbl.getItems().get(indice);
                             
                            //Establecemos el valor de la celda:
                            textProperty().set("" + e.getPersona().getApellidoPaterno()); 
                             
                        }
                        else
                            setText(null);
                    }
                };
            }
        });
        
        tcPersonaApellidoMaterno.setCellFactory(new Callback<TableColumn<Empleado, String>, 
                                       TableCell<Empleado, String>>()
        {
            @Override
            public TableCell<Empleado, String> call(TableColumn<Empleado, 
                                                    String> param)
            {
                return new TableCell<Empleado, String>()
                {
                    @Override
                    protected void updateItem(String item, boolean empty) 
                    {
                        //Pedimos el indice del elemento que quiere mostrar
                        //el TableView:
                        int indice = getIndex();
                        
                        Empleado e = null;
                        
                        //Inicializamos la celda con el valor que nos pasa el
                        //TableView:
                        super.updateItem(item, empty);
                        
                        //Preguntamos si el indice (posicion) del objeto
                        //está dentro del tamaño de la lista:
                        if (indice >= 0 && indice < tbl.getItems().size())
                        {
                            //Obtenemos el empleado de la posición requerida:
                            e = tbl.getItems().get(indice);
                             
                            //Establecemos el valor de la celda:
                            textProperty().set("" + e.getPersona().getApellidoMaterno());      
                             
                        }
                        else
                            setText(null);
                    }
                };
            }
        });
        
        tcPersonaGenero.setCellFactory(new Callback<TableColumn<Empleado, String>, 
                                       TableCell<Empleado, String>>()
        {
            @Override
            public TableCell<Empleado, String> call(TableColumn<Empleado, 
                                                    String> param)
            {
                return new TableCell<Empleado, String>()
                {
                    @Override
                    protected void updateItem(String item, boolean empty) 
                    {
                        //Pedimos el indice del elemento que quiere mostrar
                        //el TableView:
                        int indice = getIndex();
                        
                        Empleado e = null;
                        
                        //Inicializamos la celda con el valor que nos pasa el
                        //TableView:
                        super.updateItem(item, empty);
                        
                        //Preguntamos si el indice (posicion) del objeto
                        //está dentro del tamaño de la lista:
                        if (indice >= 0 && indice < tbl.getItems().size())
                        {
                            //Obtenemos el empleado de la posición requerida:
                            e = tbl.getItems().get(indice);
                             
                            //Establecemos el valor de la celda:
                            textProperty().set("" + e.getPersona().getGenero());  
                             
                        }
                        else
                            setText(null);
                    }
                };
            }
        });
        tcPersonaDomicilio.setCellFactory(new Callback<TableColumn<Empleado, String>, 
                                       TableCell<Empleado, String>>()
        {
            @Override
            public TableCell<Empleado, String> call(TableColumn<Empleado, 
                                                    String> param)
            {
                return new TableCell<Empleado, String>()
                {
                    @Override
                    protected void updateItem(String item, boolean empty) 
                    {
                        //Pedimos el indice del elemento que quiere mostrar
                        //el TableView:
                        int indice = getIndex();
                        
                        Empleado e = null;
                        
                        //Inicializamos la celda con el valor que nos pasa el
                        //TableView:
                        super.updateItem(item, empty);
                        
                        //Preguntamos si el indice (posicion) del objeto
                        //está dentro del tamaño de la lista:
                        if (indice >= 0 && indice < tbl.getItems().size())
                        {
                            //Obtenemos el empleado de la posición requerida:
                            e = tbl.getItems().get(indice);
                             
                            //Establecemos el valor de la celda:
                            textProperty().set("" + e.getPersona().getDomicilio()); 
                             
                        }
                        else
                            setText(null);
                    }
                };
            }
        });
        tcPersonaTelefono.setCellFactory(new Callback<TableColumn<Empleado, String>, 
                                       TableCell<Empleado, String>>()
        {
            @Override
            public TableCell<Empleado, String> call(TableColumn<Empleado, 
                                                    String> param)
            {
                return new TableCell<Empleado, String>()
                {
                    @Override
                    protected void updateItem(String item, boolean empty) 
                    {
                        //Pedimos el indice del elemento que quiere mostrar
                        //el TableView:
                        int indice = getIndex();
                        
                        Empleado e = null;
                        
                        //Inicializamos la celda con el valor que nos pasa el
                        //TableView:
                        super.updateItem(item, empty);
                        
                        //Preguntamos si el indice (posicion) del objeto
                        //está dentro del tamaño de la lista:
                        if (indice >= 0 && indice < tbl.getItems().size())
                        {
                            //Obtenemos el empleado de la posición requerida:
                            e = tbl.getItems().get(indice);
                             
                            //Establecemos el valor de la celda:
                            textProperty().set("" + e.getPersona().getTelefono());                            
                             
                        }
                        else
                            setText(null);
                    }
                };
            }
        });
        tcPersonaRfc.setCellFactory(new Callback<TableColumn<Empleado, String>, 
                                       TableCell<Empleado, String>>()
        {
            @Override
            public TableCell<Empleado, String> call(TableColumn<Empleado, 
                                                    String> param)
            {
                return new TableCell<Empleado, String>()
                {
                    @Override
                    protected void updateItem(String item, boolean empty) 
                    {
                        //Pedimos el indice del elemento que quiere mostrar
                        //el TableView:
                        int indice = getIndex();
                        
                        Empleado e = null;
                        
                        //Inicializamos la celda con el valor que nos pasa el
                        //TableView:
                        super.updateItem(item, empty);
                        
                        //Preguntamos si el indice (posicion) del objeto
                        //está dentro del tamaño de la lista:
                        if (indice >= 0 && indice < tbl.getItems().size())
                        {
                            //Obtenemos el empleado de la posición requerida:
                            e = tbl.getItems().get(indice);
                             
                            //Establecemos el valor de la celda:
                            textProperty().set("" + e.getPersona().getRfc());                            
                             
                        }
                        else
                            setText(null);
                    }
                };
            }
        });
        //DATOS USUARIO
        tcUsuarioId.setCellFactory(new Callback<TableColumn<Empleado, Integer>, 
                                       TableCell<Empleado, Integer>>()
        {
            @Override
            public TableCell<Empleado, Integer> call(TableColumn<Empleado, 
                                                    Integer> param)
            {
                return new TableCell<Empleado, Integer>()
                {
                    @Override
                    protected void updateItem(Integer item, boolean empty) 
                    {
                        //Pedimos el indice del elemento que quiere mostrar
                        //el TableView:
                        int indice = getIndex();
                        
                        Empleado e = null;
                        
                        //Inicializamos la celda con el valor que nos pasa el
                        //TableView:
                        super.updateItem(item, empty);
                        
                        //Preguntamos si el indice (posicion) del objeto
                        //está dentro del tamaño de la lista:
                        if (indice >= 0 && indice < tbl.getItems().size())
                        {
                            //Obtenemos el empleado de la posición requerida:
                            e = tbl.getItems().get(indice);
                             
                            //Establecemos el valor de la celda:
                            textProperty().set("" + e.getUsuario().getId());
                                                         
                        }
                        else
                            setText(null);
                    }
                };
            }
        });
        tcUsuarioNombreUsuario.setCellFactory(new Callback<TableColumn<Empleado, String>, 
                                       TableCell<Empleado, String>>()
        {
            @Override
            public TableCell<Empleado, String> call(TableColumn<Empleado, 
                                                    String> param)
            {
                return new TableCell<Empleado, String>()
                {
                    @Override
                    protected void updateItem(String item, boolean empty) 
                    {
                        //Pedimos el indice del elemento que quiere mostrar
                        //el TableView:
                        int indice = getIndex();
                        
                        Empleado e = null;
                        
                        //Inicializamos la celda con el valor que nos pasa el
                        //TableView:
                        super.updateItem(item, empty);
                        
                        //Preguntamos si el indice (posicion) del objeto
                        //está dentro del tamaño de la lista:
                        if (indice >= 0 && indice < tbl.getItems().size())
                        {
                            //Obtenemos el empleado de la posición requerida:
                            e = tbl.getItems().get(indice);
                             
                            //Establecemos el valor de la celda:
                            textProperty().set("" + e.getUsuario().getNombreUsuario());     
                             
                        }
                        else
                            setText(null);
                    }  
                };
            }
        });
        
        }catch(Exception x){
            x.printStackTrace();
        }
    /*    tcUsuarioContrasenia.setCellFactory(new Callback<TableColumn<Empleado, String>, 
                                       TableCell<Empleado, String>>()
        {
            @Override
            public TableCell<Empleado, String> call(TableColumn<Empleado, 
                                                    String> param)
            {
                return new TableCell<Empleado, String>()
                {
                    @Override
                    protected void updateItem(String item, boolean empty) 
                    {
                        //Pedimos el indice del elemento que quiere mostrar
                        //el TableView:
                        int indice = getIndex();
                        
                        Empleado e = null;
                        
                        //Inicializamos la celda con el valor que nos pasa el
                        //TableView:
                        super.updateItem(item, empty);
                        
                        //Preguntamos si el indice (posicion) del objeto
                        //está dentro del tamaño de la lista:
                        if (indice >= 0 && indice < tbl.getItems().size())
                        {
                            //Obtenemos el empleado de la posición requerida:
                            e = tbl.getItems().get(indice);
                            
                            //Establecemos el valor de la celda:
                            textProperty().set("" + e.getUsuario().getContrasenia());                            
                        }
                        else
                            setText(null);
                    }
                };
            }
        });
                tcUsuarioRol.setCellFactory(new Callback<TableColumn<Empleado, String>, 
                                       TableCell<Empleado, String>>()
        {
            @Override
            public TableCell<Empleado, String> call(TableColumn<Empleado, 
                                                    String> param)
            {
                return new TableCell<Empleado, String>()
                {
                    @Override
                    protected void updateItem(String item, boolean empty) 
                    {
                        //Pedimos el indice del elemento que quiere mostrar
                        //el TableView:
                        int indice = getIndex();
                        
                        Empleado e = null;
                        
                        //Inicializamos la celda con el valor que nos pasa el
                        //TableView:
                        super.updateItem(item, empty);
                        
                        //Preguntamos si el indice (posicion) del objeto
                        //está dentro del tamaño de la lista:
                        if (indice >= 0 && indice < tbl.getItems().size())
                        {
                            //Obtenemos el empleado de la posición requerida:
                            e = tbl.getItems().get(indice);
                            
                            //Establecemos el valor de la celda:
                            textProperty().set("" + e.getUsuario().getRol());                            
                        }
                        else
                            setText(null);
                    }
                };
            }
        });*/
      
        //Una vez configuradas las columnas, las pondremos en la tabla,
        //pero antes, quítaremos cualquier columna que pudiera tener:
        tbl.getColumns().clear();
        
        //Agregamos las columnas a la tabla, en el orden 
        //que deseamos que aparezcan:
        tbl.getColumns().addAll(tcEmpleadoId, 
                                tcPersonaId, 
                                tcUsuarioId,
                                tcPersonaNombre,
                                tcPersonaApellidoPaterno, 
                                tcPersonaApellidoMaterno,
                                tcPersonaGenero,
                                tcPersonaDomicilio,
                                tcPersonaTelefono,
                                tcPersonaRfc,
                                tcUsuarioNombreUsuario,
                              //  tcUsuarioContrasenia,
                                tcUsuarioRol,
                                tcEmpleadoNumero,
                                tcEmpleadoPuesto,
                                tcEmpleadoEstatus
                                                 );
    }
}
