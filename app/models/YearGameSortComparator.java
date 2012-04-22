package models;

import java.util.Comparator;

public class YearGameSortComparator implements Comparator<Game> {
	public int compare(Game g1, Game g2){
        
        if (g1 == null)
            return -1;
        if (g2 == null)
            return 1;
        
        int year1 = g1.getAnnee();
        int year2 = g2.getAnnee();
		 
	    if (year1 > year2)  return -1; 
	    else if(year1 == year2) return 0; 
	    else return 1;
	}
}
