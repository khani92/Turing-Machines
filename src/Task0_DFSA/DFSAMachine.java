package Task0_DFSA;

public class DFSAMachine {

	private int numOfStates;
	private DFSAState[] machineState;
	private int[] acceptingStates;

	public int getNumOfStates() {
		return numOfStates;
	}

	public void setNumOfStates(int numOfStates) {
		this.numOfStates = numOfStates;
	}

	public int[] getAcceptingStates() {
		return acceptingStates;
	}

	public void setAcceptingStates(int[] acceptingStates) {
		this.acceptingStates = acceptingStates;
	}

	public DFSAState[] getMachineState() {
		return machineState;
	}

	public void setMachineState(DFSAState[] machineState) {
		this.machineState = machineState;
	}

	public DFSAState getStartState() {
		return machineState[0];
	}

}
