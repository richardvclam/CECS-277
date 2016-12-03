package network_fwg;

import java.io.Serializable;

/**
 * Pattern class that holds the String value of a pattern.
 * @author Richard Lam
 */
public class Pattern implements Serializable {

	/**
	 * String pattern 
	 */
	private String pattern;
	
	/**
	 * Constructor
	 * @param pattern value of the pattern to instantiate with
	 */
	public Pattern(String pattern) {
		this.pattern = pattern;
	}
	
	/**
	 * Returns the string value of the pattern.
	 * @return the string value of the pattern
	 */
	public String getPattern() {
		return pattern;
	}
	
	/**
	 * Checks if two Pattern objects are equal to each other.
	 */
	@Override
	public boolean equals(Object o) {
		if (o instanceof Pattern) {
			Pattern p = (Pattern) o;
			return p.getPattern().equals(pattern);
		}
		return false;
	}
	
	/**
	 * Returns a hashcode value for Pattern.
	 */
	@Override
	public int hashCode() {
		return pattern.hashCode();
	}

}
