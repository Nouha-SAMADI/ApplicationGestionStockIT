package model;

import ma.fstt.ParametrageDeReference;

import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class ParametrageDeReferenceDAO extends BaseDAO<ParametrageDeReference>{
    public ParametrageDeReferenceDAO() throws SQLException {
        super();
    }

    @Override
    public void save(ParametrageDeReference object) throws SQLException {


        String request = "insert into parametrageRef (type, categorie,quantity,stockMax,stockMin,reference,brand,serialNumber) values (? , ?, ?, ?, ?, ?, ?, ?)";

        // mapping objet table

        this.preparedStatement = this.connection.prepareStatement(request);
        // mapping
        this.preparedStatement.setString(1 , object.getType());

        this.preparedStatement.setString(2 , object.getCategorie());

        this.preparedStatement.setInt(3 , object.getQuantity());

        this.preparedStatement.setInt(4 , object.getStockMax());

        this.preparedStatement.setInt(5 , object.getStockMin());

        this.preparedStatement.setString(6 , object.getReference());

        this.preparedStatement.setString(7 , object.getBrand());

        if(object.getSerialNumber() != 0){
            this.preparedStatement.setInt(8 , object.getSerialNumber());
        }else {
            this.preparedStatement.setNull(8, Types.INTEGER);
        }





        this.preparedStatement.execute();

    }

    public void update(ParametrageDeReference object) throws SQLException {
        String request = "update parametrageRef set type = ?, categorie = ?, quantity = ?, stockMax = ?, stockMin = ?, reference = ?, brand = ?, serialNumber = ? where id = ?";

        this.preparedStatement = this.connection.prepareStatement(request);
        // mapping
        this.preparedStatement.setString(1, object.getType());
        this.preparedStatement.setString(2, object.getCategorie());
        this.preparedStatement.setInt(3, object.getQuantity());
        this.preparedStatement.setInt(4, object.getStockMax());
        this.preparedStatement.setInt(5, object.getStockMin());
        this.preparedStatement.setString(6, object.getReference());
        this.preparedStatement.setString(7, object.getBrand());

        if (object.getSerialNumber() != 0) {
            this.preparedStatement.setInt(8, object.getSerialNumber());
        } else {
            this.preparedStatement.setNull(8, Types.INTEGER);
        }

        this.preparedStatement.setLong(9, object.getId());

        this.preparedStatement.executeUpdate();
    }


    @Override
    public void delete(ParametrageDeReference object) throws SQLException {

        String request = "delete from parametrageRef where id=?";
        this.preparedStatement = this.connection.prepareStatement(request);
        this.preparedStatement.setLong(1, object.getId());
        this.preparedStatement.executeUpdate();

    }


    @Override
    public List<ParametrageDeReference> getAll() throws SQLException {
        List<ParametrageDeReference> mylist = new ArrayList<ParametrageDeReference>();

        String request = "select * from parametrageRef ";

        this.statement = this.connection.createStatement();

        this.resultSet =   this.statement.executeQuery(request);

// parcours de la table
        while ( this.resultSet.next()){

// mapping table objet
            mylist.add(new ParametrageDeReference(this.resultSet.getLong(1)
                    ,this.resultSet.getString(2)
                    ,this.resultSet.getString(3)
                    ,this.resultSet.getInt(4)
                    ,this.resultSet.getInt(5)
                    ,this.resultSet.getInt(6)
                    ,this.resultSet.getString(7)
                    ,this.resultSet.getString(8)
                    ,this.resultSet.getInt(9)));


        }


        return mylist;
    }

    @Override
    public ParametrageDeReference getOne(Long id) throws SQLException {
        return null;
    }
}
