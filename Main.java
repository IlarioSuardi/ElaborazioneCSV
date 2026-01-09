import java.io.*;

public class Main {
    public static void main(String[] args) {
        Morte[] morti = new Morte[2000];
        int n = 0;
        int[] maxPerCampo = new int[6];

        try {
            BufferedReader br = new BufferedReader(new FileReader("suardi.csv"));
            String header = br.readLine();

            String riga;
            while ((riga = br.readLine()) != null && n < morti.length) {

                String[] campi = riga.split(",");

                if (campi.length >= 6) {

                    for (int i = 0; i < 6; i++) {
                        int lunghezzaAttuale = campi[i].length();
                        if (lunghezzaAttuale > maxPerCampo[i]) {
                            maxPerCampo[i] = lunghezzaAttuale;
                        }
                    }

                    morti[n] = new Morte(campi);
                    n++;
                }
            }
            br.close();

            //1.Numero campi
            System.out.println("Numero campi: " + (6 + 2));

            //2.Lunghezza massima record e campi
            int maxRecord = 0;
            for (int i = 0; i < n; i++) {
                int lunghezza = morti[i].toCSV().length();
                if (lunghezza > maxRecord) maxRecord = lunghezza;
            }
            System.out.println("Lunghezza massima record: " + maxRecord);
            System.out.print("Lunghezza massima per ogni campo: ");
            for(int m : maxPerCampo) System.out.print(m + " ");
            System.out.println();

            //3.Visualizzazione 3 campi significativi
            System.out.println("\nANNO | STATO | DECESSI");
            for (int i = 0; i < n; i++) {
                System.out.println(morti[i].year + " | " + morti[i].state + " | " + morti[i].deaths);
            }

            //4.Ricerca record per campo
            String stato = "California";
            System.out.println("\nRicerca per stato: " + stato);
            for (int i = 0; i < n; i++) {
                if (morti[i].state.equalsIgnoreCase(stato)) {
                    System.out.println(morti[i].toCSV());
                    break;
                }
            }

            //5.Aggiunta record in coda
            if (n < morti.length) {
                morti[n++] = new Morte(new String[]{"2025","001","TEST CAUSE","ITALY","999","9.9"});
            }

            //6.Modifica e Cancellazione logica
            morti[0].deaths = "12345";
            if (n > 1) morti[1].cancellato = "true";

            //7.Scrittura CSV con record a dimensione fissa
            BufferedWriter bw = new BufferedWriter(new FileWriter("output.csv"));
            String formatoriga = "%-10s %-60s %-30s %-25s %-12s %-10s %-10s %-10s";
            bw.write(String.format(formatoriga, "ANNO", "CAUSA_113", "NOME_CAUSA", "STATO", "DECESSI", "RATE", "MIO_VAL", "CANC LOGICA"));
            bw.newLine();

            for (int i = 0; i < n; i++) {
                String rigaFissa = String.format(formatoriga, morti[i].year, morti[i].cause113, morti[i].causeName, morti[i].state, morti[i].deaths, morti[i].rate, morti[i].mioValore, morti[i].cancellato);
                bw.write(rigaFissa);
                bw.newLine();
            }
            bw.close();

            System.out.println("\nOPERAZIONE COMPLETATA: File 'output.csv' generato con record a dimensione fissa.");

        } catch (Exception e) {
            System.out.println("ERRORE: " + e.getMessage());
            e.printStackTrace();
        }
    }
}