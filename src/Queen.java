public class Queen {

    private int[] tabuleiro;
    private boolean[] usado;
    private int contador;


    // Cria o objeto "Queen" que armazena o tamanho do tabuleiro sendo ele de tamanho NxN.
    // Como nosso problema é de 8 rainhas, então na main já colocamos o "8" como atributo.
    public Queen(int n) {

        tabuleiro = new int[n];
        usado = new boolean[n];
        contador = 0;

        for (int i=0; i<n; i++) {
            tabuleiro[i] = -1;
            usado[i] = false;
        }
    }


    //Função principal
    public void resolverProblema() {

        resolverProblemaRec(0);
    }

    // Pré-condição: a variável tabuleiro armazena uma permutação de rainhas do índice 0 até (local - 1)
    //               que é válida para um conjunto de números da localização de rainhas sem conflitos.
    //               local representa a coluna onde estamos posicionando a próxima rainha,
    //               e o contador acompanha as linhas nas quais as rainhas já foram posicionadas.
    public void resolverProblemaRec(int local) {

        int i;

        // Caso achemos a solução, coloque no terminal a mesma.
        if (local == tabuleiro.length) {
            imprimirSolucao();
            contador++;
        }

        // Aqui que entra o backtracking
        // Loop "for" entre as possíveis localizações para colocar a próxima rainha.
        for (i=0; i< tabuleiro.length; i++) {

            // Tentar somente essa linha se ela não foi usada.
            if (usado[i] == false) {

                // Com a função "conflito", analisar caso haja algum conflito
                // como as diagonais entre as rainhas passadas.
                if (!conflito(local, i)) {

                    // Local para colocar a Rainha.
                    tabuleiro[local] = i;

                    // Marcar a linha usada para facilitar a resolução de conflitos!
                    usado[i] = true;

                    // Resolver de maneira recursiva as outras soluções com diferença de local
                    // para não dar o mesmo resultado.
                    resolverProblemaRec(local+1);

                    //Depois tornamos "false" para fazermos outros tabuleiros
                    // consequentemente, outras soluções.
                    usado[i] = false;
                }

            }
        }

    }

    private boolean conflito(int local, int linha) {

        int i;

        // Observar se há algum conflito, ou seja, analisar
        // caso haja alguma rainha que possa "matar" a outra
        // naquela localização de acordos com as postas anteriormente.
        for (i = 0; i < local; i++)

            // Caso as diagonais tenham a mesma distância, ou seja, há alguma
            // rainha que pode causar mal à outra.
            if (Math.abs(local - i) == Math.abs(tabuleiro[i] - linha))
                return true;

        // Não há nenhum conflito, então podemos colocar a rainha exatamente nesse local.
        return false;
    }

    // Imprime o tabuleiro em 2D da solução proposta
    public void imprimirSolucao() {

        int linha,coluna;

        System.out.println("Solução N°" + (contador + 1) + ":\n");

        // Coluna
        for (linha = 0; linha < tabuleiro.length; linha++) {

            // Linha
            for (coluna = 0; coluna < tabuleiro.length; coluna++) {

                //Rainha(Q) que está na coluna "j", está na linha "i".
                if (tabuleiro[coluna] == linha)
                    System.out.print("Q ");

                    // Completar a tabela com ". " para indicar os espaços vazios.
                else
                    System.out.print(". ");
            }
            System.out.println();
        }
    }

    // Imprime a quantidade de soluções armazenadas.
    public void imprimirQuantidade() {
        System.out.println("Quantidade de Soluções para esse problema: "+ contador);
    }

}





