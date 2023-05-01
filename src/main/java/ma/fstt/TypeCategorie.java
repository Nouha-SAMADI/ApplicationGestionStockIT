package ma.fstt;

import java.util.List;

public class TypeCategorie {
    private String type;
    private List<String> categories;

    public TypeCategorie(String type, List<String> categories) {
        this.type = type;
        this.categories = categories;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    @Override
    public String toString() {
        return type;
    }
}
