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
            char op = ler.next().toLowerCase().charAt(0);

            if (op == 'm') {
                multiplayer = true;
                i++;
            } else if (op == 's') {
                multiplayer = false;
                i++;
            } else {
                System.out.println("Opção inválida, tente novamente.\n");
            }
        }

        boolean automatico = false;

        for(int i = 0; i == 0;) {
            System.out.print("\nDe que modo você deseja alocar os barcos:\na -> automatico\nm -> manual\n\nDigite a sua opção: ");
            char op = ler.next().toLowerCase().charAt(0);

            if (op == 'a') {
                automatico = true;
                i++;
            } else if (op == 'm') {
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
                char op = ler.next().toLowerCase().charAt(0);

                if (op == 'd') {
                    dificil = true;
                    i++;
                } else if (op == 'f') {
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
            for (int i = 1; i != 0;){
                mostraTabuleiro(campoJogador);
                System.out.print(i+"x Barco grande (4 espaços):\nh - horizontal\nv - vertical\nOpção: ");
                char opDirecao = ler.next().toLowerCase().charAt(0);
                System.out.print("Linha: ");
                int linha=ler.nextInt();
                System.out.print("Coluna: ");
                char coluna= ler.next().toLowerCase().charAt(0);
                int numColuna = pegarNumero(coluna);
                if(checarLivre(linha, numColuna, opDirecao, campoJogador, 4)){
                    System.out.println("ta livre");
                    campoJogador = alocarBarco(linha, numColuna, opDirecao, campoJogador, 4);
                    i--;
                }else{
                    System.out.println("não ta livre, tenta de novo");
                }
            }
        }

        //System.out.println("\nMultiplayer = "+multiplayer+"\nAutomático = "+automatico+"\nDifícil = "+dificil);

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
            if(coluna+tamanho>campo[0].length){ //checa pra ver se não passa pra fora do campo
                return false;
            }

            for (int i=linha;i<linha+tamanho;i++){ //checa se o espaço que o barco vai ficar é água
                if(!campo[i][coluna].equals("♒")){
                    return false; //pq daí se for diferente quer dizer que ja esta ocupado
                }
            }
        }
        else{ //se não for  h vai ser v
            if(linha+tamanho>campo.length){ //checar pra ver se não passa pra fora do campo, mas agora na vertical
                return false;
            }

            for (int i=linha;i<linha+tamanho;i++){ //checa pra ver se é tudo água
                if(!campo[linha][i].equals("♒")){
                    return false;
                }
            }
        }

        return true; // se tiver tudo livre, retorna como verdade, ou seja, é tudo água então pode colocar o barquinho
    }

    static String[][] alocarBarco(int linha, int coluna, char direcao, String[][] campo, int tamanho){
        if (direcao == 'h'){
            for (int i = linha;i<linha+tamanho;i++){
                campo[linha][i] = "⛵";
            }
            return campo;
        }else{
            for (int i = coluna;i<coluna+tamanho;i++){
                campo[i][coluna] = "⛵";
            }
            return campo;
        }
    }

}