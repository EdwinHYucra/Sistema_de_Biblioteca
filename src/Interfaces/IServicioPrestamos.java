/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Interfaces;

/**
 *
 * @author Dayanna
 */
public interface IServicioPrestamos {
    boolean validarDisponibilidadReserva();
    boolean solicitarReserva();
    void cancelarReserva();
}
