package com.chess;

public class GameMode {
    private final int modeNr;

    public static final int PLAYER_WHITE  = 1;
    public static final int PLAYER_BLACK  = 2;
    public static final int TWO_PLAYERS   = 3;
    public static final int ANALYSIS      = 4;
    public static final int TWO_COMPUTERS = 5;
    public static final int EDIT_GAME     = 6;
    public static final int TWO_PLAYERS_WHITE_REMOTE   = 7;
    public static final int TWO_PLAYERS_BLACK_REMOTE   = 8;
    

    public GameMode(int modeNr) {
        this.modeNr = modeNr;
    }

    public int getModeNr() {
        return modeNr;
    }

    /** Return true if white side is controlled by a human. */
    public final boolean playerWhite() {
        switch (modeNr) {
        case PLAYER_WHITE:
        case TWO_PLAYERS:
        case ANALYSIS:
        case EDIT_GAME:
        case TWO_PLAYERS_BLACK_REMOTE:
            return true;
        default:
            return false;
        }
    }

    /** Return true if black side is controlled by a human. */
    public final boolean playerBlack() {
        switch (modeNr) {
        case PLAYER_BLACK:
        case TWO_PLAYERS:
        case ANALYSIS:
        case EDIT_GAME:
        case TWO_PLAYERS_WHITE_REMOTE:	
            return true;
        default:
            return false;
        }
    }

    public final boolean analysisMode() {
        return modeNr == ANALYSIS;
    }
    
    /** Return true if it is a humans turn to move. */
    public final boolean humansTurn(boolean whiteMove) {
        return whiteMove ? playerWhite() : playerBlack();
    }

    /** Return true if the clocks are running. */
    public final boolean clocksActive() {
        switch (modeNr) {
        case PLAYER_WHITE:
        case PLAYER_BLACK:
        case TWO_PLAYERS:
        case TWO_COMPUTERS:
        case TWO_PLAYERS_WHITE_REMOTE:
        case TWO_PLAYERS_BLACK_REMOTE:
            return true;
        default:
            return false;    
        }
    }

    @Override
    public boolean equals(Object o) {
        if ((o == null) || (o.getClass() != this.getClass()))
            return false;
        GameMode other = (GameMode)o;
        return modeNr == other.modeNr;
    }

    @Override
    public int hashCode() {
        return modeNr;
    }
}
