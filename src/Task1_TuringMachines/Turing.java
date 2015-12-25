package Task1_TuringMachines;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Turing {

	private State[] machineStates;
	private int numOfStates;
	private char[] tape = new char[40];
	private int tapeHead = 20;
	State currState;
	State haltState;
	Map<String, Transition> delta = new HashMap<>();

	Turing(int states) {

		this.numOfStates = states + 1;
		machineStates = new State[numOfStates];

		for (int i = 0; i < numOfStates; i++) {
			machineStates[i] = new State(i);
		}
		currState = machineStates[0];
		haltState = machineStates[states];
		for (int i = 0; i < 40; i++) {
			tape[i] = 'B';
		}
	}

	public static void main(String[] args) {

		System.out.println("Enter the input string");
		Scanner scan = new Scanner(System.in);
		String input = scan.nextLine();
		scan.close();

		Turing machine = new Turing(1);
		initializeInputTape(machine.tape, input, machine.tapeHead);

		Transition one = new Transition('0', Transition.RIGHT, 0);
		Transition two = new Transition('1', Transition.RIGHT, 0);
		Transition three = new Transition('B', Transition.LEFT, 1);

		machine.addTransition(0, '0', two);
		machine.addTransition(0, '1', one);
		machine.addTransition(0, 'B', three);

		System.out.println(input);
		String outTape = machine.execute();
		System.out.println(outTape);

	}

	public void addTransition(int state, char inChar, Transition t) {
		String key = state + "" + inChar;
		delta.put(key, t);
	}

	public String execute() {

		String key = null;
		while (currState != haltState) {
			key = currState.getStateId() + "" + tape[tapeHead];

			Transition t = delta.get(key);

			tape[tapeHead] = t.getWrite();

			if (t.getMove() == Transition.RIGHT) {
				tapeHead++;
			} else if (t.getMove() == Transition.LEFT) {
				tapeHead--;
			}

			currState = machineStates[t.getFinalState()];
		}

		return String.valueOf(tape);
	}

	public static void initializeInputTape(char[] inTape, String input, int tapeHead) {

		for (int i = 0; i < input.length(); i++) {
			inTape[tapeHead++] = input.charAt(i);
		}
	}
}

class State {

	private int stateId;

	State(int state) {
		this.stateId = state;
	}

	public int getStateId() {
		return stateId;
	}

	public void setStateId(int stateId) {
		this.stateId = stateId;
	}
}
