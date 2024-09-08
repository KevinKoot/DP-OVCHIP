package OV;

import java.sql.*;
import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        String dbURL = "jdbc:postgresql://localhost:1111/OVCHIP";
        String user = "postgres";
        String pass = "hallo";

        try {
            Connection myConn = DriverManager.getConnection(dbURL, user, pass);

            ReizigerDAOPsql reizigerDAO = new ReizigerDAOPsql(myConn);


            testReizigerDAO(reizigerDAO);

            myConn.close();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void testReizigerDAO(ReizigerDAO rdao) throws SQLException {

        System.out.println("\n---------- Test ReizigerDAO findAll methode -------------");

        // Haal alle reizigers op uit de database
        List<Reiziger> reizigers = rdao.findAll();
        System.out.println("[Test] ReizigerDAO.findAll() geeft de volgende reizigers:");
        for (Reiziger r : reizigers) {
            System.out.println(r);
        }
        System.out.println();

        //-----------------------------------------------------------------------------------------------------------------------------------------------------------------//

        System.out.println("\n---------- Test ReizigerDAO save methode -------------");
        // Maak een nieuwe reiziger aan en persisteer deze in de database
        String gbdatum = "1981-03-14";
        Reiziger sietske = new Reiziger(77, "S", "", "Koot", java.sql.Date.valueOf(gbdatum));
        System.out.print("[Test] Eerst " + reizigers.size() + " reizigers, na ReizigerDAO.save() ");
        rdao.save(sietske);
        reizigers = rdao.findAll();
        System.out.println(reizigers.size() + " reizigers\n");



        //-----------------------------------------------------------------------------------------------------------------------------------------------------------------//

        System.out.println("\n---------- Test ReizigerDAO Delete methode -------------");

        List<Reiziger> reizigers2 = rdao.findAll();
        System.out.println("Aantal reizigers voor het verwijderen: " + reizigers2.size());

        boolean deleted = rdao.delete(sietske);
        System.out.println("Reiziger deleted: " + deleted);

        reizigers = rdao.findAll();
        System.out.println("Aantal reizigers na verwijderen: " + reizigers.size());

        //-----------------------------------------------------------------------------------------------------------------------------------------------------------------//

        System.out.println("\n---------- Test ReizigerDAO Update methode -------------");

        String gbdatum2 = "2002-09-17";

        Reiziger reiziger1 = new Reiziger(1, "G", "van", "Koot", java.sql.Date.valueOf(gbdatum2));

        rdao.update(reiziger1);

        System.out.println("Updated achternaam van de reiziger: " + sietske.getAchterNaam());


        //-----------------------------------------------------------------------------------------------------------------------------------------------------------------//

        System.out.println("\n---------- Test ReizigerDAO findById methode -------------");
        int id = 1;

        Reiziger reiziger = rdao.findById(id);

        if (reiziger != null) {
            System.out.println("reiziger: aan de hand van de ID" + reiziger);
        } else {
            System.out.println("geen reiziger gevonden met correcte id");
        }


        //-----------------------------------------------------------------------------------------------------------------------------------------------------------------//


            System.out.println("\n---------- Test ReizigerDAO findByGb methode -------------");

            LocalDate date = LocalDate.of(1981, 3, 14);

            List<Reiziger> reizigerss = rdao.findByGbDatum(date);

            for(Reiziger r : reizigerss){

                System.out.println("reiziger gevonden aan de hand van GB datum: " + r);}
        }


            //-----------------------------------------------------------------------------------------------------------------------------------------------------------------//







//    private static void testReizigerDAObyGbDatum(ReizigerDAO rdao) throws SQLException{
//
//        LocalDate date = LocalDate.of(1981, 3, 14);
//
//        List<Reiziger> reizigers = rdao.findByGbDatum(date);
//
//        for(Reiziger r : reizigers){
//
//            System.out.println("reiziger gevonden aan de hand van GB datum: " + r);
//        }
        }




