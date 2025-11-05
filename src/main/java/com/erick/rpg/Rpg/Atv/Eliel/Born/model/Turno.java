package com.erick.rpg.Rpg.Atv.Eliel.Born.model;

import com.erick.rpg.Rpg.Atv.Eliel.Born.enums.Acao;

public class Turno {

    private Acao acaoJogador;
    private Acao acaoInimigo;
    private String resultado;

    public Turno(Acao acaoJogador, Acao acaoInimigo, String resultado) {
        this.acaoJogador = acaoJogador;
        this.acaoInimigo = acaoInimigo;
        this.resultado = resultado;
    }

    public Acao getAcaoJogador() {
        return acaoJogador;
    }

    public Acao getAcaoInimigo() {
        return acaoInimigo;
    }

    public String getResultado() {
        return resultado;
    }
}
