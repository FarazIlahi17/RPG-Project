package com.example.rpggameproject.Characters;

public class Enemy extends Character {
    public Enemy(){
        this.name = "EnemyDude";
        this.hp = 222;
        this.max_hp = this.hp;
        this.basic_attack = 20;
    }

    public int getBasic_attack(){
        return this.basic_attack;

    }

    public void updateBar() {

    }
}



