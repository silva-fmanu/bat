import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        String[][] campoJogador = new String[10][10];
        for (int i = 0; i < campoJogador.length; i++){
            for (int j = 0; j< campoJogador[i].length; j++){
                campoJogador[i][j] = "♒";
            }
        }
        Scanner ler=new Scanner(System.in);

        boolean multiplayer = false;

        for(int i = 0; i == 0;) {
            System.out.print("m -> multiplayer\ns -> singleplayer(contra máquina)\n\nDigite a sua opção: ");
            char opMultiplayer = ler.next().toLowerCase().charAt(0);

            if (opMultiplayer == 'm') {
                multiplayer = true;
                i++;
            } else if (opMultiplayer == 's') {
                multiplayer = false;
                i++;
            } else {
                System.out.println("Opção inválida, tente novamente.\n");
            }
        }

        boolean automatico = false;

        for(int i = 0; i == 0;) {
            System.out.print("\na -> automatico\nm -> manual\n\nDigite a sua opção: ");
            char opMultiplayer = ler.next().toLowerCase().charAt(0);

            if (opMultiplayer == 'a') {
                automatico = true;
                i++;
            } else if (opMultiplayer == 'm') {
                automatico = false;
                i++;
            } else {
                System.out.println("Opção inválida, tente novamente.\n");
            }
        }

        boolean dificil = false;

        for(int i = 0; i == 0;) {
            System.out.print("\nf -> facil\nd -> dificil\n\nDigite a sua opção: ");
            char opMultiplayer = ler.next().toLowerCase().charAt(0);

            if (opMultiplayer == 'd') {
                dificil = true;
                i++;
            } else if (opMultiplayer == 'f') {
                dificil = false;
                i++;
            } else {
                System.out.println("Opção inválida, tente novamente.\n");
            }
        }

        System.out.println("\nMultiplayer = "+multiplayer+"\nAutomático = "+automatico+"\nDifícil = "+dificil);

        System.out.println();
        mostraTabuleiro(campoJogador);

        System.out.print("Linha:");
        int linha=ler.nextInt();
        System.out.print("Coluna:");
        char coluna= ler.next().toLowerCase().charAt(0);
        int numColuna = pegarNumero(coluna);


        System.out.println(linha);
        System.out.println(coluna);
        campoJogador[linha][numColuna] = "💣";
        System.out.println();
        mostraTabuleiro(campoJogador);
    }

    static void mostraTabuleiro(String[][] campo){
        System.out.println("|| A| B| C| D | E| F| G | H| I| J|");
        for (int i = 0; i < campo.length; i++) {
            System.out.print(i+"|");
            for (int j = 0; j < campo[i].length; j++) {
                System.out.print(campo[i][j]+"|");
            }
            System.out.println();
        }
    }

    static int pegarNumero(char op){
        int num = 0;
        switch(op){
            case 'b' -> num=1;
            case 'c' -> num=2;
            case 'd' -> num=3;
            case 'e' -> num=4;
            case 'f' -> num=5;
            case 'g' -> num=6;
            case 'h' -> num=7;
            case 'i' -> num=8;
            case 'j' -> num=9;
        }

        return num;
    }
}