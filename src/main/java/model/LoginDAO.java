package model;

import ma.fstt.Login;

import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class LoginDAO extends BaseDAO<Login>{
    public LoginDAO() throws SQLException {
        super();
    }

    public String userType;

    @Override
    public void save(Login object) throws SQLException {
        String request = "insert into login (username,password,userType,emailAddress,profilePicturePath) values (? , ?, ?, ?,?)";

        // mapping objet table

        this.preparedStatement = this.connection.prepareStatement(request);
        // mapping
        this.preparedStatement.setString(1 , object.getUsername());

        this.preparedStatement.setString(2 , object.getPassword());

        this.preparedStatement.setString(3, object.getUserType());

        this.preparedStatement.setString(4 , object.getEmailAddress());

        this.preparedStatement.setString(5, object.getProfilePicturePath());




        this.preparedStatement.execute();

    }

    @Override
    public void update(Login object) throws SQLException {
        String request = "update login set  username = ?, password = ?, userType = ?, emailAddress = ?, profilePicturePath = ? where id = ?";

        this.preparedStatement = this.connection.prepareStatement(request);
        // mapping
        this.preparedStatement.setString(1, object.getUsername());
        this.preparedStatement.setString(2, object.getPassword());
        this.preparedStatement.setString(3, object.getUserType());
        this.preparedStatement.setString(4, object.getEmailAddress());
        this.preparedStatement.setString(5, object.getProfilePicturePath());
        this.preparedStatement.setLong(6, object.getId());


        this.preparedStatement.executeUpdate();
    }

    @Override
    public void delete(Login object) throws SQLException {
        String request = "delete from login where id=?";
        this.preparedStatement = this.connection.prepareStatement(request);
        this.preparedStatement.setLong(1, object.getId());
        this.preparedStatement.executeUpdate();

    }

    @Override
    public List<Login> getAll() throws SQLException {
        List<Login> mylist = new ArrayList<Login>();

        String request = "select * from login ";

        this.statement = this.connection.createStatement();

        this.resultSet =   this.statement.executeQuery(request);

// parcours de la table
        while ( this.resultSet.next()){

// mapping table objet
            mylist.add(new Login(this.resultSet.getLong(1)
                    ,this.resultSet.getString(2)
                    ,this.resultSet.getString(3)
                    ,this.resultSet.getString(4)
                    ,this.resultSet.getString(5)
                    ,this.resultSet.getString(6)
            ));


        }


        return mylist;
    }

    @Override
    public Login getOne(Long id) throws SQLException {
        return null;
    }

    public Login getLoggedInUser(String username) throws SQLException {
        String request = "SELECT * FROM login WHERE username = ?";
        this.preparedStatement = this.connection.prepareStatement(request);
        this.preparedStatement.setString(1, username);
        this.resultSet = this.preparedStatement.executeQuery();

        if (this.resultSet.next()) {
            Long id = this.resultSet.getLong("id");
            String password = this.resultSet.getString("password");
            String userType = this.resultSet.getString("userType");
            String emailAddress = this.resultSet.getString("emailAddress");
            String profilePicture = this.resultSet.getString("profilePicturePath");

            return new Login(id, username, password, userType, emailAddress,profilePicture);
        }

        return null;
    }

    public  boolean authentifier(String username, String password) throws SQLException{
        String request = "Select username,password,userType from login where username = ?";
        this.preparedStatement = this.connection.prepareStatement(request);
        this.preparedStatement.setString(1, username);

        this.resultSet = this.preparedStatement.executeQuery();

        while(this.resultSet.next()){
            System.out.println(this.resultSet.getString(1));
            System.out.println(this.resultSet.getString(2));
            System.out.println(this.resultSet.getString(3));
             userType = this.resultSet.getString(3);
            if (password.equals(this.resultSet.getString(2))){
                return true;
            }
        }
        return false;
    }
}
