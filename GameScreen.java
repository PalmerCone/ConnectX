package cpsc2150.connectX;
/**
 * Palmer Cone
 * CPSC2150 - 003
 * Homework 3 - ConnectX
 * 3/25/2020
 */
/**
 * @invariants: MIN_BOUNDS_COL <= board's number of columns <= MAX_BOUNDS_COL
 * @invariants: MIN_BOUNDS_ROW <= board's number of columns <= MAX_BOUNDS_ROW
 * @invariants: 'A' <= player <= 'Z'
 * @invariants: 'A' <= all elements in listPlayers <= 'Z'
 * @variables:
 * board: GameBoard - holds all values in play, may be declared as a map(memory efficient) or a char array (Fast)
 * turn: int [1] - keeps track of which player takes the next turn
 * col: int[1] - holds column value
 * listPlayers: char[MIN_PLAYERS .. MAX_PLAYERS]
 * play: bool[1] - returned value for if the user wants to play again
 * player: char[1] - keeps track of a player
 */
import java.util.Scanner;

import static cpsc2150.connectX.IGameBoard.*;

public class GameScreen {
    public static void main(String[] args){
        /**
         * Variable Declarations
         */
        boolean play;
        int col;
        int turn;
        char[] listPlayers;
        char player;
        IGameBoard board;
        int maxCols;

        do {
            /**
             * Retrieves a list of characters representing players
             */
            listPlayers = getList();
            /**
             * Creates an instance of IGameBoard, with customization options for the user
             */
            board= getBoard();
            /**
             * Retrieves the number of columns in GameBoard to check user input
             */
            maxCols=board.getNumColumns();
            /**
             * Sets turn to 0, indicating the start of the new game
             */
            turn = 0;
            do {

                col=-1;
                player=getPlayer(turn, listPlayers);
                turn++;
                System.out.println("It is Player "+player+"'s turn.");
                while (col>=maxCols||col<0) {
                    col = getCol();
                    /**
                     * Checks the users input against size restrictions and gives feedback
                     */
                    if (col>=maxCols){

                        System.out.println("Column number can not exceed "+ maxCols);
                        System.out.println("Column number must be greater than 0");

                    }
                    /**
                     * Checks the users input to see if the selected column is full and provides feedbac
                     */
                    else if (!board.checkIfFree(col)){
                        System.out.println("Column "+col+" is full.");
                        col=-1;

                    }
                    else{
                        /**
                         * Places the token in the board
                         */
                        board.placeToken(player,col);
                        /**
                         * Checks if the last placed token led to a win and lets the user know if it does.
                         */
                        if (board.checkForWin(col)){
                            System.out.println(player + " has won the game.");
                        }
                        /**
                         * Checks if the last placed token resulted in a tie and lets the user know if it does.
                         */
                        if (board.checkTie()){
                            System.out.println("The game was tied.");
                        }

                    }
                }
                /**
                 * Prints the board using toString.
                 */
                System.out.println(board.toString());
            } while (!board.checkForWin(col)&&!board.checkTie());
            /**
             * Asks user if they would like to play again. If they want to play again, the game is sent to the start.
             * if not, The program is let out of the loop and the main method terminates.
             */
            play=playAgain();
        } while (play);
    }
    /**
     * playAgain() requests a char from the user in order to determine if the program should loop again.
     * It is also case Insensitive allowing for 'Y' or 'y' for yes and 'N' or 'n' for no.
     * Postcondition: returns true or false
     * @return true iff the user decides they want to play again, else false.
     */
    public static boolean playAgain(){
        Scanner sc = new Scanner(System.in);
        char c='i';
        boolean loop=true;
        while (loop) {
            System.out.println("Would you like to play again? [Y]es or [N]o");
            c = sc.next().charAt(0);
            if (c=='Y'||c=='y') {
                loop=false;
                return true;
            }
            else if (c=='N'||c=='n'){
                loop=false;
                return false;
            }
            else
                System.out.println("Please type a acceptable character.");
        }
        return true;
    }

