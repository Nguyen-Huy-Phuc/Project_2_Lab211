package controller;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import model.Flower;
import model.Order;
import model.OrderDetail;
import model.OrderHeader;
import tool.Add;
import tool.Check;

/**
 * Lớp OrderList đại diện cho một danh sách các đơn đặt hàng. Nó mở rộng
 * HashSet<Order> và cung cấp các phương thức bổ sung để quản lý đơn hàng.
 *
 * @tác giả Nguyễn Huy Phúc
 */
public class OrderList extends HashSet<Order> {

    Add add = new Add();
    Check check = new Check();

    /**
     * Kiểm tra xem ID đơn hàng đã tồn tại trong danh sách chưa.
     *
     * @param id ID đơn hàng cần kiểm tra
     * @return {@code true} nếu ID đơn hàng tồn tại, {@code false} nếu không
     */
    public boolean isExistOrderID(String id) {
        for (Order o : this) {
            if (o.getOrH().getOrderID().trim().equalsIgnoreCase(id)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Kiểm tra xem ID chi tiết đơn hàng đã tồn tại trong danh sách chưa.
     *
     * @param id ID chi tiết đơn hàng cần kiểm tra
     * @return {@code true} nếu ID chi tiết đơn hàng tồn tại, {@code false} nếu
     * không
     */
    public boolean isExistOrderDetailID(String id) {
        for (Order o : this) {
            for (OrderDetail ord : o.getOrD()) {
                if (ord.getOrderDetailID().trim().equalsIgnoreCase(id)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Kiểm tra xem ID hoa có tồn tại trong bất kỳ chi tiết đơn đặt hàng nào
     * không.
     *
     * @param id ID hoa để kiểm tra
     * @return {@code true} nếu ID hoa tồn tại, {@code false} nếu không
     */
    public boolean isExistFlowerID(String id) {
        for (Order or : this) {
            for (OrderDetail ord : or.getOrD()) {
                if (ord.getFlowerID().trim().equalsIgnoreCase(id)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Tính toán tổng chi phí của một đơn đặt hàng dựa trên đơn giá và số lượng
     * hoa.
     *
     * @param listF Danh sách các loại hoa kèm theo đơn giá
     * @param listOr Danh sách chi tiết đơn hàng với số lượng
     * @return Tổng chi phí của đơn đặt hàng
     */
    public int calculateCost(Set<Flower> listF, ArrayList<OrderDetail> listOr) {
        int cout = 0;
        for (OrderDetail or : listOr) {
            int quantity = Integer.parseInt(or.getQuantity());
            int price = this.findUnitPrice(listF, or.getFlowerID());
            cout += quantity * price;
        }
        return cout;
    }

    /**
     * Thêm một đơn đặt hàng mới vào danh sách.
     *
     * @param o Thứ tự thêm
     * @return {@code true} nếu đơn hàng được thêm thành công, {@code false} nếu
     * không
     */
    public boolean addNewOrer(Order o) {
        return this.add(o);
    }

    /**
     * Nhắc người dùng nhập ID đơn đặt hàng và kiểm tra xem nó đã tồn tại trong
     * danh sách chưa.
     *
     * @return ID đơn đặt hàng không trùng lặp do người dùng nhập
     */
    public String checkOrderID() {
        boolean c = true;
        String orderID = null;
        do {
            orderID = add.addOrderID();
            if (orderID.trim().equalsIgnoreCase("")) {
                System.out.println("   (!) Please enter a non-null value !!! Try again.");
            } else if (this.isExistOrderID(orderID)) {
                System.out.println("   (!) ID already exists");
            } else {
                c = false;
            }
        } while (c);
        return orderID;
    }

    /**
     * Kiểm tra và yêu cầu nhập ID chi tiết đơn hàng hợp lệ.
     *
     * @return ID chi tiết đơn hàng hợp lệ
     */
    public String checkOrderDetailID() {
        boolean c = true;
        String orderDetailID = null;
        do {
            orderDetailID = add.addOrderDetailID();
            if (orderDetailID.trim().equalsIgnoreCase("")) {
                System.out.println("   (!) Please enter a non-null value !!! Try again.");
            } else if (this.isExistOrderDetailID(orderDetailID)) {
                System.out.println("   (!) ID already exists");
            } else {
                c = false;
            }
        } while (c);
        return orderDetailID;
    }

    /**
     * Tìm kiếm và trả về giá tiền của một loại hoa trong danh sách hoa.
     *
     * @param listF Danh sách hoa để tìm kiếm
     * @param flowerID ID của loại hoa cần tìm kiếm
     * @return Giá tiền của loại hoa được tìm thấy, hoặc 0 nếu không tìm thấy.
     */
    public int findUnitPrice(Set<Flower> listF, String flowerID) {
        String s = "";
        for (Flower f : listF) {
            if (f.getiD().equalsIgnoreCase(flowerID)) {
                s = f.getUnitPrice();
            }
        }
        int i = Integer.parseInt(s);
        return i;
    }

    /**
     * Hiển thị thông tin các đơn hàng trong một khoảng thời gian nhất định.
     *
     * @param d1 Ngày bắt đầu trong khoảng thời gian (định dạng "dd/MM/yyyy")
     * @param d2 Ngày kết thúc trong khoảng thời gian (định dạng "dd/MM/yyyy")
     */
    public void displayOrder(String d1, String d2) {
        int sumCost = 0, sumCout = 0;
        LocalDate sDate = LocalDate.parse(d1, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        LocalDate eDate = LocalDate.parse(d2, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        List<Order> orderList = new ArrayList<>(this);
        for (Order o : orderList) {
            if (o.getOrH().getOrderDate().isAfter(sDate) && o.getOrH().getOrderDate().isBefore(eDate)) {
                sumCout += o.getOrD().size();
                sumCost += o.getCost();
                System.out.printf("       |%5s%s\n", (orderList.indexOf(o) + 1), o.toString());
            }
        }
        DecimalFormat df = new DecimalFormat("$ ###,###,###");
        System.out.printf("       |%5s|%-10s|%15s|%-15s|%15d|%15s\n", "", "Total", "", "", sumCout, df.format(sumCost));
    }

    /**
     * Hiển thị danh sách các bệnh nhân trong một khoảng thời gian nhất định. Sử
     * dụng các thông tin ngày bắt đầu và ngày kết thúc được nhập từ người dùng.
     */
    public void display() {
        System.out.println("       LIST OF PATIENTS");
        String sDate = check.checkStartDate();
        String eDate = check.checkEndDate();
        System.out.print("       ");
        for (int i = 0; i < 82; i++) {
            System.out.print("-");
        }
        System.out.println();
        System.out.printf("       |%5s|%-10s|%15s|%-15s|%15s|%15s|\n", "NO", "Order ID", "Order Date", "Customer", "Flower Count", "Order Total");
        System.out.print("       ");
        for (int i = 0; i < 82; i++) {
            System.out.print("-");
        }
        System.out.println();
        this.displayOrder(sDate, eDate);
    }

    /**
     * Sắp xếp danh sách đơn hàng theo thứ tự tăng dần của OrderID.
     */
    public void sortOrderASCByOrderID() {
        int sumCost = 0, sumCout = 0;
        List<Order> OrderList = new ArrayList<Order>(this);
        List<Order> OrderList1 = new ArrayList<Order>(this);

        // Sắp xếp danh sách đơn hàng theo OrderID
        Collections.sort(OrderList, new Comparator<Order>() {
            public int compare(Order o1, Order o2) {
                return o1.getOrH().getOrderID().compareTo(o2.getOrH().getOrderID());
            }
        });

        // Hiển thị thông tin đơn hàng đã sắp xếp
        for (Order o : OrderList) {
            sumCout += o.getOrD().size();
            sumCost += o.getCost();
            System.out.printf("       |%5s|%s\n", (OrderList1.indexOf(o) + 1), o.toString());
        }

        DecimalFormat df = new DecimalFormat("$ ###,###,###");

        // Hiển thị tổng số đơn hàng và tổng chi phí
        System.out.printf("       |%5s|%-10s|%15s|%-15s|%15d|%15s\n", "", "Total", "", "", sumCout, df.format(sumCost));
    }

    /**
     * Sắp xếp danh sách đơn hàng theo thứ tự giảm dần của OrderID.
     */
    public void sortOrderDESCByOrderID() {
        int sumCost = 0, sumCout = 0;
        List<Order> OrderList = new ArrayList<Order>(this);
        List<Order> OrderList1 = new ArrayList<Order>(this);

        // Sắp xếp danh sách đơn hàng theo OrderID theo thứ tự giảm dần
        Collections.sort(OrderList, new Comparator<Order>() {
            public int compare(Order o1, Order o2) {
                return o2.getOrH().getOrderID().compareTo(o1.getOrH().getOrderID());
            }
        });

        // Hiển thị thông tin đơn hàng đã sắp xếp
        for (Order o : OrderList) {
            sumCout += o.getOrD().size();
            sumCost += o.getCost();
            System.out.printf("       |%5s|%s\n", (OrderList1.indexOf(o) + 1), o.toString());
        }

        DecimalFormat df = new DecimalFormat("$ ###,###,###");

        // Hiển thị tổng số đơn hàng và tổng chi phí
        System.out.printf("       |%5s|%-10s|%15s|%-15s|%15d|%15s\n", "", "Total", "", "", sumCout, df.format(sumCost));
    }

    /**
     * Sắp xếp danh sách đơn hàng theo thứ tự tăng dần của tên khách hàng.
     */
    public void sortOrderASCByCustomerName() {
        int sumCost = 0, sumCout = 0;
        List<Order> OrderList = new ArrayList<Order>(this);
        List<Order> OrderList1 = new ArrayList<Order>(this);

        // Sắp xếp danh sách đơn hàng theo tên khách hàng theo thứ tự tăng dần
        Collections.sort(OrderList, new Comparator<Order>() {
            public int compare(Order o1, Order o2) {
                return o1.getOrH().getCustomerName().compareTo(o2.getOrH().getCustomerName());
            }
        });

        // Hiển thị thông tin đơn hàng đã sắp xếp
        for (Order o : OrderList) {
            sumCout += o.getOrD().size();
            sumCost += o.getCost();
            System.out.printf("       |%5s|%s\n", (OrderList1.indexOf(o) + 1), o.toString());
        }

        DecimalFormat df = new DecimalFormat("$ ###,###,###");

        // Hiển thị tổng số đơn hàng và tổng chi phí
        System.out.printf("       |%5s|%-10s|%15s|%-15s|%15d|%15s\n", "", "Total", "", "", sumCout, df.format(sumCost));
    }

    /**
     * Sắp xếp danh sách đơn hàng theo thứ tự giảm dần của tên khách hàng.
     */
    public void sortOrderDESCByCustomerName() {
        int sumCost = 0, sumCout = 0;
        List<Order> OrderList = new ArrayList<Order>(this);
        List<Order> OrderList1 = new ArrayList<Order>(this);

        // Sắp xếp danh sách đơn hàng theo tên khách hàng theo thứ tự giảm dần
        Collections.sort(OrderList, new Comparator<Order>() {
            public int compare(Order o1, Order o2) {
                return o2.getOrH().getCustomerName().compareTo(o1.getOrH().getCustomerName());
            }
        });

        // Hiển thị thông tin đơn hàng đã sắp xếp
        for (Order o : OrderList) {
            sumCout += o.getOrD().size();
            sumCost += o.getCost();
            System.out.printf("       |%5s|%s\n", (OrderList1.indexOf(o) + 1), o.toString());
        }

        DecimalFormat df = new DecimalFormat("$ ###,###,###");

        // Hiển thị tổng số đơn hàng và tổng chi phí
        System.out.printf("       |%5s|%-10s|%15s|%-15s|%15d|%15s\n", "", "Total", "", "", sumCout, df.format(sumCost));
    }

    /**
     * Sắp xếp danh sách đơn hàng theo thứ tự tăng dần của tổng số đơn hàng.
     */
    public void sortOrderASCByOrderTotal() {
        int sumCost = 0, sumCout = 0;
        List<Order> OrderList = new ArrayList<Order>(this);
        List<Order> OrderList1 = new ArrayList<Order>(this);

        // Sắp xếp danh sách đơn hàng theo tổng số đơn hàng theo thứ tự tăng dần
        Collections.sort(OrderList, new Comparator<Order>() {
            public int compare(Order o1, Order o2) {
                return Integer.compare(o1.getCost(), o2.getCost());
            }
        });

        // Hiển thị thông tin đơn hàng đã sắp xếp
        for (Order o : OrderList) {
            sumCout += o.getOrD().size();
            sumCost += o.getCost();
            System.out.printf("       |%5s|%s\n", (OrderList1.indexOf(o) + 1), o.toString());
        }

        DecimalFormat df = new DecimalFormat("$ ###,###,###");

        // Hiển thị tổng số đơn hàng và tổng chi phí
        System.out.printf("       |%5s|%-10s|%15s|%-15s|%15d|%15s\n", "", "Total", "", "", sumCout, df.format(sumCost));
    }

    /**
     * Sắp xếp danh sách đơn hàng theo thứ tự giảm dần của tổng số đơn hàng.
     */
    public void sortOrderDESCByOrderTotal() {
        int sumCost = 0, sumCout = 0;
        List<Order> OrderList = new ArrayList<Order>(this);
        List<Order> OrderList1 = new ArrayList<Order>(this);

        // Sắp xếp danh sách đơn hàng theo tổng số đơn hàng theo thứ tự giảm dần
        Collections.sort(OrderList, new Comparator<Order>() {
            public int compare(Order o1, Order o2) {
                return Integer.compare(o2.getCost(), o1.getCost());
            }
        });

        // Hiển thị thông tin đơn hàng đã sắp xếp
        for (Order o : OrderList) {
            sumCout += o.getOrD().size();
            sumCost += o.getCost();
            System.out.printf("       |%5s|%s\n", (OrderList1.indexOf(o) + 1), o.toString());
        }

        DecimalFormat df = new DecimalFormat("$ ###,###,###");

        // Hiển thị tổng số đơn hàng và tổng chi phí
        System.out.printf("       |%5s|%-10s|%15s|%-15s|%15d|%15s\n", "", "Total", "", "", sumCout, df.format(sumCost));
    }

    /**
     * Sắp xếp danh sách đơn hàng theo thứ tự tăng dần của ngày đặt hàng.
     */
    public void sortOrderASCByOrderDate() {
        int sumCost = 0, sumCout = 0;
        List<Order> OrderList = new ArrayList<Order>(this);
        List<Order> OrderList1 = new ArrayList<Order>(this);

        // Sắp xếp danh sách đơn hàng theo ngày đặt hàng theo thứ tự tăng dần
        Collections.sort(OrderList, new Comparator<Order>() {
            public int compare(Order o1, Order o2) {
                return o1.getOrH().getOrderDate().compareTo(o2.getOrH().getOrderDate());
            }
        });

        // Hiển thị thông tin đơn hàng đã sắp xếp
        for (Order o : OrderList) {
            sumCout += o.getOrD().size();
            sumCost += o.getCost();
            System.out.printf("       |%5s|%s\n", (OrderList1.indexOf(o) + 1), o.toString());
        }

        DecimalFormat df = new DecimalFormat("$ ###,###,###");

        // Hiển thị tổng số đơn hàng và tổng chi phí
        System.out.printf("       |%5s|%-10s|%15s|%-15s|%15d|%15s\n", "", "Total", "", "", sumCout, df.format(sumCost));
    }

    /**
     * Sắp xếp danh sách đơn hàng theo thứ tự giảm dần của ngày đặt hàng.
     */
    public void sortOrderDESCByOrderDate() {
        int sumCost = 0, sumCout = 0;
        List<Order> OrderList = new ArrayList<Order>(this);
        List<Order> OrderList1 = new ArrayList<Order>(this);

        // Sắp xếp danh sách đơn hàng theo ngày đặt hàng theo thứ tự giảm dần
        Collections.sort(OrderList, new Comparator<Order>() {
            public int compare(Order o1, Order o2) {
                return o2.getOrH().getOrderDate().compareTo(o1.getOrH().getOrderDate());
            }
        });

        // Hiển thị thông tin đơn hàng đã sắp xếp
        for (Order o : OrderList) {
            sumCout += o.getOrD().size();
            sumCost += o.getCost();
            System.out.printf("       |%5s|%s\n", (OrderList1.indexOf(o) + 1), o.toString());
        }

        DecimalFormat df = new DecimalFormat("$ ###,###,###");

        // Hiển thị tổng số đơn hàng và tổng chi phí
        System.out.printf("       |%5s|%-10s|%15s|%-15s|%15d|%15s\n", "", "Total", "", "", sumCout, df.format(sumCost));
    }

    /**
     * Hiển thị danh sách đơn hàng được sắp xếp theo thứ tự tăng dần của Order
     * ID.
     */
    public void displaySortOrderASCByOrderID() {
        // In đường kẻ ngang
        for (int i = 0; i < 82; i++) {
            System.out.print("-");
        }
        System.out.println();

        // In tiêu đề cột
        System.out.printf("       |%5s|%-10s|%15s|%-15s|%15s|%15s|\n", "NO", "Order ID", "Order Date", "Customer", "Flower Count", "Order Total");

        // In đường kẻ ngang
        System.out.print("       ");
        for (int i = 0; i < 82; i++) {
            System.out.print("-");
        }
        System.out.println();

        // Sắp xếp và hiển thị danh sách đơn hàng theo thứ tự tăng dần của Order ID
        this.sortOrderASCByOrderID();
    }

    /**
     * Hiển thị danh sách đơn hàng được sắp xếp theo thứ tự giảm dần của Order
     * ID.
     */
    public void displaySortOrderDESCByOrderID() {
        // In đường kẻ ngang
        for (int i = 0; i < 82; i++) {
            System.out.print("-");
        }
        System.out.println();

        // In tiêu đề cột
        System.out.printf("       |%5s|%-10s|%15s|%-15s|%15s|%15s|\n", "NO", "Order ID", "Order Date", "Customer", "Flower Count", "Order Total");

        // In đường kẻ ngang
        System.out.print("       ");
        for (int i = 0; i < 82; i++) {
            System.out.print("-");
        }
        System.out.println();

        // Sắp xếp và hiển thị danh sách đơn hàng theo thứ tự giảm dần của Order ID
        this.sortOrderDESCByOrderID();
    }

    /**
     * Hiển thị danh sách đơn hàng được sắp xếp theo thứ tự tăng dần của Tên
     * khách hàng.
     */
    public void displaySortOrderASCByCustomerName() {
        // In đường kẻ ngang
        for (int i = 0; i < 82; i++) {
            System.out.print("-");
        }
        System.out.println();

        // In tiêu đề cột
        System.out.printf("       |%5s|%-10s|%15s|%-15s|%15s|%15s|\n", "NO", "Order ID", "Order Date", "Customer", "Flower Count", "Order Total");

        // In đường kẻ ngang
        System.out.print("       ");
        for (int i = 0; i < 82; i++) {
            System.out.print("-");
        }
        System.out.println();

        // Sắp xếp và hiển thị danh sách đơn hàng theo thứ tự tăng dần của Tên khách hàng
        this.sortOrderASCByCustomerName();
    }

    /**
     * Hiển thị danh sách đơn hàng được sắp xếp theo thứ tự giảm dần của Tên
     * khách hàng.
     */
    public void displaySortOrderDESCByCustomerName() {
        // In đường kẻ ngang
        for (int i = 0; i < 82; i++) {
            System.out.print("-");
        }
        System.out.println();

        // In tiêu đề cột
        System.out.printf("       |%5s|%-10s|%15s|%-15s|%15s|%15s|\n", "NO", "Order ID", "Order Date", "Customer", "Flower Count", "Order Total");

        // In đường kẻ ngang
        System.out.print("       ");
        for (int i = 0; i < 82; i++) {
            System.out.print("-");
        }
        System.out.println();

        // Sắp xếp và hiển thị danh sách đơn hàng theo thứ tự giảm dần của Tên khách hàng
        this.sortOrderDESCByCustomerName();
    }

    /**
     * Hiển thị danh sách đơn hàng được sắp xếp theo thứ tự tăng dần của Tổng
     * đơn hàng.
     */
    public void displaySortOrderASCByOrderTotal() {
        // In đường kẻ ngang
        for (int i = 0; i < 82; i++) {
            System.out.print("-");
        }
        System.out.println();

        // In tiêu đề cột
        System.out.printf("       |%5s|%-10s|%15s|%-15s|%15s|%15s|\n", "NO", "Order ID", "Order Date", "Customer", "Flower Count", "Order Total");

        // In đường kẻ ngang
        System.out.print("       ");
        for (int i = 0; i < 82; i++) {
            System.out.print("-");
        }
        System.out.println();

        // Sắp xếp và hiển thị danh sách đơn hàng theo thứ tự tăng dần của Tổng đơn hàng
        this.sortOrderASCByOrderTotal();
    }

    /**
     * Hiển thị danh sách đơn hàng được sắp xếp theo thứ tự giảm dần của Tổng
     * đơn hàng.
     */
    public void displaySortOrderDESCByOrderTotal() {
        // In đường kẻ ngang
        for (int i = 0; i < 82; i++) {
            System.out.print("-");
        }
        System.out.println();

        // In tiêu đề cột
        System.out.printf("       |%5s|%-10s|%15s|%-15s|%15s|%15s|\n", "NO", "Order ID", "Order Date", "Customer", "Flower Count", "Order Total");

        // In đường kẻ ngang
        System.out.print("       ");
        for (int i = 0; i < 82; i++) {
            System.out.print("-");
        }
        System.out.println();

        // Sắp xếp và hiển thị danh sách đơn hàng theo thứ tự giảm dần của Tổng đơn hàng
        this.sortOrderDESCByOrderTotal();
    }

    /**
     * Hiển thị danh sách đơn hàng được sắp xếp theo thứ tự tăng dần của Ngày
     * đặt hàng.
     */
    public void displaySortOrderASCByOrderDate() {
        // In đường kẻ ngang
        for (int i = 0; i < 82; i++) {
            System.out.print("-");
        }
        System.out.println();

        // In tiêu đề cột
        System.out.printf("       |%5s|%-10s|%15s|%-15s|%15s|%15s|\n", "NO", "Order ID", "Order Date", "Customer", "Flower Count", "Order Total");

        // In đường kẻ ngang
        System.out.print("       ");
        for (int i = 0; i < 82; i++) {
            System.out.print("-");
        }
        System.out.println();

        // Sắp xếp và hiển thị danh sách đơn hàng theo thứ tự tăng dần của Ngày đặt hàng
        this.sortOrderASCByOrderDate();
    }

    /**
     * Hiển thị danh sách đơn hàng được sắp xếp theo thứ tự giảm dần của Ngày
     * đặt hàng.
     */
    public void displaySortOrderDESCByOrderDate() {
        // In đường kẻ ngang
        for (int i = 0; i < 82; i++) {
            System.out.print("-");
        }
        System.out.println();

        // In tiêu đề cột
        System.out.printf("       |%5s|%-10s|%15s|%-15s|%15s|%15s|\n", "NO", "Order ID", "Order Date", "Customer", "Flower Count", "Order Total");

        // In đường kẻ ngang
        System.out.print("       ");
        for (int i = 0; i < 82; i++) {
            System.out.print("-");
        }
        System.out.println();

        // Sắp xếp và hiển thị danh sách đơn hàng theo thứ tự giảm dần của Ngày đặt hàng
        this.sortOrderDESCByOrderDate();
    }

    /**
     * Sắp xếp danh sách các đối tượng Order dựa trên yêu cầu người dùng.
     */
    public void sort() {
        boolean check1 = true;
        do {
            System.out.println("       LIST OF PATIENTS");
            String sortedBy = check.checkSortedBy(); // Phương thức checkSortedBy() chưa được định nghĩa.
            String sortOrder = check.checkSortOrder(); // Phương thức checkSortOrder() chưa được định nghĩa.
            if (sortOrder.equalsIgnoreCase("asc") && sortedBy.equalsIgnoreCase("order id")) {
                check1 = false;
                this.displaySortOrderASCByOrderID();
            } else if (sortOrder.equalsIgnoreCase("desc") && sortedBy.equalsIgnoreCase("order id")) {
                check1 = false;
                this.displaySortOrderDESCByOrderID();
            } else if (sortOrder.equalsIgnoreCase("asc") && sortedBy.equalsIgnoreCase("order date")) {
                check1 = false;
                this.displaySortOrderASCByOrderDate();
            } else if (sortOrder.equalsIgnoreCase("desc") && sortedBy.equalsIgnoreCase("order date")) {
                check1 = false;
                this.displaySortOrderDESCByOrderDate();
            } else if (sortOrder.equalsIgnoreCase("asc") && sortedBy.equalsIgnoreCase("customer name")) {
                check1 = false;
                this.displaySortOrderASCByCustomerName();
            } else if (sortOrder.equalsIgnoreCase("desc") && sortedBy.equalsIgnoreCase("customer name")) {
                check1 = false;
                this.displaySortOrderDESCByCustomerName();
            } else if (sortOrder.equalsIgnoreCase("asc") && sortedBy.equalsIgnoreCase("order total")) {
                check1 = false;
                this.displaySortOrderASCByOrderTotal();
            } else if (sortOrder.equalsIgnoreCase("desc") && sortedBy.equalsIgnoreCase("order total")) {
                check1 = false;
                this.displaySortOrderDESCByOrderTotal();
            } else {
                System.out.println("       Try Again");
            }
        } while (check1);
    }

    /**
     * Tải dữ liệu từ tệp OrderList.dat và thêm các đối tượng Order vào danh
     * sách hiện tại.
     *
     * @throws Exception nếu xảy ra lỗi trong quá trình tải dữ liệu.
     */
    public void loadFile() throws Exception {
        ArrayList<Order> arr = new ArrayList<>();
        FileInputStream fileIn = null;
        ObjectInputStream objectIn = null;
        try {
            File file = new File(System.getProperty("user.dir") + "/src/File/OrderList.dat");
            fileIn = new FileInputStream(file);
            objectIn = new ObjectInputStream(fileIn);
            Object obj;
            while ((obj = objectIn.readObject()) != null) {
                if (obj instanceof Order) {
                    arr.add((Order) obj);
                }
            }
            objectIn.close();
            fileIn.close();

        } catch (EOFException eof) {
            System.out.println("       ORDER LOADED!!!");
        } catch (Exception ex) {
            System.out.println("       ORDER LOAD FAIL!!!");
            throw ex;
        } finally {
            if (objectIn != null) {
                objectIn.close();
            }
            if (fileIn != null) {
                fileIn.close();
            }
        }

        for (Order order : arr) {
            this.add(order);
        }
    }

    /**
     * Lưu danh sách các đối tượng Order vào tệp OrderList.dat.
     *
     * @return giá trị check: 0 nếu danh sách rỗng, 1 nếu lưu thành công.
     * @throws Exception nếu xảy ra lỗi trong quá trình lưu.
     */
    public int saveFile() throws Exception {
        int check = 0;
        ArrayList<Order> list = new ArrayList<>(this);
        if (list.isEmpty()) {
            System.out.println("There is no order in the list");
        } else {
            FileOutputStream fileOut;
            ObjectOutputStream objectOut;
            try {
                File file = new File(System.getProperty("user.dir") + "/src/File/OrderList.dat");
                fileOut = new FileOutputStream(file);
                objectOut = new ObjectOutputStream(fileOut);
                for (Object obj : list) {
                    objectOut.writeObject(obj);
                }
                objectOut.flush();
                objectOut.close();
                fileOut.close();
                System.out.println("       ORDER SAVED!!!");
                check = 1;
            } catch (Exception ex) {
                System.out.println("       ORDER SAVE FAIL!!!");
                throw ex;
            }
        }
        return check;
    }

}
