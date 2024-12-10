/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.locadoraauto.enums;

/**
 *
 * @author vitor
 */
public enum TipoSeguro {
    BASICO("Danos a terceiros, roubo ou furto, cobertura parcial para colisões"),
    INTERMEDIARIO("Cobertura para passageiros em caso de acidente, carro reserva, proteção contra vandalismo, todos benefícios do nível básico"),
    AVANCADO("Carro reserva, assistência VIP, todos benefícios do nível intermediário");
    
    private final String descricao;
    private TipoSeguro(String descricao){this.descricao = descricao;}
    public String getDescricao(){return descricao;}
}
