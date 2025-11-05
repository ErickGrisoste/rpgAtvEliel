package com.erick.rpg.Rpg.Atv.Eliel.Born.controller;

import com.erick.rpg.Rpg.Atv.Eliel.Born.dto.GameResponse;
import com.erick.rpg.Rpg.Atv.Eliel.Born.enums.Acao;
import com.erick.rpg.Rpg.Atv.Eliel.Born.model.Partida;
import com.erick.rpg.Rpg.Atv.Eliel.Born.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rpg")
public class GameController {

    @Autowired
    private GameService gameService;

    @PostMapping("/iniciar")
    public Partida iniciar(@RequestParam String classe){
        return gameService.iniciarPartida(classe);
    }

    @PostMapping("/acao")
    public GameResponse jogar(@RequestParam String idPartida, @RequestParam Acao acao){
        return gameService.executarTurno(idPartida, acao);
    }

}
