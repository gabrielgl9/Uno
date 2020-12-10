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
public class Jogador {
    
    private String nome;
    private final ArrayList<Carta> mao = new ArrayList();
    
    public Jogador(String nome)
    {
        this.nome = nome;
    }
    
    public String getNome()
    {
        return this.nome;
    }
    
    public void recebeCarta(Carta carta) 
    {
        this.mao.add(carta);
    }
    
    public Carta descartaCarta(Carta carta) 
    {
        return this.mao.remove(this.mao.indexOf(carta));
    }
    
    public ArrayList<Carta> getMao() 
    {
        return this.mao;
    }
}
