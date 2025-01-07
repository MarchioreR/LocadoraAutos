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
public class Alugador extends Usuario {

    private int idAlugador;
    private String endereco;
 

    public Alugador() {
    }

    /**
     * @return the idAlugador
     */
    public int getIdAlugador() {
        return idAlugador;
    }

    /**
     * @param idAlugador the idAlugador to set
     */
    public void setIdAlugador(int idAlugador) {
        this.idAlugador = idAlugador;
    }

    /**
     * @return the endereco
     */
    public String getEndereco() {
        return endereco;
    }

    /**
     * @param endereco the endereco to set
     */
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

}
