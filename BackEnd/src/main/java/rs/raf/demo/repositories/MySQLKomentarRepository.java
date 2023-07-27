package rs.raf.demo.repositories;

import rs.raf.demo.entities.Komentar;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class MySQLKomentarRepository extends MySqlAbstractRepository implements IKomentarRepository {



    @Override
    public List<Komentar> all() {
        List<Komentar> komentari = new ArrayList<>();

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from komentari");
            while (resultSet.next()) {
                komentari.add(new Komentar(resultSet.getInt("id"), resultSet.getString("tekst"), resultSet.getString("autor")));
//                ubaciti sva polja
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(statement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return komentari;
    }

    @Override
    public Komentar findById(int id) {
        return null;
    }

    @Override
    public synchronized Komentar insert(Komentar komentar) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            String[] generatedColumns = {"id"};

            preparedStatement = connection.prepareStatement("INSERT INTO komentari (tekst) VALUES(?)", generatedColumns);
            preparedStatement.setString(1, komentar.getTekst());
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {
                komentar.setId(resultSet.getInt(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return komentar;
    }



	
}
