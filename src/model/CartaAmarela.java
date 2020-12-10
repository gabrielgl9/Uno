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
public class CartaAmarela extends Carta {
    
    private String cor;
    
    public CartaAmarela(String simbolo, String efeito)
    {
        this.simbolo = simbolo;
        this.efeito = efeito;
        setCor("amarelo");
    }

    @Override
    public String obterDescricao() {
        return this.simbolo + " amarelo";
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
