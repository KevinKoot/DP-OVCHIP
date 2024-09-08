package OV;


import java.sql.Date;

public class Reiziger {

    private int id;
    private String voorLetters;
    private String tussenVoegsel;
    private String achterNaam;
    private Date geboorteDatum;




    public Reiziger(){

    }


    public Reiziger(int id, String voorletters, String tussenvoegsel, String achterNaam, Date geboorteDatum) {
        this.id = id;
        this.voorLetters = voorletters;
        this.tussenVoegsel = tussenvoegsel;
        this.achterNaam = achterNaam;
        this.geboorteDatum = geboorteDatum;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVoorLetters() {
        return voorLetters;
    }

    public void setVoorLetters(String voorLetters) {
        this.voorLetters = voorLetters;
    }

    public String getTussenVoegsel() {
        return tussenVoegsel;
    }

    public void setTussenVoegsel(String tussenVoegsel) {
        this.tussenVoegsel = tussenVoegsel ;
    }

    public String getAchterNaam() {
        return achterNaam;
    }

    public void setAchterNaam(String achterNaam) {
        this.achterNaam = achterNaam;
    }

    public Date getGeboorteDatum() {
        return geboorteDatum;
    }

    public void setGeboorteDatum(Date geboorteDatum) {
        this.geboorteDatum = geboorteDatum;
    }

    public String toString(){
        if(tussenVoegsel != null){
            return "     #" + id + ": " + voorLetters + ". " + tussenVoegsel + " " + achterNaam + "  " + geboorteDatum;
        } else {
            return "     #" + id + ": " + voorLetters + ". " + achterNaam + "  (" + geboorteDatum + ")";

        }
    }
}
