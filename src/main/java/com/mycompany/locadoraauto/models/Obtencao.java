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
    private Automovel automovel;
    private Montadora montadora;
    private Date dataObt;
    private float valorObt;

    public Obtencao(int idObtencao, Automovel automovel, Montadora montadora, Date dataObt, float valorObt) {
        this.idObtencao = idObtencao;
        this.automovel = automovel;
        this.montadora = montadora;
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

    /**
     * @return the montadora
     */
    public Montadora getMontadora() {
        return montadora;
    }

    /**
     * @param montadora the montadora to set
     */
    public void setMontadora(Montadora montadora) {
        this.montadora = montadora;
    }

   
}
