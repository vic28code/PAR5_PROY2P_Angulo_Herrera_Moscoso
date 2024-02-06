/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controlador;

import static Controlador.InicioSesionController.iniciarcerrar;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import modelo.App;
import modelo.Promocion;
import modelo.Sexo;
import modelo.Usuario;

/**
 * FXML Controller class
 *
 * @author Grupo1
 */
public class BienvenidoController implements Initializable {

    @FXML
    private Label lblWelcome;
    @FXML
    private Label lblNombre;

    public static ArrayList<Promocion> promociones = new ArrayList<>();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        textoLabels(InicioSesionController.usuarioSeleccionado);
        VentanaPedidos();
        CargarLista();

    }

    @FXML
    void ReservarVuelo(ActionEvent e) {
        try {
            Stage st = new Stage();
            Scene sc = new Scene(App.loadFXML("/Vistas/ReservarVuelo"));
            st.setScene(sc);
            st.show();
        } catch (Exception a) {
            a.printStackTrace();
        }
    }

    public void textoLabels(Usuario user) {
        String name = user.getNombre();
        Sexo gender = user.getSexo();

        if (gender.equals(Sexo.H)) {
            lblWelcome.setText("BIENVENIDO");
            lblNombre.setText(name.toUpperCase());

        } else {
            lblWelcome.setText("BIENVENIDA");
            lblNombre.setText(name.toUpperCase());
        }
    }

    @FXML
    void encontrar(ActionEvent e) throws FileNotFoundException {
        Stage stage = new Stage();
        ArrayList<Promocion> locales = new ArrayList<>();
        AnchorPane contenedor = new AnchorPane();
        contenedor.setMaxSize(700, 700);
        contenedor.setMinSize(700, 700);

        InputStream inputStreams = new FileInputStream("src/main/resources/imagen/mapa.PNG");
        Image mapa = new Image(inputStreams);
        ImageView imgv = new ImageView(mapa);
        imgv.setFitHeight(505.8);
        imgv.setFitWidth(788.4);
        contenedor.setMaxSize(800, 800);
        contenedor.getChildren().addAll(imgv);
        imgv.setLayoutX(15);
        imgv.setLayoutY(15);
        try (BufferedReader bfr = new BufferedReader(new FileReader(new File("src/main/resources/Textos/promociones.txt")))) {
            String linea = bfr.readLine();
            while (linea != null) {
                String[] lineas = linea.split(",");
                double coordenadax = Double.parseDouble(lineas[0]);
                double coordenaday = Double.parseDouble(lineas[1]);
                Promocion local = new Promocion(coordenadax, coordenaday, lineas[2], lineas[3], Integer.parseInt(lineas[4]));
                locales.add(local);
                promociones.add(local);
                linea = bfr.readLine();
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Archivo no encontrado");
        } catch (IOException ex) {
            System.out.println("Error al leer el archivo");
        }
        Scene scene = new Scene(contenedor, 610, 465);
        stage.setScene(scene);
        stage.setTitle("Ubicaciones");
        stage.show();
        iniciarImagenes(locales, contenedor);
    }

    public static void mostrarImagenes(ArrayList<Promocion> locales, AnchorPane contenedor) throws FileNotFoundException {
        for (Promocion local : locales) {
            InputStream inputStream = new FileInputStream("src/main/resources/imagen/icono.png");
            Image ubicacion = new Image(inputStream);
            ImageView imgv = new ImageView(ubicacion);

            imgv.setOnMouseClicked(event -> {
                Stage informacion = new Stage();
                VBox cinfo = new VBox();
                cinfo.setMaxSize(300, 250);
                cinfo.setMinSize(300, 250);
                cinfo.setStyle("-fx-background-color:#6d4094;");
                HBox cheladeria = new HBox();
                Label Descuentos = new Label("\n\n          " + local.getPais() + "\n");
                Descuentos.setStyle("-fx-font-family:'Tahoma';");
                Descuentos.setStyle("-fx-font-weight: bold;");
                Descuentos.setStyle("-fx-text-fill: white;");

                cheladeria.getChildren().addAll(Descuentos);
                HBox cdatos = new HBox();
                Label datos = new Label("\n         " + local.getCodigo() + "\n\n         Descuento: " + local.getDescuento() + "\n\n\n\n\n\n");
                datos.setStyle("-fx-font-family:'Arial Black';");
                datos.setStyle("-fx-text-fill: white;");

                cdatos.getChildren().addAll(datos);
                HBox ctiempo = new HBox();
                Label tiempo = new Label();
                tiempo.setStyle("-fx-text-fill: yellow;");
                tiempo.setText(".            Cerrando en 5 segundos...         ");
                Button cerrar = new Button("Cerrar");
                cerrar.setOnAction(event2 -> {
                    informacion.close();
                });
                ctiempo.getChildren().addAll(tiempo, cerrar);
                cinfo.getChildren().addAll(cheladeria, cdatos, ctiempo);
                Scene escena = new Scene(cinfo);
                informacion.setScene(escena);
                informacion.setTitle("Detalle Ubicación");
                informacion.show();
                iniciarcerrar(tiempo, informacion);
            });
            Random rd = new Random();
            int numero = rd.nextInt(10) + 1;
            try {
                Thread.sleep(numero * 1000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            Platform.runLater(new Runnable() {
                public void run() {

                    contenedor.getChildren().add(imgv);

                    imgv.setLayoutX(local.getCoordenadaX());
                    imgv.setLayoutY(local.getCoordenadaY());
                    System.out.println("Coordenadas establecidas...");
                    imgv.setFitHeight(60);
                    imgv.setFitWidth(60);
                    System.out.println("...apropiadamente");

                }
            });

        }
    }

    public static void iniciarImagenes(ArrayList<Promocion> locales, AnchorPane contenedor) {
        Thread dormir = new Thread(new Runnable() {
            public void run() {
                try {
                    mostrarImagenes(locales, contenedor);
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
        });
        dormir.start();
    }

    private void VentanaPedidos() {
        Stage st = new Stage();
        VBox rootpedido = new VBox();
        String info = null;
        st.setTitle("Pedidos generados");
        ListView<String> lv = new ListView<>();
        ObservableList<String> listaElementos = FXCollections.observableArrayList();
        try {
            File file = new File("src/main/resources/Textos/reservas.txt");
            FileReader fw = new FileReader(file);
            BufferedReader br = new BufferedReader(fw);
            while ((info = br.readLine()) != null) {
                String[] tokens = info.split(",");
                String codigoReserva = tokens[0];
                String cedulaUsu = tokens[1];
                ArrayList<Usuario> listUsu = InicioSesionController.ListaUsuarios;
                for (Usuario u : listUsu) {
                    if (u.getCedula().equals(cedulaUsu)) {
                        String datosUsuario = u.getNombre() + " " + u.getApellido();
                        String linea = codigoReserva + "-" + datosUsuario;
                        listaElementos.add(linea);

                    }
                }

            }
            lv.setItems(listaElementos);
            rootpedido.getChildren().add(lv);
        } catch (Exception I) {
            I.printStackTrace();
        }
        Scene sc = new Scene(rootpedido, 300, 300);
        st.setScene(sc);
        st.show();
        actualizar(listaElementos, lv);

    }

    public static void actualizar(ObservableList<String> listaElementos, ListView<String> lv) {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        scheduler.scheduleAtFixedRate(() -> {
            Platform.runLater(() -> {
                try (BufferedReader br = new BufferedReader(new FileReader("src/main/resources/Textos/reservas.txt"))) {
                    listaElementos.clear();
                    String info;
                    while ((info = br.readLine()) != null) {
                        String[] tokens = info.split(",");
                        String codigoReserva = tokens[0];
                        String cedulaUsu = tokens[1];
                        ArrayList<Usuario> listUsu = InicioSesionController.ListaUsuarios;
                        for (Usuario u : listUsu) {
                            if (u.getCedula().equals(cedulaUsu)) {
                                String datosUsuario = u.getNombre() + " " + u.getApellido();
                                String linea = codigoReserva + "-" + datosUsuario;
                                listaElementos.add(linea);
                            }
                        }

                    }
                    lv.setItems(listaElementos);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        },
                 0, 5, TimeUnit.SECONDS
        );
    }

//    public static void actualizar(ObservableList<String> listaElementos, ListView<String> lv) {
//        Thread dormir2 = new Thread(new Runnable() {
//            public void run() {
//                int a = 1;
//                while (a != 0) {
//                    for (int i = 5; i != 0; i--) {
//                        try {
//                            Thread.sleep(5000);
//                        } catch (InterruptedException ex) {
//                            ex.printStackTrace();
//                        }
//                        Platform.runLater(new Runnable() {
//                            public void run() {
//                                listaElementos.clear();
//                                try {
//                                    String info = null;
//                                    File file = new File("src/main/resources/Textos/Pagos.txt");
//                                    FileReader fw = new FileReader(file);
//                                    BufferedReader br = new BufferedReader(fw);
//                                    while ((info = br.readLine()) != null) {
//                                        String[] tokens = info.split(",");
//                                        Usuario u = InicioSesionController.usuarioSeleccionado;
//                                        String codigoReserva = tokens[1];
//                                        String datosUsuario = u.getNombre() + " " + u.getApellido();
//                                        String linea = codigoReserva + "-" + datosUsuario;
//                                        listaElementos.add(linea);
//                                    }
//                                    lv.setItems(listaElementos);
//                                } catch (Exception I) {
//                                    I.printStackTrace();
//                                }
//                            }
//                        });
//                    }
//                }
//            }
//        });
//        dormir2.start();
//    }
    void CargarLista() {
        try (BufferedReader bfr = new BufferedReader(new FileReader(new File("src/main/resources/Textos/promociones.txt")))) {
            String linea = bfr.readLine();
            while (linea != null) {
                String[] lineas = linea.split(",");
                double coordenadax = Double.parseDouble(lineas[0]);
                double coordenaday = Double.parseDouble(lineas[1]);
                Promocion local = new Promocion(coordenadax, coordenaday, lineas[2], lineas[3], Integer.parseInt(lineas[4]));
                promociones.add(local);
                linea = bfr.readLine();
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Archivo no encontrado");
        } catch (IOException ex) {
            System.out.println("Error al leer el archivo");
        }

    }

}
