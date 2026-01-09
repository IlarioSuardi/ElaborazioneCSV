import java.io.*;

class Morte {
    public String year;
    public String cause113;
    public String causeName;
    public String state;
    public String deaths;
    public String rate;
    public int mioValore;
    public String cancellato;

    public Morte(String[] campi) {
        this.year = campi[0];
        this.cause113 = campi[1];
        this.causeName = campi[2];
        this.state = campi[3];
        this.deaths = campi[4];
        this.rate = campi[5];
        this.mioValore = (int)(Math.random() * 11) + 10;
        this.cancellato = "false";
    }

    public String toCSV() {
        return year + "," + cause113 + "," + causeName + "," + state + "," + deaths + "," + rate + "," + mioValore + "," + cancellato;
    }
}