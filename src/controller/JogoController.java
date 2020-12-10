package controller;

import java.util.ArrayList;
import model.Baralho;
import model.Carta;
import model.Jogador;
import model.Lixo;
import view.JogadorView;
import view.JogoView;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Gabriel
 */
public class JogoController {
    
    private final ArrayList<Jogador> jogadores = new ArrayList();
    private final Lixo lixo = new Lixo();
    private final Baralho baralho = new Baralho();
    private final JogadorView jogadorView = new JogadorView(this);
    private final JogoView jogoView = new JogoView(this);
    private int qtdJogadores;
    private int jogAtual = 0;

    /**
     * Inicia o jogo
     * 
     */
    public void iniciarJogo() 
    {
        qtdJogadores = jogoView.intQtdJogadores(2, 9);
        registrarJogadores();
        distribuirCartas();
        iniciarLixo();
    }
    
    /**
     * Distribui 7 cartas para cada jogador
     * 
     */
    public void distribuirCartas()
    {
        for (int i = 0; i < 7; i++) {
            for (int v = 0; v < qtdJogadores; v++) {
                jogadores.get(v).recebeCarta(baralho.comprar());
            }
        }
    }
    
    /**
     * Inicia o lixo
     * Obs: a primeira carta não poderá ser especial nem preta
     */
    public void iniciarLixo()
    {
        Carta cartaLixo;
        do {
            cartaLixo = baralho.comprar();
            lixo.recebeDescarte(cartaLixo);
        } while (cartaLixo.getEfeito().length() > 0 || cartaLixo.getCor().equals("preto"));
    }
    
    /**
     * Registra os jogadores
     * 
     */
    public void registrarJogadores() 
    {
        for (int i = 1; i <= qtdJogadores; i++) {
            String n = jogoView.InformeJogador(i);
            jogadores.add(new Jogador(n));
        }
    }

    /**
     * Iniciar rodada
     * 
     */
    public void iniciarRodada() 
    {
        jogoView.mostrarJogo(jogadores.get(jogAtual), lixo, false);
    }
    
    /**
     * Comprar nova carta
     * 
     */
    public void comprarCarta()
    {
        // Pega o jogador
        Jogador jogador = jogadores.get(jogAtual);
        
        // Jogador compra uma nova carta
        jogador.recebeCarta(baralho.comprar());
                
        // Verifica se o baralho acabou
        baralho.pegarCartasLixo(lixo);
        
        // Mostra o jogo
        jogoView.mostrarJogo(jogador, lixo, true);
    }
    
    /**
     * Descartar carta
     * @param cartaDescartadaIndex 
     */
    public void descartarCarta(int cartaDescartadaIndex)
    {
        // Pega o jogador
        Jogador jogador = jogadores.get(jogAtual);
        
        // Busca a carta descartada de acordo com o indice
        Carta cartaDescartada = jogador.getMao().get(cartaDescartadaIndex);
        
        // Remove a carta informada da mão do jogador
        jogador.descartaCarta(cartaDescartada);
        
        // Adiciona a carta descartada no lixo
        lixo.recebeDescarte(cartaDescartada);
        
        // Continua a sequencia se informar um +2 ou +4 ou compra o somatório de +2 ou +4
        cartaDescartada.aplicarSomatorio(jogador, lixo, baralho);
        
        // Escolhe a cor se for descartado uma carta preta
        if (lixo.mostrarUltimoDescarte().getCor().equals("preto")) {
            jogadorView.escolherCor(lixo);
        }
        
        // Próximo jogador
        proximoJogador(true);
    }
           
    /**
     * Chama a view para fazer a jogada
     * 
     * @param comprado
     */
    public void fazerJogada(boolean comprado)
    {
        // Pega o jogador
        Jogador jogador = jogadores.get(jogAtual);
                
        // Verifica se o jogador quer descartar uma carta
        jogadorView.fazerJogada(jogador, lixo, comprado);
    }
    
    /**
     * Avança para o próximo jogador
     * 
     * @param novoDescarte
     */
    public void proximoJogador(boolean novoDescarte)
    {
        // Se há um vencedor, exibe a view e finaliza o jogo
        if (jogadorView.verificarVencedor(jogadores.get(jogAtual))) System.exit(0);
       
        // Verifica se o jogador está em uno
        jogadorView.gritarUno(jogadores.get(jogAtual));
        
        // Seta o indice do proximo jogador
        jogAtual++;
        if (jogAtual == qtdJogadores) {
            jogAtual = 0;
        }
        
        // Aplica efeitos da ultima carta jogada (se existir) e teve um novo descarte
        if (novoDescarte) {
            jogAtual = lixo.mostrarUltimoDescarte().aplicarEfeito(jogadores, jogAtual);
        }
        
        // Inicia próxima rodada
        iniciarRodada();
    }
}
