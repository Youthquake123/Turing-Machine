package myCode;

import editor.UTMEditor;

/**
 * <p>A class that contains the main method to run a universal Turing machine (UTM) either in editor mode or in simulation mode.</p>
 * <p>The UTM can be loaded from a file and given an input string to simulate its computation.</p>
 * <p>The simulation can be animated or not depending on the user's preference.</p>
 * @version 1.0
 * @author <b>Aoxu Zhang</b>
 * <a href="https://en.wikipedia.org/wiki/Universal_Turing_machine">UniversalTuringmachine</a>
 */
public class Main {

	private static final String ANIMATION_OPTION = "--noanimation"; // A constant for the option to disable animation
	private static final String INVALID_ARGUMENTS_MSG = "Invalid arguments. "
			+ "Expected 3 arguments for file path, input and animation or no arguments for the editor mode."; // A constant for the error message

	/**
	 * <p>Runs the UTM either in editor mode or in simulation mode based on the command-line arguments.<br>
	 * <p>For empty array, the TM runs in editor mode where the user can create and edit the TM using a graphical user interface (GUI).<p>
	 * <p>For 3-element-array, the TM runs in simulation mode where it will load a UTM from a file, take an input string and simulate its computation.<br>
	 * <p>The 1st element of the array should be the file path of the UTM, the 2nd element should be the input string and the 3rd element should be either "--noanimation" to disable animation or anything else to enable animation.<p>
	 * <p>If the array has any other number of elements, an IllegalArgumentException will be thrown.<p>
	 * @param args an array of strings that contains the command-line arguments. 
	 * @throws IllegalArgumentException if the number of arguments is not 0 or 3
	 */
	public static void main(String[] args) {

		String filePath = null; 
		String input = null; 
		String animation = null; 
		boolean isAnimated = true; 
		boolean hasEditor = false; 
		MyUTMController utmControl = new MyUTMController();

		// If there are 3 arguments, run in simulation mode
		if (args.length == 3) { 
			hasEditor = false;
			filePath = args[0];
			input = args[1];
			animation = args[2];
		} else if (args.length == 0) { // If there are no arguments, run in editor mode
			hasEditor = true;
		} else {
			System.err.println("IllegalArgument");
			throw new IllegalArgumentException(INVALID_ARGUMENTS_MSG);
		}

		if (hasEditor) { // If running in editor mode, create a GUI for editing the UTM
			UTMEditor utmEdi = new UTMEditor();
			utmEdi.setUTMController(utmControl);

		} else { 
			if (animation.equalsIgnoreCase(ANIMATION_OPTION)) { 
				isAnimated = false;
			}
			utmControl.setAnimated(isAnimated); 
			utmControl.loadTuringMachineFrom(filePath); 
			utmControl.runUTM(input); 
		}
	}
}
