package AnimalShop;

import Animal.*;
import AnimalShop.Exception.AnimalNotFountException;
import AnimalShop.Exception.InsufficientBalanceException;
import Customer.Customer;

import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MyAnimalShop implements AnimalShop{
    private LocalDate closingTime=LocalDate.of(2022, 12, 31);
    private String name;
    private double balance;
    private double initialBalance;
    /**
     * typeList：二维列表，第一层存储各类动物列表，第二层存储动物对象
     */
    private List<LinkedList<Animal>> typeList =new ArrayList<>();
    private List<Customer> customerList=new ArrayList();
    private boolean isOpen;

    public MyAnimalShop(double balance,boolean isOpen,String name) {
        this.balance = balance;
        this.isOpen = isOpen;
        this.name=name;
        this.initialBalance=balance;
        /**
         * 注册可能出现的动物类型
         */
        Cat.register();
        ChineseGardenDog.register();
        Snail.register();
        for (int i=0;i<Animal.registry.size();i++){
            LinkedList<Animal> animalList=new LinkedList<Animal>();
            animalList.add(Animal.registry.get(i));
            typeList.add(animalList);
        }
    }
    @Override
    public void buy(Animal animal, int quantity) throws InsufficientBalanceException{
        if (quantity*animal.getPrice()>balance) {
            throw new InsufficientBalanceException(this,balance,animal,quantity);
        }
        addAnimal(animal,quantity);
        balance-=quantity*animal.getCost();
    }
    @Override
    public void entertainCustomers(Customer customer,Bill bill) {
        customer.setLatestArrival();
        customerList.add(customer);
        checkBill(bill);
    }
    @Override
    public void closure() {
        isOpen=false;
        for (int i=0;i<customerList.size();i++){
            System.out.println(customerList.get(i).toString());
        }
        System.out.println("今日利润："+String.format("%.2f", balance-initialBalance));
    }
    @Override
    public void closure(LocalDate localDate) {
        if (localDate.isAfter(closingTime)){
            isOpen=false;
            for (int i=0;i<customerList.size();i++){
                System.out.println(customerList.get(i).toString());
            }
            System.out.println("今日利润："+String.format("%.2f", balance-initialBalance));
        }
    }

    private void addAnimal(Animal animal, int quantity){
        if (animal instanceof Cat cat){
            for (int j=0;j<quantity;j++){
                typeList.get(0).add(cat);
            }
        }else if ( animal instanceof ChineseGardenDog chineseGardenDog) {
            for (int j=0;j<quantity;j++){
                typeList.get(1).add(chineseGardenDog);
            }
        }else if (animal instanceof Snail snail){
            for (int j=0;j<quantity;j++){
                typeList.get(2).add(snail);
            }
        }
    }

    private void checkBill(Bill bill){
        for (Map.Entry entry:bill.getBill().entrySet()) {
            Class need =(Class) entry.getKey();
            int needQuantity = (Integer) entry.getValue();
            int searchTime =1;

            for (LinkedList<Animal> list:typeList) {
                if (need.equals(list.get(0).getClass()) ){
                    if (needQuantity <= ( list.size() -1 ) ) {
                        for (int i=0;i<needQuantity;i++){
                            System.out.println(list.get(list.size()-1).toString());
                            list.remove(list.size()-1);
                        }
                        balance += list.get(0).getPrice()*needQuantity;
                    }else {
                        if (list.size()==1){
                            throw new AnimalNotFountException(this,need,bill);
                        }
                        System.out.println("处理订单号"+bill.getNo()+"时动物："+need.getName()+"数目不足，本次交易不出售该类动物");
                    }
                }else {
                    searchTime++;
                }
            }
            if (searchTime > typeList.size()) {
                throw new AnimalNotFountException(this,need,bill);
            }
        }
    }


    public double getBalance() {
        return balance;
    }
    public boolean isIsOpen() {
        return isOpen;
    }
    public String getName() {
        return name;
    }
}
