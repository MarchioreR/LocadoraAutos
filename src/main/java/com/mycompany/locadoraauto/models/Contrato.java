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
    private int idAlugador;
    private int idSeguro;
    private Date dataIn;
    private Date dataTer;
    private float valorContrato;

    public Contrato(int idContrato, int idAlugador, int idSeguro, Date dataIn, Date dataTer, float valorContrato) {
        this.idContrato = idContrato;
        this.idAlugador = idAlugador;
        this.idSeguro = idSeguro;
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
}
