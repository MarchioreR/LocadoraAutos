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
public class Vendedor extends Usuario {

    private float valorSalario;
    private float comissaoVenda;

    public Vendedor() {
    }

    /**
     * @return the valorSalario
     */
    public float getValorSalario() {
        return valorSalario;
    }

    /**
     * @param valorSalario the valorSalario to set
     */
    public void setValorSalario(float valorSalario) {
        this.valorSalario = valorSalario;
    }

    /**
     * @return the comissaoVenda
     */
    public float getComissaoVenda() {
        return comissaoVenda;
    }

    /**
     * @param comissaoVenda the comissaoVenda to set
     */
    public void setComissaoVenda(float comissaoVenda) {
        this.comissaoVenda = comissaoVenda;
    }

    @Override
    public void ImprimirUsuario() {
        super.ImprimirUsuario();
        System.out.println("\n Salario: " + getValorSalario());
    }
}
