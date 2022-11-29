package Animal;

public class ChineseGardenDog extends Animal {
    boolean isVaccineInjected;
    public static void register(){
        Animal.registry.add(new ChineseGardenDog());
    }
    public ChineseGardenDog(String name, int age, Gender sexual,boolean isVaccineInjected) {
        super(name, age, sexual, 100,50);
        this.isVaccineInjected=isVaccineInjected;
    }

    public ChineseGardenDog() {super(100,50);}

    @Override
    public String toString() {
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append("\n品种:")
                .append("中华田园犬")
                .append("\n名字:")
                .append(name)
                .append("\n年龄:")
                .append(age)
                .append("\n性别:")
                .append(sexual)
                .append("\n价格:")
                .append(price)
                .append("\n是否接种疫苗:")
                .append(isVaccineInjected)
                .append("\n");
        return stringBuilder.toString();
    }

    public boolean getVaccineInjected() {
        return isVaccineInjected;
    }

    public static ChineseGardenDog createChineseGardenDog(String name, int age, Gender sexual,boolean isVaccineInjected){
        return new ChineseGardenDog(name,age,sexual,isVaccineInjected);
    }
    public static ChineseGardenDog createChineseGardenDog(){
        return new ChineseGardenDog();
    }
}
