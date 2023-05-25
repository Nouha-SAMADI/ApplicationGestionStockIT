package ma.fstt;
import java.sql.Timestamp;
import java.time.LocalDate;

public class Entry {
    private int id;
    private String productReference;
    private int quantity;
    private Timestamp entryDate;

    public Entry() {
    }

    public Entry(String productReference, int quantity, Timestamp entryDate) {
        this.productReference = productReference;
        this.quantity = quantity;
        this.entryDate = entryDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductReference() {
        return productReference;
    }

    public void setProductReference(String productReference) {
        this.productReference = productReference;
    }

    public int getQuantity() {
        return quantity;
    }

    public Entry(String productReference, int quantity) {
        this.productReference = productReference;
        this.quantity = quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Timestamp getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(Timestamp entryDate) {
        this.entryDate = entryDate;
    }
}

