package com.erick.rpg.Rpg.Atv.Eliel.Born.model;

public class JogadorView {

    private String classe;
    private int vida;

    public JogadorView(String classe, int vida) {
        this.classe = classe;
        this.vida = vida;
    }

    public String getClasse() {
        return classe;
    }

    public int getVida() {
        return vida;
    }
}
