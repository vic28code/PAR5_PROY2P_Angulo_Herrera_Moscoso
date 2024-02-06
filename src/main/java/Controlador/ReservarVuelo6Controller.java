/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controlador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import modelo.App;

/**
 * FXML Controller class
 *
 * @author Grupo1
 */
public class ReservarVuelo6Controller implements Initializable {

    @FXML
    private VBox contenedorprimervuelo;
    @FXML
    private VBox contenedorsegundorvuelo;
    @FXML
    private Label fechaIda;
    @FXML
    private Label Partida;
    @FXML
    private Label FechaRegreso;
    @FXML
    private Label Regreso;
    @FXML
    private Label totalReserva;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        CargarPrimerVuelo();
        CargarSegundoVuelo();
        totalReserva.setText("Total de tu reserva: " + String.valueOf(ReservarVuelo2Controller.vueloseleccionado.getPrecio() + ReservarVuelo4Controller.vueloseleccionado2.getPrecio()));

    }

    void CargarPrimerVuelo() {
        fechaIda.setText("Vuelo de ida: " + ReservarVueloController.fecha_partida);
        //fechaIda.setStyle("-fx-font-weight: bold;");
        Partida.setText(ReservarVueloController.LugarSalida + " a " + ReservarVueloController.LugarLlegada);

        HBox contenedorHorizontal = new HBox();
        Label duracion = new Label("Duracion: " + ReservarVuelo2Controller.vueloseleccionado.getDuracion());
        HBox contenedorBoton = new HBox();
        Button boton = new Button("Detalles");
        boton.setOnAction(e -> {
            Stage st = new Stage();
            st.setWidth(200);
            st.setHeight(100);
            VBox root = new VBox();
            HBox paraBoton = new HBox();
            Button cerrar = new Button();
            cerrar.setMinWidth(50);
            cerrar.setMinHeight(50);
            cerrar.setMaxWidth(50);
            cerrar.setMaxHeight(50);
            cerrar.setText("Cerrar");
            cerrar.setStyle("-fx-font-weight: bold; -fx-text-fill: black;");

            cerrar.setOnAction(event -> {
                st.close();
            });
            StackPane stackPane = new StackPane();
            paraBoton.getChildren().addAll(stackPane, cerrar);
            BackgroundFill backgroundFill = new BackgroundFill(Color.PURPLE, null, null);
            Background background = new Background(backgroundFill);
            root.setBackground(background);
            Label Vuelo = new Label("Vuelo: " + ReservarVuelo2Controller.vueloseleccionado.getCodigoVuelo());
            Vuelo.setStyle("-fx-font-weight: bold; -fx-text-fill: white;");
            Label CodigoAvion = new Label(ReservarVuelo2Controller.vueloseleccionado.getCodigoAvion());
            CodigoAvion.setStyle("-fx-font-weight: bold;-fx-text-fill: white;");
            Label tarifa = new Label(String.valueOf("Tarifa " + ReservarVuelo3Controller.TarifaViaje1.getTipo()));
            tarifa.setStyle("-fx-font-weight: bold;-fx-text-fill: white;");

            root.getChildren().addAll(Vuelo, CodigoAvion, tarifa, paraBoton);
            Scene popup = new Scene(root);
            st.setScene(popup);
            st.show();

        });
        Label separador = new Label("-------------------");
        Label HoraInicio = new Label(ReservarVuelo2Controller.vueloseleccionado.getHsalida());
        Label HoraLlegada = new Label(ReservarVuelo2Controller.vueloseleccionado.getHLlegada());
        Label Precio = new Label(String.valueOf(ReservarVuelo2Controller.vueloseleccionado.getPrecio()));
        contenedorHorizontal.setSpacing(20);
        contenedorHorizontal.getChildren().addAll(HoraInicio, separador, HoraLlegada);
        contenedorBoton.setAlignment(Pos.CENTER_LEFT);
        contenedorBoton.setPadding(new Insets(0, 0, 0, 10));
        contenedorBoton.getChildren().add(boton);
        contenedorprimervuelo.getChildren().addAll(duracion, contenedorHorizontal, Precio, contenedorBoton);

    }

    void CargarSegundoVuelo() {
        FechaRegreso.setText("Vuelo de regreso: " + ReservarVueloController.fecha_Regreso);
        //fechaIda.setStyle("-fx-font-weight: bold;");
        Regreso.setText(ReservarVueloController.LugarLlegada + " a " + ReservarVueloController.LugarSalida);
        HBox contenedorHorizontal = new HBox();
        Label duracion = new Label("Duración: " + ReservarVuelo4Controller.vueloseleccionado2.getDuracion());
        HBox contenedorBoton = new HBox();
        Button boton = new Button("Detalles: ");
        boton.setOnAction(e -> {
            Stage st = new Stage();
            st.setWidth(200);
            st.setHeight(100);
            VBox root = new VBox();
            HBox paraBoton = new HBox();
            Button cerrar = new Button();
            cerrar.setMinWidth(50);
            cerrar.setMinHeight(50);
            cerrar.setMaxWidth(50);
            cerrar.setMaxHeight(50);
            cerrar.setText("Cerrar");

            cerrar.setOnAction(event -> {
                st.close();
            });
            StackPane stackPane = new StackPane();
            paraBoton.getChildren().addAll(stackPane, cerrar);
            BackgroundFill backgroundFill = new BackgroundFill(Color.PURPLE, null, null);
            Background background = new Background(backgroundFill);
            root.setBackground(background);
            Label Vuelo = new Label("Vuelo: " + ReservarVuelo4Controller.vueloseleccionado2.getCodigoVuelo());
            Vuelo.setStyle("-fx-font-weight: bold;-fx-text-fill: white;");
            Label CodigoAvion = new Label(ReservarVuelo4Controller.vueloseleccionado2.getCodigoAvion());
            CodigoAvion.setStyle("-fx-font-weight: bold;-fx-text-fill: white;");
            Label tarifa = new Label(String.valueOf("Tarifa " + ReservarVuelo5Controller.TarifaViaje2.getTipo()));
            tarifa.setStyle("-fx-font-weight: bold;-fx-text-fill: white;");

            root.getChildren().addAll(Vuelo, CodigoAvion, tarifa, paraBoton);
            Scene popup = new Scene(root);
            st.setScene(popup);
            st.show();

        });

        Label separador = new Label("-------------------");
        Label HoraInicio = new Label(ReservarVuelo4Controller.vueloseleccionado2.getHsalida());
        Label HoraLlegada = new Label(ReservarVuelo4Controller.vueloseleccionado2.getHLlegada());
        Label Precio = new Label(String.valueOf(ReservarVuelo4Controller.vueloseleccionado2.getPrecio()));
        contenedorHorizontal.setSpacing(20);
        contenedorHorizontal.getChildren().addAll(HoraInicio, separador, HoraLlegada);
        contenedorBoton.setAlignment(Pos.CENTER_LEFT);
        contenedorBoton.setPadding(new Insets(0, 0, 0, 10));
        contenedorBoton.getChildren().add(boton);
        contenedorsegundorvuelo.getChildren().addAll(duracion, contenedorHorizontal, Precio, contenedorBoton);

    }

    @FXML
    void Confirmar(ActionEvent event) {
        try {
            Stage ventana = (Stage) contenedorsegundorvuelo.getScene().getWindow();
            Scene escena = new Scene(App.loadFXML("/Vistas/ReservarPasajeros"));
            ventana.setScene(escena);
        } catch (Exception a) {
            a.printStackTrace();
        }

    }

}
