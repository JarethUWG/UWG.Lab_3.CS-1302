package edu.westga.cs1302.project2.model;

import java.util.Comparator;

/** Sorts ingredients by type alphabetically
 * 
 * @author Jareth Batty
 * @version Fall 2024
 */
public class TypeComparator implements Comparator<Ingredient> {
	
	/**
	 * Sorts ingredients by type alphabetically
	 * 
	 * @precondition first != null && second != null
	 * 
	 * @param first the first ingredient object be be compared
	 * @param second the second ingredient object be be compared
	 * 
	 * @return 1 if the first type is greater than the second, 
	 * -1 if the second type is greater than the first, 0 if the types are equal
	 */
	public int compare(Ingredient first, Ingredient second) {
		if (first == null || second == null) {
			throw new IllegalArgumentException("Input ingredients cannot be null");
		}
		return first.getType().compareTo(second.getType());
	}
	
	@Override
	public String toString() {
		return "Type";
	}
}
