/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cecar.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author oderb
 */
public class ConexionSql {

    private static Connection cnx = null;
    static Statement sentencia;
    static ResultSet resultado;

    public static Connection Conectar() {
        try {
            cnx = DriverManager.getConnection("jdbc:mysql://localhost:3306/scrappingcovid", "root", "");

            Class.forName("com.mysql.jdbc.Driver");

            System.out.println("Conectado!");
            sentencia = cnx.createStatement();

        } catch (SQLException ex) {
            Logger.getLogger(ConexionSql.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConexionSql.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cnx;
    }

    public static void guardarCorreo(String correo) {
        try {

            String sent = "INSERT INTO correo (email) VALUES('" + correo + "')";

            sentencia.execute(sent);

            System.out.println("Guardado!");
        } catch (SQLException ex) {
            Logger.getLogger(ConexionSql.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("No Guardado :(");
        }
    }

    public static ArrayList<Correo> obtenerCorreo() {
        String sent = "SELECT * FROM correo";
        String r;
        ArrayList<Correo> list = new ArrayList<>();
        try {

            ResultSet rs = sentencia.executeQuery(sent);       
            
            while (rs.next()) {
                Correo corr = new Correo();
                corr.setId(rs.getInt(1));
                corr.setCorreo(rs.getString(2));
                
                list.add(corr);
                
            }

        } catch (SQLException ex) {
            System.out.println("NO BUSCO");
            Logger.getLogger(ConexionSql.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list;
        
    }

    public static void EliminarCorreo(String correo) {
        try {

            String sent = "DELETE FROM correo WHERE email ='" + correo + "'";

            sentencia.execute(sent);

            System.out.println("Eliminado!");

        } catch (SQLException ex) {
            Logger.getLogger(ConexionSql.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("No Eliminado :(");
        }
    }


}
