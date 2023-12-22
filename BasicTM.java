package myCode;

import utm.*;

/**
 * BasicTM is a class act as the superclass of every variant of Turing machine
 * which provides several method to enable subclasses to override.
 * @author Aoxu Zhang
 * @version 1.3
 */
public class BasicTM extends TuringMachine {
    public int rulesNumber; // the number of rules in the transition function
    protected UniversalTuringMachine UTM = null; // the universal Turing machine that simulates this machine
    public boolean isAnimated = true; // a flag that indicates whether the simulation is animated or not
    protected int index; // the index of the current rule in the transition function
    protected String[][] rules; // the array of rules in the transition function


    /**
     * Constructs a general Turing machine with given parameters.
     * @param rulesNumber Number of rules in the transition function
     * @param initialState Initial state of the machine
     * @param acceptState Accept state of the machine
     * @param rejectState Reject state of the machine
     */
    public BasicTM(int rulesNumber, String initialState, String acceptState, String rejectState) {
        super(rulesNumber, initialState, acceptState, rejectState);
        this.rulesNumber = rulesNumber;
    }

    /**
     * Writes a table of rules for a Turing machine based on a string input with specific format.
     * <pre>
     * The string inputted has multiple rules separated by commas.
     * The method calls the addRule method to add each rule to the table
     * (addRule method is defined in its father class Turning Machine).
     * </pre>
     * @author Aoxu Zhang
     * @param rulesStr Contains designated rules for the Turing machine
     */
    public void WriteRulesTable(String rulesStr) {
        String[] rulesTemp = rulesStr.split(",|<>");
        String currentState = null, newState = null;
        char currentSymbol = 0, newSymbol = 0;
        Move move = null;
        for(int i=0; i<(rulesTemp.length/5); i++) {
            for(int j=0; j<5; j++) {
                switch(j) {
                    case 0:
                        currentState = rulesTemp[5*i+j];
                    case 1:
                        currentSymbol = rulesTemp[5*i+j].charAt(0);
                    case 2:
                        newState = rulesTemp[5*i+j];
                    case 3:
                        newSymbol = rulesTemp[5*i+j].charAt(0);
                    case 4:
                        if(rulesTemp[5*i+j].equals("LEFT")) { move = MoveGeneral.LEFT; }
                        else if(rulesTemp[5*i+j].equals("RIGHT")) { move = MoveGeneral.RIGHT; }
                        else { move = MoveGeneral.RESET; }
                }
            }
          addRule(currentState, currentSymbol, newState, newSymbol, move);
        }
    }

    /**
     * Sets the universal Turing machine that simulates this machine.
     * @param UTM the universal Turing machine object
     */
    public void setUTM(UniversalTuringMachine UTM) {
        this.UTM = UTM;
    }

    /**
     * Sets the animation flag for the simulation.
     * @param isAnimated true if the simulation is animated, false otherwise
     */
    public void setAnimated(boolean isAnimated) {
        this.isAnimated = isAnimated;
    }
    
    /**
     * Sets the direction of movement for the head based on the current rule.
     * @author Aoxu Zhang
     */
    public void setDirection(){
        if(rules[index][4].equals("RIGHT")){
            UTM.moveHead(MoveClassical.RIGHT, isAnimated);
        } else {
            UTM.moveHead(MoveClassical.LEFT, isAnimated);
        }
    }
    
    /**
     * Sets the initial cell of the tape based on the input string.
     * @param UTM the universal Turing machine object
     * @param input the input string for the machine
     */
    public void setInitialCell(UniversalTuringMachine UTM,String input){
        UTM.loadInput(input);
    }
    
    /**
     * Finds the index of the rule that matches the current state and symbol of the machine.
     * @param cSymbol the current symbol on the tape
     * @return the index of the matching rule, or -1 if no rule matches
     */
    public int findRules(String cSymbol){
        rules= getRules();
        for(index=0; index<rulesNumber; index++) {
            if(getHead().getCurrentState().equals(rules[index][0]) && cSymbol.equals(rules[index][1])) {
                break;
            }
        }
        return index;
    }
    
    /**
	 * Runs a Turing Machine.
     * <pre>Reads symbols from the tape, looks up the rule associated with the current state and the symbol read.
     * Provides the universal rules to run a Turning Machine.
     * That is:
     * 1. Search for matching rules based on the state of the head and the symbol on the tape.
     * 2. Overwrites the symbol of current cell with the symbol specified by the triggered rule.
     * 3. Moves the head specified by the triggered rule.
     * 4. Updates the state of Head according to the triggered rule
     * The machine Keeps running until it reaches the halt state,and displays the halt state of the UTM.
     * ("qa" for ACCEPTED and "qr" for REJECTED).</pre>
     * @author Aoxu Zhang
     * @throws NullPointerException if UTM is null.
     */
    public void Run(){
        if(UTM != null) {
            boolean isHalted = false;
            while(!isHalted) {
                //The head of TM reads the symbol from the cell it currently points to
                String cSymbol = getTape().get(getHead().getCurrentCell()).toString();
                //Look up the rule associated with the current state and the symbol read
                index = findRules(cSymbol);
                //The head of TM overwrites the current cell with the symbol specified by the triggered rule.
                UTM.writeOnCurrentCell(rules[index][3].charAt(0));
                //The head of TM moves one cell to the right or one cell to the left
                setDirection();
                //The state of TM is updated according to the triggered rule.
                UTM.updateHeadState(rules[index][2]);
                isHalted = getHead().getCurrentState().equals("qa")||getHead().getCurrentState().equals("qr");
                }
            HaltState haltState = getHead().getCurrentState().equals("qa") ? HaltState.ACCEPTED:HaltState.REJECTED;
            UTM.displayHaltState(haltState);
            }
        else {
            System.err.println("UTM is Missing.");
        }
    }
}
