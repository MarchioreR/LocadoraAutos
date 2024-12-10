/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.locadoraauto.enums;

/**
 *
 * @author vitor
 */
public enum TipoVeiculo {
    HIBRIDO("Híbrido"),
    ELETRICO("Elétrico"),
    COMBUSTAO("Combustão");
    
    private final String descricao;
    private TipoVeiculo(String descricao){this.descricao = descricao;}
    public String getDescricao(){return descricao;}
}
