package ma.fstt;

public class ParametrageDeReference {
    private long id;
    private Type type;
    private Category category;
    private int quantity;
    private int stockMax;
    private int stockMin;
    private String reference;
    private String brand;
    private String serialNumber;






        public ParametrageDeReference(long id, Type type, Category category, int quantity, int stockMax, int stockMin, String reference, String brand, String serialNumber) {
            this.id = id;
            this.type = type;
            this.category = category;
            this.quantity = quantity;
            this.stockMax = stockMax;
            this.stockMin = stockMin;
            this.reference = reference;
            this.brand = brand;
            this.serialNumber = serialNumber;
        }

        public ParametrageDeReference(long id, Type type, Category categorie, int quantity, int stockMax, int stockMin, String reference, String brand) {
            this(id,type,categorie,quantity,stockMax,stockMin,reference,brand,null);
        }

        public ParametrageDeReference(Type type, Category category, int quantity, int stockMax, int stockMin, String reference, String brand, String serialNumber) {
            this.type = type;
            this.category = category;
            this.quantity = quantity;
            this.stockMax = stockMax;
            this.stockMin = stockMin;
            this.reference = reference;
            this.brand = brand;
            this.serialNumber = serialNumber;
        }

        public ParametrageDeReference() {

        }



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
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

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

}
