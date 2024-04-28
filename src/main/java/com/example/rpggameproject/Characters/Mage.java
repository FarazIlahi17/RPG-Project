package com.example.rpggameproject.Characters;

public class Mage extends Character {

    private int mana;
    private int max_mana;
    private int mana_consume;
    private int special_attack;

    public  Mage(){
        this.name = "Mage";
        this.hp = 100;
        this.max_hp = this.hp;
        this.basic_attack = 25;
        this.mana = 120;
        this.max_mana = this.mana;
        this.mana_consume = 15;
        this.special_attack = 75;
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
    public void resetBar(){
        this.mana = this.max_mana;
    }
    public void manaHealed(){
        this.mana += 30;
    }
    public int getMana(){
        return this.mana;
    }
    public int getMax_mana(){
        return this.max_mana;
    }
}