    /**
     * getList() asks the user for the desired number of players, checks if it is valid. if not,
     * then the user will be provided an error message and asked to try again. When correct input is given,
     * getList() will create a character array with the given size. Then, the user will be asked for more
     * characters to represent the players. The input will be checked to see if it is not a space and to ensure
     * that it is mutually exclusive with every character already in the array.
     * If it is a lower case character, it will be capitalized
     * Postcondition: returns a list of unique capital letters received by the user representing players
     * @return A list of unique capital characters representing players
     */
    public static char[] getList(){
        Scanner sc = new Scanner(System.in);
        Scanner sc2 = new Scanner(System.in);
        int numPlayers = 0;
        while (numPlayers > MAX_PLAYERS || numPlayers < MIN_PLAYERS) {
            System.out.println("How many players?");
            numPlayers = sc.nextInt();
            if (numPlayers > MAX_PLAYERS || numPlayers < MIN_PLAYERS){
                System.out.println("Number of players must be between " + MAX_PLAYERS+ " and "+MIN_PLAYERS+".");
            }
        }

        char[] players = new char[numPlayers];
        String s;
        char temp;
        boolean repeat = false;
        for (int i = 0; i < numPlayers; i++ ){
            players[i] = ' ';
            while (players[i] == ' ') {
                repeat = false;
                System.out.println("Enter the character to represent player " + (i+1));
                s = sc2.nextLine();
                temp = s.charAt(0);
                if (temp == 32){
                    repeat = true;
                }
                if (temp > 90) {
                    temp -= 32;
                }
                for (char p : players) {
                    if (p == temp){
                        repeat = true;
                        System.out.println("Invalid selection please pick a new character.");
                    }
                }
                if (repeat == false) {
                    players[i] = temp;
                }
            }
        }
        return players;
    }
    /**
     * getPlayer receives the number of turns made and a character array representing players.
     * It then returns the character for the player whose turn it is using modulus
     * @param turn- holds the number of turns that have been played.
     * @param players - contains an array of characters representing players.
     * Precondition: turn >= 0
     * Precontition: players.length > 0
     * Postcondition: returns the turn % size element of players
     * @return players[turn % players.length]
     *
     */

    public static char getPlayer(int turn, char[] players){
        int Size = players.length;
        return players[turn % Size];
    }

    /**
     * getBoard takes in user input and checks it in order to create a user customized game.
     * It also finds out how many tokens the user would like to get in a row in order to win the game.
     * Then, it asks the user to specify if they would like a memory efficient game (Map) or a fast game (array)
     * Postcondition: board exists
     * Postcondition: MIN_BOUNDS_ROW <= gb.getRows() <= MAX_BOUNDS_ROW
     * Postcondiion:  MIN_BOUNDS_COL <= gb.getColumns() <= MAX_BOUNDS_COL
     * Postcondition: MIN_IN_A_ROW <= gb.getNumToWin() <= MAX_IN_A_ROW
     * Postcondition: returns an instance of IGameBoard
     * Postcondition: the parameterized constructor for gb was called.
     * @return gb - a constructed gameboard
     */
    public static IGameBoard getBoard () {
        Scanner sc = new Scanner(System.in);
        IGameBoard gb;
        int r,c,tw;
        do {
            System.out.println("How many rows should be on the board?");
            r = sc.nextInt();
            if ( r > MAX_BOUNDS_ROW || r < MIN_BOUNDS_ROW){
                System.out.println("Please choose a number within the proper bounds.");
            }
        } while ( r > MAX_BOUNDS_ROW || r < MIN_BOUNDS_ROW);
        do {
            System.out.println("How many columns should be on the board?");
            c = sc.nextInt();
            if ( c > MAX_BOUNDS_COL || c < MIN_BOUNDS_COL){
                System.out.println("Please choose a number within the proper bounds.");
            }
        } while ( c > MAX_BOUNDS_ROW || c < MIN_BOUNDS_ROW);
        do {
            System.out.println("How many in a row to win?");
            tw = sc.nextInt();
            if ( tw > MAX_IN_A_ROW || tw < MIN_IN_A_ROW || tw > r || tw > c){
                System.out.println("Please choose a number within the proper bounds. That is less than the rows and " +
                         " columns.");
            }
        } while ( tw > MAX_IN_A_ROW || tw < MIN_IN_A_ROW || tw > r || tw > c);
        char choice= ' ';
        while ( choice != 'F' && choice != 'f' && choice != 'M' && choice != 'm'){
            System.out.println("Would you like a Fast Game (F/f) or a Memory Efficient Game (M/m?)");
            choice = sc.next().charAt(0);
        }
        if ( choice == 'F' || choice == 'f'){
            gb = new GameBoard(r,c,tw);
        }
        else{
            gb = new GameBoardMem(r,c,tw);
        }



        return gb;

    }
    /**
     * getCol asks the user for a column number that they would like to place the token into.
     * Postcondition: returns the users selected column
     * @return the column number the player wants to place a token.
     */
    public static int getCol(){
        System.out.println("What column would you like to place your token in?");
        Scanner sc = new Scanner(System.in);
        int rval =sc.nextInt();
        return rval;
    }



}
