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
public class Locador extends Usuario{
    private float valorSalario;
    private float comissaoLoc;

    public Locador() {
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
     * @return the comissaoLoc
     */
    public float getComissaoLoc() {
        return comissaoLoc;
    }

    /**
     * @param comissaoLoc the comissaoLoc to set
     */
    public void setComissaoLoc(float comissaoLoc) {
        this.comissaoLoc = comissaoLoc;
    }
    
    @Override
    public void ImprimirUsuario() {
        super.ImprimirUsuario();
        System.out.println("\n Salario: " + getValorSalario());
    }
}
