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
import view.Menu;

/**
 *
 * @author Nguyễn Huy Phúc
 */
public class OrderList extends HashSet<Order> {

    Add add = new Add();
    Check check = new Check();

    public boolean addNewOrer(Order o) {
        return this.add(o);
    }

    public OrderHeader addHeader() {
        String orderID = this.checkOrderID();
        String orderDate = check.checkOrderDate();
        String customerName = check.checkCustomerName();
        OrderHeader oh = new OrderHeader(orderID, orderDate, customerName);
        return oh;
    }

    public boolean isExistOrderID(String id) {
        for (Order o : this) {
            if (o.getOrH().getOrderID().trim().equalsIgnoreCase(id)) {
                return true;
            }
        }
        return false;
    }

    public boolean isExistFlowerID(String id) {
        for (Order or : this) {
            for (OrderDetail ord : or.getOrD()) {
                if (ord.getFowerID().trim().equalsIgnoreCase(id)) {
                    return true;
                }
            }
        }
        return false;
    }

    public int find(Set<Flower> listF, String fowerID) {
        String s = "";
        for (Flower F : listF) {
            if (F.getiD().equalsIgnoreCase(fowerID)) {
                s = F.getUnitPrice();
            }
        }
        int i = Integer.parseInt(s);
        return i;
    }

    public int calculateCost(Set<Flower> listF, ArrayList<OrderDetail> listOr) {
        int cout = 0;
        for (OrderDetail or : listOr) {
            int quantity = Integer.parseInt(or.getQuantity());
            int price = this.find(listF, or.getFowerID());
            cout += quantity * price;
        }
        return cout;
    }

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

    public String checkEndDate() {
        boolean checkD_ = true;
        String eDate = "";
        do {
            eDate = add.addDisplay2();
            String pattern = "^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/((19|20)\\d\\d)$";
            Pattern regex = Pattern.compile(pattern);
            Matcher matcher = regex.matcher(eDate);
            if (matcher.matches()) {
                checkD_ = false;
            } else {
                System.out.println("   (!) Please enter correct format (dd/MM/yyyy) !!! Try again.");
            }
        } while (checkD_);
        return eDate;
    }

