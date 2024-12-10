/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.locadoraauto.models;

/**
 *
 * @author vitor
 */
public class Locacao {
    private int idContrato;
    private int idAutomovel;
    private int idLocador;
    private int idLocacao;

    public Locacao(int idContrato, int idAutomovel, int idLocador, int idLocacao) {
        this.idContrato = idContrato;
        this.idAutomovel = idAutomovel;
        this.idLocador = idLocador;
        this.idLocacao = idLocacao;
    }

    /**
     * @return the idContrato
     */
    public int getIdContrato() {
        return idContrato;
    }

    /**
     * @param idContrato the idContrato to set
     */
    public void setIdContrato(int idContrato) {
        this.idContrato = idContrato;
    }

    /**
     * @return the idAutomovel
     */
    public int getIdAutomovel() {
        return idAutomovel;
    }

    /**
     * @param idAutomovel the idAutomovel to set
     */
    public void setIdAutomovel(int idAutomovel) {
        this.idAutomovel = idAutomovel;
    }

    /**
     * @return the idLocador
     */
    public int getIdLocador() {
        return idLocador;
    }

    /**
     * @param idLocador the idLocador to set
     */
    public void setIdLocador(int idLocador) {
        this.idLocador = idLocador;
    }

    /**
     * @return the idLocacao
     */
    public int getIdLocacao() {
        return idLocacao;
    }

    /**
     * @param idLocacao the idLocacao to set
     */
    public void setIdLocacao(int idLocacao) {
        this.idLocacao = idLocacao;
    }
}
