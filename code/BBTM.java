package myCode;

import utm.MoveClassical;
import utm.UniversalTuringMachine;

/**
 * This class extends the ThreeTMs class and represents a Busy Beaver Turing machine.
 * A Busy Beaver Turing machine is a Turing machine that writes the most number of 1s on the tape
 * before halting, among all Turing machines with a given number of states and symbols.
 * @author Aoxu Zhang
 * @version 1.0
 */
public class BBTM extends BasicTM {

    /**
     * Constructs a Busy Beaver Turing Machine with the given number of rules,
     * initial state, accept state and reject state.
     * <br>Invoke the parameterized constructor from the parent class.{@link BasicTM}
     * @param rulesNumber The number of rules for the Turing machine
     * @param initialState The initial state of the Turing machine
     * @param acceptState The accept state of the Turing machine
     * @param rejectState The reject state of the Turing machine
     */
    public BBTM(int rulesNumber, String initialState, String acceptState, String rejectState) {
        super(rulesNumber, initialState, acceptState, rejectState);
    }
    
    /**
     * This method sets the initial cell of the tape for a given Universal Turing Machine and input string.
     * The input string should be a binary string of length at most 20. If the input string is shorter than 20,
     * it is padded with 0s at the end. The method loads the input string on the tape and moves the head 10 cells to the right.
     * @param UTM The Universal Turing Machine to set the initial cell for
     * @param input The input string to load on the tape
     */
    public void setInitialCell(UniversalTuringMachine UTM, String input){
        if(input.length()<20){
            for(int i = input.length();i<20;i++){
                input+="0";
            }
        }
        UTM.loadInput(input);
        for (int i = 0; i < 10; i++) {
            UTM.moveHead(MoveClassical.RIGHT, false);
        }
    }
}
