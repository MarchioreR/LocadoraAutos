/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.locadoraauto.models;

import com.mycompany.locadoraauto.enums.TipoSeguro;
import java.io.Serializable;

/**
 *
 * @author vitor
 */
public class Seguro implements Serializable {

    private TipoSeguro tipoSeguro; //CRIAR ENUM
    private float valorSeguro;
    private String seguradora;

    public Seguro() {

    }

    public Seguro(int tipoSeguro) {
        switch (tipoSeguro) {
            case 1 -> {
                this.tipoSeguro = TipoSeguro.AVANCADO;
                this.valorSeguro = 50;
                break;
            }
            case 2 -> {
                this.tipoSeguro = TipoSeguro.INTERMEDIARIO;
                this.valorSeguro = 30;
                break;
            }
            case 3 -> {
                this.tipoSeguro = TipoSeguro.BASICO;
                this.valorSeguro = 15;
                break;
            }
            default ->
                throw new AssertionError();
        }

        this.seguradora = "Seguradora";
    }

    /**
     * @return the valorSeguro
     */
    public float getValorSeguro() {
        return valorSeguro;
    }

    /**
     * @param valorSeguro the valorSeguro to set
     */
    public void setValorSeguro(float valorSeguro) {
        this.valorSeguro = valorSeguro;
    }

    /**
     * @return the seguradora
     */
    public String getSeguradora() {
        return seguradora;
    }

    /**
     * @param seguradora the seguradora to set
     */
    public void setSeguradora(String seguradora) {
        this.seguradora = seguradora;
    }

    /**
     * @return the tipoSeguro
     */
    public TipoSeguro getTipoSeguro() {
        return tipoSeguro;
    }

    /**
     * @param tipoSeguro the tipoSeguro to set
     */
    public void setTipoSeguro(TipoSeguro tipoSeguro) {
        this.tipoSeguro = tipoSeguro;
    }

    public void ImprimirSeguro() {
        System.out.println(" Seguradora: " + getSeguradora() + "\n Tipo: " + getTipoSeguro().name() + "\n Valor: " + getValorSeguro());
    }
}
