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

/**
 * Lớp FlowerStore đại diện cho cửa hàng hoa. Kế thừa từ HashSet<Flower> để lưu
 * trữ và quản lý danh sách hoa.
 *
 * Cung cấp các phương thức để thêm, tìm kiếm, cập nhật, xóa hoa trong danh
 * sách. Có khả năng lưu và tải danh sách hoa từ file.
 *
 * Các đối tượng Add và Check được sử dụng để thực hiện việc nhập và kiểm tra dữ
 * liệu.
 *
 * @author Nguyễn Huy Phúc
 */
public class FlowerStore extends HashSet<Flower> {

    Add add = new Add();
    Check check = new Check();

    /**
     * Kiểm tra xem một ID hoa đã tồn tại trong danh sách hay chưa.
     *
     * @param id ID hoa cần kiểm tra
     * @return true nếu ID đã tồn tại, ngược lại trả về false
     */
    public boolean isExistID(String id) {
        for (Flower f : this) {
            if (id.trim().equalsIgnoreCase(id)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Kiểm tra xem một tên hoa đã tồn tại trong danh sách hay chưa.
     *
     * @param name Tên hoa cần kiểm tra
     * @return true nếu tên đã tồn tại, ngược lại trả về false
     */
    public boolean isExistName(String name) {
        for (Flower f : this) {
            if (name.trim().equalsIgnoreCase(f.getDescription())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Kiểm tra và nhập mã ID mới cho hoa.
     *
     * @return mã ID đã nhập
     */
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

    /**
     * Thêm một hoa mới vào danh sách.
     *
     * @param f Đối tượng hoa cần thêm
     * @return true nếu thêm thành công, ngược lại trả về false
     */
    public boolean addNewFlower(Flower f) {
        return this.add(f);
    }

    /**
     * Tìm kiếm hoa trong danh sách theo tên hoặc mã ID.
     *
     * @param check Chuỗi tìm kiếm (tên hoặc mã ID)
     * @return Đối tượng hoa tìm được, nếu không tìm thấy trả về null
     */
    public Flower findFlower(String check) {
        Flower F = null;
        for (Flower f : this) {
            if (f.getDescription().trim().equalsIgnoreCase(check) || f.getiD().trim().equalsIgnoreCase(check)) {
                F = f;
            }
        }
        return F;
    }

    /**
     * Tiếp tục quá trình tìm kiếm hoặc thoát khỏi menu tìm kiếm.
     *
     * @param find_ Giá trị hiện tại của lựa chọn (tìm kiếm hoặc thoát)
     * @param find Biến kiểm tra quá trình tìm kiếm
     * @param check Chuỗi đầu vào để kiểm tra lựa chọn
     * @return true nếu quá trình tìm kiếm tiếp tục, false nếu thoát khỏi menu
     * tìm kiếm
     */
    public boolean continueFind(int find_, boolean find, String check) {
        do {
            find_ = Integer.parseInt(check);
            switch (find_) {
                case 0:
                    boolean check1 = true;
                    do {
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

    /**
     * Tìm kiếm hoa theo tên.
     *
     * @see Add#addDescription()
     */
    public void findFlowerByName() {
        boolean find = true;
        do {
            String name = add.addDescription();
            if (this.isExistName(name)) {
                System.out.println(this.findFlower(name));
                find = false;
            } else {
                System.out.println("   (!) Nurse does not exist");
                int find_ = -1;
                String check = add.addCheck();
                if (!check.matches("\\d+")) {
                    System.out.println("   (!) Please enter true format");
                } else {
                    find = this.continueFind(find_, find, check);
                }
            }
        } while (find);
    }

    /**
     * Tìm kiếm hoa theo mã ID.
     *
     * @see Add#addID()
     */
    public void findFlowerByID() {
        boolean find = true;
        do {
            String id = add.addID();
            if (this.isExistID(id)) {
                System.out.println(this.findFlower(id));
                find = false;
            } else {
                System.out.println("   (!) Nurse does not exist");
                int find_ = -1;
                String check = add.addCheck();
                if (!check.matches("\\d+")) {
                    System.out.println("   (!) Please enter true format");
                } else {
                    find = this.continueFind(find_, find, check);
                }
            }
        } while (find);
    }

    /**
     * Cập nhật mô tả cho hoa.
     *
     * @param a Đối tượng hoa cần cập nhật
     * @see Check#checkDescription()
     */
    public void updateDescription(Flower a) {
        String description = check.checkdDscription();
        a.setDescription(description);
        System.out.println("       Update Success");
    }

    /**
     * Cập nhật ngày nhập hàng cho hoa.
     *
     * @param a Đối tượng hoa cần cập nhật
     * @see Check#checkImportDate()
     */
    public void updateImportDate(Flower a) {
        String importDate = check.checkImportDate();
        a.setImportDate(importDate);
        System.out.println("       Update Success");
    }

    /**
     * Cập nhật giá bán cho hoa.
     *
     * @param a Đối tượng hoa cần cập nhật
     * @see Check#checkUnitPrice()
     */
    public void updateUnitPrice(Flower a) {
        String unitPrice = check.checkUnitPrice();
        a.setUnitPrice(unitPrice);
        System.out.println("       Update Success");
    }

    /**
     * Cập nhật loại hoa cho hoa.
     *
     * @param a Đối tượng hoa cần cập nhật
     * @see Check#checkCategory()
     */
    public void updateCategory(Flower a) {
        String category = check.checkCategory();
        a.setCategory(category);
        System.out.println("       Update Success");
    }

    /**
     * Tiếp tục quá trình cập nhật hoặc thoát khỏi menu cập nhật.
     *
     * @param b Giá trị hiện tại của lựa chọn (cập nhật hoặc thoát)
     * @return -1 nếu thoát khỏi menu cập nhật, giá trị khác trả về giá trị hiện
     * tại của lựa chọn
     */
    public int continueUpdate(int b) {
        boolean check1 = true;
        do {
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

    /**
     * Kiểm tra và thực hiện xóa hoa khỏi danh sách.
     *
     * @param delete Biến kiểm tra quá trình xóa
     * @param staffID_ Mã ID hoa cần xóa
     * @param x Biến kiểm tra xóa
     * @return true nếu xóa thành công, false nếu xóa thất bại
     */
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

    /**
     * Xóa hoa khỏi danh sách theo mã ID.
     *
     * @param id Mã ID hoa cần xóa
     * @return Chuỗi thông báo xóa thành công hoặc xóa thất bại
     */
    public String deleteNurse(String id) {
        if (this.isExistID(id)) {
            this.remove(this.findFlower(id));
            return "       Delete Success";
        } else {
            return "       Delete Fail";
        }
    }

    /**
     * Tải các loại hoa từ tệp và thêm chúng vào FlowerStore.
     *
     * @throws Exception nếu có lỗi xảy ra trong quá trình tải.
     */
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

    /**
     * Lưu các loại hoa từ FlowerStore vào tệp.
     *
     * @return 1 nếu thao tác lưu thành công, 0 nếu không thành công.
     * @throws Exception nếu có lỗi xảy ra trong quá trình lưu.
     */
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
