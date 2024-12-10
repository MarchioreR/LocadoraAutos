/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.locadoraauto.models;

import com.mycompany.locadoraauto.enums.TipoSeguro;

/**
 *
 * @author vitor
 */
public class Seguro {
    private int idSeguro;
    private TipoSeguro tipoSeguro; //CRIAR ENUM
    private float valorSeguro;
    private String seguradora;

    /**
     * @return the idSeguro
     */
    public int getIdSeguro() {
        return idSeguro;
    }

    /**
     * @param idSeguro the idSeguro to set
     */
    public void setIdSeguro(int idSeguro) {
        this.idSeguro = idSeguro;
    }

    /**
     * @return the valorSeguro
     */
    public float getValorSeguro() {
        return valorSeguro;
    }

    /**
     * @param valorSeguro the valorSeguro to set
     */
    public void setValorSeguro(float valorSeguro) {
        this.valorSeguro = valorSeguro;
    }

    /**
     * @return the seguradora
     */
    public String getSeguradora() {
        return seguradora;
    }

    /**
     * @param seguradora the seguradora to set
     */
    public void setSeguradora(String seguradora) {
        this.seguradora = seguradora;
    }

    /**
     * @return the tipoSeguro
     */
    public TipoSeguro getTipoSeguro() {
        return tipoSeguro;
    }

    /**
     * @param tipoSeguro the tipoSeguro to set
     */
    public void setTipoSeguro(TipoSeguro tipoSeguro) {
        this.tipoSeguro = tipoSeguro;
    }
}
