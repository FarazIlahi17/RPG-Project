package com.example.rpggameproject.Characters;

public abstract class Character{
    protected String name;
    protected int hp;

    protected int max_hp;
    protected int basic_attack;


    public String getName(){
        return this.name;
    }

    public int getHp(){
        return this.hp;
    }

    public abstract int getBasic_attack();

    public abstract void updateBar();

    public void heal(){
        double current_hp = this.hp;
        double max = this.max_hp;
        this.hp += (int)(1 - (current_hp / max) * .1);
    }





}