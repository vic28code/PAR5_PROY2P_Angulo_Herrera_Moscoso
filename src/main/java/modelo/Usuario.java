/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author Grupo1
 */
public class Usuario {

    private String cedula;
    private String Nombre;
    private String Apellido;
    private String Usuario;
    private String contra;
    private Sexo sexo;

    public Usuario(String cedula, String Nombre, String Apellido, String Usuario, String contra, Sexo sexo) {
        this.cedula = cedula;
        this.Nombre = Nombre;
        this.Apellido = Apellido;
        this.Usuario = Usuario;
        this.contra = contra;
        this.sexo = sexo;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public void setApellido(String Apellido) {
        this.Apellido = Apellido;
    }

    public void setUsuario(String Usuario) {
        this.Usuario = Usuario;
    }

    public void setContra(String contra) {
        this.contra = contra;
    }

    public String getCedula() {
        return cedula;
    }

    public String getNombre() {
        return Nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public String getUsuario() {
        return Usuario;
    }

    public String getContra() {
        return contra;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    @Override
    public String toString() {
        return "Usuario{" + "cedula=" + cedula + ", Nombre=" + Nombre + ", Apellido=" + Apellido + ", Usuario=" + Usuario + ", contra=" + contra + ", sexo=" + sexo + '}';
    }

}
