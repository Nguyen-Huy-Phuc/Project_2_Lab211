package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Nguyễn Huy Phúc
 */
public class Flower implements Serializable{

    private String iD;
    private String description;
    private LocalDate importDate;
    private String unitPrice;
    private String category;

    public Flower(String iD, String description, String importDate, String unitPrice, String category) {
        this.iD = iD;
        this.description = description;
        setImportDate(importDate);
        this.unitPrice = unitPrice;
        this.category = category;
    }

    public String getiD() {
        return iD;
    }

    public void setiD(String iD) {
        this.iD = iD;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getImportDate() {
        return importDate;
    }

    public void setImportDate(String importDate) {
        LocalDate date = LocalDate.parse(importDate, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        this.importDate = date;
    }

    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

}
