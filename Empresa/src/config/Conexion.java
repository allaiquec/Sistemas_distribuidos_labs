/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package config;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Asus
 */
public class Conexion {
    Connection con;
    public Conexion(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/empresa","root","angie1234");
        }
        catch (Exception e){
            System.err.println("Error: "+e);
        }
        
    }
    
    public Connection getConnection(){
        return con;
    }
    
}
