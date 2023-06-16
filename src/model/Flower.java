package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Đại diện cho một loại hoa.
 *
 * @author Nguyễn Huy Phúc
 */
public class Flower implements Serializable {

    private String iD;
    private String description;
    private LocalDate importDate;
    private String unitPrice;
    private String category;

    /**
     * Khởi tạo một đối tượng Flower.
     *
     * @param iD mã ID của hoa
     * @param description mô tả của hoa
     * @param importDate ngày nhập hoa (dạng chuỗi)
     * @param unitPrice giá tiền của hoa
     * @param category danh mục hoa
     */
    public Flower(String iD, String description, String importDate, String unitPrice, String category) {
        this.iD = iD;
        this.description = description;
        setImportDate(importDate);
        this.unitPrice = unitPrice;
        this.category = category;
    }

    /**
     * Trả về mã ID của hoa.
     *
     * @return mã ID của hoa
     */
    public String getiD() {
        return iD;
    }

    /**
     * Trả về mô tả của hoa.
     *
     * @return mô tả của hoa
     */
    public String getDescription() {
        return description;
    }

    /**
     * Trả về ngày nhập hoa.
     *
     * @return ngày nhập hoa
     */
    public LocalDate getImportDate() {
        return importDate;
    }

    /**
     * Trả về giá tiền của hoa.
     *
     * @return giá tiền của hoa
     */
    public String getUnitPrice() {
        return unitPrice;
    }

    /**
     * Trả về danh mục của hoa.
     *
     * @return danh mục của hoa
     */
    public String getCategory() {
        return category;
    }

    /**
     * Thiết lập mã ID cho hoa.
     *
     * @param iD mã ID của hoa
     */
    public void setiD(String iD) {
        this.iD = iD;
    }

    /**
     * Thiết lập mô tả cho hoa.
     *
     * @param description mô tả của hoa
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Thiết lập ngày nhập hoa.
     *
     * @param importDate ngày nhập hoa (dạng chuỗi, ví dụ: "dd/MM/yyyy")
     */
    public void setImportDate(String importDate) {
        LocalDate date = LocalDate.parse(importDate, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        this.importDate = date;
    }

    /**
     * Thiết lập giá tiền cho hoa.
     *
     * @param unitPrice giá tiền của hoa
     */
    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }

    /**
     * Thiết lập danh mục cho hoa.
     *
     * @param category danh mục của hoa
     */
    public void setCategory(String category) {
        this.category = category;
    }
}
