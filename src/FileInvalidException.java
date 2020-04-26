// ----------------------------------------------
// COMP 249
// Assignment 3
// Written by: Xin Yuan Zhang (26373127)
// ----------------------------------------------

public class FileInvalidException extends Exception {
	
	private static final long serialVersionUID = 1L;
	/**
	 * default constructor
	 */
	public FileInvalidException() {
		super("Error: Input file cannot be parsed due to missing information " +
				"(i.e. month={}, title={}, etc.)");
	}
	/**
	 * parameterized constructor to display invalid field in input files
	 * @param errMsg invalid field
	 */
	public FileInvalidException(String errMsg) {
		super("File is invalid: Field \"" + errMsg + "\" is Empty. "
				+ "Processing stopped at this point. Other empty fields may be present as well!");
	}
	
}
