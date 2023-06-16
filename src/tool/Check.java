package tool;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import view.Menu;

/**
 * Cung cấp các phương thức kiểm tra dữ liệu đầu vào.
 *
 * author Nguyễn Huy Phúc
 */
public class Check {

    Add add = new Add();

    /**
     * Kiểm tra mô tả của hoa.
     *
     * @return mô tả hoa
     */
    public String checkdDscription() {
        boolean check = true;
        String description = null;
        do {
            description = add.addDescription();
            if (description.trim().equals("")) {
                System.out.println("Please enter a non-null value !!! Try again.");
            } else if (description.trim().length() < 3 || description.trim().length() > 50) {
                System.out.println("Please enter true fomat !!! Try again.");
            } else {
                check = false;
            }
        } while (check);
        return description;
    }

    /**
     * Kiểm tra ngày nhập hàng.
     *
     * @return ngày nhập hàng
     */
    public String checkImportDate() {
        boolean c = true;
        String importDate = null;
        do {
            importDate = add.addImportDate();
            String pattern = "^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/((19|20)\\d\\d)$";
            Pattern regex = Pattern.compile(pattern);
            Matcher matcher = regex.matcher(importDate);
            if (matcher.matches()) {
                c = false;
            } else {
                System.out.println("   (!) Please enter correct format (dd/MM/yyyy) !!! Try again.");
            }
        } while (c);
        return importDate;
    }

    /**
     * Kiểm tra giá tiền đơn vị.
     *
     * @return giá tiền đơn vị
     */
    public String checkUnitPrice() {
        boolean c = true;
        String unitPrice = null;
        do {
            unitPrice = add.addUnitPrice();
            if (unitPrice.trim().equals("")) {
                System.out.println("   (!) Please enter a non-null value !!! Try again.");
            } else if (!unitPrice.matches("\\d+")) {
                System.out.println("   (!) Salary is not true !!! try again.");
            } else {
                c = false;
            }
        } while (c);
        return unitPrice;
    }

    /**
     * Kiểm tra danh mục hoa.
     *
     * @return danh mục hoa
     */
    public String checkCategory() {
        boolean c = true;
        String category = null;
        do {
            if (category.trim().equals("")) {
                System.out.println("   (!) Please enter a non-null value !!! Try again.");
            } else {
                c = false;
            }
        } while (c);
        return category;
    }

    /**
     * Kiểm tra ngày đặt hàng.
     *
     * @return ngày đặt hàng
     */
    public String checkOrderDate() {
        boolean c = true;
        String orderDate = null;
        do {
            orderDate = add.addImportDate();
            String pattern = "^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/((19|20)\\d\\d)$";
            Pattern regex = Pattern.compile(pattern);
            Matcher matcher = regex.matcher(orderDate);
            if (matcher.matches()) {
                c = false;
            } else {
                System.out.println("   (!) Please enter correct format (dd/MM/yyyy) !!! Try again.");
            }
        } while (c);
        return orderDate;
    }

    /**
     * Kiểm tra tên khách hàng.
     *
     * @return tên khách hàng
     */
    public String checkCustomerName() {
        boolean c = true;
        String customerName = null;
        do {
            customerName = add.addcustomerName();
            if (customerName.trim().equals("")) {
                System.out.println("   (!) Please enter a non-null value !!! Try again.");
            } else {
                c = false;
            }
        } while (c);
        return customerName;
    }

    /**
     * Kiểm tra mã hoa.
     *
     * @return mã hoa
     */
    public String checkFlowerID() {
        boolean c = true;
        String flowerID = null;
        do {
            flowerID = add.addFlowerID();
            if (flowerID.trim().equals("")) {
                System.out.println("   (!) Please enter a non-null value !!! Try again.");
            } else {
                c = false;
            }
        } while (c);
        return flowerID;
    }

    /**
     * Kiểm tra số lượng hoa.
     *
     * @return số lượng hoa
     */
    public String checkQuantity() {
        boolean c = true;
        String quantity = null;
        do {
            quantity = add.addQuantity();
            if (quantity.trim().equals("")) {
                System.out.println("   (!) Please enter a non-null value !!! Try again.");
            } else if (!quantity.matches("\\d+")) {
                System.out.println("   (!) Quantity  is not true !!! try again.");
            } else {
                c = false;
            }
        } while (c);
        return quantity;
    }

    /**
     * Kiểm tra thuộc tính để sắp xếp.
     *
     * @return thuộc tính để sắp xếp
     */
    public String checkSortedBy() {
        boolean check = true;
        String sortedBy = null;
        do {
            sortedBy = add.addSortedBy();
            if (sortedBy.trim().equalsIgnoreCase("order id") || sortedBy.trim().equalsIgnoreCase("order date") || sortedBy.trim().equalsIgnoreCase("customer name") || sortedBy.trim().equalsIgnoreCase("order total")) {
                check = false;
            } else {
                System.out.println("   (!) Try Again");
            }
        } while (check);
        return sortedBy;
    }

    /**
     * Kiểm tra thứ tự sắp xếp.
     *
     * @return thứ tự sắp xếp
     */
    public String checkSortOrder() {
        boolean check = true;
        String sortedOrder = null;
        do {
            sortedOrder = add.addSortOrder();
            if (sortedOrder.equalsIgnoreCase("asc") || sortedOrder.equalsIgnoreCase("desc")) {
                check = false;
            } else {
                System.out.println("   (!) Try Again");
            }
        } while (check);
        return sortedOrder;
    }

    /**
     * Kiểm tra và trả về ngày bắt đầu hợp lệ từ người dùng.
     *
     * @return Ngày bắt đầu hợp lệ
     */
    public String checkStartDate() {
        boolean checkD = true;
        String sDate = "";
        do {
            sDate = add.addDisplay1();
            String pattern = "^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/((19|20)\\d\\d)$";
            Pattern regex = Pattern.compile(pattern);
            Matcher matcher = regex.matcher(sDate);
            if (matcher.matches()) {
                checkD = false;
            } else {
                System.out.println("   (!) Please enter correct format (dd/MM/yyyy) !!! Try again.");
            }
        } while (checkD);
        return sDate;
    }

    /**
     * Kiểm tra và trả về ngày kết thúc hợp lệ từ người dùng.
     *
     * @return Ngày kết thúc hợp lệ
     */
    public String checkEndDate() {
        boolean checkD = true;
        String eDate = "";
        do {
            eDate = add.addDisplay2();
            String pattern = "^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/((19|20)\\d\\d)$";
            Pattern regex = Pattern.compile(pattern);
            Matcher matcher = regex.matcher(eDate);
            if (matcher.matches()) {
                checkD = false;
            } else {
                System.out.println("   (!) Please enter correct format (dd/MM/yyyy) !!! Try again.");
            }
        } while (checkD);
        return eDate;
    }

}
