package aeroporto;
import java.util.Random;

public class Fila {
	private Aviao primeiro;
    private String tipo;
    private int ultimoId;
    private int tamanho;
    private final int limite;
    private int tempoEspera;
    private int totalAvioes;
    private int totalAviaoPrioridade;
    
    public Fila(int limite) {
    	 this.primeiro = null;
         this.tipo = "";
         this.ultimoId = 0;
         this.tamanho = 0;
         this.limite = limite;
         this.tempoEspera = 0;
         this.totalAvioes = 0;
         this.totalAviaoPrioridade = 0;
    }
    public void inserir() {
    	Random geradorRandom = new Random();
        if (has_space()) {
         totalAvioes++;
         ultimoId++;
            int novoId = ultimoId;
            Aviao novoAviao = new Aviao(novoId, geradorRandom.nextInt(1, 21), null);
            if (tipo.equals("decola")) {
                novoAviao.setCombustivel(geradorRandom.nextInt(1, 21));
            } else {
                if (novoAviao.getCombustivel() <= 2) {
                    if (totalAviaoPrioridade >= 2) {
                    novoAviao.setCombustivel(geradorRandom.nextInt(1, 21));
                    } else {
                        if (novoAviao.getCombustivel() <= 1) {
                        totalAviaoPrioridade++;
                        }
                    }
                }
            }
            if (primeiro == null) {
                primeiro = novoAviao;
            } else {
                Aviao current = primeiro;
                while (current.getproximo() != null) {
                    current = current.getproximo();
                }
                current.setproximo(novoAviao);
            }
            tamanho++;
        }
    }

    public Aviao getprimeiro() {
        return primeiro;
    }

    public String checar() {
        Aviao current = this.primeiro;
        if (current.getCombustivel() <= 1) {
            tempoEspera += primeiro.gettempoEspera();
            String removido = "Saiu: "+primeiro;
            primeiro = primeiro.getproximo();
            this.tamanho--;
            this.totalAviaoPrioridade--;
            return removido;
        }
        while (current != null) {
        	if (current.getproximo() != null) {
                if (current.getproximo().getCombustivel() <= 1) {
                    tempoEspera += current.getproximo().gettempoEspera();
                    String removido = "Saiu: " + current.getproximo();
                    current.setproximo(current.getproximo().getproximo());
                    tamanho--;
                    totalAviaoPrioridade--;
                    return removido;
                }
            }
            current = current.getproximo();
        }
        return "";
    }

    public boolean remover() {
        if (is_empty()){
            return false;
        }
        String cheque;
        if (tipo.equals("pouso")) {
            cheque = checar();
            System.out.println(cheque);
        } else {
            cheque = "";
        }
        if (cheque.equals("")) {
            tempoEspera += primeiro.gettempoEspera();
            System.out.println("Saiu: "+primeiro);
            primeiro = primeiro.getproximo();
            this.tamanho--;
            return false;
        }
        return true;
    }

    public boolean has_space() {
        return limite > tamanho;
    }

    public String show_fila() {
        if (is_empty()){
            return "";
        }
        Aviao current = this.primeiro;
        String texto = "";
        while (current != null) {
            texto += current.toString() + "\n";
            current = current.getproximo();
        }
        return texto;
    }

    public void passarTempo() {
        if (is_empty()){
            return;
        }
        Aviao current = this.primeiro;
        while (current != null) {
            if (this.tipo.equals("pouso")) {
                current.setCombustivel(current.getCombustivel() - 1);
                if (current.getCombustivel() <= 2) {
                    this.totalAviaoPrioridade++;
                }
            }
            current.settempoEspera(current.gettempoEspera() + 1);
            current = current.getproximo();
        }
    }

    public int tempoMedioEspera() {
        if (totalAvioes > 0) {
            return tempoEspera / totalAvioes;
        } else {
            return 0;
        }
    }

    public boolean is_empty(){
        return this.primeiro == null;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getTamanho() {
        return tamanho;
    }

    public int gettotalAviaoPrioridade() {
        return totalAviaoPrioridade;
    }
}
