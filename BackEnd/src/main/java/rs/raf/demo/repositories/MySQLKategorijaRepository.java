package rs.raf.demo.repositories;


import rs.raf.demo.entities.Kategorija;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class MySQLKategorijaRepository extends MySqlAbstractRepository implements IKategorijaRepository {



    @Override
    public List<Kategorija> all() {
        List<Kategorija> kategorije = new ArrayList<>();

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from kategorije");
            while (resultSet.next()) {
                kategorije.add(new Kategorija(resultSet.getInt("id"),
                        resultSet.getString("ime"), resultSet.getString("opis")));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(statement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return kategorije;
    }

    @Override
    public Kategorija findById(int id) {
        return null;
    }

    @Override
    public synchronized Kategorija insert(Kategorija kategorija) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            String[] generatedColumns = {"id"};

            preparedStatement = connection.prepareStatement("INSERT INTO kategorije (ime, opis) VALUES(?, ?)", generatedColumns);
            preparedStatement.setString(1, kategorija.getIme());
            preparedStatement.setString(2, kategorija.getOpis());
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {
                kategorija.setId(resultSet.getInt(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return kategorija;
    }

    @Override
    public void edit(Kategorija kategorija) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("UPDATE kategorije SET ime=?,opis=? WHERE id=?");
            preparedStatement.setString(1, kategorija.getIme());
            preparedStatement.setString(2, kategorija.getOpis());
            preparedStatement.setInt(3, kategorija.getId());
            int rowCount = preparedStatement.executeUpdate();

            if(rowCount == 1){
                return;
            }else {
                throw new RuntimeException("Greska");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeConnection(connection);
        }
    }

    @Override
    public void delete(Integer id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();
            preparedStatement = connection.prepareStatement("select * from vesti WHERE fk_kategorija=?");
            preparedStatement.setInt(1, id.intValue());
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return;
            }

            preparedStatement = null;
            preparedStatement = connection.prepareStatement("DELETE FROM kategorije WHERE id=?");
            preparedStatement.setInt(1, id.intValue());
            int rowCount = preparedStatement.executeUpdate();

            if(rowCount == 1){
                return;
            }else {
                throw new RuntimeException("Greska");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }
    }


}
