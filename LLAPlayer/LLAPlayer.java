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

 package connectx.LLAPlayer;

 import connectx.CXPlayer;
 import connectx.CXBoard;
 import connectx.CXGameState;
 import connectx.CXCell;
import connectx.CXCellState;

import java.util.TreeSet;
 import java.util.Random;
 import java.util.Arrays;
 import java.util.concurrent.TimeoutException;
 
 /**
  * Software player only a bit smarter than random.
  * <p>
  * It can detect a single-move win or loss. In all the other cases behaves
  * randomly.
  * </p>
  */
 public class LLAPlayer implements CXPlayer {
     private Random rand;
     private CXGameState myWin;
     private CXGameState yourWin;
     private CXCellState maximizingCellState;
     private int  TIMEOUT;
     private long START;
 
     /* Default empty constructor */
     public LLAPlayer() {
     }
 
     public void initPlayer(int M, int N, int K, boolean first, int timeout_in_secs) {
         // New random seed for each game
         rand    = new Random(System.currentTimeMillis());
         myWin   = first ? CXGameState.WINP1 : CXGameState.WINP2;
         yourWin = first ? CXGameState.WINP2 : CXGameState.WINP1;
         maximizingCellState = first ? CXCellState.P1 : CXCellState.P2;
         TIMEOUT = timeout_in_secs;
     }

     private void checktime() throws TimeoutException {
		if ((System.currentTimeMillis() - START) / 1000.0 >= TIMEOUT * (99.0 / 100.0))
			throw new TimeoutException();
	}

    public String playerName() {
        return "LLA";
    }

    private int[] AlphaBeta_Pruning(CXBoard B, boolean isMax, int alpha, int beta, int depth){
        int[] pair = new int[2];
        if(depth == 0 || B.gameState() != CXGameState.OPEN){
            pair[0] = evaluate(B);
            pair[1] = B.getLastMove().j;
            return pair;
        }
        else if(isMax){
            pair[0] = -1000000;
            for(int i : B.getAvailableColumns()){ //possibile euristica sull'ordinamento
                B.markColumn(i);
                int tmp = AlphaBeta_Pruning(B, false, alpha, beta, depth-1)[0];
                if (pair[0] < tmp){
                    pair[0] = tmp;
                    pair[1] = i;
                }
                B.unmarkColumn();
                if(alpha >= beta){      //cutoff Beta
                    break;
                }
            }
            return pair;
        }
        else{
            pair[0] = 1000000;
            for(int i : B.getAvailableColumns()){ //possibile euristica sull'ordinamento
                B.markColumn(i);
                int tmp = AlphaBeta_Pruning(B, true, alpha, beta, depth-1)[0];
                if (pair[0] > tmp){
                    pair[0] = tmp;
                    pair[1] = i;
                }
                B.unmarkColumn();
                if(alpha >= beta){      //cutoff Alpha
                    break;
                }
            } 
            return pair;
        }
    }

    private int[] iterativeDeepening(CXBoard B, boolean isMax, int depth){
        int[] pair = new int[2];
        int alpha = -1000000, beta = 1000000;

        try {
            for(int i = 0; i < depth; i++){
                checktime();
                pair = AlphaBeta_Pruning(B, isMax, alpha, beta, i);
            }
        }catch (TimeoutException e) {
			System.err.println("Timeout!!! Random column selected");
			return pair;
		}

        return pair;
    }

    private int evaluate(CXBoard B) {
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
            return 0;
        }
    //return 0;
    }


    @Override
    public int selectColumn(CXBoard B) {
        return iterativeDeepening(B, false, TIMEOUT)[1];
    }
}
 