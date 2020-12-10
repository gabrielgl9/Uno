/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Gabriel
 */
public abstract class Carta {
    
    protected String simbolo;
    protected String efeito;
    
    public abstract String obterDescricao();
    public abstract String getCor();
    public abstract void setCor(String cor);
    
    public String getSimbolo()
    {
        return this.simbolo;
    }
    
    public String getEfeito()
    {
        return this.efeito;
    }
    
    @Override
    public String toString() {
        return obterDescricao();
    }
    
    /**
     * Aplica o somatório de todos os +2 ou +4 em sequência 
     * e faz o jogador comprar se ele não continuar incrementando
     * 
     * @param jogador
     * @param lixo
     * @param baralho
     */
    public void aplicarSomatorio(Jogador jogador, Lixo lixo, Baralho baralho)
    {
        // Busca a primeira carta do lixo antes de jogar
        Carta ultimaCarta = lixo.getLixo().get(lixo.getLixo().size() - 2);
        
        // Verifica se a carta do lixo é +2 ou +4
        if (!ultimaCarta.getSimbolo().startsWith("+")) return;
        
        // Verifica se a carta a ser jogada é +2 ou +4
        if (this.getSimbolo().startsWith("+")) return;
        
        // Faz o somatório das cartas com +
        int somatorio = 0;
        for (int i = lixo.getLixo().size() - 2; lixo.getLixo().get(i).getSimbolo().startsWith("+"); i--) {
            Carta cartaLixo = lixo.getLixo().get(i);
            somatorio += Integer.parseInt(String.valueOf(cartaLixo.getSimbolo().charAt(1)));
        }
        
        // Ordena as compras de acordo com o somatório de +
        for (int v = 0; v < somatorio; v++) {
            jogador.recebeCarta(baralho.comprar());
            baralho.pegarCartasLixo(lixo);    
        }       
    }
    
    /**
     * Aplica o efeito das cartas de inverter e de bloquear
     * 
     * @param jogadores
     * @param index
     * @return 
     */
    public int aplicarEfeito(ArrayList<Jogador> jogadores, int index)
    {
        if (this.getEfeito().equals("inverte")) {
            Collections.reverse(jogadores);    
        }
        
        if (this.getEfeito().equals("bloqueia")) {
            return index == jogadores.size() ? 0 : ++index;
        }
        
        return index;
    }
}
