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
public class Obtencao {
    private int idAutomovel;
    private int idMontadora;
    private Date dataObt;
    private float valorObt;

    public Obtencao(int idAutomovel, int idMontadora, Date dataObt, float valorObt) {
        this.idAutomovel = idAutomovel;
        this.idMontadora = idMontadora;
        this.dataObt = dataObt;
        this.valorObt = valorObt;
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
     * @return the dataObt
     */
    public Date getDataObt() {
        return dataObt;
    }

    /**
     * @param dataObt the dataObt to set
     */
    public void setDataObt(Date dataObt) {
        this.dataObt = dataObt;
    }

    /**
     * @return the valorObt
     */
    public float getValorObt() {
        return valorObt;
    }

    /**
     * @param valorObt the valorObt to set
     */
    public void setValorObt(float valorObt) {
        this.valorObt = valorObt;
    }
}
