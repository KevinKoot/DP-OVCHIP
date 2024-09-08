package OV;

import OV.Reiziger;
import OV.ReizigerDAO;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReizigerDAOPsql implements ReizigerDAO {

    private Connection connection;




    public ReizigerDAOPsql(Connection connection) {
        this.connection = connection;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean save(Reiziger reiziger) {
        try {


            String query = "insert into reiziger (reiziger_id, voorletters, tussenvoegsel, achternaam, geboortedatum) " +
                    "values(?, ?, ?, ?, ?)";


            PreparedStatement stmnt = connection.prepareStatement(query);

            stmnt.setInt(1, reiziger.getId());
            stmnt.setString(2, reiziger.getVoorLetters());
            stmnt.setString(3, reiziger.getTussenVoegsel());
            stmnt.setString(4, reiziger.getAchterNaam());
            stmnt.setDate(5, reiziger.getGeboorteDatum());

            int rijenInserted = stmnt.executeUpdate();
            stmnt.close();

            return rijenInserted > 0;


        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Reiziger reiziger) {

        try {

            String query = "update reiziger set achternaam = ? where reiziger_id = ?";

            PreparedStatement stmt = connection.prepareStatement(query);

            stmt.setString(1, reiziger.getAchterNaam());
            stmt.setInt(2, reiziger.getId());

            int rowsGeUpdate = stmt.executeUpdate();
            stmt.close();

            return rowsGeUpdate > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public boolean delete(Reiziger reiziger) {

        try {

            String query = "delete from reiziger where reiziger_id = ?";

            PreparedStatement stmnt = connection.prepareStatement(query);
            stmnt.setInt(1, reiziger.getId());

            int rijenVerwijderd = stmnt.executeUpdate();
            stmnt.close();

            return rijenVerwijderd > 0;


        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Reiziger findById(int id) {

        try {


            String query = "Select * from reiziger where reiziger_id = ?";

            PreparedStatement stmnt = connection.prepareStatement(query);

            stmnt.setInt(1, id);

            ResultSet rs = stmnt.executeQuery();

            if (rs.next()) {
                String voorletters = rs.getString("voorletters");
                String tussenvoegsel = rs.getString("tussenvoegsel");
                String achternaam = rs.getString("achternaam");
                Date geboortedatum = rs.getDate("geboortedatum");

                return new Reiziger(id, voorletters, tussenvoegsel, achternaam, geboortedatum);

            }


            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;

        }
    }


    @Override
    public List<Reiziger> findAll() {

        List<Reiziger> reizigers = new ArrayList<>();

        try {

            String query = "select * from reiziger";
            Statement myStmt = connection.createStatement();
            ResultSet myRS = myStmt.executeQuery(query);

            while (myRS.next()) {
                int id = myRS.getInt("reiziger_id");
                String voorletters = myRS.getString("voorletters");
                String tussenvoegsel = myRS.getString("tussenvoegsel");
                String achterNaam = myRS.getString("achternaam");
                Date geboorteDatum = myRS.getDate("geboortedatum");

                Reiziger reiziger = new Reiziger(id, voorletters, tussenvoegsel, achterNaam, geboorteDatum);

                reizigers.add(reiziger);
            }

            myRS.close();
            myStmt.close();

            return reizigers;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Reiziger> findByGbDatum(LocalDate date) {
        try {

            List<Reiziger> reizigers = new ArrayList<>();

            String query = "select * from reiziger where geboortedatum = ?";

            PreparedStatement stmt = connection.prepareStatement(query);

            stmt.setDate(1, java.sql.Date.valueOf(date));

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("reiziger_id");
                String voorletters = rs.getString("voorletters");
                String tussenvoegsel = rs.getString("tussenvoegsel");
                String achternaam = rs.getString("achternaam");
                Date geboortedatum = rs.getDate("geboortedatum");

                Reiziger reiziger = new Reiziger(id, voorletters, tussenvoegsel, achternaam, geboortedatum);

                reizigers.add(reiziger);

            }

            return reizigers;

        } catch (SQLException e) {
            throw new RuntimeException();

        }


    }
}
