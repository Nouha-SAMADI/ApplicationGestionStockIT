package ma.fstt;
import java.time.LocalDate;

public class Entry {
    private int id;
    private String productReference;
    private int quantity;
    private LocalDate entryDate;

    public Entry() {
    }

    public Entry(String productReference, int quantity, LocalDate entryDate) {
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

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public LocalDate getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(LocalDate entryDate) {
        this.entryDate = entryDate;
    }
}

