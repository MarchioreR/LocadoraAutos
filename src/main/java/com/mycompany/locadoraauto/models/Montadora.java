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
    private int idMontadora;
    private String endereco;

    public Montadora() {
    }

    /**
     * @return the idMontadora
     */
    public int getIdMontadora() {
        return idMontadora;
    }

    /**
     * @param idMontadora the idMontadora to set
     */
    public void setIdMontadora(int idMontadora) {
        this.idMontadora = idMontadora;
    }

    /**
     * @return the Endereco
     */
    public String getEndereco() {
        return endereco;
    }

    /**
     * @param endereco the Endereco to set
     */
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
}
