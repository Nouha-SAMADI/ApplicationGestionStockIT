package model;

import ma.fstt.Category;
import ma.fstt.ParametrageDeReference;
import ma.fstt.Type;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

public class ParametrageDeReferenceDAO extends BaseDAO<ParametrageDeReference>{
    public ParametrageDeReferenceDAO() throws SQLException {
        super();
    }

    @Override
    public void save(ParametrageDeReference object) throws SQLException {

        String request = "INSERT INTO parametrageRef (type_id, category_id, quantity, stockMax, stockMin, reference, brand, serialNumber) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        this.preparedStatement = this.connection.prepareStatement(request);
        this.preparedStatement.setLong(1, object.getType().getId());
        this.preparedStatement.setLong(2, object.getCategory().getId());
        this.preparedStatement.setInt(3, object.getQuantity());
        this.preparedStatement.setInt(4, object.getStockMax());
        this.preparedStatement.setInt(5, object.getStockMin());
        this.preparedStatement.setString(6, object.getReference());
        this.preparedStatement.setString(7, object.getBrand());
        this.preparedStatement.setInt(8, object.getSerialNumber());

        this.preparedStatement.execute();

    }

    @Override
    public void update(ParametrageDeReference object) throws SQLException {
        String request = "UPDATE parametrageRef SET type_id = ?, category_id = ?, quantity = ?, stockMax = ?, stockMin = ?, reference = ?, brand = ?, serialNumber = ? WHERE id = ?";

        this.preparedStatement = this.connection.prepareStatement(request);
        this.preparedStatement.setLong(1, object.getType().getId());
        this.preparedStatement.setLong(2, object.getCategory().getId());
        this.preparedStatement.setInt(3, object.getQuantity());
        this.preparedStatement.setInt(4, object.getStockMax());
        this.preparedStatement.setInt(5, object.getStockMin());
        this.preparedStatement.setString(6, object.getReference());
        this.preparedStatement.setString(7, object.getBrand());
        this.preparedStatement.setInt(8, object.getSerialNumber());
        this.preparedStatement.setLong(9, object.getId());

        this.preparedStatement.executeUpdate();

    }

    @Override
    public void delete(ParametrageDeReference object) throws SQLException {
        String request = "DELETE FROM parametrageRef WHERE id = ?";

        this.preparedStatement = this.connection.prepareStatement(request);
        this.preparedStatement.setLong(1, object.getId());

        this.preparedStatement.executeUpdate();

    }

    @Override
    public ParametrageDeReference getOne(Long id) throws SQLException {
        String request = "SELECT * FROM parametrageRef WHERE id = ?";

        this.preparedStatement = this.connection.prepareStatement(request);
        this.preparedStatement.setLong(1, id);

        this.resultSet = this.preparedStatement.executeQuery();

        if (this.resultSet.next()) {
            long typeId = this.resultSet.getLong("type_id");
            long categoryId = this.resultSet.getLong("category_id");
            int quantity = this.resultSet.getInt("quantity");
            int stockMax = this.resultSet.getInt("stockMax");
            int stockMin = this.resultSet.getInt("stockMin");
            String reference = this.resultSet.getString("reference");
            String brand = this.resultSet.getString("brand");
            int serialNumber = this.resultSet.getInt("serialNumber");

            Type type = getTypeById(typeId);
            Category category = getCategoryById(categoryId);

            return new ParametrageDeReference(id, type, category, quantity, stockMax, stockMin, reference, brand, serialNumber);
        }

        return null;
    }

    public ParametrageDeReference getByReference(String reference) throws SQLException {
        String query = "SELECT * FROM parametrageRef WHERE reference = ?";
        this.preparedStatement = this.connection.prepareStatement(query);
        this.preparedStatement.setString(1, reference);
        this.resultSet = this.preparedStatement.executeQuery();

        if (resultSet.next()) {
            long typeId = this.resultSet.getLong("type_id");
            long categoryId = this.resultSet.getLong("category_id");
            int quantity = this.resultSet.getInt("quantity");
            int stockMax = this.resultSet.getInt("stockMax");
            int stockMin = this.resultSet.getInt("stockMin");
            String brand = this.resultSet.getString("brand");
            int serialNumber = this.resultSet.getInt("serialNumber");

            Type type = getTypeById(typeId);
            Category category = getCategoryById(categoryId);

            return new ParametrageDeReference( type, category, quantity, stockMax, stockMin,reference, brand, serialNumber);
        }

        return null;
    }

