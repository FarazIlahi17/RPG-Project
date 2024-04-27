package com.example.rpggameproject.Characters;

public class Assassin extends Character {

    private int crit_chance;
    private boolean isCrit;

     public  Assassin(){
         this.name = "Assassin";
         this.hp = 101;
         this.max_hp = 101;
         this.basic_attack = 15;
         this.crit_chance = 0;
         this.isCrit = false;
     }

    public int getBasic_attack(){
        return this.basic_attack;
    }

    public int getCrit_chance(){
         return this.crit_chance;
    }

    public boolean getisCrit() {
        return isCrit;
    }
    public void setisCrit(boolean b){
         this.isCrit = b;
    }

    public void updateBar() {
        this.crit_chance += 19;
    }

    public void resetBar(){
         this.crit_chance = 0;
    }
}