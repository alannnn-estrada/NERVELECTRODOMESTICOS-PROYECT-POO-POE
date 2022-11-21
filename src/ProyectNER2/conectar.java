/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ProyectNER2;


import java.sql.*;
import javax.swing.*;
/**
 *
 * @author Alumno16SC
 */
public class conectar {
    Connection conect = null;
    public Connection conexion(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conect = DriverManager.getConnection("jdbc:mysql://localhost:3306/NERVELE","root","");
            //*JOptionPane.showMessageDialog(null, "Conectando Con Base De Datos");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error "+ e);
        }
        return conect;
    }
    public static void main(String[] args) {
        
    }
    
}