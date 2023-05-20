package gyakorlofeladat;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class GyakorloFeladat {

    private List<String> sorok;
    private ArrayList<GyakorloProgram> emberek;

    public static void main(String[] args) throws IOException, ParseException {
        new GyakorloFeladat().feladatok();
    }

    public GyakorloFeladat() throws IOException, ParseException {
        sorok = Files.readAllLines(Path.of("emberek.txt"));
        emberek = new ArrayList<>();
        for (int i = 1; i < sorok.size(); i++) {
            emberek.add(new GyakorloProgram(sorok.get(i)));
        }
    }

    private void feladatok() throws IOException, ParseException {
        kiKeressLegtobbet();
        atlagFizetes();
        mindenkiBudapesti();
        huszEvFelettiPesti();
        milyenCimekVannak();
        nemPestiKiiras();
    }

    private void kiKeressLegtobbet() {
        int maxIndex = 0;
        int maxFizetes = 0;

        for (int i = 0; i < emberek.size(); i++) {
            if (emberek.get(i).getFizetes() > maxFizetes) {
                maxFizetes = emberek.get(i).getFizetes();
                maxIndex = i;
            }
        }
        System.out.println("Ez az ember keress a legtöbbet: " + emberek.get(maxIndex).getNev()+ "\n");
    }

    private void atlagFizetes() {
        int osszes = 0;
        for (int i = 0; i < emberek.size(); i++) {
            osszes += emberek.get(i).getFizetes();

        }
        System.out.println("Ennyi az átlag fizetés: " + osszes / emberek.size() + "Ft\n");
    }

    private void mindenkiBudapesti() {
        int i = 0, N = emberek.size();
        while (i < N && pestiEmberek(emberek.get(i))) {
            i++;
        }

        System.out.println("Mindenki budapesti?");
        System.out.println(i >= N ? "Igen\n" : "Nem\n");
    }

    private boolean pestiEmberek(GyakorloProgram ember) {
        boolean pesti = ember.getCím().equals("Budapest");
        return pesti;
    }

    private void huszEvFelettiPesti() {
        int i = 0, N = emberek.size();
        while (i < N && pestiEmber20Felett(emberek.get(i))) {
            i++;
        }
        System.out.println("Van 20 év feletti budapesti?");
        System.out.println(i < N ? "Igen\n" : "Nem\n");
    }

    private boolean pestiEmber20Felett(GyakorloProgram ember) {
        boolean pesti = ember.getCím().equals("Budapest");
        boolean huszFelett = ember.getKor() > 20;
        return pesti && huszFelett;
    }

    private void milyenCimekVannak() throws IOException {
        List<GyakorloProgram> Cimek = new ArrayList<>();
        for (GyakorloProgram cimek : emberek) {
            Cimek.add(cimek);
        }

        String kimenet = "";
        for (GyakorloProgram cimek : emberek) {
            kimenet += cimek + "\n";
        }
        System.out.println("Ezek a címek vannak:\n" + kimenet);
        Files.writeString(Path.of("Cimek.txt"), kimenet);
        System.out.println("\"Cimek.txt\" kiírva!\n");

        HashSet<String> kulonbozoCimek = new HashSet<>();
        for (GyakorloProgram cimek : emberek) {
            kulonbozoCimek.add(cimek.getCím());
        }
        for (String kulonbozoCim : kulonbozoCimek) {
            System.out.println(kulonbozoCim);
        }

        HashMap<String, Integer> cimDb = new HashMap<>();
        for (GyakorloProgram cimek : emberek) {
            String kulcs = cimek.getCím();
            if (cimDb.containsKey(kulcs)) {
                int ertek = cimDb.get(kulcs);
                cimDb.put(kulcs, ++ertek);
            } else {
                cimDb.put(kulcs, 1);
            }
        }
        System.out.println("\n");
        for (Map.Entry<String, Integer> entry : cimDb.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            System.out.printf("%-11s %4d db\n", key + ":", value);

        }

    }

    private void nemPestiKiiras() throws IOException {
        String fejlec = "NÉV;KOR;CÍM;FIZETÉS";
        List<String> nemPestiek = new ArrayList<>();
        nemPestiek.add(fejlec);
        for (GyakorloProgram cimek : emberek) {
            if (!cimek.getCím().equals("Budapest")) {
                String nev = cimek.getNev();
                String kor = Integer.toString(cimek.getKor());
                String cim = cimek.getCím();
                String fizetes = Integer.toString(cimek.getFizetes());
                
              nemPestiek.add(nev + ";" + kor + ";" + cim + ";" + fizetes);  
            }
            
            
        }

        String kimenet = "";
        for (String cimek : nemPestiek) {
            kimenet += cimek + "\n";
        }
        System.out.println("Ezek az emberek a nem pestiek:\n\n" + kimenet);
        Files.writeString(Path.of("nemBP.txt"), kimenet);
        System.out.println("\"nemPB.txt\" kiírva!\n");
    }

}
