package cpsc2150.connectX;

import org.junit.Assert;
import org.junit.Test;

public class TestGameBoard {
    private IGameBoard GBFactory(){
        return new GameBoard(5,5, 4);
    }
    private IGameBoard GBFactory(int row, int col, int winNum){
        return new GameBoard(row, col, winNum);
    }
    private char[][] makeEmptyArr(char[][] arr) {
        for (int i = 0; i < arr.length; i++ ) {
            for (int j = 0; j < arr[i].length; j++) {
                arr[i][j] = ' ';
            }
        }
        return arr;
    }
    private String BoardString(char [][] arr){
        String s = "| ";
        for (int i = 0; i < arr[0].length; i++){
            if ( i < 9 ) {
                s = s + i + "| ";
            }
            else{
                s = s + i + "|";
            }
        }
        for (int i = arr.length - 1; i>=0 ; i-- ){
            s = s + "\n| ";
            for (int j = 0; j < arr[0].length ; j++){

                s = s + arr[i][j] + "| ";
            }

        }
        return s;
    }

    @Test
    public void testConstructor_maxRows_maxCols_maxNumToWin(){
        int rows = 100;
        int cols = 100;
        int num2Win = 25;
        IGameBoard gb = GBFactory(rows, cols, num2Win);
        char[][] arr = new char [rows][cols];
        arr = makeEmptyArr(arr);
        String s1 = BoardString(arr);
        String s2 = gb.toString();
        Assert.assertTrue(s2.equals(s1));
    }
    @Test
    public void testConstructor_maxRows_minCols_maxNumToWin(){
        int rows = 100;
        int cols = 3;
        int num2Win = 25;
        IGameBoard gb = GBFactory(rows, cols, num2Win);
        char[][] arr = new char [rows][cols];
        arr = makeEmptyArr(arr);
        String s1 = BoardString(arr);
        String s2 = gb.toString();
        Assert.assertTrue(s2.equals(s1));
    }

    @Test
    public void testConstructor_minRows_minCols_minWinNum(){
        int rows = 3;
        int cols = 3;
        int num2Win = 3;
        IGameBoard gb = GBFactory(rows, cols, num2Win);
        char[][] arr = new char [rows][cols];
        arr = makeEmptyArr(arr);
        String s1 = BoardString(arr);
        String s2 = gb.toString();
        Assert.assertTrue(s2.equals(s1));
    }
    @Test
    public void test_checkIfFree_EmptyCol(){
        IGameBoard gb = GBFactory();
        String s = gb.toString();
        boolean bool = gb.checkIfFree(3);
        Assert.assertTrue(bool && s.equals(gb.toString()));
    }
    @Test
    public void test_checkIfFree_LastEmptyRow(){
        IGameBoard gb = GBFactory();
        for (int i = 0 ; i < 4; i++){
            gb.placeToken('W', 3);
        }
        String s = gb.toString();
        boolean bool = gb.checkIfFree(3);
        Assert.assertTrue(bool && s.equals(gb.toString()));
    }
    @Test
    public void test_checkIfFree_NoRoom(){
        IGameBoard gb = GBFactory();

        for (int i = 0 ; i <= 4; i++){
            gb.placeToken('W', 3);
        }
        String s = gb.toString();
        boolean bool = gb.checkIfFree(3);

        Assert.assertTrue(!bool && s.equals(gb.toString()));
    }


