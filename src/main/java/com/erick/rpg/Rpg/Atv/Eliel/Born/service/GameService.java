package com.erick.rpg.Rpg.Atv.Eliel.Born.service;

import com.erick.rpg.Rpg.Atv.Eliel.Born.dto.GameResponse;
import com.erick.rpg.Rpg.Atv.Eliel.Born.enums.Acao;
import com.erick.rpg.Rpg.Atv.Eliel.Born.model.*;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GameService {

    private Map<String, Partida> partidas = new HashMap<>();
    private boolean jogadorAtacando = true;

    public Partida iniciarPartida(String classeJogador){
        Personagem jogador = criarClasse(classeJogador);
        Personagem inimigo = criarClasseAleatoria();
        String id = UUID.randomUUID().toString().substring(0, 5);
        Partida partida = new Partida(jogador, inimigo);
        partida.setId(id);
        partidas.put(id, partida);
        return partida;
    }

    private Personagem criarClasse(String classeJogador) {
        return switch (classeJogador.toLowerCase()){
            case "mago" -> new Mago();
            case "arqueiro" -> new Arqueiro();
            default -> new Guerreiro();
        };
    }

    private Personagem criarClasseAleatoria() {
        List<String> classes = List.of("guerreiro", "mago", "arqueiro");
        String aleatoria = classes.get(new Random().nextInt(classes.size()));
        return criarClasse(aleatoria);
    }

    public GameResponse executarTurno(String idPartida, Acao acaoJogador){
        Partida partida = partidas.get(idPartida);
        if (partida == null || partida.isTerminou()) return null;

        Personagem jogador = partida.getJogador();
        Personagem inimigo = partida.getInimigo();

        if (!partida.getHistorico().isEmpty()){
            Turno ultimoTurno = partida.getHistorico().getLast();

            if (ultimoTurno.getAcaoJogador() == Acao.CURAR && acaoJogador == Acao.CURAR){
                Turno invalido = new Turno(acaoJogador, null, "Você não pode ser curar duas vezes seguidas!");
                partida.getHistorico().add(invalido);

                return new GameResponse(idPartida, "EM_ANDAMENTO", "Você não pode ser curar duas vezes seguidas!",
                        null,
                        new JogadorView(jogador.getNomeClasse(), jogador.getVida()),
                        new JogadorView(inimigo.getNomeClasse(), inimigo.getVida()),
                        null);
            }
        }

        Acao acaoInimigo = Math.random() < 0.5 ? Acao.ATACAR : Acao.CURAR;

        StringBuilder resultado = new StringBuilder();


        if (inimigo.getVida() <= 0){
            partida.encerrar("Jogador");
            resultado.append("Você venceu!");
        } else {

            if (jogadorAtacando) {
                aplicarAcao(jogador, inimigo, acaoJogador, resultado, jogadorAtacando);
            } else if (!jogadorAtacando){
                aplicarAcao(inimigo, jogador, acaoInimigo, resultado, jogadorAtacando);
            }

            jogadorAtacando = jogadorAtacando ? false : true;


            if (jogador.getVida() <= 0){
                partida.encerrar("Inimigo");
                resultado.append("Você perdeu!");
            }
        }

        Turno turno = new Turno(acaoJogador, acaoInimigo, resultado.toString());
        partida.setUltimoTurno(turno);
        partida.getHistorico().add(turno);

        String status = partida.isTerminou() ? "FINALIZADO" : "EM_ANDAMENTO";
        String vencedor = partida.isTerminou() ? partida.getVencedor() : null;
        String mensagem = partida.getHistorico().isEmpty() ? "" :
                partida.getHistorico().getLast().getResultado();

        List<String> historico = partida.getHistorico().stream()
                .map(Turno::getResultado)
                .toList();

        return new GameResponse(idPartida, status, mensagem, vencedor,
                new JogadorView(jogador.getNomeClasse(), jogador.getVida()),
                new JogadorView(inimigo.getNomeClasse(), inimigo.getVida()),
                historico);
    }

    private void aplicarAcao(Personagem atacante, Personagem alvo, Acao acao, StringBuilder resultado, boolean jogadorAtacando) {
        String quem = jogadorAtacando ? "Você" : "Inimigo";

        double chance = Math.random();
        double variacao = 0.5 + Math.random();

        if (acao == Acao.ATACAR){
            if (chance <= atacante.getTaxaSucesso()){

                int dano = (int) (atacante.getAtaque() * variacao) - alvo.getDefesa();
                if (dano < 0) dano = 0;
                alvo.setVida(alvo.getVida() - dano);
                resultado.append(quem).append(" atacou e causou ").append(dano).append(" pontos de dano. ");

            } else {
                resultado.append(quem).append(" errou o ataque! ");
            }

        } else if (acao == Acao.CURAR){

            if (chance <= atacante.getTaxaSucesso() && atacante.getVida() < atacante.getVidaCheia()){

                int cura = Math.min(atacante.getVida() + atacante.getCura(), atacante.getVidaCheia());

                atacante.setVida(cura);
                resultado.append(quem).append(" se curou em ").append(atacante.getCura()).append(" pontos de vida. ");

            } else {
                resultado.append(quem).append(" tentou se curar, mas falhou!");
            }
        }

    }
}
