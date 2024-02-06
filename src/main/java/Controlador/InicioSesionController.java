/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controlador;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import modelo.Usuario;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import modelo.Sexo;

/**
 * FXML Controller class
 *
 * @author Grupo1
 */
public class InicioSesionController implements Initializable {

    public static ArrayList<Usuario> ListaUsuarios = IniciarUsuarios();
    public static Usuario usuarioSeleccionado;
    @FXML
    TextField usuario;
    @FXML
    TextField contra;
    @FXML
    private HBox root;
    @FXML
    private ImageView background;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ListaUsuarios = IniciarUsuarios();
        try {
            File jpgFile = new File("src/main/resources/imagen/ecuflightBC.jpg");
            background.setImage(new Image(jpgFile.toURI().toString()));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static ArrayList<Usuario> IniciarUsuarios() { //Metodo que carga los usuarios en el sistema
        ArrayList<Usuario> listaUsu = new ArrayList<>();
        try {
            File fl = new File("src/main/resources/Textos/Cliente.txt");
            FileReader fr = new FileReader(fl);
            BufferedReader br = new BufferedReader(fr);
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] informacion = linea.split(",");
                Usuario usu = new Usuario(informacion[0], informacion[1], informacion[2], informacion[3], informacion[4], Sexo.valueOf(informacion[5]));
                listaUsu.add(usu);
            }
        } catch (IOException e) {
            e.printStackTrace();

        }
        return listaUsu;
    }

    public static Usuario obtenerUsuario(ArrayList<Usuario> clientes, String user, String password) {
        for (Usuario cliente : clientes) {
            if (cliente.getUsuario().equals(user) && cliente.getContra().equals(password)) {
                return cliente;
            }
        }
        return null;
    }

    @FXML
    void IniciarSesion(ActionEvent ae) {
        boolean despliegue = false;
        for (Usuario usu : ListaUsuarios) {
            if (usu.getUsuario().equals(usuario.getText()) && usu.getContra().equals(contra.getText())) {
                despliegue = true;
                usuarioSeleccionado = usu;
            }
        }
        if (despliegue == true) {
            CambiarEscena();
        } else {
            Alert al = new Alert(Alert.AlertType.INFORMATION);
            al.setContentText("CREDENCIALES INVÁLIDAS");
            al.showAndWait();
        }
    }

    void CambiarEscena() {
        try {
            Stage actual = (Stage) usuario.getScene().getWindow();
            FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("/Vistas/Bienvenido.fxml"));
            Scene sc = new Scene(fxmlloader.load(), 807, 480);
            actual.setScene(sc);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /*
    * Cierra la ventana despues de un tiempo ademas de que avisa mediante un label el tiempo que falta para que se cierre
    * @param label Etiqueta que muestra el tiempo faltante para que se cierra la ventana
    * @param escenario Ventana a cerrar
     */
    public static void cerrar(Label label, Stage escenario) {
        for (int i = 5; i != 0; i--) {
            String status = "Cerrando en " + i + " segundos..                                ";
            Platform.runLater(new Runnable() {
                public void run() {
                    label.setText(status);
                }
            });
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        Platform.runLater(new Runnable() {
            public void run() {
                escenario.close();
            }
        });
    }

    /**
     * Inicia el metodo cerrar
     *
     * @param label Etiqueta que muestra el tiempo faltante para que se cierra
     * la ventana
     * @param escenario Ventana a cerrar
     */
    public static void iniciarcerrar(Label label, Stage escenario) {
        Thread dormir2 = new Thread(new Runnable() {
            public void run() {
                cerrar(label, escenario);
            }
        });
        dormir2.start();
    }

    public static Usuario getUsuarioSeleccionado() {
        return usuarioSeleccionado;
    }

}
