package myCode;

import utm.MoveClassical;

/**
 * LRTM class represents the Left-Reset Turing Machine.
 * Has the ability to reset its head to the leftmost position.
 * whenever it encounters a symbol in its transition rules that specifies a "LEFT" direction. It extends the ThreeTMs
 * class and overrides the setDirection() method to implement the additional functionality.
 * @author Aoxu Zhang
 * @version 1.1
 */
public class LRTM extends BasicTM {

	/**
	 * Constructs a new Left-Reset Turning Machine object with the given number of rules,
     * initial, accept, and reject states.
	 * <br>Invoke the parameterized constructor from the parent class.{@link BasicTM}
	 * @param rulesNumber the number of transition rules for the Turing machine
	 * @param initialState the initial state of the Turing machine
	 * @param acceptState the accepting state of the Turing machine
	 * @param rejectState the rejecting state of the Turing machine
	 */
    public LRTM(int rulesNumber, String initialState, String acceptState, String rejectState) {
        super(rulesNumber, initialState, acceptState, rejectState);
    }

 
    /**
     * Overrides the setDirection() method in the parent class to move the head to the right or reset it to the leftmost 
     * position, depending on the direction specified in the current transition rule.
     */
    @Override
    public void setDirection(){
        if(super.rules[super.index][4].equals("RIGHT")){
            UTM.moveHead(MoveClassical.RIGHT, isAnimated);
        } else {
            UTM.getTuringMachine().getHead().reset();
        }
    }

}
