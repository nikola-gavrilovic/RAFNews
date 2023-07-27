package rs.raf.demo.repositories;



import rs.raf.demo.entities.Kategorija;
import rs.raf.demo.entities.Komentar;
import rs.raf.demo.entities.Tag;
import rs.raf.demo.entities.Vest;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class MySQLTagRepository extends MySqlAbstractRepository implements ITagRepository {



    @Override
    public List<Tag> all() {
        List<Tag> tagovi = new ArrayList<>();

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from tagovi");
            while (resultSet.next()) {
                tagovi.add(new Tag(resultSet.getInt("id"),
                        resultSet.getString("tag")));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(statement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return tagovi;
    }

    @Override
    public List<Vest> findVestiByTagId(int id) {
        List<Vest> vesti = new ArrayList<>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ResultSet resultSetKategorije = null;
        try{
            connection = this.newConnection();
            preparedStatement = connection.prepareStatement("SELECT v.* FROM vesti_tagovi vt inner join vesti v on v.id = vt.fk_vesti where vt.fk_tagovi = ?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                int vestId = resultSet.getInt("id");
                String naslov = resultSet.getString("naslov");
                String tekst = resultSet.getString("tekst");
                LocalDate vremeKreiranja = resultSet.getObject("vreme_kreiranja", LocalDate.class);
                int brojPoseta = resultSet.getInt("broj_poseta");
                String autor = resultSet.getString("autor");

                preparedStatement = connection.prepareStatement("SELECT k.* FROM vesti v inner join kategorije k on k.id = v.fk_kategorija where v.id = ?");
                preparedStatement.setInt(1, vestId);
                resultSetKategorije = preparedStatement.executeQuery();

                Kategorija kategorija = null;
                if(resultSetKategorije.next()) {
                    int kategorijaId = resultSetKategorije.getInt("id");
                    String ime = resultSetKategorije.getString("ime");
                    String opis = resultSetKategorije.getString("opis");

                    kategorija = new Kategorija(kategorijaId, ime, opis);
                }

                Vest vest = new Vest(vestId, naslov, tekst, brojPoseta, vremeKreiranja, kategorija, autor);

                vesti.add(vest);
            }

            resultSetKategorije.close();
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
            this.closeResultSet(resultSetKategorije);
        }

        return vesti;
    }

    @Override
    public synchronized Tag insert(Tag tag) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            String[] generatedColumns = {"id"};

            preparedStatement = connection.prepareStatement("INSERT INTO tagovi (ime) VALUES(?)", generatedColumns);
            preparedStatement.setString(1, tag.getTag());
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {
                tag.setId(resultSet.getInt(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return tag;
    }



	
}
