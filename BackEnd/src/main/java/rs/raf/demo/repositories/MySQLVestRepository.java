package rs.raf.demo.repositories;

import rs.raf.demo.entities.Kategorija;
import rs.raf.demo.entities.Komentar;
import rs.raf.demo.entities.Tag;
import rs.raf.demo.entities.Vest;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class MySQLVestRepository extends MySqlAbstractRepository implements IVestRepository {



    @Override
    public List<Vest> all() {
        List<Vest> vesti = new ArrayList<>();

        PreparedStatement preparedStatement = null;
        Connection connection = null;
        ResultSet resultSetVesti = null;
        ResultSet resultSetTagovi = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("select * from vesti order by id desc");
            resultSetVesti = preparedStatement.executeQuery();
            while (resultSetVesti.next()) {
                List<Tag> tagovi = new ArrayList<>();
                int vestId = resultSetVesti.getInt("id");
                String naslov = resultSetVesti.getString("naslov");
                String tekst = resultSetVesti.getString("tekst");
                LocalDate vremeKreiranja = resultSetVesti.getObject ( "vreme_kreiranja", LocalDate.class );
                int brojPoseta = resultSetVesti.getInt("broj_poseta");
                String autor = resultSetVesti.getString("autor");

                preparedStatement = connection.prepareStatement("SELECT t.* FROM vesti v inner join vesti_tagovi vt on v.id = vt.fk_vesti inner join tagovi t on t.id = vt.fk_tagovi where v.id = ?");
                preparedStatement.setInt(1, vestId);
                resultSetTagovi = preparedStatement.executeQuery();
                while(resultSetTagovi.next()){
                    int tagId = resultSetTagovi.getInt("t.id");
                    String tag = resultSetTagovi.getString("t.tag");

                    Tag t = new Tag(tagId,tag);

                    tagovi.add(t);
                }

                vesti.add(new Vest(vestId, naslov, tekst, vremeKreiranja, brojPoseta, autor,
                        null,
                        null,
                        tagovi));
            }

            resultSetVesti.close();
            resultSetTagovi.close();
            preparedStatement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeResultSet(resultSetVesti);
            this.closeResultSet(resultSetTagovi);
            this.closeConnection(connection);
            this.closeStatement(preparedStatement);
        }

        return vesti;
    }

    @Override
    public synchronized Vest findById(int id) {
        Vest vest = null;

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("SELECT v.*,k.* FROM vesti v inner join kategorije k on k.id = v.fk_kategorija " +
                    " where v.id = ?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            int vestId = 0;
            if(resultSet.next()) {
                vestId = resultSet.getInt("v.id");
                String naslov = resultSet.getString("v.naslov");
                String tekst = resultSet.getString("v.tekst");
                Date date = resultSet.getDate("v.vreme_kreiranja");
                LocalDate vremeKreiranja = date.toLocalDate();
                int brojPoseta = resultSet.getInt("v.broj_poseta");
                String autor = resultSet.getString("autor");

                int kategorijaId = resultSet.getInt("k.id");
                String ime = resultSet.getString("k.ime");
                String opis = resultSet.getString("k.opis");

                Kategorija kategorija = new Kategorija(kategorijaId, ime, opis);

                vest = new Vest(vestId, naslov, tekst, brojPoseta, vremeKreiranja, kategorija, autor);
            }
            List<Tag> tagovi = new ArrayList<>();
            preparedStatement = connection.prepareStatement("SELECT t.* FROM vesti v inner join vesti_tagovi vt on v.id = vt.fk_vesti inner join tagovi t on t.id = vt.fk_tagovi where v.id = ?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                int tagId = resultSet.getInt("t.id");
                String tag = resultSet.getString("t.tag");

                Tag t = new Tag(tagId,tag);

                tagovi.add(t);
            }

            if(vest != null && tagovi != null) {
                vest.setTagovi(tagovi);
            }

            preparedStatement = connection.prepareStatement("SELECT * FROM komentari where fk_vest = ? order by id desc");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            List<Komentar> comments = new ArrayList<>();

            while(resultSet.next()) {
                int commentId = resultSet.getInt("id");
                String tekst = resultSet.getString("tekst");
                String autor = resultSet.getString("autor");
                LocalDate datumKreiranja = resultSet.getObject("datum_kreiranja", LocalDate.class);
                Komentar comment = new Komentar(commentId, autor, tekst, datumKreiranja);
                comments.add(comment);
            }
            vest.setKomentari(comments);

            preparedStatement = connection.prepareStatement("UPDATE vesti SET broj_poseta=? WHERE id=?");
            preparedStatement.setInt(1, (vest.getBrojPoseta()+1));
            preparedStatement.setInt(2, id);
            int rowCount = preparedStatement.executeUpdate();

            if(rowCount != 1){
                throw new RuntimeException("Greska");
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

        return vest;
    }


    @Override
    public synchronized Vest insert(Vest vest) {
//        public synchronized Vest insert(Vest vest, int kategorijaId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            String[] generatedColumns = {"id"};

//            for(Tag tag : vest.getTagovi()){
//                try {
//                    preparedStatement = connection.prepareStatement("INSERT INTO tagovi (tag) VALUES(?)", generatedColumns);
//                    preparedStatement.setString(1, tag.getTag());
//                    preparedStatement.executeUpdate();
//                    resultSet = preparedStatement.getGeneratedKeys();
//
//                    if (resultSet.next()) {
//                        tag.setId(resultSet.getInt(1));
//                    }
//                }catch (SQLIntegrityConstraintViolationException e){
//
//                }
//            }

//            preparedStatement = connection.prepareStatement("INSERT INTO vesti (naslov, tekst, broj_poseta) VALUES(?, ?, ?)", generatedColumns);
            preparedStatement = connection.prepareStatement("INSERT INTO vesti (naslov, tekst, broj_poseta, fk_kategorija, vreme_kreiranja, autor) VALUES(?, ?, ?, ?, ?, ?)", generatedColumns);
            preparedStatement.setString(1, vest.getNaslov());
            preparedStatement.setString(2, vest.getTekst());
            preparedStatement.setInt(3, vest.getBrojPoseta());
            preparedStatement.setInt(4, vest.getKategorija().getId());
            preparedStatement.setObject(5, LocalDate.now());
            preparedStatement.setString(6, vest.getAutor());
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {
                vest.setId(resultSet.getInt(1));
            }

            for(Tag tag : vest.getTagovi()){
                try {
                    int tagId = ifTagExist(connection,tag.getTag());
                    if(tagId != 0) {
                        preparedStatement = connection.prepareStatement("INSERT INTO vesti_tagovi (fk_vesti, fk_tagovi) VALUES(?, ?)", Statement.RETURN_GENERATED_KEYS);
                        preparedStatement.setInt(1, vest.getId());
                        preparedStatement.setInt(2, tagId);
                        preparedStatement.executeUpdate();
                        resultSet = preparedStatement.getGeneratedKeys();
                    }else{
                        preparedStatement = connection.prepareStatement("INSERT INTO tagovi (tag) VALUES(?)", generatedColumns);
                        preparedStatement.setString(1, tag.getTag());
                        preparedStatement.executeUpdate();
                        resultSet = preparedStatement.getGeneratedKeys();

                        if (resultSet.next()) {
                            tag.setId(resultSet.getInt(1));
                        }

                        preparedStatement = connection.prepareStatement("INSERT INTO vesti_tagovi (fk_vesti, fk_tagovi) VALUES(?, ?)", Statement.RETURN_GENERATED_KEYS);
                        preparedStatement.setInt(1, vest.getId());
                        preparedStatement.setInt(2, tag.getId());
                        preparedStatement.executeUpdate();
                        resultSet = preparedStatement.getGeneratedKeys();

                    }
                }catch (SQLIntegrityConstraintViolationException e){

                }

//                int autoIncKeyFromApi = -1;
//
//                if (resultSet.next()) {
//                    autoIncKeyFromApi = resultSet.getInt(1);
//                }
            }

//            preparedStatement = connection.prepareStatement("select * from vesti join kategorije on kategorije.id = vesti.fk_kategorija where vesti.id = ?");
//            preparedStatement.setInt(1, vest.getId());
//            resultSet = preparedStatement.executeQuery();
//
//            Vest vestDb = null;
//            while(resultSet.next()) {
//                int vestId = resultSet.getInt("id");
//                String naslov = resultSet.getString("naslov");
//                String tekst = resultSet.getString("tekst");
//                vestDb = new Vest(vestId, naslov, tekst);
//            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return vest;
    }

    @Override
    public Vest addKomentar(Komentar komentar, int id) {
        Vest vest = null;

        String[] generatedColumns = {"id"};

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("INSERT INTO komentari (tekst, datum_kreiranja, fk_vest, autor) VALUES(?, ?, ?, ?)", generatedColumns);
            preparedStatement.setString(1, komentar.getTekst());
            preparedStatement.setObject(2, LocalDate.now());
            preparedStatement.setInt(3, id);
            preparedStatement.setString(4, komentar.getAutor());
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();


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

        return vest;
    }

    @Override
    public void deleteById(int id) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("DELETE FROM komentari where fk_vest = ?");
            preparedStatement.setInt(1, id);
            int rowKomentar = preparedStatement.executeUpdate();

            preparedStatement = connection.prepareStatement("DELETE FROM vesti where id = ?");
            preparedStatement.setInt(1, id);
            int rowVest = preparedStatement.executeUpdate();

            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeConnection(connection);
        }

    }

    @Override
    public List<Vest> najcitanijeVesti() {
        List<Vest> vesti = new ArrayList<>();

        PreparedStatement preparedStatement = null;
        Connection connection = null;
        ResultSet resultSetVesti = null;
        ResultSet resultSetTagovi = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("SELECT * FROM vesti WHERE vreme_kreiranja >= CURDATE() - INTERVAL 30 DAY ORDER BY broj_poseta DESC LIMIT 10");
            resultSetVesti = preparedStatement.executeQuery();
            while (resultSetVesti.next()) {
                List<Tag> tagovi = new ArrayList<>();
                int vestId = resultSetVesti.getInt("id");
                String naslov = resultSetVesti.getString("naslov");
                String tekst = resultSetVesti.getString("tekst");
                LocalDate vremeKreiranja = resultSetVesti.getObject ( "vreme_kreiranja", LocalDate.class );
                int brojPoseta = resultSetVesti.getInt("broj_poseta");
                String autor = resultSetVesti.getString("autor");

                preparedStatement = connection.prepareStatement("SELECT t.* FROM vesti v inner join vesti_tagovi vt on v.id = vt.fk_vesti inner join tagovi t on t.id = vt.fk_tagovi where v.id = ?");
                preparedStatement.setInt(1, vestId);
                resultSetTagovi = preparedStatement.executeQuery();
                while(resultSetTagovi.next()){
                    int tagId = resultSetTagovi.getInt("t.id");
                    String tag = resultSetTagovi.getString("t.tag");

                    Tag t = new Tag(tagId,tag);

                    tagovi.add(t);
                }

                vesti.add(new Vest(vestId,
                        naslov,
                        tekst,
                        vremeKreiranja,
                        brojPoseta,
                        autor,
                        null,
                        null,
                        tagovi));
//                ubaciti sva polja
            }

            resultSetVesti.close();
            resultSetTagovi.close();
            preparedStatement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeResultSet(resultSetVesti);
            this.closeResultSet(resultSetTagovi);
            this.closeConnection(connection);
            this.closeStatement(preparedStatement);
        }

        return vesti;
    }

    private int ifTagExist(Connection connection, String tag) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * "
                + "FROM tagovi "
                + "WHERE tag = ?;");
        preparedStatement.setString(1, tag);

        ResultSet resultSet = preparedStatement.executeQuery();

        if(resultSet.next() == false){
            return 0;
        }else{
//            resultSet.next();
        }
        return resultSet.getInt("id");
    }


}
