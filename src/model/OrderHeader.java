package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Đại diện cho thông tin header của đơn hàng.
 * 
 * - orderID: mã ID của đơn hàng
 * - orderDate: ngày đặt hàng
 * - customerName: tên khách hàng
 * 
 * Đối tượng OrderHeader chứa thông tin về mã ID của đơn hàng, ngày đặt hàng và tên khách hàng.
 * Cung cấp các phương thức để truy xuất và thiết lập thông tin header của đơn hàng.
 * Đồng thời, cung cấp phương thức toString để trả về chuỗi biểu diễn của thông tin header đơn hàng.
 * 
 * 
 * author Nguyễn Huy Phúc
 */
public class OrderHeader implements Serializable {

    private String orderID;
    private LocalDate orderDate;
    private String customerName;

    /**
     * Khởi tạo một đối tượng OrderHeader.
     *
     * @param orderID      mã ID của đơn hàng
     * @param orderDate    ngày đặt hàng (dạng chuỗi "dd/MM/yyyy")
     * @param customerName tên khách hàng
     */
    public OrderHeader(String orderID, String orderDate, String customerName) {
        this.orderID = orderID;
        setOrderDate(orderDate);
        this.customerName = customerName;
    }

    /**
     * Trả về mã ID của đơn hàng.
     *
     * @return mã ID của đơn hàng
     */
    public String getOrderID() {
        return orderID;
    }

    /**
     * Trả về ngày đặt hàng.
     *
     * @return ngày đặt hàng
     */
    public LocalDate getOrderDate() {
        return orderDate;
    }

    /**
     * Trả về tên khách hàng.
     *
     * @return tên khách hàng
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * Thiết lập mã ID của đơn hàng.
     *
     * @param orderID mã ID của đơn hàng
     */
    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    /**
     * Thiết lập ngày đặt hàng.
     *
     * @param orderDate ngày đặt hàng (dạng chuỗi "dd/MM/yyyy")
     */
    public void setOrderDate(String orderDate) {
        LocalDate date = LocalDate.parse(orderDate, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        this.orderDate = date;
    }

    /**
     * Thiết lập tên khách hàng.
     *
     * @param customerName tên khách hàng
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**
     * Trả về chuỗi biểu diễn của thông tin header đơn hàng.
     *
     * @return chuỗi biểu diễn của thông tin header đơn hàng
     */
    @Override
    public String toString() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return String.format("|%-10s|%15s|%-15s", getOrderID(), dtf.format(getOrderDate()), getCustomerName());
    }

}
