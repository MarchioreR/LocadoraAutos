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
    private int idObtencao;
    private int idAutomovel;
    private int idMontadora;
    private int idVendedor;
    private Date dataObt;
    private float valorObt;

    public Obtencao(int idObtencao, int idAutomovel, int idMontadora, int idVendedor, Date dataObt, float valorObt) {
        this.idObtencao = idObtencao;
        this.idAutomovel = idAutomovel;
        this.idMontadora = idMontadora;
        this.idVendedor = idVendedor;
        this.dataObt = dataObt;
        this.valorObt = valorObt;
        
    }

    /**
     * @return the idObtencao
     */
    public int getIdObtencao() {
        return idObtencao;
    }

    /**
     * @param idObtencao the idObtencao to set
     */
    public void setIdObtencao(int idObtencao) {
        this.idObtencao = idObtencao;
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
     * @return the idVendedor
     */
    public int getIdVendedor() {
        return idVendedor;
    }

    /**
     * @param idVendedor the idVendedor to set
     */
    public void setIdVendedor(int idVendedor) {
        this.idVendedor = idVendedor;
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
