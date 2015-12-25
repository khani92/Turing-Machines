package Task0_DFSA;

import java.util.Scanner;

public class DFSASimulator {

	public static void main(String[] args) {

		DFSASimulator simulator = new DFSASimulator();

		System.out.println("Enter the encoded string");

		Scanner scan = new Scanner(System.in);
		String input = scan.nextLine().trim();
		scan.close();
		// String input = "00100101010011001111111011110";
		Scanner decoderObj = new Scanner(input);
		DFSAMachine machine = InputDecoder.initializeMachine(decoderObj, input);

		decoderObj.reset();
		String word = decoderObj.next();
		if (simulator.runMachine(word, machine)) {
			System.out.println("Accept");
		} else {
			System.out.println("Reject");
		}
		decoderObj.close();
	}

	public boolean runMachine(String word, DFSAMachine dfs) {
		boolean isAccepted = false;
		DFSAState currState = null;

		currState = dfs.getStartState();

		for (int i = 0; i < word.length(); i++) {
			int input = Integer.parseInt(word.charAt(i) + "");

			currState = currState.getTransition()[input];
		}

		for (int i = 0; i < dfs.getAcceptingStates().length; i++) {
			if (currState.stateId == dfs.getAcceptingStates()[i]) {
				isAccepted = true;
				break;
			}
		}
		return isAccepted;

	}
}

class InputDecoder {

	public static DFSAMachine initializeMachine(Scanner decoderObj, String input) {

		DFSAMachine dfs = new DFSAMachine();
		int numOfStates = 0;

		numOfStates = decoderObj.findInLine("([0]+)").trim().length();

		DFSAState[] machineStates = new DFSAState[numOfStates];

		for (int i = 0; i < numOfStates; i++) {
			machineStates[i] = new DFSAState();
			machineStates[i].stateId = i;
		}

		// Machine created, now initialize it.
		dfs.setNumOfStates(numOfStates);
		dfs.setMachineState(machineStates);

		for (int i = 0; i < numOfStates; i++) {
			String s = decoderObj.findInLine("[0]+1[0]+");
			String[] inputs = s.trim().split("1");

			// This array holds the delta for each state
			DFSAState[] transition = new DFSAState[2];

			for (int j = 0; j < 2; j++) {
				transition[j] = dfs.getMachineState()[inputs[j].trim().length() - 1];
			}

			dfs.getMachineState()[i].setTransition(transition);
		}

		decoderObj.skip("11");

		String s = decoderObj.findInLine("([0]+1)+");
		String[] acceptingStates = s.split("1");

		int[] acceptArray = new int[acceptingStates.length];

		for (int i = 0; i < acceptingStates.length; i++) {
			acceptArray[i] = acceptingStates[i].length() - 1;
		}
		dfs.setAcceptingStates(acceptArray);

		decoderObj.skip("1");
		return dfs;
	}
}
