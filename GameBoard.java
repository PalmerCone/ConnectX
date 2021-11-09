
/**
 * Palmer Cone
 * CPSC2150 - 003
 * Homework 3 - ConnectX
 * 3/25/2020
 */

package cpsc2150.connectX;
/**
 * @invariants: board must be of size[rows][cols]
 *              maxRows==rows
 *              maxCols==cols
 *              Elements in board[][] must be ' ', 'X', or 'O'.
 */
public class GameBoard extends AbsGameBoard {
    private char[][] board;
    private int maxRows;
    private int maxCols;
    private int numToWin;
    /**
     * Parameterized constructor call for GameBoard
     * Precondition: MIN_BOUNDS_ROW <= gb.getRows() <= MAX_BOUNDS_ROW
     * Precondition:  MIN_BOUNDS_COL <= gb.getColumns() <= MAX_BOUNDS_COL
     * Precondition: MIN_IN_A_ROW <= gb.getNumToWin() <= MAX_IN_A_ROW
     * Postcondition: board is initialized
     * @param rows   contains the number of rows in the array
     * @param cols   contains the number of columns in the array
     * @param winNum contains the number of tokens in a row required to win.
     */
    public GameBoard(int rows,int cols,int winNum) {

        maxRows = rows;
        maxCols = cols;
        numToWin = winNum;
        char def = ' ';
        board = new char[maxRows][maxCols];

        for (int i =0; i <maxRows; i++){
            for (int j=0; j<maxCols;j++){

                board[i][j]=def;
            }
        }

    }
    /**
     * Retrieves the number of rows from the instance of IGameBoard.
     * Precondition: this != null
     * Postcondition: getNumRows = rows
     * @return the number of rows in GameBoard.
     */
    public int getNumRows(){
        return maxRows;
    }

    /**
     * Retrieves the number of columns from the instance of IGameBoard.
     * @return maxCols
     * Precondition: this != null
     * Postcondition: returns maxCols
     */
    public int getNumColumns(){
        return maxCols;
    }

    /**
     * Retrieves the number of tokens in a row in order to result in a win.
     * @return the number of tokens in a row in gameboard.
     * Precondition: this != null
     * Postcondition: Returns winNum
     */
    public int getNumToWin(){
        return numToWin;
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
        int i = 0;
        BoardPosition bp;
        while (i < maxRows) {
            bp = new BoardPosition(i, c);
            if (whatsAtPos(bp)==' ') {
                board[i][c] = p;
                break;
            }
            i += 1;

        }
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
    public char whatsAtPos(BoardPosition pos){
        int r= pos.getRow();
        int c = pos.getColumn();
        return board[r][c];
    }
}