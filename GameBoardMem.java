package cpsc2150.connectX;
import java.util.*;
/**
 * Palmer Cone
 * CPSC2150 - 003
 * Homework 3 - ConnectX
 * 3/25/2020
 */
public class GameBoardMem extends AbsGameBoard {
    private int maxRows, maxCols, numToWin;
    private Map<Character, List<BoardPosition>> board;

    /**
     * Parameterized constructor call for GameBoardMem
     * Precondition: MIN_BOUNDS_ROW <= gb.getRows() <= MAX_BOUNDS_ROW
     * Precondition:  MIN_BOUNDS_COL <= gb.getColumns() <= MAX_BOUNDS_COL
     * Precondition: MIN_IN_A_ROW <= gb.getNumToWin() <= MAX_IN_A_ROW
     * Postcondition: board is initialized
     * @param rows   contains the number of rows in the map to be permitted
     * @param cols   contains the number of columns in the map to be permitted
     * @param winNum contains the number of tokens in a row required to win.
     */

    GameBoardMem(int rows, int cols, int winNum) {
        board = new HashMap<Character, List<BoardPosition>>();
        maxRows = rows;
        maxCols = cols;
        numToWin = winNum;
    }

    /**
     * Retrieves the number of rows from the instance of IGameBoard.
     * Precondition: this != null
     * Postcondition: getNumRows = rows
     * @return the number of rows in GameBoard.
     */
    public int getNumRows() {
        return maxRows;
    }

    /**
     * Retrieves the number of columns from the instance of IGameBoard.
     * @return maxCols
     * Precondition: this != null
     * Postcondition: returns maxCols
     */
    public int getNumColumns() {
        return maxCols;
    }

    /**
     * Retrieves the number of tokens in a row in order to result in a win.
     * @return the number of tokens in a row in gameboard.
     * Precondition: this != null
     * Postcondition: Returns winNum
     */
    public int getNumToWin() {
        return numToWin;
    }

    /**
     * Overrides default method for determining if the player is at a location using Map functions
     * @param pos holds row & column of location being checked
     * @param player hold the player being checked
     * Precondition: pos follows restrictions for BoardPosition
     * Precondtiion: player exists in m
     * Postcondition: true is returned if player occupies space in board else false
     * @return true iff player is at pos
     */
    @Override
    public boolean isPlayerAtPos(BoardPosition pos, char player) {
        for (Map.Entry<Character, List<BoardPosition>> m : board.entrySet()) {
            if (m.getKey() == player) {
                for (BoardPosition i : m.getValue()) {
                    if (i.equals(pos)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    /**
     * Retrieves the value at pos
     * @param pos holds the row & column being checked
     * Precondition: board != null
     * Precondition: 0<= pos.getRow() <getNumRows()
     * Precondition: 0<= pos.getColumn() < getNumColumns()
     * @return the player character at pos or ' ' if there is no player
     * Postcondition: return [whatsAtPos = the character in board pos is referring to]
     * and ['A' <= whatsAtPos(pos) <= 'Z' or ' '.
     */
    public char whatsAtPos(BoardPosition pos) {
        if (!board.isEmpty()){
            for (Map.Entry<Character, List<BoardPosition>> m : board.entrySet()) {
                for (BoardPosition bp : m.getValue()) {
                    if (bp.equals(pos)) {
                        return m.getKey();
                    }
                }
            }
        }
        return ' ';
    }

    /**
     * Places a token at the lowest empty spot in c
     * @param p has the players char
     * @param c holds the value of column the method places a letter in
     * Precondition: 'A' <= p <= 'Z'
     * Precondition: board != null
     * Precondition: 0 <= c < maxCols
     * Precondition: column c is not full
     * Postcondition: p was placed at the lowest available row in column c in board
     *
     */
    public void placeToken(char p, int c) {
        BoardPosition pos;
        int i = 0;
        if (!board.isEmpty()) {
            for (Map.Entry<Character, List<BoardPosition>> m : board.entrySet()) {
                for (BoardPosition bp : m.getValue()) {
                    if (bp.getColumn() == c) {
                        i++;
                    }
                }
            }
        }
        pos = new BoardPosition(i,c);
        if (board.isEmpty()||!board.containsKey(p)){
            List<BoardPosition> list = new ArrayList<BoardPosition>();
            list.add(pos);
            board.put(p,list);
        }
        else {
            board.get(p).add(pos);
        }

    }
}





