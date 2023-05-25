package ma.fstt;

import java.sql.Timestamp;
import java.time.LocalDate;

public class Sortie {

    private Long id;
    private String productReference;
    private int quantity;
    private Timestamp sortieDate;
    private Long userId;


    public Sortie(Long id, String productReference, int quantity, Timestamp stockOutDate, Long userId) {
        this.id = id;
        this.productReference = productReference;
        this.quantity = quantity;
        this.sortieDate = stockOutDate;
        this.userId = userId;
    }

    private String username;

    public Sortie(String reference, int quantity, Long userId) {
        this.productReference = reference;
        this.quantity = quantity;

        this.userId = userId;
    }

    public Sortie() {

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Timestamp getSortieDate() {
        return sortieDate;
    }

    public void setSortieDate(Timestamp sortieDate) {
        this.sortieDate = sortieDate;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Sortie(Long id, String productReference, int quantity, Timestamp stockOutDate, Long userId, String username) {
        this.id = id;
        this.productReference = productReference;
        this.quantity = quantity;
        this.sortieDate = stockOutDate;
        this.userId = userId;
        this.username = username;
    }
}
