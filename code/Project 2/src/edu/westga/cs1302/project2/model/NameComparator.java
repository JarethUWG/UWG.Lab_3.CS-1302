package edu.westga.cs1302.project2.model;

import java.util.Comparator;

/** Sorts ingredients by name alphabetically
 * 
 * @author Jareth Batty
 * @version Fall 2024
 */
public class NameComparator implements Comparator<Ingredient> {
	
	/**
	 * Sorts ingredients by name alphabetically
	 * 
	 * @precondition first != null && second != null
	 * 
	 * @param first the first ingredient object be be compared
	 * @param second the second ingredient object be be compared
	 * 
	 * @return 1 if the first name is greater than the second, 
	 * -1 if the second name is greater than the first, 0 if the names are equal
	 */
	public int compare(Ingredient first, Ingredient second) {
		if (first == null || second == null) {
			throw new IllegalArgumentException("Input ingredients cannot be null");
		}
		if (first.getName().compareTo(second.getName()) < 0) {
			return -1;
		} else if (first.getName().compareTo(second.getName()) > 0) {
			return 1;
		} else {
			return 0;
		}
	}
	
	@Override
	public String toString() {
		return "Name";
	}
}
