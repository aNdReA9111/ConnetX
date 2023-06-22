/*
 *  Copyright (C) 2022 Lamberto Colazzo
 *
 *  This file is part of the ConnectX software developed for the
 *  Intern ship of the course "Information technology", University of Bologna
 *  A.Y. 2021-2022.
 *
 *  ConnectX is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This  is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details; see <https://www.gnu.org/licenses/>.
 */

 package connectx.AFLP;

 import connectx.CXPlayer;
 import connectx.CXBoard;
 import connectx.CXGameState;
 import connectx.CXCell;
 import connectx.CXCellState;

 import java.util.TreeSet;
 import java.util.Random;
 import java.util.Arrays;
 import java.util.concurrent.ExecutionException;
 import java.util.concurrent.TimeoutException;
 import java.util.PriorityQueue;

 public class AFLP implements CXPlayer {
     private Random rand;
     private CXGameState myWin;
     private CXGameState yourWin;
     private CXCellState maximizingCellState;
     private boolean isMaximizing;
     private int  TIMEOUT;
     private long START;

     /* Default empty constructor */
     public AFLP() { }

     public void initPlayer(int M, int N, int K, boolean first, int timeout_in_secs) {
        // New random seed for each game
        rand    = new Random(System.currentTimeMillis());
        myWin   = first ? CXGameState.WINP1 : CXGameState.WINP2;
        yourWin = first ? CXGameState.WINP2 : CXGameState.WINP1;
        maximizingCellState = first ? CXCellState.P1 : CXCellState.P2;
        isMaximizing = true;
        TIMEOUT = timeout_in_secs;
     }

    private void checktime() throws TimeoutException {
		if ((System.currentTimeMillis() - START) / 1000.0 >= TIMEOUT * (99.0 / 100.0))
			throw new TimeoutException();
	}

    public String playerName() {
        return "AFLP";
    }

    private int[] AlphaBeta_Pruning(CXBoard B, boolean isMax, int alpha, int beta, int depth) throws TimeoutException {
        int[] pair = new int[2];
        checktime();
        if(depth == 0 || B.gameState() != CXGameState.OPEN){
            pair[0] = evaluate(B);  pair[1] = B.getLastMove().j;
            return pair;
        }
        else if(isMax){
            pair[0] = -1000000;                     //eval
            pair[1] = B.getAvailableColumns()[0];
            /*
            prendi la configurazione che hai qua

            chiami uan funzione evalColonne(B.getAvailableColumns())
            questa funzione l'array eval
            */
            //int[] eval = evalColumns(B, isMax);
            //int[] columns = heapsort(B, eval);
        

            for(int i : B.getAvailableColumns()){
                B.markColumn(i);
                int tmp = AlphaBeta_Pruning(B, false, alpha, beta, depth-1)[0];
                if (pair[0] < tmp){
                    pair[0] = tmp;
                    pair[1] = i;
                }
                B.unmarkColumn();
                if(pair[0] > alpha)
                    alpha = pair[0];
                
                if(alpha >= beta){      //cutoff Beta
                    break;
                }
            }
            return pair;
        }
        else{
            pair[0] = 1000000;
            pair[1] = B.getAvailableColumns()[0];
            //int[] eval = evalColumns(B, isMax);
            //int[] columns = heapsort(B, eval);

            for(int i : B.getAvailableColumns()) {
                B.markColumn(i);
                int tmp = AlphaBeta_Pruning(B, true, alpha, beta, depth-1)[0];
                if (pair[0] > tmp){
                    pair[0] = tmp;
                    pair[1] = i;
                }
                B.unmarkColumn();
                if(pair[0] < beta)
                    beta = pair[0];

                if(alpha >= beta){      //cutoff Alpha
                    break;
                }
            }
            return pair;
        }
    }

    private int evaluate(CXBoard B) throws TimeoutException {
        if(B.gameState() == myWin)
            return 1000000;
        else if(B.gameState() == yourWin)
            return -1000000;
        else if(B.gameState() == CXGameState.DRAW)
            return 0;
        else {
            //valutazione euristica di una situazione di gioco non finale
            // nell'eval assegno punteggi positivi per le sequenze di pedine del player massimizzante
            // e negativi per il minimizzante
            //CXCellState s = B[i][j];
            //CXCellState s = B[i][j];
            int n = 0;
            int n1 = 0,n2 = 0, n3 = 0, n4 = 0; //n1 = numero di sequenze di lunghezza X-1, X-2 per n2, X-3 per n3, X-4 per n4
            CXCellState[][] cellBoard = B.getBoard();

            //Ottimizzo gli indici --> da fare
            //i = 0...ultima riga con pedina
            //j = prima colonna con pedina...ultima colonna con pedina
            int firstColumn = 0, lastColumn = B.N - 1; int lastRow = 0;

            for(int j = 0 ; j < B.N ; j++){
                if(cellBoard[0][j] != CXCellState.FREE)
                    break;
                else firstColumn++;
            }

            for(int j = B.N - 1 ; j >= 0 ; j--){
                if(cellBoard[0][j] != CXCellState.FREE)
                    break;
                else lastColumn--;
            }

            for(int j = firstColumn; j <= lastColumn; j++){
                if(B.fullColumn(j)){
                    lastRow = B.M - 1;
                    break;
                }
                else{
                    int i;
                    for(i = 0; i < B.M && cellBoard[i][j] != CXCellState.FREE; i++){}
                    if((i-1) > lastRow)
                        lastRow = (i-1);
                }
            }

            for(int i = 0; i <= lastRow; i++)
            {
                for(int j = firstColumn; j <= lastColumn; j++)
                {
                    checktime();
                    //controllo orizzontale
                    boolean enter_check = true;
                    boolean condition1, condition2;
                    int k;
                    n = 1;
                    if(j-1 >= 0){
                        enter_check = (cellBoard[i][j-1] != cellBoard[i][j]);
                        condition1 = (cellBoard[i][j-1] == CXCellState.FREE);
                    }
                    else condition1 = false;
                    for(k = 1; enter_check && j+k < B.N && cellBoard[i][j+k] == cellBoard[i][j]; k++) n++;
                    if(j+k >= B.N){
                        condition2 = false;
                    }
                    else condition2 = (cellBoard[i][j+k] == CXCellState.FREE);
                    if(n == B.X - 1 && (condition1 || condition2)){
                        if(cellBoard[i][j] == maximizingCellState) n1++;
                        else n1--;
                    }
                    else if(n == B.X - 2 && (condition1 || condition2)){
                        if(cellBoard[i][j] == maximizingCellState) n1++;
                        else n2--;
                    }
                    else if(n == B.X - 3 && (condition1 || condition2) && B.X > 5){
                        if(cellBoard[i][j] == maximizingCellState) n1++;
                        else n3--;
                    }
                    else if(n == B.X - 4 && (condition1 || condition2) && B.X > 7){
                        if(cellBoard[i][j] == maximizingCellState) n1++;
                        else n4--;
                    }

                    //controllo verticale
                    enter_check = true;
                    condition1 = false;
                    condition2 = false;
                    n = 1;
                    if(i-1 >= 0){
                        enter_check = (cellBoard[i-1][j] != cellBoard[i][j]);
                        condition1 = (cellBoard[i-1][j] == CXCellState.FREE);
                    }
                    else condition1 = false;
                    for(k = 1; enter_check && i+k < B.M && cellBoard[i+k][j] == cellBoard[i][j]; k++) n++;
                    if(i+k >= B.M) {
                        condition2 = false;
                    }
                    else condition2 = (cellBoard[i+k][j] == CXCellState.FREE);
                    if(n == B.X - 1 && (condition1 || condition2)){
                        if(cellBoard[i][j] == maximizingCellState) n1++;
                        else n1--;
                    }
                    else if(n == B.X - 2 && (condition1 || condition2)){
                        if(cellBoard[i][j] == maximizingCellState) n2++;
                        else n2--;
                    }
                    else if(n == B.X - 3 && (condition1 || condition2) && B.X > 5){
                        if(cellBoard[i][j] == maximizingCellState) n3++;
                        else n3--;
                    }
                    else if(n == B.X - 4 && (condition1 || condition2) && B.X > 7){
                        if(cellBoard[i][j] == maximizingCellState) n4++;
                        else n4--;
                    }

                    //controllo diagonale
                    enter_check = true;
                    condition1 = false;
                    condition2 = false;
                    n = 1;
                    if(i-1 >= 0 && j-1 >= 0){
                        enter_check = (cellBoard[i-1][j-1] != cellBoard[i][j]);
                        condition1 = (cellBoard[i-1][j-1] == CXCellState.FREE);
                    }
                    else condition1 = false;
                    for(k = 1; enter_check && (i+k < B.M  && j+k < B.N ) && cellBoard[i+k][j+k] == cellBoard[i][j]; k++) n++;
                    if(i+k >= B.M || j+k >= B.N) {
                        condition2 = false;
                    }
                    else condition2 = (cellBoard[i+k][j+k] == CXCellState.FREE);
                    if(n == B.X - 1 && (condition1 || condition2)){
                        if(cellBoard[i][j] == maximizingCellState) n1++;
                        else n1--;
                    }
                    else if(n == B.X - 2 && (condition1 || condition2)){
                        if(cellBoard[i][j] == maximizingCellState) n2++;
                        else n2--;
                    }
                    else if(n == B.X - 3 && (condition1 || condition2) && B.X > 5){
                        if(cellBoard[i][j] == maximizingCellState) n3++;
                        else n3--;
                    }
                    else if(n == B.X - 4 && (condition1 || condition2) && B.X > 7){
                        if(cellBoard[i][j] == maximizingCellState) n4++;
                        else n4--;
                    }


                    //controllo anti-diagonale  (destra - sinistra /basso - alto)
                    enter_check = true;
                    condition1 = false;
                    condition2 = false;
                    n = 1;
                    if(i-1 >= 0 && j+1 < B.N){
                        enter_check = (cellBoard[i-1][j+1] != cellBoard[i][j]);
                        condition1 = (cellBoard[i-1][j+1] == CXCellState.FREE);
                    }
                    else condition1 = false;
                    for(k = 1; enter_check && (i+k < B.M  && j-k >= 0) && cellBoard[i+k][j-k] == cellBoard[i][j]; k++) n++;
                    if(i+k >= B.M || j-k < 0) {
                        condition2 = false;
                    }
                    else condition2 = (cellBoard[i+k][j-k] == CXCellState.FREE);
                    if(n == B.X - 1 && (condition1 || condition2)){
                        if(cellBoard[i][j] == maximizingCellState) n1++;
                        else n1--;
                    }
                    else if(n == B.X - 2 && (condition1 || condition2)){
                        if(cellBoard[i][j] == maximizingCellState) n2++;
                        else n2--;
                    }
                    else if(n == B.X - 3 && (condition1 || condition2) && B.X > 5){
                        if(cellBoard[i][j] == maximizingCellState) n3++;
                        else n3--;
                    }
                    else if(n == B.X - 4 && (condition1 || condition2) && B.X > 7){
                        if(cellBoard[i][j] == maximizingCellState) n4++;
                        else n4--;
                    }
                }
            }
            //cambiare i seguenti punteggi per dare pesi diversi alle varie sequenze trovate
            int score1 = 50;
            int score2 = 20;
            int score3 = 10;
            int score4 = 5;

            int eval = n1 * score1 + n2 * score2 + n3 * score3 + n4 * score4;

            return eval;
        }
    }

    int[] evalColumns(CXBoard B, boolean isMax) throws TimeoutException {
        Integer[] availableColumns = B.getAvailableColumns();
        int[] eval = new int[availableColumns.length];

        for(int k = 0; k < availableColumns.length; k++){
            checktime();

            CXGameState state = B.markColumn(availableColumns[k]);
            CXCellState [][] cellBoard = B.getBoard();
            int n;
            //valuto la colonna
            
            if(state == myWin)              eval[k] =  1000;
            else if(state == yourWin)       eval[k] = -1000;
            else {    //caso generale
                //prendo le "coordinate" dell'ultima mossa effettuata
                int i = B.getLastMove().i, j = B.getLastMove().j;

                ////////////////caso orizzontale
                n = 1;
                int l = 1, r = 1, countFree = 0;

                //calcolo le celle con la stessa pedina del player massimizzante contigue
                for (l = 1; j-l >= 0 && cellBoard[i][j-l] == cellBoard[i][j]; l++) n++; // controllo all'indietro
                for (r = 1; j+r <  B.N && cellBoard[i][j+r] == cellBoard[i][j]; r++) n++; // controllo in avanti

                //calcolo le celle libere adiacenti a quelle della pedina massimizzante
                for(int p = j-l; p >= 0 && (cellBoard[i][j] == cellBoard[i][p] || cellBoard[i][p] == CXCellState.FREE) && p >= 0; p--)// controllo all'indietro
                {
                    if(cellBoard[i][j] == cellBoard[i][p] )
                        n++;
                    else countFree++;
                } // controllo in avanti
                for(int p = j+r; p < B.N && (cellBoard[i][j] == cellBoard[i][p] || cellBoard[i][p] == CXCellState.FREE); p++)
                {
                    if(cellBoard[i][j] == cellBoard[i][p] )
                        n++;
                    else countFree++;
                } // controllo in avanti

                //se nelle celle contigue è possbile arrivare a una sequenza vincente assegno il valore a questa mossa
                if(n >= B.X)    n = B.X - 1;
                if(countFree + n >= B.X)    eval[k] += n;


                ////////////////caso verticale
                n = 1;
                for (l = 1; i-l >= 0 && cellBoard[i][j] == cellBoard[i-l][j]; l++) n++;     // controllo all'indietro

                //vedo in alto quante altre celle sono libere
                countFree = B.M - i - 1;                                                    // controllo in avanti

                //se nelle celle contigue è possbile arrivare a una sequenza vincente assegno il valore a questa mossa
                if(countFree + n >= B.X)    eval[k] += n;


                ////////////////caso diagonale
                n = 1; countFree = 0;
                for (l = 1; i-l >= 0 && j-l >= 0 && cellBoard[i-l][j-l] == cellBoard[i][j]; l++) n++; // controllo all'indietro
                for (r = 1; i+r <  B.M && j+r <  B.N && cellBoard[i+r][j+r] == cellBoard[i][j]; l++) n++; // controllo in avanti

                for (int p = l; i-p >= 0 && j-p >= 0 && (cellBoard[i][j] == cellBoard[i-p][j-p] || cellBoard[i-p][j-p] == CXCellState.FREE); p++) // controllo all'indietro
                {
                    if(cellBoard[i][j] == cellBoard[i-p][j-p] )
                        n++;
                    else countFree++;
                }
                for (int p = r; i+p <  B.M && j+p <  B.N && (cellBoard[i][j] == cellBoard[i+p][j+p] || cellBoard[i+p][j+p] == CXCellState.FREE); p++)  // controllo in avanti
                {
                    if(cellBoard[i][j] == cellBoard[i+p][j+p] )
                        n++;
                    else countFree++;

                }

                //se nelle celle contigue è possbile arrivare a una sequenza vincente assegno il valore a questa mossa
                if(n >= B.X)    n = B.X - 1;
                if(countFree + n >= B.X)    eval[k] += n;

                ////////////////caso anti-diagonale
                n = 1; countFree = 0;
                for (l = 1; i-l >= 0  && j+l < B.N && cellBoard[i-l][j+l] == cellBoard[i][j]; l++) n++; // controllo all'indietro
                for (r = 1; i+r <  B.M && j-r >= 0 && cellBoard[i+r][j-r] == cellBoard[i][j]; r++) n++; // controllo in avanti


                for (int p = l; i-p >= 0   && j+p < B.N && (cellBoard[i][j] == cellBoard[i-p][j+p] || cellBoard[i-p][j+p] == CXCellState.FREE); p++)// controllo all'indietro
                {
                    if(cellBoard[i][j] == cellBoard[i-p][j+p] )
                        n++;
                    else countFree++;

                }
                for (int p = r; i+p < B.M  && j-p >=  0 && (cellBoard[i][j] == cellBoard[i+p][j-p] || cellBoard[i+p][j-p] == CXCellState.FREE); p++) // controllo in avanti
                {
                    if(cellBoard[i][j] == cellBoard[i+p][j-p] )
                        n++;
                    else countFree++;
                }

                //se nelle celle contigue è possbile arrivare a una sequenza vincente assegno il valore a questa mossa
                if(n >= B.X)    n = B.X - 1;
                if(countFree + n >= B.X)    eval[k] += n;
            }
            //vedo se si massimizza o meno
            if(!isMax)
                eval[k] *= -1;
            B.unmarkColumn();
        }
        return eval;
    }

    //il costo computazionale e' dettato dal primo for, ossia viene chiamata una offer per n volte
    //la offer ha costo log(n), quindi il costo complessivo e' O(nlogn), il medesimo dell'omonimo
    //algoritmo di ordinamento

    //offer corrisponde al costo computazionale di una insert (O(log(n))
    //poll corrisponde a una findMax + una deleteMax, ossia (O(1)) + O(log(n)) = O(log(n))
    private int[] heapsort(CXBoard B, int eval[]) throws TimeoutException{
        PriorityQueue<Pair> priorityQueue = new PriorityQueue<>();
        Pair[] pairs = new Pair[B.getAvailableColumns().length];
        int[] columns = new int[B.getAvailableColumns().length];

        checktime();
        for(int i = 0; i < B.getAvailableColumns().length; i++){
            pairs[i] = new Pair(eval[i], B.getAvailableColumns()[i]);
            priorityQueue.offer(pairs[i]);
        }

        for(int i = 0; i < B.getAvailableColumns().length; i++)
            columns[i] = priorityQueue.poll().second;

        return columns;
    }
    private int[] iterativeDeepening(CXBoard B, boolean isMax, int depth) throws TimeoutException  {
        int[] pair = new int[2];
        //pair[0] = 0;
        //pair[1] = B.getAvailableColumns()[0];
        int alpha = -1000000, beta = 1000000;
        try{
            for(int i = 1; i < depth; i++){
                checktime();
                pair = AlphaBeta_Pruning(B, isMax, alpha, beta, i);
            }
        }
        catch (TimeoutException e) {
            System.err.println("Tempo quasi finito, ritorno una scelta a profondità minore!");
            return pair;
        }

        return pair;
    }

    @Override
    public int selectColumn(CXBoard B){
        START = System.currentTimeMillis(); // Save starting time

		Integer[] L = B.getAvailableColumns();
		int out    = L[rand.nextInt(L.length)]; // Save a random column
         
        try {   out = iterativeDeepening(B, isMaximizing, 7)[1];       }
        catch (TimeoutException e) {}

        return out;
    }
}

