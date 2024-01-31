/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controlador;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import modelo.Vuelo;
import modelo.App;

/**
 * FXML Controller class
 *
 *@author Grupo1
 */
public class ReservarVuelo2Controller implements Initializable {

    public static Vuelo vueloseleccionado;
    private ComboBox<String> Ordenar = new ComboBox<>();
    ArrayList<Vuelo> VuelosDisponibles = new ArrayList<>();
    ObservableList<Vuelo> lista = FXCollections.observableArrayList();
    @FXML
    private VBox ContenedorVuelos;
    @FXML
    private HBox ContenedorSeleccion;
    @FXML
    private Label datosPersonalizados;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setCiudades();
        CargarCombobox();
        CargarVuelos();
        DesplegarVuelos();

    }

    void setCiudades() {
        String ciudadSalida = ReservarVueloController.LugarSalida;
        String ciudadLlegada = ReservarVueloController.LugarLlegada;
        String personalizado = " " + ciudadSalida + "-" + ciudadLlegada;
        datosPersonalizados.setText(personalizado);
    }

    void CargarCombobox() {
        Ordenar.getItems().addAll("Duracion");
        Ordenar.setOnAction(a -> {
            if (Ordenar.getSelectionModel().getSelectedItem().equals("Duracion")) {
                FXCollections.sort(lista);
                DesplegarVuelos();
            }
        });
        ContenedorSeleccion.getChildren().add(Ordenar);
    }

    void CargarVuelos() {
        try {
            File fl = new File("src/main/resources/Textos/Vuelos.txt");
            FileReader fr = new FileReader(fl);
            BufferedReader br = new BufferedReader(fr);
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] informacion = linea.split(",");
                Vuelo vuelo = new Vuelo(informacion[0], informacion[1], informacion[2], Integer.parseInt(informacion[3]), informacion[4], informacion[5], informacion[6], Double.parseDouble(informacion[7]));
                if (vuelo.getOrigen().equals(ReservarVueloController.LugarSalida) && (vuelo.getDestino().equals(ReservarVueloController.LugarLlegada))) {
                    lista.add(vuelo);
                    System.out.println(vuelo.getDestino());
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    void DesplegarVuelos() {
        ContenedorVuelos.getChildren().clear();

        for (Vuelo v : lista) {
            VBox contenedorInformacion = new VBox();
            HBox contenedorHorizontal = new HBox();
            contenedorInformacion.setOnMouseClicked(e -> {
                try {
                    vueloseleccionado = v;
                    Stage actual = (Stage) contenedorHorizontal.getScene().getWindow();
                    Scene scn = new Scene(App.loadFXML("/Vistas/ReservarVuelo3"));
                    actual.setScene(scn);
                } catch (Exception a) {
                    a.printStackTrace();
                }
            });
            Label duracion = new Label("Duracion: " + v.getDuracion());
            duracion.setStyle("-fx-font-weight: bold");
            Label separador = new Label("-------------------");
            separador.setStyle("-fx-font-weight: bold;\n" + "-fx-text-fill: #6d4094;");
            Label HoraInicio = new Label(v.getHsalida());
            Label HoraLlegada = new Label(v.getHLlegada());
            Label Precio = new Label(String.valueOf(v.getPrecio()));
            Precio.setStyle("-fx-font-weight: bold");

            /*Estilizando y cambiando propiedades*/
            contenedorHorizontal.setSpacing(20);
            contenedorInformacion.setSpacing(10);
            contenedorInformacion.setAlignment(Pos.CENTER);
            contenedorHorizontal.setAlignment(Pos.CENTER);
            contenedorInformacion.setId("ContenedorInformacion");
            contenedorHorizontal.setStyle("\"-fx-border-color: gray; -fx-border-width: 2; -fx-border-style: solid;\"");
            //contenedorInformacion.getStylesheets().add(getClass().getResource("/Estilos/Estilos1.css").toExternalForm());
            contenedorHorizontal.getChildren().addAll(HoraInicio, separador, HoraLlegada);
            contenedorInformacion.getChildren().addAll(duracion, contenedorHorizontal, Precio);

            ContenedorVuelos.getChildren().add(contenedorInformacion);
            //ContenedorVuelos.setStyle("-fx-background-image:url(\"/imagen/Fondo3.jpg\");\n" + "    -fx-background-size:cover;");
        }

    }
}
