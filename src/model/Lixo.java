/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author Gabriel
 */
public class Lixo {
    
    private final ArrayList<Carta> lixo = new ArrayList();

    /**
     * Adiciona a carta descartada no lixo
     * 
     * @param carta
     */
    public void recebeDescarte(Carta carta) 
    {
        this.lixo.add(carta);
        atribuirCorOrigem();
    }
    
    /**
     * Volta para a cor de origem
     * 
     */
    public void atribuirCorOrigem()
    {
        Carta penultimaCarta = this.lixo.get(this.lixo.size() - 1);

        if (penultimaCarta.getSimbolo().equals("Mudar cor") ||
            penultimaCarta.getSimbolo().equals("+4")
        ) {
            penultimaCarta.setCor("preto");
        }
    }

    /**
     * Busca o último descarte
     * @return 
     */
    public Carta mostrarUltimoDescarte() 
    {
        return this.lixo.get(this.lixo.size() - 1);
    }
    
    /**
     * Busca um descarte por index
     * @param index
     * @return 
     */
    public Carta mostrarDescartePorIndex(int index)
    {
        return this.lixo.get(index);
    }
    
    /**
     * Mostra todas as cartas do lixo
     * @return 
     */
    public ArrayList<Carta> getLixo() 
    {
        return this.lixo;
    }
    
    /**
     * Remove todas as cartas do lixo
     * 
     */
    public void removerLixo()
    {
        this.lixo.removeAll(lixo);
    }
}
