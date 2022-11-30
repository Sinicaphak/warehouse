package Test;
import Animal.*;
import AnimalShop.*;
import Customer.Customer;

import java.time.LocalDate;

import static java.lang.Thread.sleep;

public class Test {
    public static void main(String[] args) throws InterruptedException {
        var shop=new MyAnimalShop(114514, true, "myShop");
        shop.buy(Cat.createCat("AAA",10,Gender.FEMALE),20);
        shop.buy(ChineseGardenDog.createChineseGardenDog("BBB", 5, Gender.MALE,true), 10);
        shop.buy(Snail.createSnail("CCC", 1), 5);
        // shop.buy(Snail.createSnail(, ), 889464); //应用此行代码时会抛出InsufficientBalanceException

        var xiaoMing =new Customer("XiaoMing", LocalDate.now());
        var xiaoWan =new Customer("xiaoWan", LocalDate.now());
        var xioaHong=new Customer("xiaoHong", LocalDate.now());

        var bill1=new Bill(xiaoMing);
        bill1.record(Snail.createSnail(), 1);
        bill1.record(Cat.createCat(), 5);
        bill1.record(ChineseGardenDog.createChineseGardenDog(), 2);

        var bill2=new Bill(xiaoWan);
        bill2.record(Cat.createCat(),10);

        var bill3=new Bill(xioaHong);
        bill3.record(ChineseGardenDog.createChineseGardenDog(), 8);
        /**
         * 应用 bill4 时会抛出AnimalNotFountException
         */
        var bill4=new Bill(xiaoMing);
        bill4.record(Snail.createSnail(), 5);
        bill4.record(ChineseGardenDog.createChineseGardenDog(), 1);


        shop.entertainCustomers(xiaoMing, bill1);
        shop.entertainCustomers(xiaoWan, bill2);
        shop.entertainCustomers(xioaHong, bill3);

        shop.closure();
    }
}