    @Test
    public void test_placeToken_EmptyCol(){
        IGameBoard gb = GBFactory();
        char[][] arr = new char[5][5];
        arr = makeEmptyArr(arr);
        arr[0][2] = 'W';
        gb.placeToken('W', 2);
        String s = gb.toString();
        String s2 = BoardString(arr);

        Assert.assertTrue(s.equals(s2));
    }
    @Test
    public void test_placeToken_MiddleRow() {
        IGameBoard gb = GBFactory();
        char[][] arr = new char[5][5];
        arr = makeEmptyArr(arr);

        for (int i = 0 ; i < 3; i++){
            arr[i][2] = 'W';
            gb.placeToken('W', 2);
        }
        boolean conditions = false;

        arr[3][2] = 'W';
        BoardPosition bp = new BoardPosition(3,2);
        if (gb.whatsAtPos(bp) == ' '){
            gb.placeToken('W',2);
            if (gb.whatsAtPos(bp) == 'W'){
                conditions = true;
            }
        }
        String s = gb.toString();
        String s2 = BoardString(arr);
        Assert.assertTrue(s.equals(s2) && conditions);
    }
    @Test
    public void test_placeToken_lastRow() {
        IGameBoard gb = GBFactory();
        char[][] arr = new char[5][5];
        arr = makeEmptyArr(arr);

        for (int i = 0 ; i < 4; i++){
            arr[i][2] = 'W';
            gb.placeToken('W', 2);
        }
        boolean conditions = false;
        arr[4][2] = 'W';
        BoardPosition bp = new BoardPosition(4,2);
        if (gb.whatsAtPos(bp) == ' '){
            gb.placeToken('W',2);
            if (gb.whatsAtPos(bp) == 'W'){
                conditions = true;
            }
        }
        String s = gb.toString();
        String s2 = BoardString(arr);
        Assert.assertTrue(s.equals(s2) && conditions);
    }
    @Test
    public void test_placeToken_onDifferentToken() {
        IGameBoard gb = GBFactory();
        char[][] arr = new char[5][5];
        arr = makeEmptyArr(arr);

        gb.placeToken('W', 2);
        arr[0][2] = 'W';


        boolean conditions = false;
        arr[1][2] = 'O';
        BoardPosition bp = new BoardPosition(1,2);
        if (gb.whatsAtPos(bp) == ' '){
            gb.placeToken('O',2);
            if (gb.whatsAtPos(bp) == 'O'){
                conditions = true;
            }
        }
        String s = gb.toString();
        String s2 = BoardString(arr);
        Assert.assertTrue(s.equals(s2) && conditions);
    }
    @Test
    public void test_placeToken_nextToToken() {
        IGameBoard gb = GBFactory();
        char[][] arr = new char[5][5];
        arr = makeEmptyArr(arr);

        gb.placeToken('X', 1);
        arr[0][1] = 'X';
        gb.placeToken('Z', 3);
        arr[0][3] = 'Z';


        boolean conditions = false;
        arr[0][2] = 'O';
        BoardPosition bp = new BoardPosition(0,2);
        if (gb.whatsAtPos(bp) == ' '){
            gb.placeToken('O',2);
            if (gb.whatsAtPos(bp) == 'O'){
                conditions = true;
            }
        }
        String s = gb.toString();
        String s2 = BoardString(arr);
        Assert.assertTrue(s.equals(s2) && conditions);
    }
    @Test
    public void test_whatsAtPos_Empty(){
        IGameBoard gb = GBFactory();
        BoardPosition bp = new BoardPosition(0 , 2);
        String s = gb.toString();
        char a = gb.whatsAtPos(bp);
        String s2 = gb.toString();
        Assert.assertTrue(a == ' ' && s.equals(s2));
    }
    @Test
    public void test_whatsAtPos_AboveToken(){
        IGameBoard gb = GBFactory();
        gb.placeToken('X',2);
        BoardPosition bp = new BoardPosition(1 , 2);
        String s = gb.toString();
        char a = gb.whatsAtPos(bp);
        String s2 = gb.toString();
        Assert.assertTrue(a == ' ' && s.equals(s2));
    }
    @Test
    public void test_whatsAtPos_Sides(){
        IGameBoard gb = GBFactory();
        gb.placeToken('O',1);
        gb.placeToken('O',1);
        gb.placeToken('X',2);
        gb.placeToken('O',3);
        gb.placeToken('O',3);
        BoardPosition bp = new BoardPosition(0 , 2);
        String s = gb.toString();
        char a = gb.whatsAtPos(bp);
        String s2 = gb.toString();
        Assert.assertTrue(a == 'X' && s.equals(s2));
    }
    @Test
    public void test_whatsAtPos_TopFullColumn(){
        IGameBoard gb = GBFactory();
        gb.placeToken('O',1);
        gb.placeToken('O',1);
        gb.placeToken('O',2);
        gb.placeToken('O',2);
        gb.placeToken('O',2);
        gb.placeToken('O',2);
        gb.placeToken('X',2);
        gb.placeToken('O',3);
        gb.placeToken('O',3);
        BoardPosition bp = new BoardPosition(4 , 2);
        String s = gb.toString();
        char a = gb.whatsAtPos(bp);
        String s2 = gb.toString();
        Assert.assertTrue(a == 'X' && s.equals(s2));
    }
    @Test
    public void test_whatsAtPos_BottomFullColumn(){
        IGameBoard gb = GBFactory();
        gb.placeToken('O',1);
        gb.placeToken('O',1);
        gb.placeToken('X',2);
        gb.placeToken('O',2);
        gb.placeToken('O',2);
        gb.placeToken('O',2);
        gb.placeToken('O',2);
        gb.placeToken('O',3);
        gb.placeToken('O',3);
        BoardPosition bp = new BoardPosition(0 , 2);
        String s = gb.toString();
        char a = gb.whatsAtPos(bp);
        String s2 = gb.toString();
        Assert.assertTrue(a == 'X' && s.equals(s2));
    }
    @Test
    public void test_isPlayerAtPos_NothingAtPosition(){
        IGameBoard gb = GBFactory();
        boolean PaP;// PlayeratPosition


        BoardPosition bp = new BoardPosition(0 , 2);
        String s = gb.toString();
        PaP = gb.isPlayerAtPos(bp, 'X');
        String s2 = gb.toString();
        Assert.assertTrue(!PaP && s.equals(s2));
    }
    @Test
    public void test_isPlayerAtPos_OtherAtPosition(){
        IGameBoard gb = GBFactory();
        boolean PaP;// PlayeratPosition
        gb.placeToken('O',2);

        BoardPosition bp = new BoardPosition(0 , 2);
        String s = gb.toString();
        PaP = gb.isPlayerAtPos(bp, 'X');
        String s2 = gb.toString();
        Assert.assertTrue(!PaP && s.equals(s2));
    }
    @Test
    public void test_isPlayerAtPos_TokensNearPos(){
        IGameBoard gb = GBFactory();
        boolean PaP;// PlayeratPosition
        gb.placeToken('X',3);
        gb.placeToken('X',1);


        BoardPosition bp = new BoardPosition(0 , 2);
        String s = gb.toString();
        PaP = gb.isPlayerAtPos(bp, 'X');
        String s2 = gb.toString();
        Assert.assertTrue(!PaP && s.equals(s2));
    }
    @Test
    public void test_isPlayerAtPos_Surrounded(){
        IGameBoard gb = GBFactory();
        boolean PaP;// PlayeratPosition
        gb.placeToken('O',3);
        gb.placeToken('O',1);
        gb.placeToken('X',2);
        gb.placeToken('O',2);


        BoardPosition bp = new BoardPosition(0 , 2);
        String s = gb.toString();
        PaP = gb.isPlayerAtPos(bp, 'X');
        String s2 = gb.toString();
        Assert.assertTrue(PaP && s.equals(s2));
    }
    @Test
    public void test_isPlayerAtPos_Middle(){
        IGameBoard gb = GBFactory();
        boolean PaP;// PlayeratPosition
        gb.placeToken('O',2);
        gb.placeToken('O',2);
        gb.placeToken('X',2);
        gb.placeToken('O',2);
        gb.placeToken('O',2);


        BoardPosition bp = new BoardPosition(2 , 2);
        String s = gb.toString();
        PaP = gb.isPlayerAtPos(bp, 'X');
        String s2 = gb.toString();
        Assert.assertTrue(PaP && s.equals(s2));
    }
    @Test
    public void test_checkTie_AllFull(){
        IGameBoard gb = GBFactory();
        boolean PaP;// PlayeratPosition
        gb.placeToken('A',2);
        gb.placeToken('B',2);
        gb.placeToken('C',2);
        gb.placeToken('D',2);
        gb.placeToken('E',2);
        gb.placeToken('A',4);
        gb.placeToken('B',4);
        gb.placeToken('C',4);
        gb.placeToken('D',4);
        gb.placeToken('E',4);
        gb.placeToken('F',1);
        gb.placeToken('G',1);
        gb.placeToken('H',1);
        gb.placeToken('I',1);
        gb.placeToken('J',1);
        gb.placeToken('F',3);
        gb.placeToken('G',3);
        gb.placeToken('H',3);
        gb.placeToken('I',3);
        gb.placeToken('J',3);
        gb.placeToken('F',0);
        gb.placeToken('G',0);
        gb.placeToken('H',0);
        gb.placeToken('I',0);
        gb.placeToken('J',0);



        BoardPosition bp = new BoardPosition(2 , 2);
        String s = gb.toString();
        PaP =  gb.checkTie();
        String s2 = gb.toString();
        Assert.assertTrue(PaP && s.equals(s2));
    }
    @Test
    public void test_checkTie_RightFree(){
        IGameBoard gb = GBFactory();
        boolean PaP;// PlayeratPosition
        gb.placeToken('A',2);
        gb.placeToken('B',2);
        gb.placeToken('C',2);
        gb.placeToken('D',2);
        gb.placeToken('E',2);
        gb.placeToken('A',4);
        gb.placeToken('B',4);
        gb.placeToken('C',4);
        gb.placeToken('D',4);

        gb.placeToken('F',1);
        gb.placeToken('G',1);
        gb.placeToken('H',1);
        gb.placeToken('I',1);
        gb.placeToken('J',1);
        gb.placeToken('F',3);
        gb.placeToken('G',3);
        gb.placeToken('H',3);
        gb.placeToken('I',3);
        gb.placeToken('J',3);
        gb.placeToken('F',0);
        gb.placeToken('G',0);
        gb.placeToken('H',0);
        gb.placeToken('I',0);
        gb.placeToken('J',0);



        BoardPosition bp = new BoardPosition(2 , 2);
        String s = gb.toString();
        PaP =  gb.checkTie();
        String s2 = gb.toString();
        Assert.assertTrue(!PaP && s.equals(s2));
    }
    @Test
    public void test_checkTie_MiddleFree(){
        IGameBoard gb = GBFactory();
        boolean PaP;// PlayeratPosition
        gb.placeToken('A',2);
        gb.placeToken('B',2);
        gb.placeToken('C',2);
        gb.placeToken('D',2);

        gb.placeToken('A',4);
        gb.placeToken('B',4);
        gb.placeToken('C',4);
        gb.placeToken('D',4);
        gb.placeToken('E',4);
        gb.placeToken('F',1);
        gb.placeToken('G',1);
        gb.placeToken('H',1);
        gb.placeToken('I',1);
        gb.placeToken('J',1);
        gb.placeToken('F',3);
        gb.placeToken('G',3);
        gb.placeToken('H',3);
        gb.placeToken('I',3);
        gb.placeToken('J',3);
        gb.placeToken('F',0);
        gb.placeToken('G',0);
        gb.placeToken('H',0);
        gb.placeToken('I',0);
        gb.placeToken('J',0);



        BoardPosition bp = new BoardPosition(2 , 2);
        String s = gb.toString();
        PaP =  gb.checkTie();
        String s2 = gb.toString();
        Assert.assertTrue(!PaP && s.equals(s2));
    }
    @Test
    public void test_checkTie_LeftFree(){
        IGameBoard gb = GBFactory();
        boolean PaP;// PlayeratPosition
        gb.placeToken('A',2);
        gb.placeToken('B',2);
        gb.placeToken('C',2);
        gb.placeToken('D',2);
        gb.placeToken('E',2);
        gb.placeToken('A',4);
        gb.placeToken('B',4);
        gb.placeToken('C',4);
        gb.placeToken('D',4);
        gb.placeToken('E',4);
        gb.placeToken('F',1);
        gb.placeToken('G',1);
        gb.placeToken('H',1);
        gb.placeToken('I',1);
        gb.placeToken('J',1);
        gb.placeToken('F',3);
        gb.placeToken('G',3);
        gb.placeToken('H',3);
        gb.placeToken('I',3);
        gb.placeToken('J',3);
        gb.placeToken('F',0);
        gb.placeToken('G',0);
        gb.placeToken('H',0);
        gb.placeToken('I',0);


        String s = gb.toString();
        PaP =  gb.checkTie();
        String s2 = gb.toString();
        Assert.assertTrue(!PaP && s.equals(s2));
    }
    @Test
    public void test_checkHorizWin_RightToLeft(){
        IGameBoard gb = GBFactory();
        gb.placeToken('O',1);
        gb.placeToken('X',1);
        gb.placeToken('O',2);
        gb.placeToken('X',2);
        gb.placeToken('X',3);
        gb.placeToken('X',3);
        gb.placeToken('O',4);
        gb.placeToken('X',4);;
        boolean winCheck;
        BoardPosition bp = new BoardPosition(1 , 4);
        String s = gb.toString();
        winCheck = gb.checkHorizWin(bp,'X');
        String s2 = gb.toString();
        Assert.assertTrue(winCheck && s.equals(s2));
    }
    @Test
    public void test_checkHorizWin_LeftToRight(){
        IGameBoard gb = GBFactory();
        gb.placeToken('O',1);
        gb.placeToken('X',1);
        gb.placeToken('O',2);
        gb.placeToken('X',2);
        gb.placeToken('X',3);
        gb.placeToken('X',3);
        gb.placeToken('O',4);
        gb.placeToken('X',4);;
        boolean winCheck;
        BoardPosition bp = new BoardPosition(1 , 1);
        String s = gb.toString();
        winCheck = gb.checkHorizWin(bp,'X');
        String s2 = gb.toString();
        Assert.assertTrue(winCheck && s.equals(s2));
    }
    @Test
    public void test_checkHorizWin_Middle(){
        IGameBoard gb = GBFactory();
        gb.placeToken('O',1);
        gb.placeToken('X',1);
        gb.placeToken('O',2);
        gb.placeToken('X',2);
        gb.placeToken('X',3);
        gb.placeToken('X',3);
        gb.placeToken('O',4);
        gb.placeToken('X',4);;
        boolean winCheck;
        BoardPosition bp = new BoardPosition(1 , 2);
        String s = gb.toString();
        winCheck = gb.checkHorizWin(bp,'X');
        String s2 = gb.toString();
        Assert.assertTrue(winCheck && s.equals(s2));
    }
    @Test
    public void test_checkHorizWin_Disconnected(){
        IGameBoard gb = GBFactory();
        gb.placeToken('O',0);
        gb.placeToken('X',0);
        gb.placeToken('O',1);
        gb.placeToken('X',1);
        gb.placeToken('O',2);
        gb.placeToken('X',2);
        gb.placeToken('X',3);
        gb.placeToken('0',3);
        gb.placeToken('O',4);
        gb.placeToken('X',4);;

        boolean winCheck;
        BoardPosition bp = new BoardPosition(1 , 2);
        String s = gb.toString();
        winCheck = gb.checkHorizWin(bp,'X');
        String s2 = gb.toString();
        Assert.assertTrue(!winCheck && s.equals(s2));
    }
    @Test
    public void test_checkVertWin_TopToBottom(){
        IGameBoard gb = GBFactory();
        gb.placeToken('X',0);
        gb.placeToken('O',1);
        gb.placeToken('X',0);
        gb.placeToken('O',1);
        gb.placeToken('X',0);
        gb.placeToken('X',1);
        gb.placeToken('X',0);
        gb.placeToken('O',1);

        boolean winCheck;
        BoardPosition bp = new BoardPosition(3 , 0);
        String s = gb.toString();
        winCheck = gb.checkVertWin(bp,'X');
        String s2 = gb.toString();
        Assert.assertTrue(winCheck && s.equals(s2));
    }
    @Test
    public void test_checkVertWin_BottomToTop(){
        IGameBoard gb = GBFactory();
        gb.placeToken('X',0);
        gb.placeToken('O',1);
        gb.placeToken('X',0);
        gb.placeToken('O',1);
        gb.placeToken('X',0);
        gb.placeToken('X',1);
        gb.placeToken('X',0);
        gb.placeToken('O',1);

        boolean winCheck;
        BoardPosition bp = new BoardPosition(0 , 0);
        String s = gb.toString();
        winCheck = gb.checkVertWin(bp,'X');
        String s2 = gb.toString();
        Assert.assertTrue(winCheck && s.equals(s2));
    }
    @Test
    public void test_checkVertWin_Middle(){
        IGameBoard gb = GBFactory();
        gb.placeToken('X',0);
        gb.placeToken('O',1);
        gb.placeToken('X',0);
        gb.placeToken('O',1);
        gb.placeToken('X',0);
        gb.placeToken('X',1);
        gb.placeToken('X',0);
        gb.placeToken('O',1);

        boolean winCheck;
        BoardPosition bp = new BoardPosition(2 , 0);
        String s = gb.toString();
        winCheck = gb.checkVertWin(bp,'X');
        String s2 = gb.toString();
        Assert.assertTrue(winCheck && s.equals(s2));
    }
    @Test
    public void test_checkVertWin_Disconnected(){
        IGameBoard gb = GBFactory();
        gb.placeToken('X',0);
        gb.placeToken('O',1);
        gb.placeToken('X',0);
        gb.placeToken('O',1);
        gb.placeToken('X',0);
        gb.placeToken('X',1);
        gb.placeToken('O',0);
        gb.placeToken('X',0);

        boolean winCheck;
        BoardPosition bp = new BoardPosition(2 , 0);
        String s = gb.toString();
        winCheck = gb.checkVertWin(bp,'X');
        String s2 = gb.toString();
        Assert.assertTrue(!winCheck && s.equals(s2));
    }

