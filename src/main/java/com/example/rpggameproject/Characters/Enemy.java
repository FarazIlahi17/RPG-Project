package com.example.rpggameproject.Characters;

public class Enemy extends Character {
    private boolean isAttacking;
    public Enemy(){
        this.name = "EnemyDude";
        this.hp = 150;
        this.max_hp = 150;
        this.basic_attack = 25;
        this.isAttacking = true;
    }

    public int getBasic_attack(){
        return this.basic_attack;

    }
    public boolean getisAttacking(){
        return this.isAttacking;
    }
    public void setAttacking(boolean b){
        this.isAttacking = b;
    }

    public void updateBar() {

    }
}



