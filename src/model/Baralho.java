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
public class Baralho {
    
    private final ArrayList<Carta> baralho = new ArrayList();
    
    /**
     * Cria o baralho
     * 
     */
    public Baralho()
    {
        // Repete as cartas 2x
        for (int x = 0; x < 2; x++) {
            
            // Insere cartas de 0 a 9
            for (int i = 0; i < 3; i++) {
                baralho.add(new CartaAzul(Integer.toString(i), ""));
                baralho.add(new CartaAmarela(Integer.toString(i), ""));
                baralho.add(new CartaVermelha(Integer.toString(i), ""));
                baralho.add(new CartaVerde(Integer.toString(i), ""));
            }
            
            // Insere as cartas de efeito para cor azul
            baralho.add(new CartaAzul("Inverter", "inverte"));
            baralho.add(new CartaAzul("Bloquear", "bloqueia"));
            baralho.add(new CartaAzul("+2", "+2"));

            // Insere as cartas de efeito para cor amarela
            baralho.add(new CartaAmarela("Inverter", "inverte"));
            baralho.add(new CartaAmarela("Bloquear", "bloqueia"));
            baralho.add(new CartaAmarela("+2", "+2"));

            // Insere as cartas de efeito para cor vermelha
            baralho.add(new CartaVermelha("Inverter", "inverte"));
            baralho.add(new CartaVermelha("Bloquear", "bloqueia"));
            baralho.add(new CartaVermelha("+2", "+2"));    

            // Insere as cartas de efeito para cor verde
            baralho.add(new CartaVerde("Inverter", "inverte"));
            baralho.add(new CartaVerde("Bloquear", "bloqueia"));
            baralho.add(new CartaVerde("+2", "+2"));
        
            baralho.add(new CartaPreta("+4", "+4"));
            baralho.add(new CartaPreta("Mudar cor", ""));
            
        }
        
        // Embaralha
        Collections.shuffle(baralho);
    }
    
    /**
     * Remove uma carta do baralho para entregar às mãos de um jogador
     * @return 
     */
    public Carta comprar()
    {
        return this.baralho.remove(0);
    }
    
    /**
     * Quando acabar as cartas do baralho, deve-se pegar as cartas do lixo e embaralhá-las novamente
     * @param lixo 
     */
    public void pegarCartasLixo(Lixo lixo)
    {
        // Verifica se o baralho está vazio
        if (!baralho.isEmpty()) return;
                
        // Guarda a ultima carta em uma variavel de auxílio
        Carta lixoTmp = lixo.getLixo().remove(lixo.getLixo().size() - 1);
        
        // Adiciona todas as cartas do lixo no baralho
        baralho.addAll(lixo.getLixo());

        // Embaralha
        Collections.shuffle(baralho);
        
        // Remove todas as cartas do lixo
        lixo.removerLixo();
        
        // Adiciona no lixo a carta armazenada na variável de auxílio
        lixo.recebeDescarte(lixoTmp);
        
    }
}
