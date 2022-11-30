package Animal;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class Animal {
    /**
     * registry：记录可能出现的所有动物
     */
    public static List<Animal> registry=new ArrayList<>();

    protected String name;
    protected int age;
    protected Gender sexual;
    protected double cost;
    protected double price;
    protected long id;


    public Animal(String name, int age, Gender sexual, double price, double cost) {
        this.name = name;
        this.age = age;
        this.sexual = sexual;
        this.cost=cost;
        this.price = price;
        id=System.currentTimeMillis();
    }

    public Animal(double price,double cost) {
        this.price=price;
        this.cost=cost;
    }


    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Animal animal){
            if (animal.getId()==this.getId()){
                return true;
            }
        }
        return false;
    }
    @Override
    public int hashCode() {
        return Objects.hash(name, age, sexual, price,id);
    }
    public abstract String toString();
    public String getName() {
        return name;
    }
    public int getAge() {
        return age;
    }
    public Gender getSexual() {
        return sexual;
    }
    public double getPrice() {
        return price;
    }

    public double getCost() {
        return cost;
    }
    public long getId() {
        return id;
    }
}
