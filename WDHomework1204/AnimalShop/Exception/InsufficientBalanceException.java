package AnimalShop.Exception;

import Animal.Animal;
import AnimalShop.AnimalShop;
import AnimalShop.MyAnimalShop;

import java.nio.MappedByteBuffer;

/**
 * @author HP
 */
public class InsufficientBalanceException extends BaseException{
    public InsufficientBalanceException(MyAnimalShop shop, double balance, Animal animal, int quantity){
        super();
        var sb=new StringBuilder();
        sb .append(shop.getName())
            .append("的余额为")
            .append(balance)
            .append("，缺少")
            .append(animal.getCost()*quantity)
            .append("来购买")
            .append(quantity)
            .append("只")
            .append(animal.getClass().getName());
        System.err.println(sb);
    }
}
