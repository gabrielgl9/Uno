/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.JogoController;
import java.util.Scanner;
import model.Carta;
import model.Jogador;
import model.Lixo;

/**
 *
 * @author Gabriel
 */
public class JogadorView {
    
    private final JogoController controller;

    /**
     * Recebe controlador
     * @param controller 
     */
    public JogadorView(JogoController controller) 
    {
        this.controller  = controller;
    }
    
    /**
     * Verifica se há um vencedor e exibe uma mensagem caso haja
     * @param jogador
     * @return 
     */
    public boolean verificarVencedor(Jogador jogador)
    {
        if (jogador.getMao().isEmpty()) {
            System.out.println("Parabéns " + jogador.getNome() + " você venceu esta partida!");
            return true;
        }
                
        return false;
    }
    
    /**
     * Exibe mensagem de uno se o jogador possuir somente uma carta
     * @param jogador 
     */
    public void gritarUno(Jogador jogador)
    {
        if (jogador.getMao().size() == 1) {
            System.out.println("O jogador " + jogador.getNome() + " gritou: UUUUNOOOOOOOOOOOOOO!!!!!!");
        }
    }
    
    /**
     * Escolhe a cor da carta preta
     * @param lixo 
     */
    public void escolherCor(Lixo lixo)
    {
        System.out.println("Informe uma cor: \n1: Amarelo\n2: Azul\n3: Verde\n4: Vermelho");
        int resp = 0;
        Scanner scan = new Scanner(System.in);
        do {
            resp = scan.nextInt();
        } while (resp < 1 || resp > 4);
        
        switch (resp) {
            case 1:
                lixo.mostrarUltimoDescarte().setCor("amarelo");
            break;
            case 2:
                lixo.mostrarUltimoDescarte().setCor("azul");
            break;
            case 3:
                lixo.mostrarUltimoDescarte().setCor("verde");
            break;
            case 4:
                lixo.mostrarUltimoDescarte().setCor("vermelho");
            break;
        }
    }
    
    /**
     * O jogador faz a jogada informando o indice desejado a partir da tela mostrada
     * @param jogador
     * @param lixo
     * @param comprado 
     */
    public void fazerJogada(Jogador jogador, Lixo lixo, boolean comprado)
    {
        System.out.println("_____________________________");
        for (int i  = 0; i < jogador.getMao().size(); i++) {
            System.out.println("|    " + (i + 1) + " - " + jogador.getMao().get(i).obterDescricao());
            System.out.println("_____________________________");
        }

        if (comprado) {
            System.out.println("|    " + (jogador.getMao().size() + 1) + " - Passar");
            System.out.println("_____________________________");
        } else {
            System.out.println("|    " + (jogador.getMao().size() + 1) + " - Desejo comprar");
            System.out.println("_____________________________");
        }

        Scanner scan = new Scanner(System.in);
        System.out.println("Faça sua jogada: ");
        int resp = 0;
        
        Carta cartaLixo = lixo.mostrarUltimoDescarte();
        do {
            resp = scan.nextInt();
            
            // Caso o jogador tenha escolhido comprar
            if (jogador.getMao().size() + 1 == resp && !comprado) {
                controller.comprarCarta();
                return;
            }
            
            // Caso o jogador tenha escolhido passar
            if (jogador.getMao().size() + 1 == resp && comprado) {
                controller.proximoJogador(false);
                return;
            }
            
            // Força o jogador repetir a jogada se a carta tiver uma cor diferente do lixo
            // Ou se a carta não for preta
            // Ou se a carta não tiver o mesmo simbolo
            if (resp >= 1 && resp <= jogador.getMao().size()) {
                Carta cartaMao = jogador.getMao().get(resp - 1);       
                if (! (cartaMao.getCor().equals(cartaLixo.getCor()) || 
                       cartaMao.getCor().equals("preto") || 
                       cartaMao.getSimbolo().equals(cartaLixo.getSimbolo()))
                    ) {
                    resp = 0;
                }
            }
            
        } while (resp < 1 || resp > jogador.getMao().size());
        
        controller.descartarCarta(resp - 1);
    }

}
