package Animal;

public class Cat extends Animal {
    public static void register(){
        Animal.registry.add(new Cat());
    }
    public Cat(String name, int age, Gender sexual) {
        super(name, age, sexual, 200,100);
    }
    public Cat(){super(200,100);}

    @Override
    public String toString() {
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append("\n品种:")
                .append("猫猫")
                .append("\n名字:")
                .append(name)
                .append("\n年龄:")
                .append(age)
                .append("\n性别:")
                .append(sexual)
                .append("\n价格:")
                .append(price)
                .append("\n");
        return stringBuilder.toString();
    }

    public static Cat createCat(String name, int age, Gender sexual){
        return new Cat(name,age,sexual);
    }
    public static Cat createCat(){
        return new Cat();
    }
}