    public void display() {
        System.out.println("       LIST OF PATIENTS");
        String sDate = this.checkStartDate();
        String eDate = this.checkEndDate();
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

    public void sortOrderASCByOrderID() {
        int sumCost = 0, sumCout = 0;
        List<Order> OrderList = new ArrayList<Order>(this);
        List<Order> OrderList1 = new ArrayList<Order>(this);
        Collections.sort(OrderList, new Comparator<Order>() {
            public int compare(Order o1, Order o2) {
                return o1.getOrH().getOrderID().compareTo(o2.getOrH().getOrderID());
            }
        });
        for (Order o : OrderList) {
            sumCout += o.getOrD().size();
            sumCost += o.getCost();
            System.out.printf("       |%5s|%s\n", (OrderList1.indexOf(o) + 1), o.toString());
        }
        DecimalFormat df = new DecimalFormat("$ ###,###,###");
        System.out.printf("       |%5s|%-10s|%15s|%-15s|%15d|%15s\n", "", "Total", "", "", sumCout, df.format(sumCost));
    }

    public void sortOrderDESCByOrderID() {
        int sumCost = 0, sumCout = 0;
        List<Order> OrderList = new ArrayList<Order>(this);
        List<Order> OrderList1 = new ArrayList<Order>(this);
        Collections.sort(OrderList, new Comparator<Order>() {
            public int compare(Order o1, Order o2) {
                return o2.getOrH().getOrderID().compareTo(o1.getOrH().getOrderID());
            }
        });
        for (Order o : OrderList) {
            sumCout += o.getOrD().size();
            sumCost += o.getCost();
            System.out.printf("       |%5s|%s\n", (OrderList1.indexOf(o) + 1), o.toString());
        }
        DecimalFormat df = new DecimalFormat("$ ###,###,###");
        System.out.printf("       |%5s|%-10s|%15s|%-15s|%15d|%15s\n", "", "Total", "", "", sumCout, df.format(sumCost));
    }

    public void sortOrderASCByCustomerName() {
        int sumCost = 0, sumCout = 0;
        List<Order> OrderList = new ArrayList<Order>(this);
        List<Order> OrderList1 = new ArrayList<Order>(this);
        Collections.sort(OrderList, new Comparator<Order>() {
            public int compare(Order o1, Order o2) {
                return o1.getOrH().getCustomerName().compareTo(o2.getOrH().getCustomerName());
            }
        });
        for (Order o : OrderList) {
            sumCout += o.getOrD().size();
            sumCost += o.getCost();
            System.out.printf("       |%5s|%s\n", (OrderList1.indexOf(o) + 1), o.toString());
        }
        DecimalFormat df = new DecimalFormat("$ ###,###,###");
        System.out.printf("       |%5s|%-10s|%15s|%-15s|%15d|%15s\n", "", "Total", "", "", sumCout, df.format(sumCost));
    }

    public void sortOrderDESCByCustomerName() {
        int sumCost = 0, sumCout = 0;
        List<Order> OrderList = new ArrayList<Order>(this);
        List<Order> OrderList1 = new ArrayList<Order>(this);
        Collections.sort(OrderList, new Comparator<Order>() {
            public int compare(Order o1, Order o2) {
                return o2.getOrH().getCustomerName().compareTo(o1.getOrH().getCustomerName());
            }
        });
        for (Order o : OrderList) {
            sumCout += o.getOrD().size();
            sumCost += o.getCost();
            System.out.printf("       |%5s|%s\n", (OrderList1.indexOf(o) + 1), o.toString());
        }
        DecimalFormat df = new DecimalFormat("$ ###,###,###");
        System.out.printf("       |%5s|%-10s|%15s|%-15s|%15d|%15s\n", "", "Total", "", "", sumCout, df.format(sumCost));
    }

    public void sortOrderASCByOrderTotal() {
        int sumCost = 0, sumCout = 0;
        List<Order> OrderList = new ArrayList<Order>(this);
        List<Order> OrderList1 = new ArrayList<Order>(this);
        Collections.sort(OrderList, new Comparator<Order>() {
            public int compare(Order o1, Order o2) {
                return Integer.compare(o1.getCost(), o2.getCost());
            }
        });
        for (Order o : OrderList) {
            sumCout += o.getOrD().size();
            sumCost += o.getCost();
            System.out.printf("       |%5s|%s\n", (OrderList1.indexOf(o) + 1), o.toString());
        }
        DecimalFormat df = new DecimalFormat("$ ###,###,###");
        System.out.printf("       |%5s|%-10s|%15s|%-15s|%15d|%15s\n", "", "Total", "", "", sumCout, df.format(sumCost));
    }

    public void sortOrderDESCByOrderTotal() {
        int sumCost = 0, sumCout = 0;
        List<Order> OrderList = new ArrayList<Order>(this);
        List<Order> OrderList1 = new ArrayList<Order>(this);
        Collections.sort(OrderList, new Comparator<Order>() {
            public int compare(Order o1, Order o2) {
                return Integer.compare(o2.getCost(), o1.getCost());
            }
        });
        for (Order o : OrderList) {
            sumCout += o.getOrD().size();
            sumCost += o.getCost();
            System.out.printf("       |%5s|%s\n", (OrderList1.indexOf(o) + 1), o.toString());
        }
        DecimalFormat df = new DecimalFormat("$ ###,###,###");
        System.out.printf("       |%5s|%-10s|%15s|%-15s|%15d|%15s\n", "", "Total", "", "", sumCout, df.format(sumCost));
    }

    public void sortOrderASCByOrderDate() {
        int sumCost = 0, sumCout = 0;
        List<Order> OrderList = new ArrayList<Order>(this);
        List<Order> OrderList1 = new ArrayList<Order>(this);
        Collections.sort(OrderList, new Comparator<Order>() {
            public int compare(Order o1, Order o2) {
                return o1.getOrH().getOrderDate().compareTo(o2.getOrH().getOrderDate());
            }
        });
        for (Order o : OrderList) {
            sumCout += o.getOrD().size();
            sumCost += o.getCost();
            System.out.printf("       |%5s|%s\n", (OrderList1.indexOf(o) + 1), o.toString());
        }
        DecimalFormat df = new DecimalFormat("$ ###,###,###");
        System.out.printf("       |%5s|%-10s|%15s|%-15s|%15d|%15s\n", "", "Total", "", "", sumCout, df.format(sumCost));
    }

    public void sortOrderDESCByOrderDate() {
        int sumCost = 0, sumCout = 0;
        List<Order> OrderList = new ArrayList<Order>(this);
        List<Order> OrderList1 = new ArrayList<Order>(this);
        Collections.sort(OrderList, new Comparator<Order>() {
            public int compare(Order o1, Order o2) {
                return o2.getOrH().getOrderDate().compareTo(o1.getOrH().getOrderDate());
            }
        });
        for (Order o : OrderList) {
            sumCout += o.getOrD().size();
            sumCost += o.getCost();
            System.out.printf("       |%5s|%s\n", (OrderList1.indexOf(o) + 1), o.toString());
        }
        DecimalFormat df = new DecimalFormat("$ ###,###,###");
        System.out.printf("       |%5s|%-10s|%15s|%-15s|%15d|%15s\n", "", "Total", "", "", sumCout, df.format(sumCost));
    }

    public void displaySortOrderASCByOrderID() {
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
        this.sortOrderASCByOrderID();
    }

    public void displaySortOrderDESCByOrderID() {
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
        this.sortOrderDESCByOrderID();
    }

    public void displaySortOrderASCByCustomerName() {
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
        this.sortOrderASCByCustomerName();
    }

    public void displaySortOrderDESCByCustomerName() {
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
        this.sortOrderDESCByCustomerName();
    }

    public void displaySortOrderASCByOrderTotal() {
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
        this.sortOrderASCByOrderTotal();
    }

    public void displaySortOrderDESCByOrderTotal() {
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
        this.sortOrderDESCByOrderTotal();
    }

    public void displaySortOrderASCByOrderDate() {
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
        this.sortOrderASCByOrderDate();
    }

    public void displaySortOrderDESCByOrderDate() {
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
        this.sortOrderDESCByOrderDate();
    }

    public void sort() {
        boolean check1 = true;
        do {
            System.out.println("       LIST OF PATIENTS");
            String sortedBy = check.checkSortedBy();
            String sortOrder = check.checkSortOrder();
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

    public int saveFile() throws Exception {
        int check = 0;
        ArrayList<Order> list = new ArrayList<>(this);
        if (list.isEmpty()) {
            System.out.println("There is no order in list");
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
