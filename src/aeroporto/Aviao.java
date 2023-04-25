package aeroporto;

public class Aviao {
    private Aviao proximo;
    private int id;
    private int combustivel;
    private int tempoEspera = 0;

    public Aviao(int id, int combustivel, Aviao proximo) {
        this.id = id;
        this.proximo = proximo;
        this.combustivel = combustivel;
    }

    public Aviao getproximo() {
        return proximo;
    }

    public int tempoRestante() {
        return (combustivel);
    }

    public void setproximo(Aviao proximo) {
        this.proximo = proximo;
    }

    public int getCombustivel() {
        return combustivel;
    }

    public void setCombustivel(int combustivel) {
        this.combustivel = combustivel;
    }

    public int gettempoEspera() {
        return tempoEspera;
    }

    public void settempoEspera(int tempoEspera) {
        this.tempoEspera = tempoEspera;
    }

    public int getid() {
        return id;
    }

    public void setid(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        String idString = "ID: " + this.getid();
        String combustivelString = "Combustivel: " + this.getCombustivel();
        String tempoString = "Tempo com Combustivel: " + this.tempoRestante();

        int idWidth = Math.max(idString.length(), 5);
        int combustivelWidth = Math.max(combustivelString.length(), 12);
        int tempoWidth = Math.max(tempoString.length(), 25);

        idString = String.format("%-" + idWidth + "s", idString);
        combustivelString = String.format("%-" + combustivelWidth + "s", combustivelString);
        tempoString = String.format("%-" + tempoWidth + "s", tempoString);

        return  idString + "  " + combustivelString + "  " + tempoString;
    }
}