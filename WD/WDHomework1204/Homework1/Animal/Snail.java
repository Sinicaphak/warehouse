package Animal;

public class Snail extends Animal {

    public static void register(){
        Animal.registry.add(new Snail());
    }

    public Snail(String name, int age) {
        super(name, age, Gender.UNISEXUAL, 19.19,8.10);
    }

    public Snail() {super(19.19,8.10);}

    @Override
    public String toString() {
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append("\n品种:")
                .append("蜗牛")
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
    public static Snail createSnail(String name,int age){
        return new Snail(name, age);
    }
    public static Snail createSnail(){
        return new Snail();
    }

}
