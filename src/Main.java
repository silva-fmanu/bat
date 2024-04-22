import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner ler=new Scanner(System.in);

        String[][] campoJogador= criarCampo();
        String[][] campoAdversario= criarCampo();

        String[][] campoAtacarJogador = criarCampo();
        String[][] campoAtacarAdversario = criarCampo();


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
                System.out.println("Opção inválida.");
            }
        }

        boolean automatico = false;

        for(int i = 0; i == 0;) {
            System.out.print("\nJogador, de que modo você deseja alocar os barcos:\na -> automatico\nm -> manual\n\nDigite a sua opção: ");
            char op = ler.next().toLowerCase().charAt(0);

            if (op == 'a') {
                automatico = true;
                i++;
            } else if (op == 'm') {
                automatico = false;
                i++;
            } else {
                System.out.println("Opção inválida.");
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
                    System.out.println("Opção inválida.");
                }
            }
        }

        int[] tamanhos = new int[]{4,3,2,1};

        if(automatico){
            autoAlocar(campoJogador, tamanhos);
            System.out.println("\nTabuleiro do Jogador:");
            mostraTabuleiro(campoJogador);
        }else{
            manualAlocar(campoJogador, tamanhos);
        }

        boolean automaticoAd = false;

        if(multiplayer){
            System.out.println("\nJogador adversário, de que modo você deseja alocar os barcos:");
            for(int i = 0; i == 0;) {
                System.out.print("a -> automatico\nm -> manual\n\nDigite a sua opção: ");
                char op = ler.next().toLowerCase().charAt(0);

                if (op == 'a') {
                    automaticoAd = true;
                    i++;
                } else if (op == 'm') {
                    automaticoAd = false;
                    i++;
                } else {
                    System.out.println("Opção inválida.");
                }
            }
            if(automaticoAd){
                autoAlocar(campoAdversario, tamanhos);
                System.out.println("Tabuleiro do Jogador Adversário:");
                mostraTabuleiro(campoAdversario);
            }else{
                manualAlocar(campoAdversario, tamanhos);
            }
        }

        int ganhou = 0;
        int barcosAdversarios = 20;
        int barcosJogador = 20;

        if (multiplayer){
            do{
                mostraTabuleiro(campoAtacarAdversario);
                System.out.println("♒ = água | 💣 = errou um barco | ⛵ = acertou um barco | "+barcosAdversarios+" = barcos restantes");
                System.out.println("\nAtaque do Jogador:");

                //esse for é pra ter certeza que o jogador vai tentar acertar um lugar que ainda não foi tentado
                for (int i = 0 ; i == 0 ;){
                    System.out.println("Faça o ataque:");
                    int[] cordenadas = cordenadas();
                    if(checarLivre(cordenadas[0], cordenadas[1], 'h', campoAtacarAdversario, 1)){//checa pra ver se o jogador já acertou esse lugar
                        campoAtacarAdversario = ataque(cordenadas[0], cordenadas[1], campoAdversario, campoAtacarAdversario);
                        if (acertou(cordenadas[0], cordenadas[1], campoAdversario)){
                            barcosAdversarios--;
                        }
                        i++;
                    }else{
                        System.out.print("\nVocê já tentou acertar esse lugar! Tente outra posição.");
                    }
                }

                if (barcosAdversarios == 0){
                    ganhou = 1;
                    break;
                }
                mostraTabuleiro(campoAtacarAdversario);
                System.out.println("♒ = água | 💣 = errou um barco | ⛵ = acertou um barco | "+barcosAdversarios+" = barcos restantes");



                System.out.println("\nAtaque do Adversário:");

                mostraTabuleiro(campoAtacarJogador);
                System.out.println("♒ = água | 💣 = errou um barco | ⛵ = acertou um barco | "+barcosJogador+" = barcos restantes");

                //esse for é pra ter certeza que o jogador vai tentar acertar um lugar que ainda não foi tentado
                for (int i = 0 ; i == 0 ;){
                    System.out.println("Faça o ataque:");
                    int[] cordenadas = cordenadas();

                    if (checarLivre(cordenadas[0], cordenadas[1], 'h', campoAtacarJogador, 1)){//checa pra ver se o jogador já acertou esse lugar
                        campoAtacarJogador = ataque(cordenadas[0], cordenadas[1], campoJogador, campoAtacarJogador);
                        if (acertou(cordenadas[0], cordenadas[1], campoJogador)){
                            barcosJogador--;
                        }
                        i++;
                    }else{
                        System.out.println("Você já tentou acertar esse lugar! Tente outra posição.");
                    }
                }

                if (barcosJogador == 0){
                    ganhou = 2;
                    break;
                }

                mostraTabuleiro(campoAtacarJogador);
                System.out.println("♒ = água | 💣 = errou um barco | ⛵ = acertou um barco | "+barcosJogador+" = barcos restantes");


            }while(ganhou == 0);
        }
        else{
            Random gerar= new Random();
            autoAlocar(campoAdversario, tamanhos);
            if (!dificil){
                do {
                    System.out.println("\nCampo Adversário:");
                    mostraTabuleiro(campoAtacarAdversario);
                    System.out.println("♒ = água | 💣 = errou um barco | ⛵ = acertou um barco | "+barcosAdversarios+" = barcos restantes");
                    System.out.println("O adversário acertou "+(20-barcosJogador)+" dos seus barcos.");
                    System.out.println("\nAtaque do Jogador:");

                    //esse for é pra ter certeza que o jogador vai tentar acertar um lugar que ainda não foi tentado
                    for (int i = 0 ; i == 0 ;){
                        System.out.println("Faça o ataque:");
                        int[] cordenadas = cordenadas();
                        if(checarLivre(cordenadas[0], cordenadas[1], 'h', campoAtacarAdversario, 1)){//checa pra ver se o jogador já acertou esse lugar
                            campoAtacarAdversario = ataque(cordenadas[0], cordenadas[1], campoAdversario, campoAtacarAdversario);
                            if (acertou(cordenadas[0], cordenadas[1], campoAdversario)){
                                System.out.println("Acertou jogue de novo!");
                                barcosAdversarios--;

                            }
                            i++;
                        }else{
                            System.out.print("\nVocê já tentou acertar esse lugar! Tente outra posição.");
                        }
                    }

                    if (barcosAdversarios == 0){
                        ganhou = 1;
                        break;
                    }
                    mostraTabuleiro(campoAtacarAdversario);
                    System.out.println("♒ = água | 💣 = errou um barco | ⛵ = acertou um barco | "+barcosAdversarios+" = barcos restantes");



                    System.out.println("\nAtaque da Máquina:");
                    for (int i = 0 ; i == 0 ;){
                        int linha= gerar.nextInt(0, 9);
                        int coluna= gerar.nextInt(0, 9);
                        if(checarLivre(linha, coluna, 'h', campoAtacarJogador, 1)){
                            campoAtacarJogador =ataque(linha, coluna, campoJogador, campoAtacarJogador);
                            if(acertou(linha, coluna, campoJogador)){
                                System.out.println("Acertou jogue de novo!");
                                barcosJogador--;

                            }
                            i++;
                        }
                    }
                    if (barcosJogador == 0){
                        ganhou = 2;
                        break;
                    }
                    mostraTabuleiro(campoAtacarJogador);

                }while(ganhou == 0);
            }
            else{
                do {
                    System.out.println("\nCampo Adversário:");
                    mostraTabuleiro(campoAtacarAdversario);
                    System.out.println("♒ = água | 💣 = errou um barco | ⛵ = acertou um barco | "+barcosAdversarios+" = barcos restantes");
                    System.out.println("O adversário acertou "+(20-barcosJogador)+" dos seus barcos.");
                    System.out.println("\nAtaque do Jogador:");

                    //esse for é pra ter certeza que o jogador vai tentar acertar um lugar que ainda não foi tentado
                    for (int i = 0 ; i == 0 ;){
                        System.out.println("Faça o ataque:");
                        int[] cordenadas = cordenadas();
                        if(checarLivre(cordenadas[0], cordenadas[1], 'h', campoAtacarAdversario, 1)){//checa pra ver se o jogador já acertou esse lugar
                            campoAtacarAdversario = ataque(cordenadas[0], cordenadas[1], campoAdversario, campoAtacarAdversario);
                            if (acertou(cordenadas[0], cordenadas[1], campoAdversario)){
                                barcosAdversarios--;

                            }
                            i++;
                        }else{
                            System.out.print("\nVocê já tentou acertar esse lugar! Tente outra posição.");
                        }
                    }

                    if (barcosAdversarios == 0){
                        ganhou = 1;
                        break;
                    }
                    mostraTabuleiro(campoAtacarAdversario);
                    System.out.println("♒ = água | 💣 = errou um barco | ⛵ = acertou um barco | "+barcosAdversarios+" = barcos restantes");



                    System.out.println("\nAtaque da Máquina:");
                    for (int i = 0 ; i == 0 ;){
                        int linha= gerar.nextInt(0, 9);
                        int coluna= gerar.nextInt(0, 9);
                        if(checarLivre(linha, coluna, 'h', campoAtacarJogador, 1)){
                            campoAtacarJogador =ataque(linha, coluna, campoJogador, campoAtacarJogador);
                            if(acertou(linha, coluna, campoJogador)){
                                barcosJogador--;
                                tentarAcertar(linha, coluna);
                                campoAtacarJogador =ataque(linha, coluna, campoJogador, campoAtacarJogador);
                            }
                            i++;
                        }
                    }
                    if (barcosJogador == 0){
                        ganhou = 2;
                        break;
                    }
                    mostraTabuleiro(campoAtacarJogador);

                }while(ganhou == 0);
            }
        }

        if (ganhou == 1){
            System.out.println("\n\nO Jogador foi o vencedor da rodada!");
        }else{
            System.out.println("\n\nO Adversário foi o vencedor da rodada!");
        }

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
        int num;
        switch(op){
            case 'a' -> num=0;
            case 'b' -> num=1;
            case 'c' -> num=2;
            case 'd' -> num=3;
            case 'e' -> num=4;
            case 'f' -> num=5;
            case 'g' -> num=6;
            case 'h' -> num=7;
            case 'i' -> num=8;
            case 'j' -> num=9;
            default -> num=10;
        }

        return num;
    }

    static boolean checarLivre(int linha, int coluna, char direcao, String[][] campo, int tamanho){
        if (direcao == 'h'){
            if(coluna+tamanho>campo[0].length){
                return false;
            }

            for (int i=coluna;i<coluna+tamanho;i++){
                if(!campo[linha][i].equals("♒")){
                    return false;
                }
            }
        }
        else{ //se não for  h vai ser v
            if(linha+tamanho>campo.length){ //checar pra ver se não passa pra fora do campo, mas agora na vertical
                return false;
            }

            for (int i=linha;i<linha+tamanho;i++){ //checa pra ver se é tudo água
                if(!campo[i][coluna].equals("♒")){
                    return false;
                }
            }
        }

        return true; // se tiver tudo livre, retorna como verdade, ou seja, é tudo água então pode colocar o barquinho
    }

    static String[][] alocarBarco(int linha, int coluna, char direcao, String[][] campo, int tamanho){
        if (direcao == 'h'){
            for (int i = coluna;i<coluna+tamanho;i++){
                campo[linha][i] = "⛵";
            }
            return campo;
        }else{
            for (int i = linha;i<linha+tamanho;i++){
                campo[i][coluna] = "⛵";
            }
            return campo;
        }
    }

    static void autoAlocar(String[][] campoJogador, int[] tamanhos){
        Random gerar= new Random();
        boolean alocou;
        for (int i = 0; i<tamanhos.length; i++){
            for (int j = i; j >= 0;){
                do{
                    alocou = false;
                    int gerarDirecao = gerar.nextInt(1,100);
                    char opDirecao=' ';

                    if(gerarDirecao %2==0){
                        opDirecao='h';
                    }
                    else {
                        opDirecao='v';
                    }

                    int linha= gerar.nextInt(0,9);
                    int coluna= gerar.nextInt(0,9);

                    if (checarLivre(linha,coluna,opDirecao,campoJogador,tamanhos[i])){
                        alocarBarco(linha,coluna,opDirecao,campoJogador,tamanhos[i]);
                        alocou = true;
                    }
                }while(!alocou);
                j--;
            }
        }
    }
    static void manualAlocar(String[][] campo, int[] tamanhos){
        Scanner ler = new Scanner(System.in);
        for (int i = 0; i<tamanhos.length; i++){
            for (int j = i; j >= 0;){
                mostraTabuleiro(campo);
                System.out.print((j+1)+"x Barco grande ("+tamanhos[i]+" espaços):\nh - horizontal\nv - vertical\nOpção: ");
                char opDirecao = 'a';
                for (int a = 0; a == 0 ; ) {
                    if (ler.hasNextInt()){
                        System.out.println("Não é letra!");
                        ler.nextInt();
                    } else {
                        String op = ler.next();
                        if (op.length() == 1){
                            opDirecao = op.toLowerCase().charAt(0);
                            if (opDirecao == 'h' || opDirecao == 'v') {
                                a++;
                            } else {
                                System.out.println("Opção inválida.");
                            }
                        }else{
                            System.out.println("Opção inválida.");
                        }
                    }
                }

                int[] cordenadas = cordenadas();
                if(checarLivre(cordenadas[0], cordenadas[1], opDirecao, campo, tamanhos[i])){
                    System.out.println("ta livre");
                    campo = alocarBarco(cordenadas[0], cordenadas[1], opDirecao, campo, tamanhos[i]);
                    j--;
                }else{
                    System.out.println("não ta livre, tente de novo");
                }
            }
        }
    }

    static String[][] criarCampo() {
        String[][] campo = new String[10][10];
        for (int i = 0; i < campo.length; i++) {
            for (int j = 0; j < campo[i].length; j++) {
                campo[i][j] = "♒";
            }
        }
        return campo;
    }

    static String[][] ataque(int linha, int coluna, String[][] campoReferencia, String[][] campoMostra) {
        if (campoReferencia[linha][coluna].equals("⛵")){
            campoMostra[linha][coluna] = "⛵";
        }else{
            campoMostra[linha][coluna] = "💣";
        }
        return campoMostra;
    }

    static int[] cordenadas(){
        Scanner ler = new Scanner(System.in);

        int[] cordenadas = new int[2];

        for (int i = 0; i==0;) {
            for (int j = 0; j == 0;){
                System.out.print("\nLinha: ");
                if (ler.hasNextInt()){
                    cordenadas[0] = ler.nextInt();
                    j++;
                } else {
                    System.out.println("Não é um número!");
                    ler.next();
                }
            }

            if (cordenadas[0] >=0 && cordenadas[0]<=9) {
                i++;
            } else {
                System.out.println("Opção inválida.");
            }
        }


        for (int i = 0; i==0;) {
            System.out.print("\nColuna: ");
            if (ler.hasNextInt()){
                System.out.println("Não é letra!");
                ler.nextInt();
            } else {
                String charColuna = ler.next();
                if (charColuna.length() == 1){
                    int coluna = pegarNumero(charColuna.toLowerCase().charAt(0));
                    if (coluna != 10) {
                        cordenadas[1] = coluna;
                        i++;
                    } else {
                        System.out.println("Opção inválida.");
                    }
                }else{
                    System.out.println("Opção inválida.");
                }

            }
        }

        return cordenadas;
    }

    static boolean acertou(int linha, int coluna, String[][] campo){
        boolean acertou = false;
        if(campo[linha][coluna].equals("⛵")){
            acertou = true;
        }
        return acertou;
    }

    static void tentarAcertar(int linha, int coluna){
        Random gerar= new Random();
        int cont=0;
        int escolha=gerar.nextInt(1,100);
        if(escolha%2==0){//coluna
            int lado= gerar.nextInt(1,100);
            if(lado%2==0){
                coluna++; //testa um pra direita
                cont++;
            }
            else{
                coluna--;
            }
        }
        else{//linha
            int lado= gerar.nextInt(1,100);
            if(lado%2==0){
                linha++; //testa um pra cima
            }
            else{
                linha--;
            }
        }



    }

}