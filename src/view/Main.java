package view;

import controller.FlowerStore;
import controller.OrderList;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import model.Flower;
import model.Order;
import model.OrderDetail;
import model.OrderHeader;
import tool.Add;
import tool.Check;

/**
 *
 * @author Nguyễn Huy Phúc
 */
public class Main {

    public static void main(String[] args) throws Exception {
        FlowerStore fs = new FlowerStore();
        OrderList ol = new OrderList();
        Check check = new Check();
        Add add = new Add();
        Menu menu = new Menu();
        Flower flower1 = new Flower("F001", "Rose", "01/06/2023", "1099", "Red");
        fs.add(flower1);
        Flower flower2 = new Flower("F002", "Tulip", "05/06/2023", "599", "Pink");
        fs.add(flower2);
        Flower flower3 = new Flower("F003", "Sunflower", "10/06/2023", "899", "Yellow");
        fs.add(flower3);
        Flower flower4 = new Flower("F004", "Lily", "15/06/2023", "699", "White");
        fs.add(flower4);
        Flower flower5 = new Flower("F005", "Orchid", "20/06/2023", "1299", "Purple");
        fs.add(flower5);
        Flower flower6 = new Flower("F006", "Daisy", "25/06/2023", "499", "White");
        fs.add(flower6);
        Flower flower7 = new Flower("F007", "Carnation", "30/06/2023", "799", "Red");
        fs.add(flower7);
        OrderHeader orderHeader1 = new OrderHeader("ORD001", "01/06/2023", "John Doe");
        OrderHeader orderHeader2 = new OrderHeader("ORD002", "02/06/2023", "Jane Smith");
        OrderHeader orderHeader3 = new OrderHeader("ORD003", "03/06/2023", "Alice Johnson");
        OrderHeader orderHeader4 = new OrderHeader("ORD004", "04/06/2023", "Bob Thompson");
        OrderHeader orderHeader5 = new OrderHeader("ORD005", "05/06/2023", "Emily Wilson");
        OrderDetail orderDetail1 = new OrderDetail("F001", "5");
        OrderDetail orderDetail2 = new OrderDetail("F002", "3");
        OrderDetail orderDetail3 = new OrderDetail("F003", "2");
        OrderDetail orderDetail4 = new OrderDetail("F004", "4");
        OrderDetail orderDetail5 = new OrderDetail("F005", "1");
        OrderDetail orderDetail6 = new OrderDetail("F006", "2");
        OrderDetail orderDetail7 = new OrderDetail("F007", "6");
        ArrayList<OrderDetail> orderDetails1 = new ArrayList<>();
        ArrayList<OrderDetail> orderDetails2 = new ArrayList<>();
        ArrayList<OrderDetail> orderDetails3 = new ArrayList<>();
        ArrayList<OrderDetail> orderDetails4 = new ArrayList<>();
        ArrayList<OrderDetail> orderDetails5 = new ArrayList<>();
        orderDetails1.add(orderDetail1);
        orderDetails1.add(orderDetail2);
        orderDetails2.add(orderDetail3);
        orderDetails2.add(orderDetail4);
        orderDetails3.add(orderDetail5);
        orderDetails3.add(orderDetail6);
        orderDetails3.add(orderDetail7);
        orderDetails4.add(orderDetail2);
        orderDetails4.add(orderDetail4);
        orderDetails5.add(orderDetail2);
        orderDetails5.add(orderDetail3);
        orderDetails5.add(orderDetail5);
        Order od1 = new Order(orderHeader1, orderDetails1);
        int cost1 = ol.calculateCost(fs, orderDetails1);
        od1.setCost(cost1);
        Order od2 = new Order(orderHeader2, orderDetails2);
        int cost2 = ol.calculateCost(fs, orderDetails2);
        od2.setCost(cost2);
        Order od3 = new Order(orderHeader3, orderDetails3);
        int cost3 = ol.calculateCost(fs, orderDetails3);
        od3.setCost(cost3);
        Order od4 = new Order(orderHeader4, orderDetails4);
        int cost4 = ol.calculateCost(fs, orderDetails4);
        od4.setCost(cost4);
        Order od5 = new Order(orderHeader5, orderDetails5);
        int cost5 = ol.calculateCost(fs, orderDetails5);
        od5.setCost(cost5);
        ol.addNewOrer(od1);
        ol.addNewOrer(od2);
        ol.addNewOrer(od3);
        ol.addNewOrer(od4);
        ol.addNewOrer(od5);
        int checkSaveFlowerStore = 0;
        int checkSaveOrderList = 0;
        int c = -1;
        do {
            menu.printMenu();
            String check1 = add.addCheck();
            if (!check1.trim().matches("\\d+")) {
                System.out.println("   (!) Try Again");
            } else {
                c = Integer.parseInt(check1);
                switch (c) {
                    case 0:
                        boolean checkExit = true;
                        do {
                            menu.exit();
                            String d1 = add.addCheck();
                            if (d1.trim().equalsIgnoreCase("y")) {
                                if (checkSaveFlowerStore == 0 && checkSaveOrderList == 0) {
                                    boolean CheckSave_ = true;
                                    do {
                                        menu.checkSave();
                                        String checkSave = add.addCheck();
                                        if (checkSave.trim().equalsIgnoreCase("y")) {
                                            fs.saveFile();
                                            ol.saveFile();
                                            CheckSave_ = false;
                                            System.out.println("       Exit");
                                            checkExit = false;
                                        } else if (checkSave.trim().equalsIgnoreCase("n")) {
                                            CheckSave_ = false;
                                            System.out.println("       Exit");
                                            checkExit = false;
                                        } else {
                                            System.out.println("   (!) Try Again");
                                        }
                                    } while (CheckSave_);
                                } else {
                                    System.out.println("       Exit");
                                    checkExit = false;
                                }
                            } else if (d1.trim().equalsIgnoreCase("n")) {
                                c = -1;
                                checkExit = false;
                            } else {
                                System.out.println("   (!) Try Again");
                            }
                        } while (checkExit);
                        break;
                    case 1:
                        int c1 = -1;
                        do {
                            menu.FlowerList();
                            String check2 = add.addCheck();
                            if (!check2.matches("\\d+")) {
                                System.out.println("   (!) Try Again");
                            } else {
                                c1 = Integer.parseInt(check2);
                                switch (c1) {
                                    case 0:
                                        boolean checkExit1 = true;
                                        do {
                                            menu.exit();
                                            String d1 = add.addCheck();
                                            if (d1.trim().equalsIgnoreCase("y")) {
                                                System.out.println("       Exit");
                                                checkExit1 = false;
                                            } else if (d1.trim().equalsIgnoreCase("n")) {
                                                c = -1;
                                                checkExit1 = false;
                                            } else {
                                                System.out.println("   (!) Try Again");
                                            }
                                        } while (checkExit1);
                                        break;
                                    case 1:
                                        int c2 = -1;
                                        do {
                                            menu.addFlower();
                                            String check3 = add.addCheck();
                                            if (!check3.matches("\\d+")) {
                                                System.out.println("   (!) Try Again");
                                            } else {
                                                c2 = Integer.parseInt(check3);
                                                switch (c2) {
                                                    case 0:
                                                        boolean checkExit2 = true;
                                                        do {
                                                            menu.exit();
                                                            String d1 = add.addCheck();
                                                            if (d1.trim().equalsIgnoreCase("y")) {
                                                                System.out.println("       Exit");
                                                                checkExit2 = false;
                                                            } else if (d1.trim().equalsIgnoreCase("n")) {
                                                                c = -1;
                                                                checkExit2 = false;
                                                            } else {
                                                                System.out.println("   (!) Try Again");
                                                            }
                                                        } while (checkExit2);
                                                        break;
                                                    case 1:
                                                        String id = fs.checkID();
                                                        String description = check.checkdDscription();
                                                        String importDate = check.checkImportDate();
                                                        String unitPrice = check.checkUnitPrice();
                                                        String category = check.checkCategory();
                                                        Flower f = new Flower(id, description, importDate, unitPrice, category);
                                                        fs.add(f);
                                                        break;
                                                    default:
                                                        System.out.println("   (!) Try Again");
                                                }
                                            }
                                        } while (c2 != 0);
                                        break;
                                    case 2:
                                        int c3 = -1;
                                        do {
                                            menu.findFlower();
                                            String check4 = add.addCheck();
                                            if (!check4.matches("\\d+")) {
                                                System.out.println("   (!) Try Again");
                                            } else {
                                                c3 = Integer.parseInt(check4);
                                                switch (c3) {
                                                    case 0:
                                                        boolean checkExit3 = true;
                                                        do {
                                                            menu.exit();
                                                            String d1 = add.addCheck();
                                                            if (d1.trim().equalsIgnoreCase("y")) {
                                                                System.out.println("       Exit");
                                                                checkExit3 = false;
                                                            } else if (d1.trim().equalsIgnoreCase("n")) {
                                                                c = -1;
                                                                checkExit3 = false;
                                                            } else {
                                                                System.out.println("   (!) Try Again");
                                                            }
                                                        } while (checkExit3);
                                                        break;
                                                    case 1:
                                                        fs.findFlowerByName();
                                                        break;
                                                    case 2:
                                                        fs.findFlowerByID();
                                                        break;
                                                    default:
                                                        System.out.println("   (!) Try Again");
                                                        break;
                                                }
                                            }
                                        } while (c3 != 0);
                                    case 3:
                                        String name = add.addDescription();
                                        int b = -1;
                                        Flower a = fs.findFlower(name);
                                        do {
                                            menu.updateFlower();
                                            String check5 = add.addCheck();
                                            if (!check5.matches("\\d+")) {
                                                System.out.println("   (!) Please enter true fomat");
                                            } else {
                                                b = Integer.parseInt(check5);
                                                switch (b) {
                                                    case 0:
                                                        boolean checkExit4 = true;
                                                        do {
                                                            menu.exit();
                                                            String d1 = add.addCheck();
                                                            if (d1.trim().equalsIgnoreCase("y")) {
                                                                System.out.println("       Exit");
                                                                checkExit4 = false;
                                                            } else if (d1.trim().equalsIgnoreCase("n")) {
                                                                c = -1;
                                                                checkExit4 = false;
                                                            } else {
                                                                System.out.println("   (!) Try Again");
                                                            }
                                                        } while (checkExit4);
                                                        break;
                                                    case 1:
                                                        fs.updateDescription(a);
                                                        break;
                                                    case 2:
                                                        fs.updateImportDate(a);
                                                        break;
                                                    case 3:
                                                        fs.updateUnitPrice(a);
                                                        break;
                                                    case 4:
                                                        fs.updateCategory(a);
                                                        break;
                                                    default:
                                                        System.out.println("   (!) Try Again");
                                                }
                                            }
                                        } while (b != 0);
                                        break;
                                    case 4:
                                        boolean delete = true;
                                        do {
                                            String id = add.addID();
                                            boolean b_ = fs.isExistID(id);
                                            boolean b__ = ol.isExistFlowerID(id);
                                            if (b_) {
                                                boolean q = true;
                                                do {
                                                    menu.check();
                                                    String check6 = add.addCheck();
                                                    if (check6.trim().equals("y")) {
                                                        q = false;
                                                        delete = fs.checkDelete(delete, id, b__);
                                                    } else if (check6.trim().equals("n")) {
                                                        q = false;
                                                        break;
                                                    } else {
                                                        System.out.println("   (!) Try Again");
                                                    }
                                                } while (q);
                                            } else {
                                                System.out.println("   (!) Nurse does not exist");
                                            }
                                        } while (delete);
                                        break;
                                    default:
                                        System.out.println("   (!) Try Again");
                                        break;
                                }
                            }
                        } while (c1 != 0);
                        break;
                    case 2:
                        int c4 = -1;
                        do {
                            menu.orderList();
                            String check7 = add.addCheck();
                            if (!check7.trim().matches("\\d+")) {
                                System.out.println("   (!) Try Again");
                            } else {
                                c4 = Integer.parseInt(check7);
                                switch (c4) {
                                    case 0:
                                        boolean checkExit5 = true;
                                        do {
                                            menu.exit();
                                            String d1 = add.addCheck();
                                            if (d1.trim().equalsIgnoreCase("y")) {
                                                System.out.println("       Exit");
                                                checkExit5 = false;
                                            } else if (d1.trim().equalsIgnoreCase("n")) {
                                                c = -1;
                                                checkExit5 = false;
                                            } else {
                                                System.out.println("   (!) Try Again");
                                            }
                                        } while (checkExit5);
                                        break;
                                    case 1:
                                        int c5 = -1;
                                        do {
                                            menu.addOrder();
                                            String check8 = add.addCheck();
                                            if (!check8.trim().matches("\\d+")) {
                                                System.out.println("   (!) Try Again");
                                            } else {
                                                c5 = Integer.parseInt(check8);
                                                switch (c5) {
                                                    case 0:
                                                        boolean checkExit6 = true;
                                                        do {
                                                            menu.exit();
                                                            String d1 = add.addCheck();
                                                            if (d1.trim().equalsIgnoreCase("y")) {
                                                                System.out.println("       Exit");
                                                                checkExit6 = false;
                                                            } else if (d1.trim().equalsIgnoreCase("n")) {
                                                                c = -1;
                                                                checkExit6 = false;
                                                            } else {
                                                                System.out.println("   (!) Try Again");
                                                            }
                                                        } while (checkExit6);
                                                        break;
                                                    case 1:
                                                        String orderID = ol.checkOrderID();
                                                        String orderDate = check.checkOrderDate();
                                                        String customerName = check.checkCustomerName();
                                                        OrderHeader oh = new OrderHeader(orderID, orderDate, customerName);
                                                        ArrayList<OrderDetail> listOD = new ArrayList();
                                                        int c6 = -1;
                                                        int cost = 0;
                                                        do {
                                                            menu.addOrderDetail();
                                                            String check9 = add.addCheck();
                                                            if (!check9.trim().matches("\\d+")) {
                                                                System.out.println("   (!) Try Again");
                                                            } else {
                                                                c6 = Integer.parseInt(check9);
                                                                switch (c6) {
                                                                    case 0:
                                                                        boolean checkExit7 = true;
                                                                        do {
                                                                            menu.exit();
                                                                            String d1 = add.addCheck();
                                                                            if (d1.trim().equalsIgnoreCase("y")) {
                                                                                System.out.println("       Exit");
                                                                                checkExit7 = false;
                                                                            } else if (d1.trim().equalsIgnoreCase("n")) {
                                                                                c = -1;
                                                                                checkExit7 = false;
                                                                            } else {
                                                                                System.out.println("   (!) Try Again");
                                                                            }
                                                                        } while (checkExit7);
                                                                        break;
                                                                    case 1:
                                                                        boolean check10 = true;
                                                                        String flowerID = null;
                                                                        do {
                                                                            flowerID = check.checkFlowerID();
                                                                            if (!fs.isExistID(flowerID.trim())) {
                                                                                System.out.println("   (!) The flower does not exist");
                                                                            } else {
                                                                                check10 = false;
                                                                            }
                                                                        } while (check10);
                                                                        String quantity = check.checkQuantity();
                                                                        OrderDetail o = new OrderDetail(flowerID, quantity);
                                                                        listOD.add(o);
                                                                        break;
                                                                    default:
                                                                        System.out.println("   (!) Try Again");
                                                                        break;
                                                                }
                                                            }
                                                        } while (c6 != 0);
                                                        Order order = new Order(oh, listOD);
                                                        cost = ol.calculateCost(fs, listOD);
                                                        order.setCost(cost);
                                                        break;
                                                    default:
                                                        System.out.println("   (!) Try Again");
                                                        break;
                                                }
                                            }
                                        } while (c5 != 0);
                                        break;
                                    case 2:
                                        ol.display();
                                        break;
                                    case 3:
                                        ol.sort();
                                        break;
                                    default:
                                        System.out.println("   (!) Try Again");
                                        break;
                                }
                            }

                        } while (c4 != 0);
                        break;
                    case 3:
                        checkSaveFlowerStore = fs.saveFile();
                        checkSaveOrderList = ol.saveFile();
                        break;
                    case 4:
                        fs.loadFile();
                        ol.loadFile();
                        break;
                    default:
                        System.out.println("   (!) Try Again");
                        break;
                }
            }
        } while (c != 0);
    }
}
