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
public class CartaPreta extends Carta {

    private String cor;
    
    public CartaPreta(String simbolo, String efeito)
    {
        this.simbolo = simbolo;
        this.efeito = efeito;
        setCor("preto");
    }
    
    @Override
    public void setCor(String cor)
    {
        this.cor = cor;
    }
    
    @Override
    public String obterDescricao() {
        return this.simbolo + " " + this.cor;
    }
    
    @Override
    public String getCor() {
        return this.cor;
    }
    
}
