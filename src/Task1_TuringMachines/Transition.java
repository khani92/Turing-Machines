package Task1_TuringMachines;

public class Transition {

	public static final int RIGHT = 0;
	public static final int LEFT = 1;

	private char write;
	private int move;
	private int finalState;

	public Transition(char write, int move, int finalState) {
		this.write = write;
		this.move = move;
		this.finalState = finalState;
	}

	public char getWrite() {
		return write;
	}

	public int getMove() {
		return move;
	}

	public int getFinalState() {
		return finalState;
	}

}
