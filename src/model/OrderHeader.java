package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Nguyễn Huy Phúc
 */
public class OrderHeader implements Serializable{

    private String orderID;
    private LocalDate orderDate;
    private String customerName;

    public OrderHeader(String orderID, String orderDate, String customerName) {
        this.orderID = orderID;
        setOrderDate(orderDate);
        this.customerName = customerName;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        LocalDate date = LocalDate.parse(orderDate, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        this.orderDate = date;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    
  

    @Override
    public String toString() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return String.format("|%-10s|%15s|%-15s",getOrderID(), dtf.format(getOrderDate()), getCustomerName());
    }
    
}
