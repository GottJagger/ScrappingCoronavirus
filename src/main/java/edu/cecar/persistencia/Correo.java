/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cecar.persistencia;

/**
 *
 * @author oderb
 */
public class Correo {
    int id;
    String correo;

    public Correo() {
    }

    public Correo(int id, String correo) {
        this.id = id;
        this.correo = correo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    @Override
    public String toString() {
        return this.correo;
    }
    
    
    
}
