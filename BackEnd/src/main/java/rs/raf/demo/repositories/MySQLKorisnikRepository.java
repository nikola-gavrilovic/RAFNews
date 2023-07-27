package rs.raf.demo.repositories;

import rs.raf.demo.entities.Korisnik;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class MySQLKorisnikRepository extends MySqlAbstractRepository implements IKorisnikRepository {

    @Override
    public List<Korisnik> all() {
        List<Korisnik> korisnici = new ArrayList<>();

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from korisnici");
            while (resultSet.next()) {
                korisnici.add(new Korisnik(resultSet.getInt("id"),
                        resultSet.getString("email"), resultSet.getString("ime"),
                        resultSet.getString("prezime"), resultSet.getString("tip"), resultSet.getBoolean("aktivan")));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(statement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return korisnici;
    }

    @Override
    public Korisnik findById(int id) {
        return null;
    }

    @Override
    public synchronized Korisnik insert(Korisnik korisnik) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            String[] generatedColumns = {"id"};

            preparedStatement = connection.prepareStatement("INSERT INTO korisnici (email, ime, prezime, tip, password, aktivan) VALUES(?, ?, ?, ?, ?, ?)", generatedColumns);
            preparedStatement.setString(1, korisnik.getEmail());
            preparedStatement.setString(2, korisnik.getIme());
            preparedStatement.setString(3, korisnik.getPrezime());
            preparedStatement.setString(4, korisnik.getTip());
            preparedStatement.setString(5, korisnik.getHashedPassword());
            preparedStatement.setBoolean(6, true);
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {
                korisnik.setId(resultSet.getInt(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return korisnik;
    }

    @Override
    public Korisnik findUser(String email) {
        Korisnik korisnik = null;

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("SELECT * FROM korisnici where email = ?");
            preparedStatement.setString(1, email);
            resultSet = preparedStatement.executeQuery();

            int korisnikId = 0;
            if(resultSet.next()) {
                korisnikId = resultSet.getInt("id");
                String emailKorisnika = resultSet.getString("email");
                String ime = resultSet.getString("ime");
                String prezime = resultSet.getString("prezime");
                String tip = resultSet.getString("tip");
                String password = resultSet.getString("password");
                boolean aktivan = resultSet.getBoolean("aktivan");
                korisnik = new Korisnik(korisnikId, emailKorisnika, ime, prezime, tip, password, aktivan);
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return korisnik;
    }

    @Override
    public Korisnik aktivirajDeaktiviraj(Korisnik korisnik) {
        Korisnik korisnikDb = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("SELECT * FROM korisnici where id = ?");
            preparedStatement.setInt(1, korisnik.getId());
            resultSet = preparedStatement.executeQuery();

            int korisnikId = 0;
            if(resultSet.next()) {
                korisnikId = resultSet.getInt("id");
                String emailKorisnika = resultSet.getString("email");
                String ime = resultSet.getString("ime");
                String prezime = resultSet.getString("prezime");
                String tip = resultSet.getString("tip");
                boolean aktivan = resultSet.getBoolean("aktivan");
                korisnikDb = new Korisnik(korisnikId, emailKorisnika, ime, prezime, tip, aktivan);
            }

            if(!korisnikDb.getTip().equals("Kontent kreator")){
                throw new RuntimeException("Korisnik nije Kontent kreator");
            }

            preparedStatement = connection.prepareStatement("UPDATE korisnici SET aktivan=? WHERE id=?");
            boolean newStatus;
            if(korisnikDb.isAktivan()){
                newStatus = false;
            }else{
                newStatus = true;
            }
            preparedStatement.setBoolean(1, newStatus);
            preparedStatement.setInt(2, korisnikDb.getId());
            int rowCount = preparedStatement.executeUpdate();

            if(rowCount == 1){
                boolean status;
                if(korisnikDb.isAktivan()){
                    status = false;
                }else{
                    status = true;
                }
                korisnikDb.setAktivan(status);

            }else {
                throw new RuntimeException("Greska");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeConnection(connection);
            this.closeResultSet(resultSet);
        }
        return korisnikDb;
    }

    @Override
    public Korisnik edit(Korisnik korisnik) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("UPDATE korisnici SET email=?, ime=?, prezime=?, tip=? WHERE id=?");

            preparedStatement.setString(1, korisnik.getEmail());
            preparedStatement.setString(2, korisnik.getIme());
            preparedStatement.setString(3, korisnik.getPrezime());
            preparedStatement.setString(4, korisnik.getTip());
            preparedStatement.setInt(5, korisnik.getId());

            int rowCount = preparedStatement.executeUpdate();

            if(rowCount != 1){
                throw new RuntimeException("Greska");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeConnection(connection);
        }
        return korisnik;
    }


}
