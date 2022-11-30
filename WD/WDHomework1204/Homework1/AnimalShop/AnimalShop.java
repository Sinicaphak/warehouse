package AnimalShop;

import Animal.Animal;
import Customer.Customer;

import java.time.LocalDate;

public interface AnimalShop {
    /**
     * 购买
     * @param animal
     * @param quantity
     */
    void buy(Animal animal,int quantity);

    /**
     * 招待客户
     * @param customer
     */
    void entertainCustomers(Customer customer,Bill bill);

    /**
     * 歇业
     */
    void closure();

    void closure(LocalDate localDate);

}
