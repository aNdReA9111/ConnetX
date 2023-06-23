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

import java.util.Hashtable;
import java.util.PriorityQueue;
import java.util.concurrent.TimeoutException;

public class AFLP implements CXPlayer {
    private CXGameState myWin;
    private CXGameState yourWin;
    private CXCellState maximizingCellState;
    private boolean isMaximizing;
    private int  TIMEOUT;
    private long START;
    //private Hashtable<String, Integer> hashtable = new Hashtable<>(3^(40*8), 0.75f);
    private Hashtable<String, Integer> hashtable = new Hashtable<>();

    /* Default empty constructor */
    public AFLP() { }

    public void initPlayer(int M, int N, int K, boolean first, int timeout_in_secs) {
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
            pair[0] = evaluate(B, B.getBoard());  pair[1] = B.getLastMove().j;
            return pair;
        }
        else if(isMax){
            pair[0] = -1000000;                     //eval
            pair[1] = B.getAvailableColumns()[0];

            int len = B.getAvailableColumns().length;
            PriorityQueue<Pair> priorityQueue = new PriorityQueue<>(len);
            int[] eval = new int[len];
            int c = 0;

            for(int i : B.getAvailableColumns()){
                B.markColumn(i);
                eval[c] = evalMove(B);
                B.unmarkColumn();
                c++;
            }
            c = 0;

            for(int i = 0; i < len; i++)
                priorityQueue.offer(new Pair(eval[i], B.getAvailableColumns()[i]));

            for(int i = 0; i < len; i++){
                Pair p = priorityQueue.poll();
                int col = p.second;
                checktime();
                B.markColumn(col);
                int tmp = AlphaBeta_Pruning(B, false, alpha, beta, depth-1)[0];
                if (pair[0] < tmp){
                    pair[0] = tmp;
                    pair[1] = col;
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
            int len = B.getAvailableColumns().length;
            PriorityQueue<Pair> priorityQueue = new PriorityQueue<>(len);
            int[] eval = new int[len];
            int c = 0;

            for(int i : B.getAvailableColumns()){
                B.markColumn(i);
                eval[c] = evalMove(B);
                B.unmarkColumn();
                c++;
            }
            c = 0;


            for(int i = 0; i < len; i++)
                priorityQueue.offer(new Pair(eval[i], B.getAvailableColumns()[i]));

            for(int i = 0; i < len; i++){
                Pair p = priorityQueue.poll();
                int col = p.second;
                checktime();
                B.markColumn(col);
                int tmp = AlphaBeta_Pruning(B, true, alpha, beta, depth-1)[0];
                if (pair[0] > tmp){
                    pair[0] = tmp;
                    pair[1] = col;
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

    private int evalMove(CXBoard B) throws TimeoutException{
        CXCell C = B.getLastMove();
        CXCellState[][] cellBoard = B.getBoard();
        int i = C.i, j = C.j, b = 0, n = 1, n1 = 1, n2 = 1, n3 = 1, n4 = 1, b1 = 1;

        if(B.gameState() == myWin)
            return 1000;
        else if(B.gameState() == yourWin)
            return 1000;
        else if(B.gameState() == CXGameState.DRAW)
            return 0;
        else {    //controllo orizzontale
            int k, plus;
            for (k = 1; j-k >= 0 && cellBoard[i][j-k] == cellBoard[i][j]; k++) n++;
            for(k = 1; j+k < B.N && cellBoard[i][j+k] == cellBoard[i][j]; k++) n++;

            for (k = 1; j-k >= 0 && cellBoard[i][j-k] != cellBoard[i][j] && cellBoard[i][j-k] != CXCellState.FREE ; k++) b++;
            for(k = 1; j+k < B.N && cellBoard[i][j+k] != cellBoard[i][j] && cellBoard[i][j+k] != CXCellState.FREE ; k++) b++;

            if(b == B.X - 1) b1++;

            if(n == B.X - 1) n1++;
            else if(n == B.X - 2) n2++;
            else if(n == B.X - 3 && B.X > 5) n3++;
            else if(n == B.X - 4 && B.X > 7) n4++;
            checktime();
            //controllo verticale
            n = 1; b = 0;
            for(k = 1; i+k < B.M && cellBoard[i+k][j] == cellBoard[i][j]; k++) n++;
            for(k = 1; i+k < B.M && cellBoard[i+k][j] != cellBoard[i][j] && cellBoard[i+k][j] != CXCellState.FREE ; k++) b++;

            if(b == B.X - 1) b1++;

            if(n == B.X - 1) n1++;
            else if(n == B.X - 2) n2++;
            else if(n == B.X - 3 && B.X > 5) n3++;
            else if(n == B.X - 4 && B.X > 7) n4++;

            //controllo diagonale
            n = 1; b = 0;

            for (k = 1; i-k >= 0 && j-k >= 0 && cellBoard[i-k][j-k] == cellBoard[i][j]; k++) n++; // backward check
            for(k = 1; (i+k < B.M  && j+k < B.N ) && cellBoard[i+k][j+k] == cellBoard[i][j]; k++) n++;
            
            for (k = 1; i-k >= 0 && j-k >= 0 && cellBoard[i-k][j-k] != cellBoard[i][j] && cellBoard[i-k][j-k] != CXCellState.FREE; k++) b++; // backward check
            for(k = 1; (i+k < B.M  && j+k < B.N ) && cellBoard[i+k][j+k] != cellBoard[i][j] && cellBoard[i+k][j+k] != CXCellState.FREE; k++) b++;
            checktime();

            if(b == B.X - 1) b1++;            

            if(n == B.X - 1) n1++;
            else if(n == B.X - 2) n2++;
            else if(n == B.X - 3 && B.X > 5) n3++;
            else if(n == B.X - 4 && B.X > 7) n4++;


            //controllo anti-diagonale  (destra - sinistra /basso - alto)
            n = 1; b = 0;
            for (k = 1; i-k >= 0 && j+k < B.N && cellBoard[i-k][j+k] == cellBoard[i][j]; k++) n++;
            for(k = 1;(i+k < B.M  && j-k >= 0) && cellBoard[i+k][j-k] == cellBoard[i][j]; k++) n++;

            for (k = 1; i-k >= 0 && j+k < B.N && cellBoard[i-k][j+k] != cellBoard[i][j] && cellBoard[i-k][j+k] != CXCellState.FREE; k++) n++;
            for(k = 1;(i+k < B.M  && j-k >= 0) && cellBoard[i+k][j-k] != cellBoard[i][j] && cellBoard[i+k][j-k] != CXCellState.FREE; k++) n++;
            checktime();
            if(b == B.X - 1) b1++;

            if(n == B.X - 1) n1++;
            else if(n == B.X - 2 ) n2++;
            else if(n == B.X - 3 && B.X > 5) n3++;
            else if(n == B.X - 4 && B.X > 7) n4++;

            if(j - (B.X - 1) >= 0 && j + (B.X - 1) < B.N)
                plus = 2;
            else plus = 0;

            //cambiare i seguenti punteggi per dare pesi diversi alle varie sequenze trovate
            int score1 = 50;
            int score2 = 20;
            int score3 = 10;
            int score4 = 5;
            int scoreBlock1 = 150;   //blocco vittoria avversario

            return n1 * score1 + n2 * score2 + n3 * score3 + n4 * score4 + b1 * scoreBlock1 + plus;
        }
    }

    private int evaluate(CXBoard B, CXCellState[][] board) throws TimeoutException {
        checktime();
        if(B.gameState() == myWin)
            return 1000000;
        else if(B.gameState() == yourWin)
            return -1000000;
        else if(B.gameState() == CXGameState.DRAW)
            return 0;
        else if(hashtable.containsKey(board.toString())){
            return hashtable.get(board.toString());
        }
        else{
            //valutazione euristica di una situazione di gioco non finale
            // nell'eval assegno punteggi positivi per le sequenze di pedine del player massimizzante
            // e negativi per il minimizzante
            //CXCellState s = B[i][j];
            //CXCellState s = B[i][j];
            int n = 0;
            int n1 = 0,n2 = 0, n3 = 0, n4 = 0; //n1 = numero di sequenze di lunghezza X-1, X-2 per n2, X-3 per n3, X-4 per n4

            //Ottimizzo gli indici --> da fare
            //i = 0...ultima riga con pedina
            //j = prima colonna con pedina...ultima colonna con pedina
            int firstColumn = 0, lastColumn = B.N - 1; int lastRow = 0;

            for(int j = 0 ; j < B.N ; j++){
                if(board[0][j] != CXCellState.FREE)
                    break;
                else firstColumn++;
            }

            for(int j = B.N - 1 ; j >= 0 ; j--){
                if(board[0][j] != CXCellState.FREE)
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
                    for(i = 0; i < B.M && board[i][j] != CXCellState.FREE; i++){}
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
                        enter_check = (board[i][j-1] != board[i][j]);
                        condition1 = (board[i][j-1] == CXCellState.FREE);
                    }
                    else condition1 = false;
                    for(k = 1; enter_check && j+k < B.N && board[i][j+k] == board[i][j]; k++) n++;
                    if(j+k >= B.N){
                        condition2 = false;
                    }
                    else condition2 = (board[i][j+k] == CXCellState.FREE);
                    if(n == B.X - 1 && (condition1 || condition2)){
                        if(board[i][j] == maximizingCellState) n1++;
                        else n1--;
                    }
                    else if(n == B.X - 2 && (condition1 || condition2)){
                        if(board[i][j] == maximizingCellState) n2++;
                        else n2--;
                    }
                    else if(n == B.X - 3 && (condition1 || condition2) && B.X > 5){
                        if(board[i][j] == maximizingCellState) n3++;
                        else n3--;
                    }
                    else if(n == B.X - 4 && (condition1 || condition2) && B.X > 7){
                        if(board[i][j] == maximizingCellState) n4++;
                        else n4--;
                    }

                    //controllo verticale
                    enter_check = true;
                    condition1 = false;
                    condition2 = false;
                    n = 1;
                    if(i-1 >= 0){
                        enter_check = (board[i-1][j] != board[i][j]);
                        condition1 = (board[i-1][j] == CXCellState.FREE);
                    }
                    else condition1 = false;
                    for(k = 1; enter_check && i+k < B.M && board[i+k][j] == board[i][j]; k++) n++;
                    if(i+k >= B.M) {
                        condition2 = false;
                    }
                    else condition2 = (board[i+k][j] == CXCellState.FREE);
                    if(n == B.X - 1 && (condition1 || condition2)){
                        if(board[i][j] == maximizingCellState) n1++;
                        else n1--;
                    }
                    else if(n == B.X - 2 && (condition1 || condition2)){
                        if(board[i][j] == maximizingCellState) n2++;
                        else n2--;
                    }
                    else if(n == B.X - 3 && (condition1 || condition2) && B.X > 5){
                        if(board[i][j] == maximizingCellState) n3++;
                        else n3--;
                    }
                    else if(n == B.X - 4 && (condition1 || condition2) && B.X > 7){
                        if(board[i][j] == maximizingCellState) n4++;
                        else n4--;
                    }

                    //controllo diagonale
                    enter_check = true;
                    condition1 = false;
                    condition2 = false;
                    n = 1;
                    if(i-1 >= 0 && j-1 >= 0){
                        enter_check = (board[i-1][j-1] != board[i][j]);
                        condition1 = (board[i-1][j-1] == CXCellState.FREE);
                    }
                    else condition1 = false;
                    for(k = 1; enter_check && (i+k < B.M  && j+k < B.N ) && board[i+k][j+k] == board[i][j]; k++) n++;
                    if(i+k >= B.M || j+k >= B.N) {
                        condition2 = false;
                    }
                    else condition2 = (board[i+k][j+k] == CXCellState.FREE);
                    if(n == B.X - 1 && (condition1 || condition2)){
                        if(board[i][j] == maximizingCellState) n1++;
                        else n1--;
                    }
                    else if(n == B.X - 2 && (condition1 || condition2)){
                        if(board[i][j] == maximizingCellState) n2++;
                        else n2--;
                    }
                    else if(n == B.X - 3 && (condition1 || condition2) && B.X > 5){
                        if(board[i][j] == maximizingCellState) n3++;
                        else n3--;
                    }
                    else if(n == B.X - 4 && (condition1 || condition2) && B.X > 7){
                        if(board[i][j] == maximizingCellState) n4++;
                        else n4--;
                    }
                    //controllo anti-diagonale  (destra - sinistra /basso - alto)
                    enter_check = true;
                    condition1 = false;
                    condition2 = false;
                    n = 1;
                    if(i-1 >= 0 && j+1 < B.N){
                        enter_check = (board[i-1][j+1] != board[i][j]);
                        condition1 = (board[i-1][j+1] == CXCellState.FREE);
                    }
                    else condition1 = false;
                    for(k = 1; enter_check && (i+k < B.M  && j-k >= 0) && board[i+k][j-k] == board[i][j]; k++) n++;
                    if(i+k >= B.M || j-k < 0) {
                        condition2 = false;
                    }
                    else condition2 = (board[i+k][j-k] == CXCellState.FREE);
                    if(n == B.X - 1 && (condition1 || condition2)){
                        if(board[i][j] == maximizingCellState) n1++;
                        else n1--;
                    }
                    else if(n == B.X - 2 && (condition1 || condition2)){
                        if(board[i][j] == maximizingCellState) n2++;
                        else n2--;
                    }
                    else if(n == B.X - 3 && (condition1 || condition2) && B.X > 5){
                        if(board[i][j] == maximizingCellState) n3++;
                        else n3--;
                    }
                    else if(n == B.X - 4 && (condition1 || condition2) && B.X > 7){
                        if(board[i][j] == maximizingCellState) n4++;
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
            hashtable.put(board.toString(), eval);
            return eval;
        }
    }

    private int[] iterativeDeepening(CXBoard B, boolean isMax, int depth) throws TimeoutException  {
        int[] pair = new int[2];
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
		int out = 0;

        try {
            int depth;
            if(B.X > 5){
                depth = 5;
            }
            else depth = 15;
            out = iterativeDeepening(B, isMaximizing, depth)[1];       
        }
        catch (TimeoutException e) {}
        return out;
    }
}

