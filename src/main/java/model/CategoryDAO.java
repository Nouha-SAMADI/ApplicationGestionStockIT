package model;

import ma.fstt.Category;
import ma.fstt.Type;

import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

public class CategoryDAO extends BaseDAO<Category> {
    public CategoryDAO() throws SQLException {
        super();
    }

    @Override
    public void save(Category object) throws SQLException {
        String request = "insert into categories (name, type_id) values (?, ?)";

        this.preparedStatement = this.connection.prepareStatement(request);
        this.preparedStatement.setString(1, object.getName());
        this.preparedStatement.setLong(2, object.getType().getId());

        this.preparedStatement.execute();
    }

    @Override
    public void update(Category object) throws SQLException {
        String request = "update categories set name = ?, type_id = ? where id = ?";

        this.preparedStatement = this.connection.prepareStatement(request);
        this.preparedStatement.setString(1, object.getName());
        this.preparedStatement.setLong(2, object.getType().getId());
        this.preparedStatement.setLong(3, object.getId());

        this.preparedStatement.executeUpdate();
    }

    @Override
    public void delete(Category object) throws SQLException {
        String request = "delete from categories where id = ?";

        this.preparedStatement = this.connection.prepareStatement(request);
        this.preparedStatement.setLong(1, object.getId());

        this.preparedStatement.executeUpdate();
    }

    @Override
    public List<Category> getAll() throws SQLException {
        List<Category> categoryList = new ArrayList<>();

        String request = "select * from categories";

        this.statement = this.connection.createStatement();
        this.resultSet = this.statement.executeQuery(request);

        while (this.resultSet.next()) {
            Category category = new Category(this.resultSet.getLong(1), this.resultSet.getString(2), null);
            Long typeId = this.resultSet.getLong(3);
            Type type = getTypeById(typeId);
            category.setType(type);
            categoryList.add(category);
        }
        return categoryList;
    }

    @Override
    public Category getOne(Long id) throws SQLException {
        String request = "select * from categories where id = ?";

        this.preparedStatement = this.connection.prepareStatement(request);
        this.preparedStatement.setLong(1, id);

        this.resultSet = this.preparedStatement.executeQuery();

        if (this.resultSet.next()) {
            Category category = new Category(this.resultSet.getLong(1), this.resultSet.getString(2), null);
            Long typeId = this.resultSet.getLong(3);
            Type type = getTypeById(typeId);
            category.setType(type);
            return category;
        }

        return null;
    }

    private Type getTypeById(Long typeId) throws SQLException {
        TypeDAO typeDAO = new TypeDAO();
        return typeDAO.getOne(typeId);
    }


    public Category getByName(String name) throws SQLException {
        String request = "SELECT * FROM categories WHERE name = ?";
        this.preparedStatement = this.connection.prepareStatement(request);
        this.preparedStatement.setString(1, name);
        this.resultSet = this.preparedStatement.executeQuery();

        if (this.resultSet.next()) {
            Category category = new Category();
            category.setId(this.resultSet.getLong("id"));
            category.setName(this.resultSet.getString("name"));
            return category;
        }

        return null;
    }

    public List<Category> getByType(Type type) throws SQLException {
        List<Category> categories = new ArrayList<>();

        // Perform a database query to fetch categories associated with the given type
        // Replace this with your actual database query logic
        String query = "SELECT * FROM categories WHERE type_id = ?";
        this.preparedStatement = this.connection.prepareStatement(query);
        this.preparedStatement.setLong(1, type.getId());
        this.resultSet = this.preparedStatement.executeQuery();

        while (this.resultSet.next()) {
            long categoryId = this.resultSet.getLong("id");
            String categoryName = this.resultSet.getString("name");

            // Create Category objects and add them to the list
            Category category = new Category(categoryId, categoryName, type);
            categories.add(category);
        }

        // Close the result set, prepared statement, and release the connection
        this.resultSet.close();
        this.preparedStatement.close();

        return categories;
    }


    public Map<Long, Category> getCategoriesByIds(Set<Long> categoryIds) throws SQLException {
        Map<Long, Category> categoriesMap = new HashMap<>();

        // Convert the set of category IDs to a comma-separated string
        String categoryIdsString = categoryIds.stream()
                .map(Object::toString)
                .collect(Collectors.joining(","));

        String request = "SELECT * FROM categories WHERE id IN (" + categoryIdsString + ")";
        // Assuming you have a connection and statement objects in the CategoryDAO class
        this.statement = this.connection.createStatement();
        this.resultSet = this.statement.executeQuery(request);

        while (this.resultSet.next()) {
            long id = this.resultSet.getLong("id");
            String categoryName = this.resultSet.getString("name");

            Category category = new Category(id, categoryName);
            categoriesMap.put(id, category);
        }

        return categoriesMap;
    }



}