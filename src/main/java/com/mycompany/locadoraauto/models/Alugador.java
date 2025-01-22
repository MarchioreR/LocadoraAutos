/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.locadoraauto.models;

/**
 *
 * @author vitor
 */
public class Alugador extends Usuario {

    private int idade;
    private String genero;

    public Alugador() {
    }

    /**
     * @return the idade
     */
    public int getIdade() {
        return idade;
    }

    /**
     * @param idade the idade to set
     */
    public void setIdade(int idade) {
        this.idade = idade;
    }

    /**
     * @return the genero
     */
    public String getGenero() {
        return genero;
    }

    /**
     * @param genero the genero to set
     */
    public void setGenero(String genero) {
        this.genero = genero;
    }
    
    @Override
    public void ImprimirUsuario() {
        super.ImprimirUsuario();
        System.out.println("\n Idade: " + getIdade() + " Gênero: " + getGenero());
    }

}
