package com.erick.rpg.Rpg.Atv.Eliel.Born.dto;

import com.erick.rpg.Rpg.Atv.Eliel.Born.model.JogadorView;

import java.util.List;

public record GameResponse(
        String idPartida,
        String status,
        String mensagem,
        String vencedor,
        JogadorView jogador,
        JogadorView inimigo,
        List<String> historico) {
}
