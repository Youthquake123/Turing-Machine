
package myCode;

import utm.UniversalTuringMachine;
import editor.UTMController;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * <p>The <code>MyUTMController</code> class implements the <code>UTMController</code> interface and provides functionality to load,
 * initialize and run different types of Turing Machines.</p>
 * @author  <b>Aoxu Zhang</b>
 * @version 1.1
 */
public class MyUTMController implements UTMController {

	private static final int RULES_NUMBER = 20;
	private String initialState = null;
	private String acceptState = null;
	private String rejectState = null;
	private String variant = null;
	private String rulesStr = null;
	private boolean isAnimated = true;
	private UniversalTuringMachine UTM = new UniversalTuringMachine();

	/**
	 * <p>Loads the Turing Machine from the given file path.</p>
	 * @author Aoxu Zhang
	 * @param filePath The file path to load the Turing Machine from.
	 */
	@Override
	public void loadTuringMachineFrom(String filePath) {
		Properties props = new Properties();
		try (FileInputStream inputStream = new FileInputStream(filePath)) {
			props.load(inputStream);
		} catch (IOException e) {
			throw new RuntimeException("Error reading description file", e);
		}
		initialState = props.getProperty("initialState");
		acceptState = props.getProperty("acceptState");
		rejectState = props.getProperty("rejectState");
		rulesStr = props.getProperty("rules");
		variant = props.getProperty("variant");
	}

	/**
	 * <p>Sets whether the Turing Machine is animated or not.</p>
	 * @param isAnimated true if the Turing Machine should be animated, false otherwise.
	 */
	public void setAnimated(boolean isAnimated) {
		this.isAnimated = isAnimated;
	}

	/**
	 * Initializes the given Turing Machine with the loaded rules and the input string,
	 * and sets the UTM and animation properties.<p>
	 *     Reduces Duplicate Code and Be More Elegant
	 * @author Aoxu Zhang
	 * @param basicTM The Turing Machine to be initialized.
	 * @param input The input string for the Turing Machine.
	 */
	public void initialize(BasicTM basicTM, String input) {
		basicTM.WriteRulesTable(rulesStr);
		basicTM.setUTM(UTM);
		basicTM.setAnimated(isAnimated);
		UTM.loadTuringMachine(basicTM);
		basicTM.setInitialCell(UTM, input);
		UTM.display();
	}

	/**
	 * <p>Runs the Turing Machine for the given input based on the selected variant.</p>
	 * @author Aoxu Zhang
	 * @param input The input string for the Turing Machine.
	 * @throws IllegalArgumentException if the selected Turing Machine variant is invalid.
	 */
	@Override
	public void runUTM(String input) {

		switch (variant) {
			case "CLASSICAL" :
				BasicTM CTM = new ClassicalTM(RULES_NUMBER, initialState, acceptState, rejectState);
				initialize(CTM, input);
				CTM.Run();
				break;

			case "LEFT_RESET" :
				BasicTM LRTM = new LRTM(RULES_NUMBER, initialState, acceptState, rejectState);
				initialize(LRTM, input);
				LRTM.Run();
				break;

			case "BUSY_BEAVER" :
				BasicTM BBTM = new BBTM(RULES_NUMBER, initialState, acceptState, rejectState);
				initialize(BBTM, input);
				BBTM.Run();
				break;

			default:
				throw new IllegalArgumentException("Invalid TuringMachine Type Name");
		}
	}
}
