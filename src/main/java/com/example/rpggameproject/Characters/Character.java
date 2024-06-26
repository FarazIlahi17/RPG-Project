package com.example.rpggameproject.Characters;

public abstract class Character{ ;
    protected String name;
    protected String id;
    protected int hp;
    protected int max_hp;
    protected int basic_attack;


    public String getName(){
        return this.name;
    }
    public String getId(){
        return this.id;
    }
    public void setId(String i){
        this.id = i;
    }

    public int getHp(){
        return this.hp;
    }
    public int getMax_hp(){
        return this.max_hp;
    }

    public abstract int getBasic_attack();

    public abstract void updateBar();
    @Override
    public String toString() {
        return (this.name + " is the ");
    }

    public void setheal(){
        double current_hp = this.hp;
        double max = this.max_hp;
        this.hp += (int)((1 - (current_hp / max)) * 20);
    }


    public void takeDamage(int damage){
        this.hp -= damage;
    }

    public void setName(String name){
        this.name = name;
    }
    public void resetHp(){
        this.hp = this.max_hp;
    }





}