package model;

import java.io.Serializable;

/**
 * Đại diện cho chi tiết đơn hàng.
 * 
 * - orderDetailID: mã ID của chi tiết đơn hàng
 * - flowerID: mã ID của hoa
 * - quantity: số lượng
 * 
 * Đối tượng OrderDetail chứa thông tin về mã ID của hoa và số lượng trong chi tiết đơn hàng.
 * Cung cấp các phương thức để truy xuất và thiết lập thông tin chi tiết đơn hàng.
 * 
 * @author Nguyễn Huy Phúc
 */
public class OrderDetail implements Serializable {
    private String orderDetailID;
    private String flowerID;
    private String quantity;

    /**
     * Khởi tạo một đối tượng OrderDetail.
     *
     * @param orderDetailID mã ID của chi tiết đơn hàng
     * @param flowerID     mã ID của hoa
     * @param quantity     số lượng
     */
    public OrderDetail(String orderDetailID, String flowerID, String quantity) {
        this.orderDetailID = orderDetailID;
        this.flowerID = flowerID;
        this.quantity = quantity;
    }

    /**
     * Trả về mã ID của hoa.
     *
     * @return mã ID của hoa
     */
    public String getFlowerID() {
        return flowerID;
    }

    /**
     * Trả về mã ID của chi tiết đơn hàng.
     *
     * @return mã ID của chi tiết đơn hàng
     */
    public String getOrderDetailID() {
        return orderDetailID;
    }

    /**
     * Trả về số lượng.
     *
     * @return số lượng
     */
    public String getQuantity() {
        return quantity;
    }

    /**
     * Thiết lập mã ID của hoa.
     *
     * @param flowerID mã ID của hoa
     */
    public void setFlowerID(String flowerID) {
        this.flowerID = flowerID;
    }

    /**
     * Thiết lập mã ID của chi tiết đơn hàng.
     *
     * @param orderDetailID mã ID của chi tiết đơn hàng
     */
    public void setOrderDetailID(String orderDetailID) {
        this.orderDetailID = orderDetailID;
    }

    /**
     * Thiết lập số lượng.
     *
     * @param quantity số lượng
     */
    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}
