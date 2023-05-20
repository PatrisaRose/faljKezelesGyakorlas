package gyakorlofeladat;

import java.text.ParseException;

public class GyakorloProgram {

    private int kor, fizetes;
    private String nev, cim;

    public GyakorloProgram(String sor) throws ParseException {
        String[] s = sor.split(";");
        this.nev = s[0];
        this.kor = Integer.parseInt(s[1]);
        this.cim = s[2];
        this.fizetes = Integer.parseInt(s[3]);
    }

    public GyakorloProgram(int kor, int fizetes, String nev, String cím) {
        this.kor = kor;
        this.fizetes = fizetes;
        this.nev = nev;
        this.cim = cím;
    }

    public int getKor() {
        return kor;
    }

    public void setKor(int kor) {
        this.kor = kor;
    }

    public int getFizetes() {
        return fizetes;
    }

    public void setFizetes(int fizetes) {
        this.fizetes = fizetes;
    }

    public String getNev() {
        return nev;
    }

    public void setNev(String nev) {
        this.nev = nev;
    }

    public String getCím() {
        return cim;
    }

    public void setCím(String cím) {
        this.cim = cím;
    }

    @Override
    public String toString() {
        return "GyakorloProgram{" + "kor=" + kor + ", fizetes=" + fizetes + ", nev=" + nev + ", cim=" + cim + '}';
    }

    

}
