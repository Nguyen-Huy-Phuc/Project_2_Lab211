package tool;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import view.Menu;

/**
 *
 * @author Nguyễn Huy Phúc
 */
public class Check {

    Add add = new Add();

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

}
