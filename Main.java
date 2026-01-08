import java.io.*;

public class Main {

    public static String[] parseCSV(String line) {
        String[] campi = new String[50];
        int index = 0;
        boolean virgolette = false;
        String campo = "";

        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);

            if (c == '"') {
                virgolette = !virgolette;
            } else if (c == ',' && !virgolette) {
                campi[index++] = campo;
                campo = "";
            } else {
                campo += c;
            }
        }
        campi[index++] = campo;

        String[] risultato = new String[index];
        for (int i = 0; i < index; i++) {
            risultato[i] = campi[i].replace("\"", "").trim();
        }
        return risultato;
    }

    public static void main(String[] args) {

        Morte[] morti = new Morte[1000];
        int n = 0;

        try {
            BufferedReader br = new BufferedReader(new FileReader("suardi.csv"));
            br.readLine();

            String line;
            while ((line = br.readLine()) != null && n < morti.length) {
                String[] campi = parseCSV(line);
                if (campi.length >= 6) {
                    morti[n++] = new Morte(campi);
                }
            }
            br.close();

            //numero campi
            System.out.println("Numero campi: " + morti[0].toCSV().split(",").length);

            //lunghezza massima
            int max = 0;
            for (int i = 0; i < n; i++) {
                if (morti[i].toCSV().length() > max) {
                    max = morti[i].toCSV().length();
                }
            }
            System.out.println("Lunghezza massima record: " + max);

            //visualizza 3 campi
            System.out.println("\nANNO | STATO | DECESSI");
            for (int i = 0; i < 5; i++) {
                System.out.println(morti[i].year + " | " +
                        morti[i].state + " | " +
                        morti[i].deaths);
            }

            //aggiunta record
            morti[n++] = new Morte(new String[]{
                    "2025","001","TEST","IT","999","9.9"
            });

            //ricerca
            for (int i = 0; i < n; i++) {
                if (morti[i].year.equals("2010")) {
                    System.out.println("TROVATO: " + morti[i].toCSV());
                }
            }

            //modifica
            morti[0].deaths = "123";

            //cancellazione logica
            morti[1].cancellato = "true";

            //scrittura output
            BufferedWriter bw = new BufferedWriter(new FileWriter("output.csv"));
            bw.write("Year,Cause113,CauseName,State,Deaths,Rate,MioValore,Cancellato");
            for (int i = 0; i < n; i++) {
                bw.write(morti[i].toCSV());
                bw.newLine();
            }
            bw.close();

            System.out.println("\nFINE");

        } catch (Exception e) {
            System.out.println("ERRORE: " + e.getMessage());
        }
    }
}
