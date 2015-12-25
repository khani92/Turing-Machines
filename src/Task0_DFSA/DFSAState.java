package Task0_DFSA;

public class DFSAState {

	int stateId;
	DFSAState[] transition = new DFSAState[2]; // the index of the array tells
												// the
	// input to the tied state

	public DFSAState[] getTransition() {
		return transition;
	}

	public int getStateId() {
		return stateId;
	}

	public void setStateId(int stateId) {
		this.stateId = stateId;
	}

	public void setTransition(DFSAState[] transition) {
		this.transition = transition;
	}
}
