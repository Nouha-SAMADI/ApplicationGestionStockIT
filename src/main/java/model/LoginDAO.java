package model;

import ma.fstt.Login;

import java.sql.SQLException;
import java.util.List;

public class LoginDAO extends BaseDAO<Login>{
    public LoginDAO() throws SQLException {
        super();
    }

    public Long userType;

    @Override
    public void save(Login object) throws SQLException {

    }

    @Override
    public void update(Login object) throws SQLException {

    }

    @Override
    public void delete(Login object) throws SQLException {

    }

    @Override
    public List<Login> getAll() throws SQLException {
        return null;
    }

    @Override
    public Login getOne(Long id) throws SQLException {
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
            System.out.println(this.resultSet.getLong(3));
             userType = this.resultSet.getLong(3);
            if (password.equals(this.resultSet.getString(2))){
                return true;
            }
        }
        return false;
    }
}