    @Test
    public void test_checkDiagWin_TopLeftBottomRight(){
        IGameBoard gb = GBFactory();
        gb.placeToken('O',0);
        gb.placeToken('O',0);
        gb.placeToken('O',0);
        gb.placeToken('X',0);
        gb.placeToken('O',1);
        gb.placeToken('O',1);
        gb.placeToken('X',1);
        gb.placeToken('O',2);
        gb.placeToken('X',2);
        gb.placeToken('X',3);

        boolean winCheck;
        BoardPosition bp = new BoardPosition(3 , 0);
        String s = gb.toString();
        winCheck = gb.checkDiagWin(bp,'X');
        String s2 = gb.toString();
        Assert.assertTrue(winCheck && s.equals(s2));
    }
    @Test
    public void test_checkDiagWin_BottomRightTopLeft(){
        IGameBoard gb = GBFactory();
        gb.placeToken('O',0);
        gb.placeToken('O',0);
        gb.placeToken('O',0);
        gb.placeToken('X',0);
        gb.placeToken('O',1);
        gb.placeToken('O',1);
        gb.placeToken('X',1);
        gb.placeToken('O',2);
        gb.placeToken('X',2);
        gb.placeToken('X',3);

        boolean winCheck;
        BoardPosition bp = new BoardPosition(0 , 3);
        String s = gb.toString();
        winCheck = gb.checkDiagWin(bp,'X');
        String s2 = gb.toString();
        Assert.assertTrue(winCheck && s.equals(s2));
    }
    @Test
    public void test_checkDiagWin_Middle_UpLeftDownRight(){
        IGameBoard gb = GBFactory();
        gb.placeToken('O',0);
        gb.placeToken('O',0);
        gb.placeToken('O',0);
        gb.placeToken('X',0);
        gb.placeToken('O',1);
        gb.placeToken('O',1);
        gb.placeToken('X',1);
        gb.placeToken('O',2);
        gb.placeToken('X',2);
        gb.placeToken('X',3);

        boolean winCheck;
        BoardPosition bp = new BoardPosition(2 , 1);
        String s = gb.toString();
        winCheck = gb.checkDiagWin(bp,'X');
        String s2 = gb.toString();
        Assert.assertTrue(winCheck && s.equals(s2));
    }
    @Test
    public void test_checkDiagWin_TopRightBottomLeft(){
        IGameBoard gb = GBFactory();
        gb.placeToken('O',3);
        gb.placeToken('O',3);
        gb.placeToken('O',3);
        gb.placeToken('X',3);
        gb.placeToken('O',2);
        gb.placeToken('O',2);
        gb.placeToken('X',2);
        gb.placeToken('O',1);
        gb.placeToken('X',1);
        gb.placeToken('X',0);

        boolean winCheck;
        BoardPosition bp = new BoardPosition(3 , 3);
        String s = gb.toString();
        winCheck = gb.checkDiagWin(bp,'X');
        String s2 = gb.toString();
        Assert.assertTrue(winCheck && s.equals(s2));
    }
    @Test
    public void test_checkDiagWin_BottomLeftTopRight(){
        IGameBoard gb = GBFactory();
        gb.placeToken('O',3);
        gb.placeToken('O',3);
        gb.placeToken('O',3);
        gb.placeToken('X',3);
        gb.placeToken('O',2);
        gb.placeToken('O',2);
        gb.placeToken('X',2);
        gb.placeToken('O',1);
        gb.placeToken('X',1);
        gb.placeToken('X',0);

        boolean winCheck;
        BoardPosition bp = new BoardPosition(0 , 0);
        String s = gb.toString();
        winCheck = gb.checkDiagWin(bp,'X');
        String s2 = gb.toString();
        Assert.assertTrue(winCheck && s.equals(s2));
    }
    @Test
    public void test_checkDiagWin_Middle_UpRightDownLeft(){
        IGameBoard gb = GBFactory();
        gb.placeToken('O',0);
        gb.placeToken('O',0);
        gb.placeToken('X',0);
        gb.placeToken('X',0);
        gb.placeToken('X',1);
        gb.placeToken('O',1);
        gb.placeToken('O',1);
        gb.placeToken('O',2);
        gb.placeToken('X',2);
        gb.placeToken('X',3);
        gb.placeToken('O',3);
        gb.placeToken('X',4);
        gb.placeToken('O',4);
        gb.placeToken('X',4);
        gb.placeToken('X',4);



        boolean winCheck;
        BoardPosition bp = new BoardPosition(1 , 2);
        String s = gb.toString();
        winCheck = gb.checkDiagWin(bp,'X');
        String s2 = gb.toString();
        Assert.assertTrue(!winCheck && s.equals(s2));
    }













}
