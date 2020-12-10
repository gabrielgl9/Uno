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
public class CartaVerde extends Carta {
    
    private String cor;

    public CartaVerde(String simbolo, String efeito)
    {
        this.simbolo = simbolo;
        this.efeito = efeito;
        setCor("verde");
    }

    @Override
    public String obterDescricao() {
        return this.simbolo + " verde";
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
