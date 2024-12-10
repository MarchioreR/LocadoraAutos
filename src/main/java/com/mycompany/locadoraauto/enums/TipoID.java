/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.locadoraauto.enums;

/**
 *
 * @author vitor
 */
public enum TipoID {
    CPF("CPF"),
    CNPJ("CNPJ");
    
    private final String descricao;
    private TipoID(String descricao){this.descricao = descricao;}
    public String getDescricao(){return descricao;}
}
