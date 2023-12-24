package myCode;

/**
 * This class extends the ThreeTMs class and represents a classical Turing machine.
 * @version 1.0
 */
public class ClassicalTM extends BasicTM {

    /**
     * Constructs a Classical Turing Machine with the given number of rules,
     * initial state, accept state and reject state.
     * <br>Invoke the parameterized constructor from the parent class.{@link BasicTM}
     * @param rulesNumber The number of rules for the Turing machine
     * @param initialState The initial state of the Turing machine
     * @param acceptState The accept state of the Turing machine
     * @param rejectState The reject state of the Turing machine
     */
    public ClassicalTM(int rulesNumber, String initialState, String acceptState, String rejectState) {
        super(rulesNumber, initialState, acceptState, rejectState);
    }

}
