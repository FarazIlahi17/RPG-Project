package com.example.rpggameproject.Characters;

public class Tank extends Character {

    private int block_chance;
    private int spamAttack;
    public Tank(){
        this.name = "Tank";
        this.hp = 252;
        this.max_hp = this.hp;
        this.basic_attack = 30;
        this.block_chance = 100;
        this.spamAttack = 10;
    }
    public int getBasic_attack(){
        return this.basic_attack;
    }
    public int getSpamAttack(){
        return this.spamAttack;
    }

    public void updateBar(){
        this.block_chance += 25;
    }

    public void resetBar(){
        this.block_chance = 0;
    }
    public int getBlock_chance(){
        return this.block_chance;
    }



}