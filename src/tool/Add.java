package tool;

import java.util.Scanner;

/**
 *
 * @author Nguyễn Huy Phúc
 */
public class Add {

    Scanner sc = new Scanner(System.in);

    public String addID() {
        System.out.println("       Enter id: ");
        String id = sc.nextLine();
        return id;
    }

    public String addDescription() {
        System.out.println("       Enter Description: ");
        String description = sc.nextLine();
        return description;
    }

    public String addImportDate() {
        System.out.println("       Enter importDate: ");
        String importDate = sc.nextLine();
        return importDate;
    }

    public String addUnitPrice() {
        System.out.println("       Enter unit price: ");
        String unitPrice = sc.nextLine();
        return unitPrice;
    }

    public String addCategory() {
        System.out.println("       Enter category: ");
        String category = sc.nextLine();
        return category;
    }

    public String addOrderID() {
        System.out.println("       Enter order id: ");
        String orderID = sc.nextLine();
        return orderID;
    }

    public String addOrderDate() {
        System.out.println("       Enter order date: ");
        String orderDate = sc.nextLine();
        return orderDate;
    }

    public String addcustomerName() {
        System.out.println("       Enter customer name: ");
        String customerName = sc.nextLine();
        return customerName;
    }

    public String addFlowerID() {
        System.out.println("       Enter flower id: ");
        String flowerID = sc.nextLine();
        return flowerID;
    }

    public String addQuantity() {
        System.out.println("       Enter quantity: ");
        String quantity = sc.nextLine();
        return quantity;
    }

    public String addCost() {
        System.out.println("       Enter flower cost: ");
        String cost = sc.nextLine();
        return cost;
    }

    public String addCheck() {
        String check = sc.nextLine();
        return check;
    }

    public String addDisplay1() {
        System.out.print("       LIST ORDERS FROM ");
        String d1 = sc.nextLine();
        return d1;
    }

    public String addDisplay2() {
        System.out.print(" TO ");
        String d2 = sc.nextLine();
        return d2;
    }

    public String addSortedBy() {
        System.out.print("    -> Sorted by( order id or order date or customer name or order total ): ");
        String sortedBy = sc.nextLine();
        return sortedBy;
    }

    public String addSortOrder() {
        System.out.print("    -> Sort order( asc or desc ): ");
        String sortOrder = sc.nextLine();
        return sortOrder;
    }
}
