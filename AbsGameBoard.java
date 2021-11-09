package cpsc2150.connectX;

public abstract class AbsGameBoard implements IGameBoard {
    /**
     * Creates a formatted representation of the Gameboard
     * @return a formatted representation of the GameBoard.
     * Precondition: board != null
     * Postcondition: [toString is a string representation of the GameBoard]
     * Postcondition: return a formatted representation of the GameBoard.
     * Postcondition: toString is overwritten.
     */
    @Override
    public String toString(){
        String s = "| ";
        for (int i = 0; i < getNumColumns(); i++){
            if ( i < 9 ) {
                s = s + i + "| ";
            }
            else{
                s = s + i + "|";
            }
        }
        BoardPosition bp;
        for (int i = getNumRows()-1 ; i>=0 ; i-- ){
            s = s + "\n| ";
            for (int j = 0; j < getNumColumns() ; j++){
                bp = new BoardPosition(i,j);
                s = s + whatsAtPos(bp) + "| ";
            }

        }
        return s;
    }

}
