package org.solsistemas.myspa.desktop_app.gui.components;

import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import org.solsistemas.myspa.model.Reservacion;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Vanessa
 */
public class TableAdapterReservacion {
  
    public static void adapt(TableView<Reservacion> tbl)
    {
        //Creamos las columnas y establecemos el texto de cabecera que mostrarán:
        
        TableColumn<Reservacion, Integer> tcPersonaId = new TableColumn<>("Cve. Persona");       
        TableColumn<Reservacion, String> tcPersonaNombre= new TableColumn<>("Nombre");
        TableColumn<Reservacion, String> tcPersonaApellidoPaterno= new TableColumn<>("Apellido Paterno");
        TableColumn<Reservacion, String> tcPersonaApellidoMaterno= new TableColumn<>("Apellido Materno");
        TableColumn<Reservacion, String> tcPersonaGenero = new TableColumn<>("Género");
        TableColumn<Reservacion, String> tcPersonaDomicilio = new TableColumn<>("Domicilio");
        TableColumn<Reservacion, String> tcPersonaTelefono = new TableColumn<>("Teléfono");
        TableColumn<Reservacion, String> tcPersonaRfc = new TableColumn<>("RFC");
        TableColumn<Reservacion, Integer> tcClienteId = new TableColumn<>("Cve. Cliente");
        TableColumn<Reservacion, String> tcClienteNumero = new TableColumn<>("Numero Único");          
        TableColumn<Reservacion, String> tcClienteCorreo = new TableColumn<>("Email");
        TableColumn<Reservacion, Integer> tcReservacionId = new TableColumn<>("Cve. Reservacion");
        TableColumn<Reservacion, String> tcReservacionFechaHoraInicio = new TableColumn<>("Fecha y Hora Inicio");
        TableColumn<Reservacion, String> tcReservacionFechaHoraFin = new TableColumn<>("Fecha y Hora Fin");
        TableColumn<Reservacion, Integer> tcReservacionEstatus = new TableColumn<>("Estatus de Reservación");
        TableColumn<Reservacion, Integer> tcSalaId = new TableColumn<>("Cve. Sala");
        TableColumn<Reservacion, String> tcSalaNombre = new TableColumn<>("Nombre Sala");
        TableColumn<Reservacion, String> tcSalaDescripcion = new TableColumn<>("Descripcion");
        TableColumn<Reservacion, Integer> tcSalaEstatus = new TableColumn<>("Estatus Sala");
        
        //Ahora, le diremos a cada columna como deberá mostrarse y cual será
        //el atributo que mostrará de un objeto de tipo Cliente, a través de
        //un CellFactory y un CallBack.
        
        //Para propiedades directas, del objeto podemos utilizar:
        
        tcReservacionFechaHoraInicio.setCellValueFactory(new PropertyValueFactory<Reservacion, String>("fechaHoraInicio"));
        tcReservacionFechaHoraFin.setCellValueFactory(new PropertyValueFactory<Reservacion, String>("fechaHoraFin"));
        tcReservacionEstatus.setCellValueFactory(new PropertyValueFactory<Reservacion, Integer>("estatus"));
        tcReservacionId.setCellValueFactory(new PropertyValueFactory<Reservacion, Integer>("id"));
        //Para propiedades anidadas, debemos utilizar:
        tcPersonaId.setCellFactory(new Callback<TableColumn<Reservacion, Integer>, 
                                       TableCell<Reservacion, Integer>>()
        {
            @Override
            public TableCell<Reservacion, Integer> call(TableColumn<Reservacion, 
                                                    Integer> param)
            {
                return new TableCell<Reservacion, Integer>()
                {
                    @Override
                    protected void updateItem(Integer item, boolean empty) 
                    {
                        //Pedimos el indice del elemento que quiere mostrar
                        //el TableView:
                        int indice = getIndex();
                        
                        Reservacion r = null;
                        
                        //Inicializamos la celda con el valor que nos pasa el
                        //TableView:
                        super.updateItem(item, empty);
                        
                        //Preguntamos si el indice (posicion) del objeto
                        //estÃ¡ dentro del tamaÃ±o de la lista:
                        if (indice >= 0 && indice < tbl.getItems().size())
                        {
                            //Obtenemos el empleado de la posiciÃ³n requerida:
                            r = tbl.getItems().get(indice);
                            
                            //Establecemos el valor de la celda:
                            textProperty().set("" + r.getCliente().getId());                            
                        }
                        else
                            setText(null);
                    }
                };
            }
        });
        
        tcPersonaNombre.setCellFactory(new Callback<TableColumn<Reservacion, String>, 
                                       TableCell<Reservacion, String>>()
        {
            @Override
            public TableCell<Reservacion, String> call(TableColumn<Reservacion, 
                                                    String> param)
            {
                return new TableCell<Reservacion, String>()
                {
                    @Override
                    protected void updateItem(String item, boolean empty) 
                    {
                        //Pedimos el indice del elemento que quiere mostrar
                        //el TableView:
                        int indice = getIndex();
                        
                        Reservacion r = null;
                        
                        //Inicializamos la celda con el valor que nos pasa el
                        //TableView:
                        super.updateItem(item, empty);
                        
                        //Preguntamos si el indice (posicion) del objeto
                        //estÃ¡ dentro del tamaÃ±o de la lista:
                        if (indice >= 0 && indice < tbl.getItems().size())
                        {
                            //Obtenemos el empleado de la posiciÃ³n requerida:
                            r = tbl.getItems().get(indice);
                            
                            //Establecemos el valor de la celda:
                            textProperty().set(r.getCliente().getPersona().getNombre());                            
                        }
                        else
                            setText(null);
                    }
                };
            }
        });
        
        tcPersonaApellidoPaterno.setCellFactory(new Callback<TableColumn<Reservacion, String>, 
                                       TableCell<Reservacion, String>>()
        {
            @Override
            public TableCell<Reservacion, String> call(TableColumn<Reservacion, 
                                                    String> param)
            {
                return new TableCell<Reservacion, String>()
                {
                    @Override
                    protected void updateItem(String item, boolean empty) 
                    {
                        //Pedimos el indice del elemento que quiere mostrar
                        //el TableView:
                        int indice = getIndex();
                        
                        Reservacion r = null;
                        
                        //Inicializamos la celda con el valor que nos pasa el
                        //TableView:
                        super.updateItem(item, empty);
                        
                        //Preguntamos si el indice (posicion) del objeto
                        //estÃ¡ dentro del tamaÃ±o de la lista:
                        if (indice >= 0 && indice < tbl.getItems().size())
                        {
                            //Obtenemos el empleado de la posiciÃ³n requerida:
                            r = tbl.getItems().get(indice);
                            
                            //Establecemos el valor de la celda:
                            textProperty().set("" + r.getCliente().getPersona().getApellidoPaterno());                            
                        }
                        else
                            setText(null);
                    }
                };
            }
        });
        
        tcPersonaApellidoMaterno.setCellFactory(new Callback<TableColumn<Reservacion, String>, 
                                       TableCell<Reservacion, String>>()
        {
            @Override
            public TableCell<Reservacion, String> call(TableColumn<Reservacion, 
                                                    String> param)
            {
                return new TableCell<Reservacion, String>()
                {
                    @Override
                    protected void updateItem(String item, boolean empty) 
                    {
                        //Pedimos el indice del elemento que quiere mostrar
                        //el TableView:
                        int indice = getIndex();
                        
                        Reservacion r = null;
                        
                        //Inicializamos la celda con el valor que nos pasa el
                        //TableView:
                        super.updateItem(item, empty);
                        
                        //Preguntamos si el indice (posicion) del objeto
                        //estÃ¡ dentro del tamaÃ±o de la lista:
                        if (indice >= 0 && indice < tbl.getItems().size())
                        {
                            //Obtenemos el Cliente de la posiciÃ³n requerida:
                            r = tbl.getItems().get(indice);
                            
                            //Establecemos el valor de la celda:
                            textProperty().set("" + r.getCliente().getPersona().getApellidoMaterno());                            
                        }
                        else
                            setText(null);
                    }
                };
            }
        });
        tcPersonaGenero.setCellFactory(new Callback<TableColumn<Reservacion, String>, 
                                       TableCell<Reservacion, String>>()
        {
            @Override
            public TableCell<Reservacion, String> call(TableColumn<Reservacion, 
                                                    String> param)
            {
                return new TableCell<Reservacion, String>()
                {
                    @Override
                    protected void updateItem(String item, boolean empty) 
                    {
                        //Pedimos el indice del elemento que quiere mostrar
                        //el TableView:
                        int indice = getIndex();
                        
                        Reservacion r = null;
                        
                        //Inicializamos la celda con el valor que nos pasa el
                        //TableView:
                        super.updateItem(item, empty);
                        
                        //Preguntamos si el indice (posicion) del objeto
                        //estÃ¡ dentro del tamaÃ±o de la lista:
                        if (indice >= 0 && indice < tbl.getItems().size())
                        {
                            //Obtenemos el empleado de la posiciÃ³n requerida:
                            r = tbl.getItems().get(indice);
                            
                            //Establecemos el valor de la celda:
                            textProperty().set("" + r.getCliente().getPersona().getGenero());                            
                        }
                        else
                            setText(null);
                    }
                };
            }
        });
        tcPersonaDomicilio.setCellFactory(new Callback<TableColumn<Reservacion, String>, 
                                       TableCell<Reservacion, String>>()
        {
            @Override
            public TableCell<Reservacion, String> call(TableColumn<Reservacion, 
                                                    String> param)
            {
                return new TableCell<Reservacion, String>()
                {
                    @Override
                    protected void updateItem(String item, boolean empty) 
                    {
                        //Pedimos el indice del elemento que quiere mostrar
                        //el TableView:
                        int indice = getIndex();
                        
                        Reservacion r = null;
                        
                        //Inicializamos la celda con el valor que nos pasa el
                        //TableView:
                        super.updateItem(item, empty);
                        
                        //Preguntamos si el indice (posicion) del objeto
                        //estÃ¡ dentro del tamaÃ±o de la lista:
                        if (indice >= 0 && indice < tbl.getItems().size())
                        {
                            //Obtenemos el empleado de la posiciÃ³n requerida:
                            r = tbl.getItems().get(indice);
                            
                            //Establecemos el valor de la celda:
                            textProperty().set("" + r.getCliente().getPersona().getDomicilio());                            
                        }
                        else
                            setText(null);
                    }
                };
            }
        });
        tcPersonaTelefono.setCellFactory(new Callback<TableColumn<Reservacion, String>, 
                                       TableCell<Reservacion, String>>()
        {
            @Override
            public TableCell<Reservacion, String> call(TableColumn<Reservacion, 
                                                    String> param)
            {
                return new TableCell<Reservacion, String>()
                {
                    @Override
                    protected void updateItem(String item, boolean empty) 
                    {
                        //Pedimos el indice del elemento que quiere mostrar
                        //el TableView:
                        int indice = getIndex();
                        
                        Reservacion r = null;
                        
                        //Inicializamos la celda con el valor que nos pasa el
                        //TableView:
                        super.updateItem(item, empty);
                        
                        //Preguntamos si el indice (posicion) del objeto
                        //estÃ¡ dentro del tamaÃ±o de la lista:
                        if (indice >= 0 && indice < tbl.getItems().size())
                        {
                            //Obtenemos el empleado de la posiciÃ³n requerida:
                            r = tbl.getItems().get(indice);
                            
                            //Establecemos el valor de la celda:
                            textProperty().set("" + r.getCliente().getPersona().getTelefono());                            
                        }
                        else
                            setText(null);
                    }
                };
            }
        });
        tcPersonaRfc.setCellFactory(new Callback<TableColumn<Reservacion, String>, 
                                       TableCell<Reservacion, String>>()
        {
            @Override
            public TableCell<Reservacion, String> call(TableColumn<Reservacion, 
                                                    String> param)
            {
                return new TableCell<Reservacion, String>()
                {
                    @Override
                    protected void updateItem(String item, boolean empty) 
                    {
                        //Pedimos el indice del elemento que quiere mostrar
                        //el TableView:
                        int indice = getIndex();
                        
                        Reservacion r = null;
                        
                        //Inicializamos la celda con el valor que nos pasa el
                        //TableView:
                        super.updateItem(item, empty);
                        
                        //Preguntamos si el indice (posicion) del objeto
                        //estÃ¡ dentro del tamaÃ±o de la lista:
                        if (indice >= 0 && indice < tbl.getItems().size())
                        {
                            //Obtenemos el empleado de la posiciÃ³n requerida:
                            r = tbl.getItems().get(indice);
                            
                            //Establecemos el valor de la celda:
                            textProperty().set("" + r.getCliente().getPersona().getRfc());                            
                        }
                        else
                            setText(null);
                    }
                };
            }
        });
        //DATOS Cliente
        tcClienteId.setCellFactory(new Callback<TableColumn<Reservacion, Integer>, 
                                       TableCell<Reservacion, Integer>>()
        {
            @Override
            public TableCell<Reservacion, Integer> call(TableColumn<Reservacion, 
                                                    Integer> param)
            {
                return new TableCell<Reservacion, Integer>()
                {
                    @Override
                    protected void updateItem(Integer item, boolean empty) 
                    {
                        //Pedimos el indice del elemento que quiere mostrar
                        //el TableView:
                        int indice = getIndex();
                        
                        Reservacion r = null;
                        
                        //Inicializamos la celda con el valor que nos pasa el
                        //TableView:
                        super.updateItem(item, empty);
                        
                        //Preguntamos si el indice (posicion) del objeto
                        //estÃ¡ dentro del tamaÃ±o de la lista:
                        if (indice >= 0 && indice < tbl.getItems().size())
                        {
                            //Obtenemos el empleado de la posiciÃ³n requerida:
                            r = tbl.getItems().get(indice);
                            
                            //Establecemos el valor de la celda:
                            textProperty().set("" + r.getCliente().getId());                            
                        }
                        else
                            setText(null);
                    }
                };
            }
        });
        tcClienteNumero.setCellFactory(new Callback<TableColumn<Reservacion, String>, 
                                       TableCell<Reservacion, String>>()
        {
            @Override
            public TableCell<Reservacion, String> call(TableColumn<Reservacion, 
                                                    String> param)
            {
                return new TableCell<Reservacion, String>()
                {
                    @Override
                    protected void updateItem(String item, boolean empty) 
                    {
                        //Pedimos el indice del elemento que quiere mostrar
                        //el TableView:
                        int indice = getIndex();
                        
                        Reservacion r = null;
                        
                        //Inicializamos la celda con el valor que nos pasa el
                        //TableView:
                        super.updateItem(item, empty);
                        
                        //Preguntamos si el indice (posicion) del objeto
                        //estÃ¡ dentro del tamaÃ±o de la lista:
                        if (indice >= 0 && indice < tbl.getItems().size())
                        {
                            //Obtenemos el empleado de la posiciÃ³n requerida:
                            r = tbl.getItems().get(indice);
                            
                            //Establecemos el valor de la celda:
                            textProperty().set("" + r.getCliente().getNumeroUnico());                            
                        }
                        else
                            setText(null);
                    }
                };
            }
        });
        tcClienteCorreo.setCellFactory(new Callback<TableColumn<Reservacion, String>, 
                                       TableCell<Reservacion, String>>()
        {
            @Override
            public TableCell<Reservacion, String> call(TableColumn<Reservacion, 
                                                    String> param)
            {
                return new TableCell<Reservacion, String>()
                {
                    @Override
                    protected void updateItem(String item, boolean empty) 
                    {
                        //Pedimos el indice del elemento que quiere mostrar
                        //el TableView:
                        int indice = getIndex();
                        
                        Reservacion r = null;
                        
                        //Inicializamos la celda con el valor que nos pasa el
                        //TableView:
                        super.updateItem(item, empty);
                        
                        //Preguntamos si el indice (posicion) del objeto
                        //estÃ¡ dentro del tamaÃ±o de la lista:
                        if (indice >= 0 && indice < tbl.getItems().size())
                        {
                            //Obtenemos el empleado de la posiciÃ³n requerida:
                            r = tbl.getItems().get(indice);
                            
                            //Establecemos el valor de la celda:
                            textProperty().set("" + r.getCliente().getCorreo());                            
                        }
                        else
                            setText(null);
                    }
                };
            }
        });
        
        tcSalaId.setCellFactory(new Callback<TableColumn<Reservacion, Integer>, 
                                       TableCell<Reservacion, Integer>>()
        {
            @Override
            public TableCell<Reservacion, Integer> call(TableColumn<Reservacion, 
                                                    Integer> param)
            {
                return new TableCell<Reservacion, Integer>()
                {
                    @Override
                    protected void updateItem(Integer item, boolean empty) 
                    {
                        //Pedimos el indice del elemento que quiere mostrar
                        //el TableView:
                        int indice = getIndex();
                        
                        Reservacion r = null;
                        
                        //Inicializamos la celda con el valor que nos pasa el
                        //TableView:
                        super.updateItem(item, empty);
                        
                        //Preguntamos si el indice (posicion) del objeto
                        //estÃ¡ dentro del tamaÃ±o de la lista:
                        if (indice >= 0 && indice < tbl.getItems().size())
                        {
                            //Obtenemos el empleado de la posiciÃ³n requerida:
                            r = tbl.getItems().get(indice);
                            
                            //Establecemos el valor de la celda:
                            textProperty().set("" + r.getSala().getId());                            
                        }
                        else
                            setText(null);
                    }
                };
            }
        });
        tcSalaNombre.setCellFactory(new Callback<TableColumn<Reservacion, String>, 
                                       TableCell<Reservacion, String>>()
        {
            @Override
            public TableCell<Reservacion, String> call(TableColumn<Reservacion, 
                                                    String> param)
            {
                return new TableCell<Reservacion, String>()
                {
                    @Override
                    protected void updateItem(String item, boolean empty) 
                    {
                        //Pedimos el indice del elemento que quiere mostrar
                        //el TableView:
                        int indice = getIndex();
                        
                        Reservacion r = null;
                        
                        //Inicializamos la celda con el valor que nos pasa el
                        //TableView:
                        super.updateItem(item, empty);
                        
                        //Preguntamos si el indice (posicion) del objeto
                        //estÃ¡ dentro del tamaÃ±o de la lista:
                        if (indice >= 0 && indice < tbl.getItems().size())
                        {
                            //Obtenemos el empleado de la posiciÃ³n requerida:
                            r = tbl.getItems().get(indice);
                            
                            //Establecemos el valor de la celda:
                            textProperty().set("" + r.getSala().getNombre());                            
                        }
                        else
                            setText(null);
                    }
                };
            }
        });
        tcSalaDescripcion.setCellFactory(new Callback<TableColumn<Reservacion, String>, 
                                       TableCell<Reservacion, String>>()
        {
            @Override
            public TableCell<Reservacion, String> call(TableColumn<Reservacion, 
                                                    String> param)
            {
                return new TableCell<Reservacion, String>()
                {
                    @Override
                    protected void updateItem(String item, boolean empty) 
                    {
                        //Pedimos el indice del elemento que quiere mostrar
                        //el TableView:
                        int indice = getIndex();
                        
                        Reservacion r = null;
                        
                        //Inicializamos la celda con el valor que nos pasa el
                        //TableView:
                        super.updateItem(item, empty);
                        
                        //Preguntamos si el indice (posicion) del objeto
                        //estÃ¡ dentro del tamaÃ±o de la lista:
                        if (indice >= 0 && indice < tbl.getItems().size())
                        {
                            //Obtenemos el empleado de la posiciÃ³n requerida:
                            r = tbl.getItems().get(indice);
                            
                            //Establecemos el valor de la celda:
                            textProperty().set("" + r.getSala().getDescripcion());                            
                        }
                        else
                            setText(null);
                    }
                };
            }
        });
        tcSalaEstatus.setCellFactory(new Callback<TableColumn<Reservacion, Integer>, 
                                       TableCell<Reservacion, Integer>>()
        {
            @Override
            public TableCell<Reservacion, Integer> call(TableColumn<Reservacion, 
                                                    Integer> param)
            {
                return new TableCell<Reservacion, Integer>()
                {
                    @Override
                    protected void updateItem(Integer item, boolean empty) 
                    {
                        //Pedimos el indice del elemento que quiere mostrar
                        //el TableView:
                        int indice = getIndex();
                        
                        Reservacion r = null;
                        
                        //Inicializamos la celda con el valor que nos pasa el
                        //TableView:
                        super.updateItem(item, empty);
                        
                        //Preguntamos si el indice (posicion) del objeto
                        //estÃ¡ dentro del tamaÃ±o de la lista:
                        if (indice >= 0 && indice < tbl.getItems().size())
                        {
                            //Obtenemos el empleado de la posiciÃ³n requerida:
                            r = tbl.getItems().get(indice);
                            
                            //Establecemos el valor de la celda:
                            textProperty().set("" + r.getSala().getEstatus());                            
                        }
                        else
                            setText(null);
                    }
                };
            }
        });
         
        //Una vez configuradas las columnas, las pondremos en la tabla,
        //pero antes, quÃ­taremos cualquier columna que pudiera tener:
        tbl.getColumns().clear();
        
        //Agregamos las columnas a la tabla, en el orden 
        //que deseamos que aparezcan:
        tbl.getColumns().addAll(
        tcPersonaId,
        tcPersonaNombre,
        tcPersonaApellidoPaterno,
        tcPersonaApellidoMaterno,
        tcPersonaGenero,
        tcPersonaDomicilio,
        tcPersonaTelefono,
        tcPersonaRfc,
        tcClienteId,
        tcClienteNumero,
        tcClienteCorreo,
        tcReservacionId,
        tcReservacionFechaHoraInicio,
        tcReservacionFechaHoraFin,
        tcReservacionEstatus,
        tcSalaId,
        tcSalaNombre,
        tcSalaDescripcion,
        tcSalaEstatus
        );
    }
}
  

