/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.locadoraauto.models;

import com.mycompany.locadoraauto.enums.TipoID;

/**
 *
 * @author vitor
 */
public class Montadora extends Usuario{
    private String website;
    private String paisOrigem;
    public Montadora() {
    }

    /**
     * @return the website
     */
    public String getWebsite() {
        return website;
    }

    /**
     * @param website the website to set
     */
    public void setWebsite(String website) {
        this.website = website;
    }

    /**
     * @return the paisOrigem
     */
    public String getPaisOrigem() {
        return paisOrigem;
    }

    /**
     * @param paisOrigem the paisOrigem to set
     */
    public void setPaisOrigem(String paisOrigem) {
        this.paisOrigem = paisOrigem;
    }
    
    @Override
    public void ImprimirUsuario() {
        super.ImprimirUsuario();
        System.out.println("\n WebSite: " + getWebsite() + "\n País de Origem: " + getPaisOrigem());
    }
}
