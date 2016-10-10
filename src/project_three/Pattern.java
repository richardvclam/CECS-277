package project_three;

import java.io.Serializable;

public class Pattern implements Serializable {

	private String pattern;
	
	public Pattern(String pattern) {
		this.pattern = pattern;
	}
	
	public String getPattern() {
		return pattern;
	}
	
	@Override
	public boolean equals(Object o) {
		if (o instanceof Pattern) {
			Pattern p = (Pattern) o;
			return p.getPattern().equals(pattern);
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return pattern.hashCode();
	}
	
	public int[] getCount() {
		int f = 0, w = 0, g = 0;
		
		for (int i = 0; i < pattern.length(); i++) {
			char c = pattern.charAt(i);
			if (c == 'F') {
				f++;
			} else if (c == 'W') {
				w++;
			} else {
				g++;
			}
		}
		
		int[] res = {f, w, g};
		return res;
	}

}
