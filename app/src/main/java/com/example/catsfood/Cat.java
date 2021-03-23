package com.example.catsfood;

public class Cat {
    public int id;
    public String name;
    public long age;
    public long weight;
    public boolean is_male;

    Cat(int id, String n, long a, long w, boolean i) {
        this.id = id;
        name = n;
        age = a;
        weight = w;
        is_male = i;
    }

    public double food(){
        return (age * 0.1 + weight * 0.3) * (is_male ? 1 : 0.9);
    }
}
