/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.JogoController;
import java.util.InputMismatchException;
import java.util.Scanner;
import model.Jogador;
import model.Lixo;

/**
 *
 * @author Gabriel
 */
public class JogoView {
    
    private final JogoController controller;

    /**
     * Obtém o controller
     * @param controller 
     */
    public JogoView(JogoController controller) {
        this.controller  = controller;
    }
    
    /**
     * Lê a quantidade de jogadores
     * @param min
     * @param max
     * @return 
     */
    public int intQtdJogadores(int min, int max) 
    {
        System.out.println("quantos jogadores teremos ?");
        int qtd = 0;
        do {
            Scanner scanner = new Scanner(System.in);
            try {
                qtd = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("valor inválido.");
            }
        } while (qtd < min || qtd > max);
        return qtd;
    }
    
    /**
     * Lê o nome do jogador
     * @param idx
     * @return 
     */
    public String InformeJogador(int idx) 
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Informe o nome do Jogador " + idx + ":");
        String nome = scanner.nextLine();
        return nome;
    }
    
    /**
     * Mostra o jogo
     * @param jogador
     * @param lixo
     * @param comprado 
     */
    public void mostrarJogo(Jogador jogador, Lixo lixo, boolean comprado)
    {
        System.out.println("=====================================");
        System.out.println("Jogador: "+ jogador.getNome());
        System.out.println("Lixo: " + lixo.mostrarUltimoDescarte());
        controller.fazerJogada(comprado);
    }
    
}
