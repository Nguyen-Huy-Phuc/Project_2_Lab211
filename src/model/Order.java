package model;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Set;

/**
 * Đại diện cho một đơn hàng.
 * 
 * - cost: tổng giá trị đơn hàng
 * - orH: thông tin header của đơn hàng
 * - orD: danh sách các chi tiết đơn hàng
 * 
 * Đối tượng Order bao gồm thông tin header và danh sách chi tiết đơn hàng.
 * Cung cấp các phương thức để truy xuất và thiết lập các thông tin của đơn hàng.
 * 
 * @author Nguyễn Huy Phúc
 */
public class Order implements Serializable {

    private int cost;
    private OrderHeader orH;
    private ArrayList<OrderDetail> orD;

    /**
     * Khởi tạo một đối tượng Order.
     *
     * @param orH thông tin header của đơn hàng
     * @param orD danh sách các chi tiết đơn hàng
     */
    public Order(OrderHeader orH, ArrayList<OrderDetail> orD) {
        this.orH = orH;
        this.orD = orD;
    }

    /**
     * Trả về tổng giá trị của đơn hàng.
     *
     * @return tổng giá trị của đơn hàng
     */
    public int getCost() {
        return cost;
    }

    /**
     * Trả về thông tin header của đơn hàng.
     *
     * @return thông tin header của đơn hàng
     */
    public OrderHeader getOrH() {
        return orH;
    }

    /**
     * Trả về danh sách chi tiết đơn hàng.
     *
     * @return danh sách chi tiết đơn hàng
     */
    public ArrayList<OrderDetail> getOrD() {
        return orD;
    }

    /**
     * Thiết lập tổng giá trị của đơn hàng.
     *
     * @param cost tổng giá trị của đơn hàng
     */
    public void setCost(int cost) {
        this.cost = cost;
    }

    /**
     * Thiết lập thông tin header của đơn hàng.
     *
     * @param orH thông tin header của đơn hàng
     */
    public void setOrH(OrderHeader orH) {
        this.orH = orH;
    }

    /**
     * Thiết lập danh sách chi tiết đơn hàng.
     *
     * @param orD danh sách chi tiết đơn hàng
     */
    public void setOrD(ArrayList<OrderDetail> orD) {
        this.orD = orD;
    }

    /**
     * Trả về chuỗi biểu diễn của đơn hàng.
     *
     * @return chuỗi biểu diễn của đơn hàng
     */
    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("$ ###,###,###");
        return String.format("%s|%15d|%15s", orH.toString(), getOrD().size(), df.format(cost));
    }

}
