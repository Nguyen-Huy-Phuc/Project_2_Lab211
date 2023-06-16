package tool;

import java.util.Scanner;

/**
 * Lớp Add được sử dụng để nhận dữ liệu nhập từ người dùng. Cung cấp các phương
 * thức để thêm thông tin về các trường dữ liệu khác nhau. Sử dụng đối tượng
 * Scanner để nhận dữ liệu từ bàn phím. Được sử dụng trong các phương thức kiểm
 * tra và nhập dữ liệu trong các lớp khác.
 *
 * @author Nguyễn Huy Phúc
 */
public class Add {

    Scanner sc = new Scanner(System.in);

    /**
     * Nhận và trả về ID từ người dùng.
     *
     * @return ID
     */
    public String addID() {
        System.out.print("       Enter id: ");
        String id = sc.nextLine();
        return id;
    }

    /**
     * Nhận và trả về mô tả từ người dùng.
     *
     * @return Mô tả
     */
    public String addDescription() {
        System.out.print("       Enter Description: ");
        String description = sc.nextLine();
        return description;
    }

    /**
     * Nhận và trả về ngày nhập hàng từ người dùng.
     *
     * @return Ngày nhập hàng
     */
    public String addImportDate() {
        System.out.print("       Enter importDate: ");
        String importDate = sc.nextLine();
        return importDate;
    }

    /**
     * Nhận và trả về giá đơn vị từ người dùng.
     *
     * @return Giá đơn vị
     */
    public String addUnitPrice() {
        System.out.print("       Enter unit price: ");
        String unitPrice = sc.nextLine();
        return unitPrice;
    }

    /**
     * Nhận và trả về danh mục từ người dùng.
     *
     * @return Danh mục
     */
    public String addCategory() {
        System.out.print("       Enter category: ");
        String category = sc.nextLine();
        return category;
    }

    /**
     * Nhận và trả về ID đơn hàng từ người dùng.
     *
     * @return ID đơn hàng
     */
    public String addOrderID() {
        System.out.print("       Enter order id: ");
        String orderID = sc.nextLine();
        return orderID;
    }

    /**
     * Nhận và trả về ngày đặt hàng từ người dùng.
     *
     * @return Ngày đặt hàng
     */
    public String addOrderDate() {
        System.out.print("       Enter order date: ");
        String orderDate = sc.nextLine();
        return orderDate;
    }

    /**
     * Nhận và trả về tên khách hàng từ người dùng.
     *
     * @return Tên khách hàng
     */
    public String addcustomerName() {
        System.out.print("       Enter customer name: ");
        String customerName = sc.nextLine();
        return customerName;
    }

    /**
     * Nhận và trả về ID hoa từ người dùng.
     *
     * @return ID hoa
     */
    public String addFlowerID() {
        System.out.print("       Enter flower id: ");
        String flowerID = sc.nextLine();
        return flowerID;
    }

    /**
     * Yêu cầu người dùng nhập mã ID cho chi tiết đơn hàng.
     *
     * @return chuỗi biểu diễn mã ID của chi tiết đơn hàng được nhập từ bàn phím
     */
    public String addOrderDetailID() {
        System.out.print("       Enter order detail id: ");
        String orderDetail = sc.nextLine();
        return orderDetail;
    }

    /**
     * Nhận và trả về số lượng từ người dùng.
     *
     * @return Số lượng
     */
    public String addQuantity() {
        System.out.print("       Enter quantity: ");
        String quantity = sc.nextLine();
        return quantity;
    }

    /**
     * Nhận và trả về giá trị chi phí từ người dùng.
     *
     * @return Giá trị chi phí
     */
    public String addCost() {
        System.out.print("       Enter flower cost: ");
        String cost = sc.nextLine();
        return cost;
    }

    /**
     * Nhận và trả về kiểm tra từ người dùng.
     *
     * @return Kiểm tra
     */
    public String addCheck() {
        String check = sc.nextLine();
        return check;
    }

    /**
     * Nhận và trả về ngày bắt đầu từ người dùng.
     *
     * @return Ngày bắt đầu
     */
    public String addDisplay1() {
        System.out.print("       Enter start date(dd/MM/yyyy) :  ");
        String d1 = sc.nextLine();
        return d1;
    }

    /**
     * Nhận và trả về ngày kết thúc từ người dùng.
     *
     * @return Ngày kết thúc
     */
    public String addDisplay2() {
        System.out.print("       Enter end date(dd/MM/yyyy) :  ");
        String d2 = sc.nextLine();
        return d2;
    }

    /**
     * Nhận và trả về trường sắp xếp theo từ người dùng.
     *
     * @return Trường sắp xếp
     */
    public String addSortedBy() {
        System.out.print("    -> Sorted by( order id or order date or customer name or order total ): ");
        String sortedBy = sc.nextLine();
        return sortedBy;
    }

    /**
     * Nhận và trả về thứ tự sắp xếp từ người dùng.
     *
     * @return Thứ tự sắp xếp
     */
    public String addSortOrder() {
        System.out.print("    -> Sort order( asc or desc ): ");
        String sortOrder = sc.nextLine();
        return sortOrder;
    }

}
