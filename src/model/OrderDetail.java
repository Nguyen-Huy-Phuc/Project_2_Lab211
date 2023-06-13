package model;

import java.io.Serializable;

/**
 *
 * @author Nguyễn Huy Phúc
 */
public class OrderDetail implements Serializable{
    private String fowerID;
    private String quantity;
    

    public OrderDetail(String fowerID, String quantity) {
        this.fowerID = fowerID;
        this.quantity = quantity;
    }

    public String getFowerID() {
        return fowerID;
    }

    public void setFowerID(String fowerID) {
        this.fowerID = fowerID;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    
}
