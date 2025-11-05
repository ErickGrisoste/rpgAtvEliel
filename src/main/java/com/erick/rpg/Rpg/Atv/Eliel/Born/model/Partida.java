package com.erick.rpg.Rpg.Atv.Eliel.Born.model;

import java.util.ArrayList;
import java.util.List;

public class Partida {

    private String id;
    private Personagem jogador;
    private Personagem inimigo;
    private Turno ultimoTurno;
    private List<Turno> historico = new ArrayList<>();
    private boolean terminou = false;
    private String vencedor;

    public Partida(Personagem jogador, Personagem inimigo) {
        this.jogador = jogador;
        this.inimigo = inimigo;
    }

    public Personagem getJogador() {
        return jogador;
    }

    public Personagem getInimigo() {
        return inimigo;
    }

    public List<Turno> getHistorico() {
        return historico;
    }

    public boolean isTerminou() {
        return terminou;
    }

    public String getVencedor() {
        return vencedor;
    }

    public Turno getUltimoTurno() {
        return ultimoTurno;
    }

    public void setUltimoTurno(Turno ultimoTurno) {
        this.ultimoTurno = ultimoTurno;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void encerrar(String vencedor){
        this.terminou = true;
        this.vencedor = vencedor;
    }



}
