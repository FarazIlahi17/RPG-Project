package com.example.rpggameproject.Characters;

public class Mage extends Character {

    private int mana;
    private int mana_consume;
    private int special_attack;
    Mage(){
        this.name = "Mage";
        this.hp = 85;
        this.max_hp = 85;
        this.basic_attack = 18;
        this.mana = 100;
        this.mana_consume = 15;
        this.special_attack = 32;

    }


    public int getBasic_attack(){
        return this.basic_attack;
    }

    public int getSpecial_attack(){
        return this.special_attack;
    }

    public void updateBar(){
        this.mana -= this.mana_consume;
    }
}