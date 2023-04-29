package ma.fstt;

public class ParametrageDeReference {

    private long id;
    private String type;
    private String categorie;
    private int quantity;
    private int stockMax;
    private int stockMin;
    private String reference;
    private String brand;
    private int serialNumber;

    public ParametrageDeReference(long id, String type, String categorie, int quantity, int stockMax, int stockMin, String reference, String brand, int serialNumber) {
        this.id = id;
        this.type = type;
        this.categorie = categorie;
        this.quantity = quantity;
        this.stockMax = stockMax;
        this.stockMin = stockMin;
        this.reference = reference;
        this.brand = brand;
        this.serialNumber = serialNumber;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quntity) {
        this.quantity = quantity;
    }

    public int getStockMax() {
        return stockMax;
    }

    public void setStockMax(int stockMax) {
        this.stockMax = stockMax;
    }

    public int getStockMin() {
        return stockMin;
    }

    public void setStockMin(int stockMin) {
        this.stockMin = stockMin;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(int serialNumber) {
        this.serialNumber = serialNumber;
    }
}
