/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.locadoraauto.models;

import com.mycompany.locadoraauto.enums.TipoID;

/**
 *
 * @author vitor
 */
public class Alugador extends Usuario {

    private int idade;
    private String genero;

    public Alugador() {
    }

    public Alugador(int idade, String genero, int idUsuario, String nome, int tipoID, String ID, String email, String numCel, String endereco) {
        super(idUsuario, nome, tipoID, ID, email, numCel, endereco);
        this.idade = idade;
        this.genero = genero;
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
