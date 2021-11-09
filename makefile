src = AbsGameBoard.java BoardPosition.java GameBoard.java GameBoardMem.java IGameBoard.java
tests = TestGameBoard.java TestGameBoardMem.java
junit = -cp .:/usr/share/java/junit4.jar
default:
	javac GameScreen.java $(src)
run: GameScreen.class $(src)
	java GameScreen
clean:
	rm *.class
test:
	javac $(junit) $(tests) $(src)
testGB:
	java $(junit) org.junit.runner.JUnitCore TestGameBoard
testGBMem:
	java $(junit) org.junit.runner.JUnitCore TestGameBoardMem
