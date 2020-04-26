// ----------------------------------------------
// COMP 249
// Assignment 3
// Written by: Xin Yuan Zhang (26373127)
// ----------------------------------------------

import java.util.StringTokenizer;

/**
 * class for storing parsed article info
 * @author jasonzhang
 *
 */
public class JSON {
	/**
	 * data fileds used in output style
	 */
	private String author;
	private String journal;
	private String title;
	private String year;
	private String volume;
	private String number;
	private String pages;
	private String doi;
	private String month;
	/**
	 * default constructor
	 */
	public JSON() {
		author = "";
		journal = "";
		title = "";
		year = "";
		volume = "";
		number = "";
		pages = "";
		doi = "";
		month = "";
	}
	
	public String getAuthor() {
		return author;
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}
	
	public String getJournal() {
		return journal;
	}
	
	public void setJournal(String journal) {
		this.journal = journal;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getYear() {
		return year;
	}
	
	public void setYear(String year) {
		this.year = year;
	}
	
	public String getVolume() {
		return volume;
	}
	
	public void setVolume(String volume) {
		this.volume = volume;
	}
	
	public String getNumber() {
		return number;
	}
	
	public void setNumber(String number) {
		this.number = number;
	}
	
	public String getPages() {
		return this.pages;
	}
	
	public void setPages(String pages) {
		this.pages = pages;
	}
	
	public String getDoi() {
		return doi;
	}
	
	public void setDoi(String doi) {
		this.doi = doi;
	}
	
	public String getMonth() {
		return month;
	}
	
	public void setMonth(String month) {
		this.month = month;
	}
	/**
	 * method to output IEEE Style
	 * @return String
	 */
	public String toIEEE() {
		return author.replace(" and ", ", ") + ". \"" + title + "\", " + journal + ", vol. "
				+ volume + ", no. " + number + ", p. " + pages + ", " + month + " " + year + ".\n\n";
	}
	/**
	 * method to output ACM Style
	 * @return String
	 */
	public String toACM() {
		StringTokenizer st = new StringTokenizer(author.replace(" and ", "&"), "&");
		String acmAuthor = "";
		while (st.hasMoreTokens()) {
			acmAuthor = st.nextToken();
			break;
		}
		return acmAuthor + " et al. " + year + ". " + title + ". " + journal + ". " + volume + ", "
				+ number + "(" + year +  "), " + pages + ". DOI:https://doi.org/" + doi + ".\n\n";
	}
	/**
	 * method to output NJ Style
	 * @return String
	 */
	public String toNJ() {
		return author.replace("and", "&") + ". " + title + ". " + journal + ". " + volume
				+ ", " + pages + "(" + year + ").\n\n";
	}
}
