package com.statistant.ligue1.pojo.comparator;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * Sort teams by their percentage of something.
 * @author Thomas CHARMES
 */
public class PercentageComparator implements Comparator<String> {

	private String name;
	private String percentage;

	Map<String, String> base;

	public PercentageComparator(Map<String, String> base) {
		this.base = base;
	}

	public String getPercentage() {
		return percentage;
	}

	public String getName() {
		return name;
	}

	public void setPercentage(String percentage) {
		this.percentage = percentage;
	}

	public void setName(String name) {
		this.name = name;
	}

	public PercentageComparator(String name, String percentage) {
		this.percentage = percentage;
		this.name = name;
	}

	@Override
	public int compare(String a, String b) {
		String valueOfA = base.get(a);
		String valueOfB = base.get(b);
		String[] splitA = valueOfA.split("%");
		String[] splitB = valueOfB.split("%");
		if (Float.parseFloat(splitA[0]) >= Float.parseFloat(splitB[0])) {
			return -1;
		} else {
			return 1;
		}
	}
	
	public static void main(String[] args) {
		
		Map<String, String> stats = new HashMap<String, String>();
		stats.put("MHSC", "60.0%");
		stats.put("OGCN", "50.00%");
		stats.put("nul", "70%");
		PercentageComparator p = new PercentageComparator(stats);
		System.out.println(p.compare("MHSC", "nul"));
		System.out.println(p.compare("OGCN", "nul"));
	}

}

