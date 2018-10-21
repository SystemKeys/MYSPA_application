/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solsistemas.myspa.desktop_app.gui.components;

import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import org.solsistemas.myspa.model.Cliente;

/**Cliente
 *
 * @author diegg_
 */
public class TableAdapterCliente {
    public static void adapt(TableView<Cliente> tbl)
    {
        //Creamos las columnas y establecemos el texto de cabecera que mostrarán:
        TableColumn<Cliente, Integer> tcClienteId = new TableColumn<>("Cve. Cliente");
        TableColumn<Cliente, Integer> tcPersonaId = new TableColumn<>("Cve. Persona");       
        TableColumn<Cliente, String> tcPersonaNombre= new TableColumn<>("Nombre");
        TableColumn<Cliente, String> tcPersonaApellidoPaterno= new TableColumn<>("Apellido Paterno");
        TableColumn<Cliente, String> tcPersonaApellidoMaterno= new TableColumn<>("Apellido Materno");
        TableColumn<Cliente, String> tcPersonaGenero = new TableColumn<>("Género");
        TableColumn<Cliente, String> tcPersonaDomicilio = new TableColumn<>("Domicilio");
        TableColumn<Cliente, String> tcPersonaTelefono = new TableColumn<>("Teléfono");
        TableColumn<Cliente, String> tcPersonaRfc = new TableColumn<>("RFC");
        TableColumn<Cliente, Integer> tcUsuarioId = new TableColumn<>("Cve. Usuario");
        TableColumn<Cliente, String> tcUsuarioNombreUsuario = new TableColumn<>("Nombre de Usuario");
        TableColumn<Cliente, String> tcUsuarioContrasenia = new TableColumn<>("Contraseña");
        TableColumn<Cliente, String> tcUsuarioRol = new TableColumn<>("Rol");
        TableColumn<Cliente, String> tcClienteNumero = new TableColumn<>("Numero Único");
        TableColumn<Cliente, Integer> tcClienteEstatus = new TableColumn<>("Estatus");
        TableColumn<Cliente, String> tcClienteCorreo = new TableColumn<>("Email");



        
        //Ahora, le diremos a cada columna como deberá mostrarse y cual será
        //el atributo que mostrará de un objeto de tipo Cliente, a través de
        //un CellFactory y un CallBack.
        
        //Para propiedades directas, del objeto podemos utilizar:
        tcClienteId.setCellValueFactory(new PropertyValueFactory<Cliente, Integer>("id"));
        tcClienteEstatus.setCellValueFactory(new PropertyValueFactory<Cliente, Integer>("estatus"));
        tcClienteCorreo.setCellValueFactory(new PropertyValueFactory<Cliente, String>("correo"));
        tcClienteNumero.setCellValueFactory(new PropertyValueFactory<Cliente, String>("numeroUnico"));
        
        //Para propiedades anidadas, debemos utilizar:
        tcPersonaId.setCellFactory(new Callback<TableColumn<Cliente, Integer>, 
                                       TableCell<Cliente, Integer>>()
        {
            @Override
            public TableCell<Cliente, Integer> call(TableColumn<Cliente, 
                                                    Integer> param)
            {
                return new TableCell<Cliente, Integer>()
                {
                    @Override
                    protected void updateItem(Integer item, boolean empty) 
                    {
                        //Pedimos el indice del elemento que quiere mostrar
                        //el TableView:
                        int indice = getIndex();
                        
                        Cliente c = null;
                        
                        //Inicializamos la celda con el valor que nos pasa el
                        //TableView:
                        super.updateItem(item, empty);
                        
                        //Preguntamos si el indice (posicion) del objeto
                        //está dentro del tamaño de la lista:
                        if (indice >= 0 && indice < tbl.getItems().size())
                        {
                            //Obtenemos el empleado de la posición requerida:
                            c = tbl.getItems().get(indice);
                            
                            //Establecemos el valor de la celda:
                            textProperty().set("" + c.getPersona().getId());                            
                        }
                        else
                            setText(null);
                    }
                };
            }
        });
        
        tcPersonaNombre.setCellFactory(new Callback<TableColumn<Cliente, String>, 
                                       TableCell<Cliente, String>>()
        {
            @Override
            public TableCell<Cliente, String> call(TableColumn<Cliente, 
                                                    String> param)
            {
                return new TableCell<Cliente, String>()
                {
                    @Override
                    protected void updateItem(String item, boolean empty) 
                    {
                        //Pedimos el indice del elemento que quiere mostrar
                        //el TableView:
                        int indice = getIndex();
                        
                        Cliente c = null;
                        
                        //Inicializamos la celda con el valor que nos pasa el
                        //TableView:
                        super.updateItem(item, empty);
                        
                        //Preguntamos si el indice (posicion) del objeto
                        //está dentro del tamaño de la lista:
                        if (indice >= 0 && indice < tbl.getItems().size())
                        {
                            //Obtenemos el empleado de la posición requerida:
                            c = tbl.getItems().get(indice);
                            
                            //Establecemos el valor de la celda:
                            textProperty().set(c.getPersona().getNombre());                            
                        }
                        else
                            setText(null);
                    }
                };
            }
        });
        
        tcPersonaApellidoPaterno.setCellFactory(new Callback<TableColumn<Cliente, String>, 
                                       TableCell<Cliente, String>>()
        {
            @Override
            public TableCell<Cliente, String> call(TableColumn<Cliente, 
                                                    String> param)
            {
                return new TableCell<Cliente, String>()
                {
                    @Override
                    protected void updateItem(String item, boolean empty) 
                    {
                        //Pedimos el indice del elemento que quiere mostrar
                        //el TableView:
                        int indice = getIndex();
                        
                        Cliente c = null;
                        
                        //Inicializamos la celda con el valor que nos pasa el
                        //TableView:
                        super.updateItem(item, empty);
                        
                        //Preguntamos si el indice (posicion) del objeto
                        //está dentro del tamaño de la lista:
                        if (indice >= 0 && indice < tbl.getItems().size())
                        {
                            //Obtenemos el empleado de la posición requerida:
                            c = tbl.getItems().get(indice);
                            
                            //Establecemos el valor de la celda:
                            textProperty().set("" + c.getPersona().getApellidoPaterno());                            
                        }
                        else
                            setText(null);
                    }
                };
            }
        });
        
        tcPersonaApellidoMaterno.setCellFactory(new Callback<TableColumn<Cliente, String>, 
                                       TableCell<Cliente, String>>()
        {
            @Override
            public TableCell<Cliente, String> call(TableColumn<Cliente, 
                                                    String> param)
            {
                return new TableCell<Cliente, String>()
                {
                    @Override
                    protected void updateItem(String item, boolean empty) 
                    {
                        //Pedimos el indice del elemento que quiere mostrar
                        //el TableView:
                        int indice = getIndex();
                        
                        Cliente c = null;
                        
                        //Inicializamos la celda con el valor que nos pasa el
                        //TableView:
                        super.updateItem(item, empty);
                        
                        //Preguntamos si el indice (posicion) del objeto
                        //está dentro del tamaño de la lista:
                        if (indice >= 0 && indice < tbl.getItems().size())
                        {
                            //Obtenemos el Cliente de la posición requerida:
                            c = tbl.getItems().get(indice);
                            
                            //Establecemos el valor de la celda:
                            textProperty().set("" + c.getPersona().getApellidoMaterno());                            
                        }
                        else
                            setText(null);
                    }
                };
            }
        });
        tcPersonaGenero.setCellFactory(new Callback<TableColumn<Cliente, String>, 
                                       TableCell<Cliente, String>>()
        {
            @Override
            public TableCell<Cliente, String> call(TableColumn<Cliente, 
                                                    String> param)
            {
                return new TableCell<Cliente, String>()
                {
                    @Override
                    protected void updateItem(String item, boolean empty) 
                    {
                        //Pedimos el indice del elemento que quiere mostrar
                        //el TableView:
                        int indice = getIndex();
                        
                        Cliente c = null;
                        
                        //Inicializamos la celda con el valor que nos pasa el
                        //TableView:
                        super.updateItem(item, empty);
                        
                        //Preguntamos si el indice (posicion) del objeto
                        //está dentro del tamaño de la lista:
                        if (indice >= 0 && indice < tbl.getItems().size())
                        {
                            //Obtenemos el empleado de la posición requerida:
                            c = tbl.getItems().get(indice);
                            
                            //Establecemos el valor de la celda:
                            textProperty().set("" + c.getPersona().getGenero());                            
                        }
                        else
                            setText(null);
                    }
                };
            }
        });
        tcPersonaDomicilio.setCellFactory(new Callback<TableColumn<Cliente, String>, 
                                       TableCell<Cliente, String>>()
        {
            @Override
            public TableCell<Cliente, String> call(TableColumn<Cliente, 
                                                    String> param)
            {
                return new TableCell<Cliente, String>()
                {
                    @Override
                    protected void updateItem(String item, boolean empty) 
                    {
                        //Pedimos el indice del elemento que quiere mostrar
                        //el TableView:
                        int indice = getIndex();
                        
                        Cliente c = null;
                        
                        //Inicializamos la celda con el valor que nos pasa el
                        //TableView:
                        super.updateItem(item, empty);
                        
                        //Preguntamos si el indice (posicion) del objeto
                        //está dentro del tamaño de la lista:
                        if (indice >= 0 && indice < tbl.getItems().size())
                        {
                            //Obtenemos el empleado de la posición requerida:
                            c = tbl.getItems().get(indice);
                            
                            //Establecemos el valor de la celda:
                            textProperty().set("" + c.getPersona().getDomicilio());                            
                        }
                        else
                            setText(null);
                    }
                };
            }
        });
        tcPersonaTelefono.setCellFactory(new Callback<TableColumn<Cliente, String>, 
                                       TableCell<Cliente, String>>()
        {
            @Override
            public TableCell<Cliente, String> call(TableColumn<Cliente, 
                                                    String> param)
            {
                return new TableCell<Cliente, String>()
                {
                    @Override
                    protected void updateItem(String item, boolean empty) 
                    {
                        //Pedimos el indice del elemento que quiere mostrar
                        //el TableView:
                        int indice = getIndex();
                        
                        Cliente c = null;
                        
                        //Inicializamos la celda con el valor que nos pasa el
                        //TableView:
                        super.updateItem(item, empty);
                        
                        //Preguntamos si el indice (posicion) del objeto
                        //está dentro del tamaño de la lista:
                        if (indice >= 0 && indice < tbl.getItems().size())
                        {
                            //Obtenemos el empleado de la posición requerida:
                            c = tbl.getItems().get(indice);
                            
                            //Establecemos el valor de la celda:
                            textProperty().set("" + c.getPersona().getTelefono());                            
                        }
                        else
                            setText(null);
                    }
                };
            }
        });
        tcPersonaRfc.setCellFactory(new Callback<TableColumn<Cliente, String>, 
                                       TableCell<Cliente, String>>()
        {
            @Override
            public TableCell<Cliente, String> call(TableColumn<Cliente, 
                                                    String> param)
            {
                return new TableCell<Cliente, String>()
                {
                    @Override
                    protected void updateItem(String item, boolean empty) 
                    {
                        //Pedimos el indice del elemento que quiere mostrar
                        //el TableView:
                        int indice = getIndex();
                        
                        Cliente c = null;
                        
                        //Inicializamos la celda con el valor que nos pasa el
                        //TableView:
                        super.updateItem(item, empty);
                        
                        //Preguntamos si el indice (posicion) del objeto
                        //está dentro del tamaño de la lista:
                        if (indice >= 0 && indice < tbl.getItems().size())
                        {
                            //Obtenemos el empleado de la posición requerida:
                            c = tbl.getItems().get(indice);
                            
                            //Establecemos el valor de la celda:
                            textProperty().set("" + c.getPersona().getRfc());                            
                        }
                        else
                            setText(null);
                    }
                };
            }
        });
        //DATOS USUARIO
        tcUsuarioId.setCellFactory(new Callback<TableColumn<Cliente, Integer>, 
                                       TableCell<Cliente, Integer>>()
        {
            @Override
            public TableCell<Cliente, Integer> call(TableColumn<Cliente, 
                                                    Integer> param)
            {
                return new TableCell<Cliente, Integer>()
                {
                    @Override
                    protected void updateItem(Integer item, boolean empty) 
                    {
                        //Pedimos el indice del elemento que quiere mostrar
                        //el TableView:
                        int indice = getIndex();
                        
                        Cliente c = null;
                        
                        //Inicializamos la celda con el valor que nos pasa el
                        //TableView:
                        super.updateItem(item, empty);
                        
                        //Preguntamos si el indice (posicion) del objeto
                        //está dentro del tamaño de la lista:
                        if (indice >= 0 && indice < tbl.getItems().size())
                        {
                            //Obtenemos el empleado de la posición requerida:
                            c = tbl.getItems().get(indice);
                            
                            //Establecemos el valor de la celda:
                            textProperty().set("" + c.getUsuario().getId());                            
                        }
                        else
                            setText(null);
                    }
                };
            }
        });
        tcUsuarioNombreUsuario.setCellFactory(new Callback<TableColumn<Cliente, String>, 
                                       TableCell<Cliente, String>>()
        {
            @Override
            public TableCell<Cliente, String> call(TableColumn<Cliente, 
                                                    String> param)
            {
                return new TableCell<Cliente, String>()
                {
                    @Override
                    protected void updateItem(String item, boolean empty) 
                    {
                        //Pedimos el indice del elemento que quiere mostrar
                        //el TableView:
                        int indice = getIndex();
                        
                        Cliente c = null;
                        
                        //Inicializamos la celda con el valor que nos pasa el
                        //TableView:
                        super.updateItem(item, empty);
                        
                        //Preguntamos si el indice (posicion) del objeto
                        //está dentro del tamaño de la lista:
                        if (indice >= 0 && indice < tbl.getItems().size())
                        {
                            //Obtenemos el empleado de la posición requerida:
                            c = tbl.getItems().get(indice);
                            
                            //Establecemos el valor de la celda:
                            textProperty().set("" + c.getUsuario().getNombreUsuario());                            
                        }
                        else
                            setText(null);
                    }
                };
            }
        });
        tcUsuarioContrasenia.setCellFactory(new Callback<TableColumn<Cliente, String>, 
                                       TableCell<Cliente, String>>()
        {
            @Override
            public TableCell<Cliente, String> call(TableColumn<Cliente, 
                                                    String> param)
            {
                return new TableCell<Cliente, String>()
                {
                    @Override
                    protected void updateItem(String item, boolean empty) 
                    {
                        //Pedimos el indice del elemento que quiere mostrar
                        //el TableView:
                        int indice = getIndex();
                        
                        Cliente c = null;
                        
                        //Inicializamos la celda con el valor que nos pasa el
                        //TableView:
                        super.updateItem(item, empty);
                        
                        //Preguntamos si el indice (posicion) del objeto
                        //está dentro del tamaño de la lista:
                        if (indice >= 0 && indice < tbl.getItems().size())
                        {
                            //Obtenemos el empleado de la posición requerida:
                            c = tbl.getItems().get(indice);
                            
                            //Establecemos el valor de la celda:
                            textProperty().set("" + c.getUsuario().getContrasenia());                            
                        }
                        else
                            setText(null);
                    }
                };
            }
        });
                tcUsuarioRol.setCellFactory(new Callback<TableColumn<Cliente, String>, 
                                       TableCell<Cliente, String>>()
        {
            @Override
            public TableCell<Cliente, String> call(TableColumn<Cliente, 
                                                    String> param)
            {
                return new TableCell<Cliente, String>()
                {
                    @Override
                    protected void updateItem(String item, boolean empty) 
                    {
                        //Pedimos el indice del elemento que quiere mostrar
                        //el TableView:
                        int indice = getIndex();
                        
                        Cliente c = null;
                        
                        //Inicializamos la celda con el valor que nos pasa el
                        //TableView:
                        super.updateItem(item, empty);
                        
                        //Preguntamos si el indice (posicion) del objeto
                        //está dentro del tamaño de la lista:
                        if (indice >= 0 && indice < tbl.getItems().size())
                        {
                            //Obtenemos el empleado de la posición requerida:
                            c = tbl.getItems().get(indice);
                            
                            //Establecemos el valor de la celda:
                            textProperty().set("" + c.getUsuario().getRol());                            
                        }
                        else
                            setText(null);
                    }
                };
            }
        });
        
        //Una vez configuradas las columnas, las pondremos en la tabla,
        //pero antes, quítaremos cualquier columna que pudiera tener:
        tbl.getColumns().clear();
        
        //Agregamos las columnas a la tabla, en el orden 
        //que deseamos que aparezcan:
        tbl.getColumns().addAll(tcClienteId, 
                                tcPersonaId, 
                                tcPersonaNombre,
                                tcPersonaApellidoPaterno, 
                                tcPersonaApellidoMaterno,
                                tcPersonaGenero,
                                tcPersonaDomicilio,
                                tcPersonaTelefono,
                                tcPersonaRfc,
                                tcUsuarioNombreUsuario,
                                //tcUsuarioContrasenia,
                                tcUsuarioRol,
                                tcClienteNumero,
                                tcClienteCorreo,
                                tcClienteEstatus
        );
    }
}


