package cpsc2150.connectX;
/**
 * Palmer Cone
 * CPSC2150 - 003
 * Homework 3 - ConnectX
 * 3/25/2020
 */

/**
 * This object will hold the gameboard and contains methods that take care of all placements and manipulation of the
 * board.
 * @Defines: maxRows: Z- the number of rows in the board.
 *           maxCols: Z- the number of columns in the board.
 *           board: char[][]- contains the gameboard that will be played on.
 * @Initialization Ensures: [maxRows will be set to the number of rows in the structure]
 *                          [maxCols will be set to the number of columns in the structure]
 *                          [board will be initialized]
 *                          [rows][cols]. The empty array positions will be a space ' '.]
 * @Constraints
 *              3 <= number of rows <= 100
 *              3 <= number of columns <= 100
 *              3 <= number to win <= 25 && number to win <= num columns || number to win <= num rows.
 *              board array values== ('X'||'O'||' ')
 *
 *
 *
 *
 */
public interface IGameBoard {
    /**
     * Boundaries for constructing IGameBoard
     */
    public static final int MAX_BOUNDS_ROW = 100;
    public static final int MIN_BOUNDS_ROW = 3;
    public static final int MAX_BOUNDS_COL = 100;
    public static final int MIN_BOUNDS_COL = 3;
    public static final int MAX_IN_A_ROW = 25;
    public static final int MIN_IN_A_ROW = 3;
    public static final int MAX_PLAYERS = 10;
    public static final int MIN_PLAYERS = 2;


    /**
     * Retrieves the number of rows from the instance of IGameBoard.
     * Precondition: this != null
     * Postcondition: getNumRows = rows
     * @return the number of rows in GameBoard.
     */
    public int getNumRows();

    /**
     * Retrieves the number of columns from the instance of IGameBoard.
     * @return maxCols
     * Precondition: this != null
     * Postcondition: returns maxCols
     */
    public int getNumColumns();

