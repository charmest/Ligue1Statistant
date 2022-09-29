package com.statistant.ligue1.pojo.comparator;

import java.util.Comparator;
import java.util.Map;

/**
 * Sort teams by their position in standing. In case of equality, the goal difference is takken into account.
 * @author Thomas CHARMES
 */
public class StandingComparator implements Comparator<String> {
	
	private Map<String, int[]> base;
	
	public StandingComparator(Map<String, int[]> base) {
		this.base = base;
	}
	
	/**
	 * ex : {"MHSC", [10,4]} 
	 * has a higher standing than {"OM", [10,2]} 
	 * and {"RCL", [9,8]}
	 * but has a lower standing than {"PSG", [10,7]}
	 * 
	 * @param team1 first team nickname
	 * @param team2 second team nickname
	 */
	@Override
	public int compare(String team1, String team2) {
		
		int[] statsTeam1 = base.get(team1);
		int[] statsTeam2 = base.get(team2);
		if (statsTeam1 != null && statsTeam2 != null) {
			if(statsTeam1[0] > statsTeam2[0]) {
				return -1;
			}
			if(statsTeam1[0] < statsTeam2[0]) {
				return 1;
			}
			if (statsTeam1[0] == statsTeam2[0]) {
				if (statsTeam1[1] > statsTeam2[1]) {
					return -1;
				}
				else {
					return 1;
				}
			}
		}
		return 0;
	}

}
