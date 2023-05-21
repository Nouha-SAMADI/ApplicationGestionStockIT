package model;

import ma.fstt.Entry;
import ma.fstt.Login;
import ma.fstt.SessionManager;
import ma.fstt.Sortie;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SortieDAO extends BaseDAO<Sortie> {

    public SortieDAO() throws SQLException {
        super();
    }

    @Override
    public void save(Sortie object) throws SQLException {
        // Get the logged-in user
        Login loggedInUser = SessionManager.getLoggedInUser();

        // Check if a user is logged in
        if (loggedInUser != null) {
            // Retrieve the user's ID
            Long loggedInUserId = loggedInUser.getId();

            // Associate the logged-in user with the Sortie object
            object.setUserId(loggedInUserId);

            // Save the Sortie object to the database
            String request = "INSERT INTO sortie (product_reference, quantity, sortie_date, user_id) VALUES (?, ?, ?, ?)";
            this.preparedStatement = this.connection.prepareStatement(request);

            // Set the values for the prepared statement
            this.preparedStatement.setString(1, object.getProductReference());
            this.preparedStatement.setInt(2, object.getQuantity());
            this.preparedStatement.setDate(3, java.sql.Date.valueOf(object.getSortieDate()));
            this.preparedStatement.setLong(4, object.getUserId());

            // Execute the statement
            this.preparedStatement.executeUpdate();
        } else {
            // Handle the case when no user is logged in
            System.out.println("No user is logged in.");
        }

    }

    @Override
    public void update(Sortie object) throws SQLException {

    }

    @Override
    public void delete(Sortie object) throws SQLException {

    }

    @Override
    public List<Sortie> getAll() throws SQLException {
        List<Sortie> sorties = new ArrayList<>();

        String query = "SELECT * FROM sortie";
        this.preparedStatement = this.connection.prepareStatement(query);
        this.resultSet = this.preparedStatement.executeQuery();

        while (this.resultSet.next()) {
            Sortie sortie = new Sortie();
            sortie.setId(this.resultSet.getLong(1));
            sortie.setProductReference(this.resultSet.getString(2));
            sortie.setQuantity(this.resultSet.getInt(3));
            sortie.setSortieDate(this.resultSet.getDate(4).toLocalDate());
            sorties.add(sortie);
        }

        return sorties;
    }


    public List<Sortie> getAllWithUsernames() throws SQLException {
        List<Sortie> sorties = new ArrayList<>();

        String request = "SELECT s.id, s.product_reference, s.quantity, s.sortie_date, l.username "
                + "FROM sortie s "
                + "INNER JOIN login l ON s.user_id = l.id";

        this.statement = this.connection.createStatement();
        this.resultSet = this.statement.executeQuery(request);

        while (this.resultSet.next()) {
            Sortie sortie = new Sortie();
            sortie.setId(this.resultSet.getLong(1));
            sortie.setProductReference(this.resultSet.getString(2));
            sortie.setQuantity(this.resultSet.getInt(3));
            sortie.setSortieDate(this.resultSet.getDate(4).toLocalDate());
            sortie.setUsername(this.resultSet.getString(5));

            sorties.add(sortie);
        }

        return sorties;
    }

    @Override
    public Sortie getOne(Long id) throws SQLException {
        return null;
    }
}
