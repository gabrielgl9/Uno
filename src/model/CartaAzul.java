/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Gabriel
 */
public class CartaAzul extends Carta {
    
    private String cor;

    public CartaAzul(String simbolo, String efeito)
    {
        this.simbolo = simbolo;
        this.efeito = efeito;
        setCor("azul");
    }

    @Override
    public String obterDescricao() {
        return this.simbolo + " azul";
    }
    
    @Override
    public String getCor()
    {
        return this.cor;
    }
        
    @Override
    public final void setCor(String cor)
    {
        this.cor = cor;
    }
}
