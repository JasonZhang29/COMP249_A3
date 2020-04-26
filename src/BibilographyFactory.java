// ----------------------------------------------
// COMP 249
// Assignment 3
// Written by: Xin Yuan Zhang (26373127)
// ----------------------------------------------

import java.io.FileInputStream;
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.util.Vector;
import java.io.File;

/**
 * method to parse input files into json object
 * @author jasonzhang
 *
 */
public class BibilographyFactory {
	// define source files folder
	private static final String folderName = "//Users/jasonzhang//Desktop//Comp249_W20_Assg3_Files//";
	// vector for storing parsed Jason Object
	private static Vector<JSON> v = new Vector<>();
	// parameter to record the number of invalid files
	private static int errCount = 0;
	// Method to parse source files
	public static void processFilesForValidation(Scanner fileIn, int count) {
		// begin every parse to clear the vector
		v.clear();
		
		while (fileIn.hasNextLine()) {	// parsing begins
				
			String str = fileIn.nextLine();
			if (str.startsWith("@ARTICLE{")) {		// "@ARTICLE{" indicates a record begins
				JSON a = new JSON();
				v.add(a);
			}
			if (str.startsWith("}")) {				// "}" indicates a record ends
				continue;
			}
			if (str.contains("=")) {				// "=" indicates a field
				
				String key = str.substring(0,str.indexOf("="));		// name of the field
				String data = "";
				if ((str.indexOf("}") - str.indexOf("{")) != 1) {
					data = str.substring(str.indexOf("{") + 1, str.indexOf("}")); 	// data of the field
				}
				int length = data.length();
				
				try {
					switch (key) {	//put parsed fields into vector of json objects
					case "author":
						if(length == 0) {
							++errCount;
							throw new FileInvalidException("author");
						} else {
							v.lastElement().setAuthor(data);
						}
						break;
					case "journal":
						if(length == 0) {
							++errCount;
							throw new FileInvalidException("journal");
						} else {
							v.lastElement().setJournal(data);
						}
						break;
					case "title":
						if(length == 0) {
							++errCount;
							throw new FileInvalidException("title");
						} else {
							v.lastElement().setTitle(data);
						}
						break;
					case "year":
						if(length == 0) {
							++errCount;
							throw new FileInvalidException("year");
						} else {
							v.lastElement().setYear(data);
						}
						break;
					case "volume":
						if(length == 0) {
							++errCount;
							throw new FileInvalidException("volume");
						} else {
							v.lastElement().setVolume(data);
						}
						break;
					case "number":
						if(length == 0) {
							++errCount;
							throw new FileInvalidException("number");
						} else {
							v.lastElement().setNumber(data);
						}
						break;
					case "pages":
						if(length == 0) {
							++errCount;
							throw new FileInvalidException("pages");
						} else {
							v.lastElement().setPages(data);
						}
						break;
					case "doi":
						if(length == 0) {
							++errCount;
							throw new FileInvalidException("doi");
						} else {
							v.lastElement().setDoi(data);
						}
						break;
					case "month":
						if(length == 0) {
							++errCount;
							throw new FileInvalidException("month");
						} else {
							v.lastElement().setMonth(data);
						}
						break;
					default:
						if (length == 0) {
							++errCount;
							throw new FileInvalidException(key);
						}
						break;
					}
				} catch (FileInvalidException e) {			// if invalid field, display error message
					System.out.println("Error: Detected Empty Filed!");
					System.out.println("****************************");
					System.out.println();
					System.out.println("Problem detected with input file: Latex" + count + ".bib");
					System.out.println(e.getMessage());
					System.out.println();
					v.clear();
					break;
				}
			}				
		}
	}

	
	public static void main(String[] args) {
		
		System.out.println("Welcome to BibilographyFacotry! (By Xin Yuan Zhang 26373127)\n");
		boolean outErr = false;
		// first for loop to check existence of all 10 input files
		for (int i = 1; i <= 10; ++i) {
			
			String fileName = folderName + "Latex" + i + ".bib";
			
			// to check if all input files exist
			try(Scanner fileIn = new Scanner(new FileInputStream(fileName))){
				
			} catch(FileNotFoundException e) {
				System.out.println("Could not open input file Latex" + i + ".bib for reading.\n");
				System.out.println("Please check if file exists! "
						+ "Program will terminate after closing any opened files.");
				System.exit(0);
			}
			
		}
			// to check if all output files can be created
		for (int i = 1; i <= 10; ++i) {
			
			String fileExt = ".json";
			String IEEEName = folderName + "IEEE" + i + fileExt;
			String ACMName = folderName + "ACM" + i + fileExt;
			String NJName = folderName + "NJ" + i + fileExt;
			
			try (PrintWriter fout = new PrintWriter(IEEEName)) {
				
			} catch (FileNotFoundException e) {
				System.out.println("Could not create/open file " + IEEEName);
				outErr = true;
				break;
			}
			
			try (PrintWriter fout = new PrintWriter(ACMName)) {
								
			} catch (FileNotFoundException e) {
				System.out.println("Could not create/open file " + ACMName);
				outErr = true;
				break;
			}
			
			try (PrintWriter fout = new PrintWriter(NJName)) {
	
			} catch (FileNotFoundException e) {
				System.out.println("Could not create/open file " + NJName);
				outErr = true;
				break;
			}		
		}
		// if error exists during creating output files, delete files
		if(outErr) {
			File folder = new File(".");
			for (File file : folder.listFiles()) {
				if (file.getName().endsWith(".json")) {
					file.delete();
				}
			}
			System.exit(0);
		}
		// main loop to process 10 input files
		for (int i = 1; i <= 10; ++i) {
			
			String fileName = folderName + "Latex" + i + ".bib";
			String fileExt = ".json";
			String IEEEName = folderName + "IEEE" + i + fileExt;
			String ACMName = folderName + "ACM" + i + fileExt;
			String NJName = folderName + "NJ" + i + fileExt;
			try {
				Scanner fileIn = new Scanner(new FileInputStream(fileName));
				PrintWriter foutIEEE = new PrintWriter(IEEEName);
				PrintWriter foutACM = new PrintWriter(ACMName);
				PrintWriter foutNJ = new PrintWriter(NJName);
				processFilesForValidation(fileIn, i);
				if (!v.isEmpty()) {
					for (int j = 0; j < v.size(); ++j) {
						foutIEEE.append(v.elementAt(j).toIEEE());						
						foutACM.append("[" + (j + 1) +"]\t" + v.elementAt(j).toACM());
						foutNJ.append(v.elementAt(j).toNJ());
					}
				} else {
					File f = new File(IEEEName);
					f.delete();
					f = new File(ACMName);
					f.delete();
					f = new File(NJName);
					f.delete();
				}
				foutIEEE.close();
				foutACM.close();
				foutNJ.close();
				fileIn.close();
			} catch (FileNotFoundException e) {
				System.out.println(e.getMessage());
			}
		}
		System.out.println("A total of " + errCount + " files were invalid, and could not be processed. "
				+ "All other " + (10 - errCount) + " \"Valid\" files have been created.\n");
		System.out.print("Please enter the name of one of the files that you need to review: ");
		// let user to choose a file to review
		Scanner keyIn = new Scanner(System.in);
		Scanner fileIn = null;
		String reviewFile = keyIn.next();
		int tried = 0;		// a counter for recording error times
		do {
			try {
				fileIn = new Scanner(new FileInputStream(folderName + reviewFile));
				System.out.println("Here are the content of the successfully created Jason File: " + reviewFile);
				while (fileIn.hasNextLine()) {
					System.out.println(fileIn.nextLine());
				}
				tried = 2;
				System.out.println("Goodbye! Hope you have enjoyed creating the needed files "
						+ "using BibilographyFactory.");
				fileIn.close();
			} catch(FileNotFoundException e) {
				if (tried < 1) {
					System.out.println("Could not open input file. "
							+ "File does not exist; possibly it could not be created!\n");
					System.out.println("However, you will be allowed another chance to enter another file name.");
					System.out.print("Please enter the name of one of the files that you need to review: ");
					reviewFile = keyIn.next();
					++tried;
				} else {
					++tried;
					System.out.println("\nCould not open input file again! "
							+ "Either file does not exist or could not be created.");
					System.out.println("Sorry! I am unable to display your desired files! Program will exit!");
				}
			} 
		} while (tried < 2);
		keyIn.close();
	}

}
