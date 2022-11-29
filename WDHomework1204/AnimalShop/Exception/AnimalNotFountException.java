package AnimalShop.Exception;

import Animal.Animal;
import AnimalShop.*;


/**
 * @author HP
 */
public class AnimalNotFountException extends BaseException{

    public AnimalNotFountException(MyAnimalShop shop, Class need, Bill bill){
        super();
        var sb=new StringBuilder();
        sb.append(shop.getName())
          .append("在受理顾客")
          .append(bill.getCustomer().getName())
          .append("的订单")
          .append(bill.getNo())
          .append("时库存中没有动物")
          .append(need.getName());
        System.err.println(sb);
    }

}
