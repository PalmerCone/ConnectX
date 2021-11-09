package cpsc2150.connectX;
/**
 * Palmer Cone
 * CPSC2150 - 003
 * Homework 3 - ConnectX
 * 3/1/2020
 */
/**
 * @defines:  row Z- the row value that the instance of BoardPosition is holding
 *            col Z- the column value that the instance of BoardPosition is holding
 * @invariants: 0 <= row <=rows and 0 <= col <= cols
 *
 */
public class BoardPosition{
    int row,col;

    /**
     *
     * @param row - holds row value being set
     * @param col - holds column value being set
     * @pre 0 <= row < rows
     *      0 <= col < cols
     * @post this.row = row
     *       this.col = col
     */
    public BoardPosition(int row, int col){
        this.row=row;
        this.col=col;
    }
    /**
     *
     * @pre this != null
     * @post getRow= row
     *
     * @return the row value in board that BoardPosition is referring to
     *
     */

    public int getRow(){
        return row;
    }
/**
 * @pre this != null
 * @post getColumn = col
 *
 * @return the column value in board that BoardPosition is referring to
 *
 */
    public int getColumn(){
        //returns the column
        return col;
    }
    /**
     * @param bp the instance of BoardPosition being compared to this.
     * @pre: this != null and
     *       bp is an instance of BoardPosition and bp != null
     * @post: equals = (bp==this)
     * @return true iff bp and this are the same.
     *
     */
    public boolean equals(BoardPosition bp){
        return (bp.getRow() == this.row&& bp.getColumn()==this.col);


    }
    /**
     *
     * @return: a formatted representation of the point.
     * @pre: board != null
     * @post: [toString is a string representation of the BoardPosition In form (ROW,COL)]
     */
    @Override
    public String toString(){
        String s = "("+row+","+col+")";
        return s;
    }

  //  }
}
