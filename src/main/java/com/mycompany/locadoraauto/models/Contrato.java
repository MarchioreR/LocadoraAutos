/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.locadoraauto.models;

import java.util.Date;

/**
 *
 * @author vitor
 */
public class Contrato {
    private int idContrato;
    private Alugador alugador;
    private Locador locador;
    private Automovel automovel;
    private Seguro seguro;
    private Date dataIn;
    private Date dataTer;
    private float valorContrato;

    public Contrato(int idContrato, Alugador alugador, Date dataIn, Date dataTer, float valorContrato, Locador locador, Automovel automovel) {
        this.idContrato = idContrato;
        this.alugador = alugador;
        this.locador = locador;
        this.automovel = automovel;
        this.dataIn = dataIn;
        this.dataTer = dataTer;
        this.valorContrato = valorContrato;
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
     * @return the dataIn
     */
    public Date getDataIn() {
        return dataIn;
    }

    /**
     * @param dataIn the dataIn to set
     */
    public void setDataIn(Date dataIn) {
        this.dataIn = dataIn;
    }

    /**
     * @return the dataTer
     */
    public Date getDataTer() {
        return dataTer;
    }

    /**
     * @param dataTer the dataTer to set
     */
    public void setDataTer(Date dataTer) {
        this.dataTer = dataTer;
    }

    /**
     * @return the valorContrato
     */
    public float getValorContrato() {
        return valorContrato;
    }

    /**
     * @param valorContrato the valorContrato to set
     */
    public void setValorContrato(float valorContrato) {
        this.valorContrato = valorContrato;
    }

    /**
     * @return the seguro
     */
    public Seguro getSeguro() {
        return seguro;
    }

    /**
     * @param seguro the seguro to set
     */
    public void setSeguro(Seguro seguro) {
        this.seguro = seguro;
    }

    /**
     * @return the alugador
     */
    public Alugador getAlugador() {
        return alugador;
    }

    /**
     * @param alugador the alugador to set
     */
    public void setAlugador(Alugador alugador) {
        this.alugador = alugador;
    }

    /**
     * @return the locador
     */
    public Locador getLocador() {
        return locador;
    }

    /**
     * @param locador the locador to set
     */
    public void setLocador(Locador locador) {
        this.locador = locador;
    }

    /**
     * @return the automovel
     */
    public Automovel getAutomovel() {
        return automovel;
    }

    /**
     * @param automovel the automovel to set
     */
    public void setAutomovel(Automovel automovel) {
        this.automovel = automovel;
    }

    
}
