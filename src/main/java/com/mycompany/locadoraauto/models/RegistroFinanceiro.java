/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.locadoraauto.models;

/**
 *
 * @author vitor
 */
public class RegistroFinanceiro {
    private int idRegistro;
    private int idVeiculo;
    private float valorCompra;
    private float valorVenda;
    private float valorDiaria;
    private float valorManutencao;
    private float valorTotal;

    public RegistroFinanceiro(int idRegistro, int idVeiculo, float valorCompra, float valorVenda, float valorDiaria, float valorManutencao, float valorTotal) {
        this.idRegistro = idRegistro;
        this.idVeiculo = idVeiculo;
        this.valorCompra = valorCompra;
        this.valorVenda = valorVenda;
        this.valorDiaria = valorDiaria;
        this.valorManutencao = valorManutencao;
        this.valorTotal = valorTotal;
    }

    /**
     * @return the idRegistro
     */
    public int getIdRegistro() {
        return idRegistro;
    }

    /**
     * @param idRegistro the idRegistro to set
     */
    public void setIdRegistro(int idRegistro) {
        this.idRegistro = idRegistro;
    }

    /**
     * @return the valorCompra
     */
    public float getValorCompra() {
        return valorCompra;
    }

    /**
     * @param valorCompra the valorCompra to set
     */
    public void setValorCompra(float valorCompra) {
        this.valorCompra = valorCompra;
    }

    /**
     * @return the valorVenda
     */
    public float getValorVenda() {
        return valorVenda;
    }

    /**
     * @param valorVenda the valorVenda to set
     */
    public void setValorVenda(float valorVenda) {
        this.valorVenda = valorVenda;
    }

    /**
     * @return the valorDiaria
     */
    public float getValorDiaria() {
        return valorDiaria;
    }

    /**
     * @param valorDiaria the valorDiaria to set
     */
    public void setValorDiaria(float valorDiaria) {
        this.valorDiaria = valorDiaria;
    }

    /**
     * @return the valorManutencao
     */
    public float getValorManutencao() {
        return valorManutencao;
    }

    /**
     * @param valorManutencao the valorManutencao to set
     */
    public void setValorManutencao(float valorManutencao) {
        this.valorManutencao = valorManutencao;
    }

    /**
     * @return the valorTotal
     */
    public float getValorTotal() {
        return valorTotal;
    }

    /**
     * @param valorTotal the valorTotal to set
     */
    public void setValorTotal(float valorTotal) {
        this.valorTotal = valorTotal;
    }

    /**
     * @return the idVeiculo
     */
    public int getIdVeiculo() {
        return idVeiculo;
    }

    /**
     * @param idVeiculo
     */
    public void setVeiculo(int idVeiculo) {
        this.idVeiculo = idVeiculo;
    }
}