    public  List<String> getAllReferences() throws SQLException {
        List<String> references = new ArrayList<>();

        String query = "SELECT reference FROM parametrageRef";
        this.statement = this.connection.createStatement();
        this.resultSet = this.statement.executeQuery(query);

        while (this.resultSet.next()) {
            String reference = this.resultSet.getString("reference");
            references.add(reference);
        }

        return references;
    }


    public void updateQuantity(String reference, int quantity) throws SQLException {
        String query = "UPDATE parametrageRef SET quantity = quantity + ? WHERE reference = ?";
        this.preparedStatement= this.connection.prepareStatement(query);
        this.preparedStatement.setInt(1, quantity);
        this.preparedStatement.setString(2, reference);


        this.preparedStatement.executeUpdate();
    }

    public void updateSortieQuantity(String reference, int quantity) throws SQLException {
        String query = "UPDATE parametrageRef SET quantity = quantity - ? WHERE reference = ?";
        this.preparedStatement= this.connection.prepareStatement(query);
        this.preparedStatement.setInt(1, quantity);
        this.preparedStatement.setString(2, reference);


        this.preparedStatement.executeUpdate();
    }


    private Type getTypeById(long typeId) throws SQLException {
        TypeDAO typeDAO = new TypeDAO();
        return typeDAO.getOne(typeId);
    }

    private Category getCategoryById(long categoryId) throws SQLException {
        CategoryDAO categoryDAO = new CategoryDAO();
        return categoryDAO.getOne(categoryId);
    }

    @Override
    public List<ParametrageDeReference> getAll() throws SQLException {
        List<ParametrageDeReference> objectList = new ArrayList<>();

        String request = "SELECT * FROM parametrageRef";


        // Create a scrollable result set
        this.statement = this.connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        this.resultSet = this.statement.executeQuery(request);


        // Fetching all type and category IDs
        Set<Long> typeIds = new HashSet<>();
        Set<Long> categoryIds = new HashSet<>();
        while (this.resultSet.next()) {
            long typeId = this.resultSet.getLong("type_id");
            long categoryId = this.resultSet.getLong("category_id");
            typeIds.add(typeId);
            categoryIds.add(categoryId);
        }

        // Retrieving types and categories in bulk
        Map<Long, Type> types = getTypesByIds(typeIds);
        Map<Long, Category> categories = getCategoriesByIds(categoryIds);

        // Reset the result set to the beginning
        this.resultSet.beforeFirst();

        while (this.resultSet.next()) {
            long id = this.resultSet.getLong("id");
            long typeId = this.resultSet.getLong("type_id");
            long categoryId = this.resultSet.getLong("category_id");
            int quantity = this.resultSet.getInt("quantity");
            int stockMax = this.resultSet.getInt("stockMax");
            int stockMin = this.resultSet.getInt("stockMin");
            String reference = this.resultSet.getString("reference");
            String brand = this.resultSet.getString("brand");
            int serialNumber = this.resultSet.getInt("serialNumber");

            Type type = types.get(typeId);
            Category category = categories.get(categoryId);
            ParametrageDeReference param = new ParametrageDeReference(id, type, category, quantity, stockMax, stockMin, reference, brand, serialNumber);
            objectList.add(param);
        }

        return objectList;
    }

    private Map<Long, Type> getTypesByIds(Set<Long> typeIds) throws SQLException {
        TypeDAO typeDAO = new TypeDAO();
        return typeDAO.getTypesByIds(typeIds);
    }

    private Map<Long, Category> getCategoriesByIds(Set<Long> categoryIds) throws SQLException {
        CategoryDAO categoryDAO = new CategoryDAO();
        return categoryDAO.getCategoriesByIds(categoryIds);
    }

}

