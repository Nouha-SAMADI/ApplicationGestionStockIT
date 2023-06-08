package model;



import ma.fstt.Type;

import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

public class TypeDAO extends BaseDAO<Type> {
    public TypeDAO() throws SQLException {
        super();
    }

    @Override
    public void save(Type object) throws SQLException {
        String request = "insert into types (name) values (?)";

        this.preparedStatement = this.connection.prepareStatement(request);
        this.preparedStatement.setString(1, object.getName());

        this.preparedStatement.execute();
    }

    @Override
    public void update(Type object) throws SQLException {
        String request = "update types set name = ? where id = ?";

        this.preparedStatement = this.connection.prepareStatement(request);
        this.preparedStatement.setString(1, object.getName());
        this.preparedStatement.setLong(2, object.getId());

        this.preparedStatement.executeUpdate();
    }

    @Override
    public void delete(Type object) throws SQLException {
        String request = "delete from types where id = ?";

        this.preparedStatement = this.connection.prepareStatement(request);
        this.preparedStatement.setLong(1, object.getId());

        this.preparedStatement.executeUpdate();
    }

    @Override
    public List<Type> getAll() throws SQLException {
        List<Type> typeList = new ArrayList<>();

        String request = "select * from types";

        this.statement = this.connection.createStatement();
        this.resultSet = this.statement.executeQuery(request);

        while (this.resultSet.next()) {
            Type type = new Type(this.resultSet.getLong(1), this.resultSet.getString(2));
            typeList.add(type);
        }

        return typeList;
    }

    @Override
    public Type getOne(Long id) throws SQLException {
        String request = "select * from types where id = ?";

        this.preparedStatement = this.connection.prepareStatement(request);
        this.preparedStatement.setLong(1, id);

        this.resultSet = this.preparedStatement.executeQuery();

        if (this.resultSet.next()) {
            return new Type(this.resultSet.getLong(1), this.resultSet.getString(2));
        }

        return null;
    }



    public Type getByName(String name) throws SQLException {
        String request = "SELECT * FROM types WHERE name = ?";
        this.preparedStatement = this.connection.prepareStatement(request);
        this.preparedStatement.setString(1, name);
        this.resultSet = this.preparedStatement.executeQuery();

        if (this.resultSet.next()) {
            Type type = new Type();
            type.setId(this.resultSet.getLong("id"));
            type.setName(this.resultSet.getString("name"));
            return type;
        }

        return null;
    }

    public Map<Long, Type> getTypesByIds(Set<Long> typeIds) throws SQLException {
        Map<Long, Type> typesMap = new HashMap<>();

        // Convert the set of type IDs to a comma-separated string
        String typeIdsString = typeIds.stream()
                .map(Object::toString)
                .collect(Collectors.joining(","));

        String request = "SELECT * FROM types WHERE id IN (" + typeIdsString + ")";
        // Assuming you have a connection and statement objects in the TypeDAO class
        this.statement = this.connection.createStatement();
        this.resultSet = this.statement.executeQuery(request);

        while (this.resultSet.next()) {
            long id = this.resultSet.getLong("id");
            String typeName = this.resultSet.getString("name");

            Type type = new Type(id, typeName);
            typesMap.put(id, type);
        }

        return typesMap;
    }

}

