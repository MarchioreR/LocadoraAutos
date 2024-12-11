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
public class Usuario {
    private String nome;
    private TipoID tipoID;
    private String email;
    private String numCel;

    public Usuario(String nome, TipoID tipoID, String email, String numCel) {
        setNome(nome);
        setTipoID(tipoID);
        setEmail(email);
        setEmail(email);
        setNumCel(numCel);
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the numCel
     */
    public String getNumCel() {
        return numCel;
    }

    /**
     * @param numCel the numCel to set
     */
    public void setNumCel(String numCel) {
        this.numCel = numCel;
    }

    /**
     * @return the tipoID
     */
    public TipoID getTipoID() {
        return tipoID;
    }

    /**
     * @param tipoID the tipoID to set
     */
    public void setTipoID(TipoID tipoID) {
        this.tipoID = tipoID;
    }
}