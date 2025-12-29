import java.io.*;

public class Main {
    public static void main(String[] args) {

        try {
            FileReader fr = new FileReader("suardi.csv");
            BufferedReader br = new BufferedReader(fr);

            Morte[] morti = new Morte[1000];
            int i = 0;

            String line = br.readLine(); // intestazione

            while ((line = br.readLine()) != null && i < morti.length) {
                String[] campi = line.split(",");
                morti[i] = new Morte(campi);
                i++;
            }

            br.close();
            fr.close();

            if (i > 0) {
                System.out.println("Numero di campi: " + morti[0].toCSV().split(",").length);
            }

            int max = 0;
            for (int j=0; j < i; j++) {
                if (morti[j].toCSV().length() > max) {
                    max = morti[j].toCSV().length();
                }
            }
            System.out.println("Lunghezza massima record: " + max);

            System.out.println("\nANNO  STATO  DECESSI");
            for (int j = 0; j < 5 && j < i; j++) {
                System.out.println(morti[j].year + " " + morti[j].state + " " + morti[j].deaths
                );
            }

            morti[i]=new Morte(new String[]{"2020","001","Cause Name","State","1000","10.0"});
            i++;

            for (int j=0; j < i; j++) {
                if(morti[j].year.equals("2010") && morti[j].state.equals("California")) {
                    System.out.println("Record trovato: " + morti[j].toCSV());
                }
            }

            for (int j = 0; j < i; j++) {
                if (morti[j].year.equals("2025") &&
                        morti[j].state.equals("IT")) {
                    morti[j].deaths = "999";
                }
            }

            for (int j = 0; j < i; j++) {
                if (morti[j].year.equals("2025") &&
                        morti[j].state.equals("IT")) {
                    morti[j].cancellato = "true";
                }
            }
        }
        catch (IOException e) {}
            System.out.println("Errore nella lettura del file: " + e);
    }
}
