/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package modelo;

/**
 *
 * @author Grupo1
 */
public interface Pagable {
    public Pago GenerarTransaccion(String Idpago, String codigoR, double Total, int Descuento, char FormaPago, double totalPagar);
    
}
