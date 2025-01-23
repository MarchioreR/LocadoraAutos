/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.locadoraauto.models;

/**
 *
 * @author vitor
 */
public class Venda {
    private int idVenda;
    private Automovel automovel;
    private Vendedor vendedor;
    private Alugador alugador;
    private int valorVenda;

    public Venda(int idVenda, Automovel automovel, Vendedor vendedor, Alugador alugador, int valorVenda) {
        this.idVenda = idVenda;
        this.automovel = automovel;
        this.vendedor = vendedor;
        this.alugador = alugador;
        this.valorVenda = valorVenda; 
    }
    
    /**
     * @return the idVenda
     */
    public int getIdVenda() {
        return idVenda;
    }

    /**
     * @param idVenda the idVenda to set
     */
    public void setIdVenda(int idVenda) {
        this.idVenda = idVenda;
    }

    /**
     * @return the valorVenda
     */
    public int getValorVenda() {
        return valorVenda;
    }

    /**
     * @param valorVenda the valorVenda to set
     */
    public void setValorVenda(int valorVenda) {
        this.valorVenda = valorVenda;
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
     * @return the vendedor
     */
    public Vendedor getVendedor() {
        return vendedor;
    }

    /**
     * @param vendedor the vendedor to set
     */
    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
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
}
