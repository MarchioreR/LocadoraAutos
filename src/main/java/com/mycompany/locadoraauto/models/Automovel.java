/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.locadoraauto.models;

import com.mycompany.locadoraauto.enums.TipoStatus;
import com.mycompany.locadoraauto.enums.TipoVeiculo;

/**
 *
 * @author vitor
 */
public class Automovel {
    private int idAutomovel;
    private String modelo;
    private String placa;
    private TipoVeiculo tipoVeic;
    private float valorDia;
    private TipoStatus status;

    public Automovel(int idAutomovel, String modelo, String placa, TipoVeiculo tipoVeic, float valorDia, TipoStatus status) {
        this.idAutomovel = idAutomovel;
        this.modelo = modelo;
        this.placa = placa;
        this.tipoVeic = tipoVeic;
        this.valorDia = valorDia;
        this.status = status;
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
     * @return the modelo
     */
    public String getModelo() {
        return modelo;
    }

    /**
     * @param modelo the modelo to set
     */
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    /**
     * @return the placa
     */
    public String getPlaca() {
        return placa;
    }

    /**
     * @param placa the placa to set
     */
    public void setPlaca(String placa) {
        this.placa = placa;
    }

    /**
     * @return the tipoVeic
     */
    public TipoVeiculo getTipoVeic() {
        return tipoVeic;
    }

    /**
     * @param tipoVeic the tipoVeic to set
     */
    public void setTipoVeic(TipoVeiculo tipoVeic) {
        this.tipoVeic = tipoVeic;
    }

    /**
     * @return the valorDia
     */
    public float getValorDia() {
        return valorDia;
    }

    /**
     * @param valorDia the valorDia to set
     */
    public void setValorDia(float valorDia) {
        this.valorDia = valorDia;
    }

    /**
     * @return the status
     */
    public TipoStatus getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(TipoStatus status) {
        this.status = status;
    }
}