package Customer;

import AnimalShop.Bill;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Customer {
    private String name;
    private int arriveFrequency=0;
    private LocalDate latestArrival;
    private List<Bill> billList=new ArrayList<>();

    public Customer(String name, LocalDate latestArrival) {
        this.name = name;
        this.latestArrival = latestArrival;
    }
    public void recordBill(Bill bill){
        billList.add(bill);
    }
    @Override
    public String toString() {
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append("客户姓名:")
                .append(name)
                .append("\n到店次数:")
                .append(arriveFrequency)
                .append("\n最新到店时间:")
                .append(latestArrival);
        return stringBuilder.toString();
    }

    public String getName() {
        return name;
    }

    public void setLatestArrival(){
        latestArrival=LocalDate.now();
        arriveFrequency++;
    }

    public static Customer create(String name, LocalDate latestArrival){
        return new Customer(name,latestArrival);
    }
}
