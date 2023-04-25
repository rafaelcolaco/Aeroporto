package aeroporto;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Random gerador_random = new Random();
        Scanner scanner = new Scanner(System.in);
        boolean rodando = true;
        Fila filaPousar1 = new Fila(10);
        filaPousar1.setTipo("pouso");
        Fila filaDecolagem1 = new Fila(10);
        filaDecolagem1.setTipo("decola");
        Fila filaPousar2 = new Fila(10);
        filaPousar2.setTipo("pouso");
        Fila filaDecolagem2 = new Fila(10);
        filaDecolagem2.setTipo("decola"); 
        double[] tabela1 = {0f, 0f, 0f};
        double[] tabela2 = {0f, 0f, 0f};

        while (rodando) {

            int numeroAvioesPousar = gerador_random.nextInt(0, 3);
            int numeroAvioesDecolar = gerador_random.nextInt(0, 3);
            adicionarAvioes(filaPousar1, filaPousar2, numeroAvioesPousar, gerador_random);
            adicionarAvioes(filaDecolagem1, filaDecolagem2, numeroAvioesDecolar, gerador_random);

            mostrar(filaDecolagem1,filaPousar1,tabela1,1);
            mostrar(filaDecolagem2,filaPousar2,tabela2,2);

            System.out.print("digite qualquer coisa para continuar ou sair para finalizar:");
            String input_lido = scanner.next();

            if (input_lido.equals("sair")) {
                rodando = false;
            }

            System.out.println("\n");

            System.out.println("Removido da pista:");
            if (filaPousar1.gettotalAviaoPrioridade() >= 1) {
                boolean pousoSaiuPrioridade1 = filaPousar1.remover();
                if (pousoSaiuPrioridade1) {
                    tabela1[2] += 1; 
                }
            } else if (filaPousar1.is_empty()) {
                filaDecolagem1.remover();
            } else if (filaDecolagem1.is_empty()) {
                filaPousar1.remover();
            } else {
                if (filaPousar1.getTamanho() < filaDecolagem1.getTamanho()) {
                    filaDecolagem1.remover();
                } else {
                    filaPousar1.remover();
                }
            }

            System.out.println("Removido da pista:");
            if (filaPousar2.gettotalAviaoPrioridade() >= 1) {
                boolean pousoSaiuPrioridade2 = filaPousar2.remover();
                if (pousoSaiuPrioridade2) {
                    tabela2[2] += 1;
                }
            } else if (filaPousar2.is_empty()) {
                filaDecolagem2.remover();
            } else if (filaDecolagem2.is_empty()) {
                filaPousar2.remover();
            } else {
                if (filaPousar2.getTamanho() < filaDecolagem2.getTamanho()) {
                    filaDecolagem2.remover();
                } else {
                    filaPousar2.remover();
                }
            }

            filaPousar1.passarTempo();
            filaPousar2.passarTempo();
            filaDecolagem1.passarTempo();
            filaDecolagem2.passarTempo();
            System.out.println("\n");
        }
    }

    public static void mostrar(Fila fila_decolagem, Fila fila_aterrisagem, double[] tabela, int pista){
        System.out.println("Fila para pouso "+pista+" \n");
        System.out.println(fila_aterrisagem.show_fila());
        System.out.println("Fila para decolar "+pista+" \n");
        System.out.println(fila_decolagem.show_fila());
        tabela[0] = fila_decolagem.tempoMedioEspera();
        tabela[1] = fila_aterrisagem.tempoMedioEspera();
        System.out.println("\n" + "Tempo médio de espera para pouso" + "          O tempo médio de espera para pousar" + "          Numero de avioes que aterrissam em reserva");
        System.out.format("%-42.3f%-45.3f%-45.1f\n", tabela[0], tabela[1], tabela[2]);
        System.out.println("\n");
    }

    public static void adicionarAvioes(Fila fila1, Fila fila2, int qtd, Random gerador_random) {
        for (int i = 0; i <= qtd; i++) {
            if (fila1.getTamanho() == fila2.getTamanho()) {
                int randFila = gerador_random.nextInt(0,2);
                if (randFila == 0) {
                    fila1.inserir();
                } else if (randFila == 1) {
                    fila2.inserir();
                }
            } else if (fila1.getTamanho() < fila2.getTamanho()) {
                fila1.inserir();
            } else {
                fila2.inserir();
            }
        }
    }


   
}