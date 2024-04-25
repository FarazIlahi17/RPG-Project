package com.example.rpggameproject.Characters;

public class Knight extends Character {

    private int charge_bar;

    private int special_attack;
    public Knight(){
        this.name = "Knight";
        this.hp = 137;
        this.max_hp = 137;
        this.basic_attack = 13;
        this.charge_bar = 0;
        this.special_attack = 38;

    }
    public int getBasic_attack(){
        return this.basic_attack;

    }

    public int getSpecial_attack(){
        return this.special_attack;
    }

    public void updateBar(){
        this.charge_bar += 20;
    }

    public void resetBar(){
        this.charge_bar = 0;
    }




}