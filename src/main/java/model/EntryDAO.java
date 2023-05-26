package model;

import ma.fstt.Entry;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EntryDAO extends BaseDAO<Entry>{
    public EntryDAO() throws SQLException {
        super();
    }

    @Override
    public void save(Entry object) throws SQLException {

        String query = "INSERT INTO entry (product_reference, quantity, entry_date) VALUES (?, ?, ?)";
        this.preparedStatement = this.connection.prepareStatement(query);
        this.preparedStatement.setString(1, object.getProductReference());
        this.preparedStatement.setInt(2, object.getQuantity());
        this.preparedStatement.setTimestamp(3,object.getEntryDate());
        this.preparedStatement.executeUpdate();



    }

    @Override
    public void update(Entry object) throws SQLException {

    }

    @Override
    public void delete(Entry object) throws SQLException {

    }

    @Override
    public List<Entry> getAll() throws SQLException {
        List<Entry> entries = new ArrayList<>();

        String query = "SELECT * FROM entry";
        this.preparedStatement = this.connection.prepareStatement(query);
        this.resultSet = this.preparedStatement.executeQuery();

        while (this.resultSet.next()) {
            Entry entry = new Entry();
            entry.setId(this.resultSet.getInt("id"));
            entry.setProductReference(this.resultSet.getString("product_reference"));
            entry.setQuantity(this.resultSet.getInt("quantity"));
            entry.setEntryDate(this.resultSet.getTimestamp("entry_date"));
            entries.add(entry);
        }

        return entries;

    }

    @Override
    public Entry getOne(Long id) throws SQLException {
        return null;
    }
    public Entry getLastEntry() throws SQLException {
        Entry lastEntry = null;

        String query = "SELECT * FROM entry ORDER BY entry_date DESC LIMIT 1";
        this.preparedStatement = this.connection.prepareStatement(query);
        this.resultSet = this.preparedStatement.executeQuery();

        if (this.resultSet.next()) {
            lastEntry = new Entry();
            lastEntry.setId(this.resultSet.getInt("id"));
            lastEntry.setProductReference(this.resultSet.getString("product_reference"));
            lastEntry.setQuantity(this.resultSet.getInt("quantity"));
            lastEntry.setEntryDate(this.resultSet.getTimestamp("entry_date"));
        }

        return lastEntry;
    }

}
