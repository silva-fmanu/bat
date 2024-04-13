import java.util.Random;
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
            System.out.print("Você deseja jogar:\nm -> multiplayer\ns -> singleplayer(contra máquina)\n\nDigite a sua opção: ");
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
            System.out.print("\nDe que modo você deseja alocar os barcos:\na -> automatico\nm -> manual\n\nDigite a sua opção: ");
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

        if(!multiplayer){
            for(int i = 0; i == 0;) {
                System.out.print("\nQual dificuldade de jogo você deseja:\nf -> facil\nd -> dificil\n\nDigite a sua opção: ");
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
        }

        if(automatico){
            //faz automático
        }else{
            mostraTabuleiro(campoJogador);
            System.out.print("1x Barco grande (4 espaços):\nh - horizontal\nv - vertical\nOpção: ");
            char opDirecao = ler.next().toLowerCase().charAt(0);
            System.out.print("Linha: ");
            int linha=ler.nextInt();
            System.out.print("Coluna: ");
            char coluna= ler.next().toLowerCase().charAt(0);
            int numColuna = pegarNumero(coluna);
            //checarLivre(linha, numColuna, opDirecao, campoJogador, 4);
        }

        System.out.println("\nMultiplayer = "+multiplayer+"\nAutomático = "+automatico+"\nDifícil = "+dificil);

        System.out.println();
        mostraTabuleiro(campoJogador);

        System.out.print("Linha:");
        int linha=ler.nextInt();
        System.out.print("Coluna:");
        char coluna= ler.next().toLowerCase().charAt(0);
        int numColuna = pegarNumero(coluna);

       //if(chegarLivre(...)===true){
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
    static boolean checarLivre(int linha, int coluna, char direcao, String[][] campo, int tamanho){
        if (direcao == 'h'){
            if(coluna+direcao >campo[0].length){
                return false;
            }

            for (int i=coluna;i<coluna+tamanho;i++){
                if(!campo[i].equals("♒")){
                    return false; //pq daí se for diferente quer dizer que ja esta ocupado
                }
            }
        }
        else{ //se não for  h vai ser v
            if(linha+tamanho>campo.length){
                return false;
            }

            for (int i=coluna;i<coluna+tamanho;i++){

                if(!campo[i].equals("♒")){
                    return false;
                }
            }
        }

        return true;
    }
}