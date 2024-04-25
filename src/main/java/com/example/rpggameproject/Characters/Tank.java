package com.example.rpggameproject.Characters;

public class Tank extends Character {

    private int block_chance;
    Tank(){
        this.name = "Tank";
        this.hp = 192;
        this.max_hp = 192;
        this.basic_attack = 7;
        this.block_chance = 0;

    }
    public int getBasic_attack(){
        return this.basic_attack;

    }

    public void updateBar(){
        this.block_chance += 25;
    }

    public void resetBar(){
        this.block_chance = 0;
    }



}