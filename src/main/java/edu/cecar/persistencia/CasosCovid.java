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
public class CasosCovid {
    
    String Pais;
    int totalCasosConfirmados;
    int totalCasosNuevosConfirmados;
    int totalMuertos;
    int TotalNuevosMuertos;

    public CasosCovid() {
    }

    public CasosCovid(String Pais, int totalCasosConfirmados, int totalCasosNuevosConfirmados, int totalMuertos, int TotalNuevosMuertos) {
        this.Pais = Pais;
        this.totalCasosConfirmados = totalCasosConfirmados;
        this.totalCasosNuevosConfirmados = totalCasosNuevosConfirmados;
        this.totalMuertos = totalMuertos;
        this.TotalNuevosMuertos = TotalNuevosMuertos;
    }

    public String getPais() {
        return Pais;
    }

    public void setPais(String Pais) {
        this.Pais = Pais;
    }

    public int getTotalCasosConfirmados() {
        return totalCasosConfirmados;
    }

    public void setTotalCasosConfirmados(int totalCasosConfirmados) {
        this.totalCasosConfirmados = totalCasosConfirmados;
    }

    public int getTotalCasosNuevosConfirmados() {
        return totalCasosNuevosConfirmados;
    }

    public void setTotalCasosNuevosConfirmados(int totalCasosNuevosConfirmados) {
        this.totalCasosNuevosConfirmados = totalCasosNuevosConfirmados;
    }

    public int getTotalMuertos() {
        return totalMuertos;
    }

    public void setTotalMuertos(int totalMuertos) {
        this.totalMuertos = totalMuertos;
    }

    public int getTotalNuevosMuertos() {
        return TotalNuevosMuertos;
    }

    public void setTotalNuevosMuertos(int TotalNuevosMuertos) {
        this.TotalNuevosMuertos = TotalNuevosMuertos;
    }

    
    
}
