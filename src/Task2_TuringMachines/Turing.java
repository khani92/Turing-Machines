package Task2_TuringMachines;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Turing {

	private State[] machineStates;
	private int numOfStates;
	private char[] tape = new char[40];
	private int tapeHead = 1;
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

		// Enter total states excluding halt state. Eg for q0 to q6 if q6 is the
		// halt stte enter 6 below
		Turing machine = new Turing(6);
		initializeInputTape(machine.tape, input, machine.tapeHead);

		Transition t0 = new Transition('B', Transition.RIGHT, 1);
		machine.addTransition(0, '0', t0);

		Transition t1 = new Transition('0', Transition.RIGHT, 1);
		machine.addTransition(1, '0', t1);
		Transition t2 = new Transition('1', Transition.RIGHT, 2);
		machine.addTransition(1, '1', t2);

		machine.addTransition(2, '1', t2);
		Transition t3 = new Transition('1', Transition.LEFT, 3);
		machine.addTransition(2, '0', t3);

		Transition t4 = new Transition('0', Transition.LEFT, 3);
		machine.addTransition(3, '0', t4);
		machine.addTransition(3, '1', t3);
		
		Transition t5 = new Transition('B', Transition.RIGHT, 0);
		machine.addTransition(3, 'B', t5);
		
		Transition t6 = new Transition('B', Transition.LEFT, 4);
		machine.addTransition(2, 'B', t6);
		machine.addTransition(4, '1', t6);
		
		Transition t7 = new Transition('0', Transition.LEFT, 4);
		machine.addTransition(4, '0', t7);
		
		Transition t8 = new Transition('0', Transition.RIGHT, 6);
		machine.addTransition(4, 'B', t8);
		
		Transition t9 = new Transition('B', Transition.RIGHT, 5);
		machine.addTransition(0, '1', t9);
		machine.addTransition(5, '0', t9);
		machine.addTransition(5, '1', t9);
		
		Transition t10 = new Transition('B', Transition.RIGHT, 6);
		machine.addTransition(5, 'B', t10);
		
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
