import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner ler=new Scanner(System.in);
        String[][] tabuleiro=new String[10][10];
        mostraTabuleiro(tabuleiro);

        System.out.print("Linha:");
        int linha=ler.nextInt();
        System.out.print("Coluna:");
        String coluna=ler.next().toLowerCase();

        marcarNaMatriz(linha,coluna);

        System.out.println(linha);
        System.out.println(coluna);
    }

    static void mostraTabuleiro(String[][]campo){
        System.out.println("|||A| B| C| D| E| F| G| H| I| J| K|");
        for (int i = 0; i < campo.length; i++) {
            System.out.print(i+"|");
            for (int j = 0; j < campo[i].length; j++) {
                System.out.print("♒|");
            }
            System.out.println();
        }
    }
}