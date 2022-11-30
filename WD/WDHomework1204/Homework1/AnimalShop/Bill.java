package AnimalShop;

import Animal.*;
import Customer.Customer;

import java.util.*;

public class Bill {
    private Map<Class,Integer> billMap=new HashMap<>();
    private UUID no;
    private Customer customer;

    public Bill(Customer customer) {
        this.customer = customer;
        no=UUID.randomUUID();
        customer.recordBill(this);
    }

    public Customer getCustomer() {
        return customer;
    }

    public void record(Animal animal, int quantity){
        billMap.put(animal.getClass(), quantity);
    }

    public Map<Class, Integer> getBill() {
        return billMap;
    }

    public UUID getNo() {
        return no;
    }
}
