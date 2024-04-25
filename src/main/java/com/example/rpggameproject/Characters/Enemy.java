package com.example.rpggameproject.Characters;

public class Enemy extends Character {
    Enemy(){
        this.name = "EnemyDude";
        this.hp = 150;
        this.max_hp = 150;
        this.basic_attack = 25;

    }

    public int getBasic_attack(){
        return this.basic_attack;

    }

    public void updateBar() {

    }
}



