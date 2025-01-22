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
    private int idAutomovel;
    private int idVendedor;
    private int idComprador;
    private int valorVenda;

    public Venda(int idVenda, int idAutomovel, int idVendedor, int valorVenda, int idComprador) {
        this.idVenda = idVenda;
        this.idAutomovel = idAutomovel;
        this.idVendedor = idVendedor;
        this.valorVenda = valorVenda;
        this.idComprador = idComprador;
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
     * @return the idComprador
     */
    public int getIdComprador() {
        return idComprador;
    }

    /**
     * @param idComprador the idComprador to set
     */
    public void setIdComprador(int idComprador) {
        this.idComprador = idComprador;
    }
}
