package models;

import java.util.Comparator;

public class TitleGameSortComparator implements Comparator<Game> {
	
	public int compare(Game g1, Game g2){
		return g1.getTitle().compareTo(g2.getTitle());
	}

}
