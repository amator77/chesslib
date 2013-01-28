package com.chess;

import java.util.ArrayList;

import com.chess.gamelogic.Game;
import com.chess.gamelogic.Move;
import com.chess.gamelogic.Position;

public interface GUIInterface {

    /** Update the displayed board position. */
    public void setPosition(Position pos, String variantInfo, ArrayList<Move> variantMoves);

    /** Mark square sq as selected. Set to -1 to clear selection. */
    public void setSelection(int sq);

    final static class GameStatus {
        public Game.GameState state = Game.GameState.ALIVE;
        public int moveNr = 0;
        /** Move required to claim draw, or empty string. */
        public String drawInfo = "";
        public boolean white = false;
        public boolean ponder = false;
        public boolean thinking = false;
        public boolean analyzing = false;
    }

    /** Set the status text. */
    public void setStatus(GameStatus status);

    /** Update the list of moves. */
    public void moveListUpdated();

    /** Ask what to promote a pawn to. Should call reportPromotePiece() when done. */
    public void requestPromotePiece();

    /** Run code on the GUI thread. */
    public void runOnUIThread(Runnable runnable);

    /** Report that user attempted to make an invalid move. */
    public void reportInvalidMove(Move m);

    /** Report remaining thinking time to GUI. */
    public void setRemainingTime(long wTime, long bTime, long nextUpdate);

    /** Report a move made that is a candidate for GUI animation. */
    public void setAnimMove(Position sourcePos, Move move, boolean forward);

    /** Get the default player name. */
    public String whitePlayerName();
    
    /** Get the default player name. */
    public String blackPlayerName();

    /** Return true if only main-line moves are to be kept. */
    public boolean discardVariations();

	public void remoteMoveMade();
}
