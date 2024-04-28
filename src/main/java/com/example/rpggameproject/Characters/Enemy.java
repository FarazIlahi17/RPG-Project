package com.example.rpggameproject.Characters;

public class Enemy extends Character {
    private boolean isAttacking;
    public Enemy(){
        this.name = "EnemyDude";
        this.hp = 222;
        this.max_hp = this.hp;
        this.basic_attack = 20;
        this.isAttacking = (int)(Math.random() * 11) <= 4;
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



