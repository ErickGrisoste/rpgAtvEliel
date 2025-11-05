package com.erick.rpg.Rpg.Atv.Eliel.Born.model;

public class Personagem {

    private String nomeClasse;
    private int vida;
    private int ataque;
    private int defesa;
    private int cura;
    private double taxaSucesso;
    private int vidaCheia;

    public Personagem(String nomeClasse, int vida, int ataque, int defesa, int cura, double taxaSucesso, int vidaCheia) {
        this.nomeClasse = nomeClasse;
        this.vida = vida;
        this.ataque = ataque;
        this.defesa = defesa;
        this.cura = cura;
        this.taxaSucesso = taxaSucesso;
        this.vidaCheia = vidaCheia;
    }

    public String getNomeClasse() {
        return nomeClasse;
    }

    public void setNomeClasse(String nomeClasse) {
        this.nomeClasse = nomeClasse;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public int getAtaque() {
        return ataque;
    }

    public void setAtaque(int ataque) {
        this.ataque = ataque;
    }

    public int getDefesa() {
        return defesa;
    }

    public void setDefesa(int defesa) {
        this.defesa = defesa;
    }

    public int getCura() {
        return cura;
    }

    public void setCura(int cura) {
        this.cura = cura;
    }

    public double getTaxaSucesso() {
        return taxaSucesso;
    }

    public void setTaxaSucesso(double taxaSucesso) {
        this.taxaSucesso = taxaSucesso;
    }

    public int getVidaCheia() {
        return vidaCheia;
    }

    public void setVidaCheia(int vidaCheia) {
        this.vidaCheia = vidaCheia;
    }
}
