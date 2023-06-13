package model;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Set;

/**
 *
 * @author Nguyễn Huy Phúc
 */
public class Order implements Serializable{

    private int cost;
    private OrderHeader orH;
    private ArrayList<OrderDetail> orD;

    public Order(OrderHeader orH, ArrayList<OrderDetail> orD) {
        this.orH = orH;
        this.orD = orD;
    }

    public ArrayList<OrderDetail> getOrD() {
        return orD;
    }

    public void setOrD(ArrayList<OrderDetail> orD) {
        this.orD = orD;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public OrderHeader getOrH() {
        return orH;
    }

    public void setOrH(OrderHeader orH) {
        this.orH = orH;
    }


    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("$ ###,###,###");
        return String.format("%s|%15d|%15s", orH.toString(), getOrD().size(),df.format(cost));
    }

}