    /**
     * Retrieves the number of tokens in a row in order to result in a win.
     * @return the number of tokens in a row in gameboard.
     * Precondition: this != null
     * Postcondition: Returns winNum
     */
    public int getNumToWin();
    /**
     * Checks the board to see if there is room for a token to be placed.
     * @param c holds the value of the column in board that the method will check.
     * Precondition: [0 <= c < maxCols]
     * Precondition: this != null
     * Postcondition: returns true or false depending on whether or not there is room for a token to be placed.
     * @return true iff the spot at the top of the column c in board contains no player.
     *      else return false
     *
     */
    public default boolean checkIfFree(int c){
        int r=getNumRows();
        BoardPosition bp;
        bp= new BoardPosition(r-1,c);
        char a = whatsAtPos(bp);
        return ((whatsAtPos(bp)==' '));
    }
    /**
     * Checks to see if the most recently placed token resulted in a win
     * @param c holds the column location of the last token placed
     * Precondition: column is 0 <= col <= maxCols
     * Postcondition: returns true if a player has won, otherwise false
     * @return true iff either checkDiagWin(), checkVertWin(), or checkHorizWin().
     *               meaning, a player has reached numToWin chars in a row.
     *
     */
    public default boolean checkForWin(int c){
        int row = getNumRows()-1;
        boolean found=false;

        char play=' ';
        BoardPosition BP;

        BP = new BoardPosition(row,c);
        row--;

        while (!found){
            BP = new BoardPosition(row, c);
            if (whatsAtPos(BP)!=' '){
                found=true;
                play=whatsAtPos(BP);

            }
            else{
                if ( row == 0 ){
                    System.out.println("Position not found in checkForWin() in IGameBoard");
                    break;
                }
                row--;
            }

        }
        boolean over= (checkDiagWin(BP,play)||checkHorizWin(BP,play)
                ||checkVertWin(BP,play));


        return over;
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
    public void placeToken(char p, int c);
    /**
     * Determines if the player has won horizontally
     * @param pos holds location of last move
     * @param p holds the most recent player's character
     * Precondition: 'A' <= p <= 'Z'
     * Precondition: board != null
     * Precondition: 0 <= pos.row < maxRow
     * Precondition: 0 <= pos.col < maxCol
     * Postcondition: return
     * @return true iff the player has connected
     *          getNumToWin() characters horizontally
     */
    public default boolean checkHorizWin(BoardPosition pos, char p){
        int r;
        r = pos.getRow();
        int c;
        c = pos.getColumn();
        int count=1;
        BoardPosition temp;
        int i=c + 1;
        while (r<getNumRows()&&r>=0&&i<getNumColumns()&&i>=0) {
            temp = new BoardPosition(r,i);
            if (isPlayerAtPos(temp,p)) {
                count++;
                i++;
            }
            else{
                break;
            }
        }
        i=c-1;
        while (r<getNumRows()&&r>=0&&i<getNumColumns()&&i>=0){
            temp = new BoardPosition(r,i);
            if (isPlayerAtPos(temp,p)){
                count++;
                i--;
            }
            else{
                break;
            }
        }

        return count>=getNumToWin();
    }
    /**
     * Determines if the player has won vertically
     * @param pos holds location of last move
     * @param p holds the most recent player's character
     * Precondition: 'A' <= p <= 'Z'
     * Precondition: board != null
     * Precondition: 0 <= pos.row < maxRow
     * Precondition: 0 <= pos.col < maxCol
     * Postcondition: return
     * @return true iff the player has connected
     *          getNumToWin() characters vertically
     */
    public default boolean checkVertWin(BoardPosition pos, char p){
        int r=pos.getRow();
        int c = pos.getColumn();
        int count=1;
        int i=r + 1;
        BoardPosition temp;
        while (i<getNumRows()&&i>=0&&c<getNumColumns()&&c>=0) {
            temp = new BoardPosition(i,c);
            if (isPlayerAtPos(temp,p)) {
                count++;
                i++;
            }
            else{
                break;
            }
        }
        i=r-1;
        while (i<getNumRows()&&i>=0&&c<getNumColumns()&&c>=0){
            temp = new BoardPosition(i,c);
            if (isPlayerAtPos(temp,p)) {
                count++;
                i--;
            }
            else{
                break;
            }
        }

        return count>=getNumToWin();
    }
    /**
     * Determines if the player has won diagonally
     * @param pos holds location of last move
     * @param p holds the most recent player's character
     * Precondition: 'A' <= p <= 'Z'
     * Precondition: board != null
     * Precondition: 0 <= pos.row < getNumRows()
     * Precondition: 0 <= pos.col < getNumCols()
     * Postcondition: return true if the game was won diagonally
     * @return true iff the player has connected
     *          getNumToWin() characters diagonally
     */
    public default boolean checkDiagWin(BoardPosition pos, char p){
        int r=pos.getRow();
        int c = pos.getColumn();
        int count=1;
        int i=r + 1;
        int k=c + 1;
        BoardPosition bp;
        while ((i<getNumRows())&&(i>=0)&&(k<getNumColumns())&&(k>=0)) {
            bp= new BoardPosition(i,k);
            if (isPlayerAtPos(bp,p)) {
                count++;
                i++;
                k++;
            }
            else{
                break;
            }
        }
        i=r-1;
        k=c-1;
        while (i<getNumRows()&&i>=0&&k<getNumColumns()&&k>=0) {
            bp=new BoardPosition(i,k);
            if (isPlayerAtPos(bp,p)){
                count++;
                i--;
                k--;
            }
            else{
                break;
            }
        }
        if (count >=getNumToWin()){

            return true;
        }
        else {
            count =1;
            i = r + 1;
            k = c - 1;
            while (i < getNumRows() && i >= 0 && k < getNumColumns() && k >= 0) {
                bp=new BoardPosition(i,k);
                if (isPlayerAtPos(bp,p)){
                    count++;
                    i++;
                    k--;
                }
                else break;
            }
            i = r - 1;
            k = c + 1;
            while (i < getNumRows() && i >= 0 && k < getNumColumns() && k >= 0) {
                bp=new BoardPosition(i,k);
                if (isPlayerAtPos(bp,p)){
                    count++;
                    i--;
                    k++;
                }
                else{
                    break;
                }
            }


            return count>=getNumToWin();
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
    public char whatsAtPos(BoardPosition pos);
    /**
     * Determined
     * @param pos holds row & column being checked
     * @param  player holds the character of the player being checked
     * Precondition: 0 <= pos.getRow() < getNumRows()
     * Precondition: 0 <= pos.getCol() < getNumColumns()
     * Precondition: 'A' <= player <= 'Z'
     * Postcondition: true is returned iff player occupies pos
     * return player == whatsAtPos(pos)
     */
    public default boolean isPlayerAtPos(BoardPosition pos, char player){
        char c= whatsAtPos(pos);

        return c==player;
    }
    /**
     * Creates a formatted representation of the Gameboard
     * @return a formatted representation of the GameBoard.
     * Precondition: board != null
     * Postcondition: [toString is a string representation of the GameBoard]
     * Postcondition: return a formatted representation of the GameBoard.
     */
    public String toString();
    /**
     * Checks to see if the game has resulted in a tie.
     * Precondtion: board != null
     * Postcondition: returns true if the game ends in a tie else false
     * @return false is returned iff the highest row in every column of board is occupied,
     *              else true
     */
    public default boolean checkTie(){
        for (int i = 0; i < getNumColumns(); i++){
            if(checkIfFree(i)){
                return false;
            }
        }
        return true;
    }
}
