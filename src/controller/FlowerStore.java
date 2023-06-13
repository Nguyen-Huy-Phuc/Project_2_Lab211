package controller;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import model.Flower;
import tool.Add;
import tool.Check;
import view.Menu;

/**
 *
 * @author Nguyễn Huy Phúc
 */
public class FlowerStore extends HashSet<Flower> {

    Add add = new Add();
    Check check = new Check();

    public boolean isExistID(String id) {
        for (Flower f : this) {
            if (id.trim().equalsIgnoreCase(id)) {
                return true;
            }
        }
        return false;
    }


    public boolean isExistName(String name) {
        for (Flower f : this) {
            if (name.trim().equalsIgnoreCase(f.getDescription())) {
                return true;
            }
        }
        return false;
    }

    public String checkID() {
        boolean c = true;
        String id = null;
        do {
            id = add.addID();
            if (id.trim().equals("")) {
                System.out.println("   (!) Please enter a non-null value !!! Try again.");
            } else if (this.isExistID(id)) {
                System.out.println("   (!) ID already exists");
            } else {
                c = false;
            }
        } while (c);
        return id;
    }

    public void add() {
        String id = this.checkID();
        String description = check.checkdDscription();
        String importDate = check.checkImportDate();
        String unitPrice = check.checkUnitPrice();
        String category = check.checkCategory();
        Flower f = new Flower(id, description, importDate, unitPrice, category);
        this.add(f);
    }

    public boolean addNewFlower(Flower f) {
        return this.add(f);
    }


    public Flower findFlower(String check) {
        Flower F = null;
        for (Flower f : this) {
            if (f.getDescription().trim().equalsIgnoreCase(check) || f.getiD().trim().equalsIgnoreCase(check)) {
                F = f;
            }
        }
        return F;
    }

    public boolean continueFind(int find_, boolean find, String check) {
        do {
            find_ = Integer.parseInt(check);
            switch (find_) {
                case 0:
                    boolean check1 = true;
                    do {
//                                    menu.exit();
                        String dd = add.addCheck();
                        if (dd.trim().equalsIgnoreCase("y")) {
                            System.out.println("       Exit");
                            check1 = false;
                        } else if (dd.trim().equalsIgnoreCase("n")) {
                            find_ = -1;
                            check1 = false;
                        } else {
                            System.out.println("   (!) Try Again");
                        }
                    } while (check1);
                    find = false;
                    break;
                case 1:
                    find_ = 0;
                    break;
                default:
                    System.out.println("   (!) Try Again");
            }
        } while (find_ != 0);
        return find;
    }

    public void findFlowerByName() {
        boolean find = true;
        do {
            String name = add.addDescription();
            if (this.isExistName(name)) {
                System.out.println(this.findFlower(name));
                find = false;
            } else {
                System.out.println("   (!) Nurse does not exist");
//                menu.findNurse();
                int find_ = -1;
                String check = add.addCheck();
                if (!check.matches("\\d+")) {
                    System.out.println("   (!) Please enter true fomat");
                } else {
                    find = this.continueFind(find_, find, check);
                }
            }
        } while (find);
    }

    public void findFlowerByID() {
        boolean find = true;
        do {
            String id = add.addID();
            if (this.isExistID(id)) {
                System.out.println(this.findFlower(id));
                find = false;
            } else {
                System.out.println("   (!) Nurse does not exist");
//                menu.findNurse();
                int find_ = -1;
                String check = add.addCheck();
                if (!check.matches("\\d+")) {
                    System.out.println("   (!) Please enter true fomat");
                } else {
                    find = this.continueFind(find_, find, check);
                }
            }
        } while (find);
    }

    public void updateDescription(Flower a) {
        String description = check.checkdDscription();
        a.setDescription(description);
        System.out.println("       Update Success");
    }

    public void updateImportDate(Flower a) {
        String importDate = check.checkImportDate();
        a.setImportDate(importDate);
        System.out.println("       Update Success");
    }

    public void updateUnitPrice(Flower a) {
        String unitPrice = check.checkUnitPrice();
        a.setUnitPrice(unitPrice);
        System.out.println("       Update Success");
    }

    public void updateCategory(Flower a) {
        String category = check.checkCategory();
        a.setCategory(category);
        System.out.println("       Update Success");
    }


    public int continueUpdate(int b) {
        boolean check1 = true;
        do {
//                            menu.exit();
            String d1 = add.addCheck();
            if (d1.trim().equalsIgnoreCase("y")) {
                System.out.println("       Exit");
                check1 = false;
            } else if (d1.trim().equalsIgnoreCase("n")) {
                b = -1;
                check1 = false;
            } else {
                System.out.println("   (!) Try Again");
            }
        } while (check1);
        return b;
    }

    public boolean checkDelete(boolean delete, String staffID_, boolean x) {
        String check = add.addCheck();
        if (!x) {
            delete = false;
            System.out.print(this.deleteNurse(staffID_));
        } else {
            System.out.println("       Delete Fail");
        }
        return delete;
    }

    public String deleteNurse(String id) {
        this.remove(id);
        return "       Delete Success.\n";
    }
    public void loadFile() throws Exception {
        ArrayList<Flower> arr = new ArrayList<>();
        FileInputStream fileIn = null;
        ObjectInputStream objectIn = null;
        try {
            File file = new File(System.getProperty("user.dir") + "/src/File/FlowerStore.dat");
            fileIn = new FileInputStream(file);
            objectIn = new ObjectInputStream(fileIn);
            Object obj;
            while ((obj = objectIn.readObject()) != null) {
                if (obj instanceof Flower) {
                    arr.add((Flower) obj);
                }
            }
            objectIn.close();
            fileIn.close();

        } catch (EOFException eof) {
            System.out.println("       FLOWER LOADED!!!");
        } catch (Exception ex) {
            System.out.println("       FLOWER LOAD FAIL!!!");
            throw ex;
        } finally {
            if (objectIn != null) {
                objectIn.close();
            }
            if (fileIn != null) {
                fileIn.close();
            }
        }

        for (Flower flower : arr) {
            this.add(flower);
        }
    }
     public int saveFile() throws Exception {
          int check = 0;
        ArrayList<Flower> list = new ArrayList<>(this);
        if (list.isEmpty()) {
            System.out.println("There is no flower in store");
        } else {
            FileOutputStream fileOut;
            ObjectOutputStream objectOut;
            try {
                File file = new File(System.getProperty("user.dir") + "/src/File/FlowerStore.dat");
                fileOut = new FileOutputStream(file);
                objectOut = new ObjectOutputStream(fileOut);
                for (Object obj : list) {
                    objectOut.writeObject(obj);
                }
                objectOut.flush();
                objectOut.close();
                fileOut.close();
                System.out.println("       FLOWER SAVED!!!");
                check = 1;
            } catch (Exception ex) {
                System.out.println("       FLOWER SAVE FAIL!!!");
                throw ex;
            }
        }
        return check;
    }
}
