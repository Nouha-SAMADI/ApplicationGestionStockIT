package ma.fstt;

public class Category {
    private long id;
    private String name;

    public Category(long categoryId, String categoryName) {
        this.id = categoryId;
        this.name = categoryName;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    private Type type;

    public Category(long id, String name, Type type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    public Category() {

    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Type getType() {
        return type;
    }

    @Override
    public String toString() {
        return name;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
