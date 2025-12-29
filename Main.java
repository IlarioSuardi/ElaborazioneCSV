import java.io.*;

public class Main {
    public static void main(String[] args) {
        try{
            FileReader fr = new FileReader("suardi.csv");
            BufferedReader br = new BufferedReader(fr);

            morte[] morti = new morte[1000];
            int i = 0;

            String line = br.readLine();
            String line;
            while((line = br.readLine()) != null && i < morti.length){
                String[] campi = line.split(",");
                morti[i] = new morte(campi);
                i++;
            }
    }
}
