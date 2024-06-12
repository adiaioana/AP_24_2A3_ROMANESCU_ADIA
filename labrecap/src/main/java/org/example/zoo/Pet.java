package org.example.zoo;

public abstract class Pet {
    public int age;

    public Pet(int x)
    {
        age=x;
    }
    public int getAge(){
        return age;
    }
}
