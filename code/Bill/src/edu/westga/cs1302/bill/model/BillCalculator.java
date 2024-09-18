package edu.westga.cs1302.bill.model;

/**
 * Manages a set of BillItems.
 * 
 * @author CS 1302, Jareth Batty
 * @version Fall 2024
 */
public class BillCalculator {
	
	/**
	 * Return the subtotal for the bill
	 * 
	 * @param items the array of bill items to be calculated
	 * 
	 * @return the subtotal for the bill
	 */
	public static double getSubTotal(BillItem[] items) {
		double subTotal = 0.0;
		for (BillItem item : items) {
			if (item != null) {
				subTotal += item.getAmount();
			}
		}
		return subTotal;
	}

	/**
	 * Return the tax for the bill
	 * 
	 * @param items the array of bill items to be calculated
	 * 
	 * @return the tax for the bill
	 */
	public static double getTax(BillItem[] items) {
		return BillCalculator.getSubTotal(items) * 0.1;
	}

	/**
	 * Return the tip for the bill
	 * 
	 * @param items the array of bill items to be calculated
	 * 
	 * @return the tip for the bill
	 */
	public static double getTip(BillItem[] items) {
		return BillCalculator.getSubTotal(items) * 0.2;
	}

	/**
	 * Return the total for the bill
	 * 
	 * @param items the array of bill items to be calculated
	 * 
	 * @return the total for the bill
	 */
	public static double getTotal(BillItem[] items) {
		return BillCalculator.getSubTotal(items) + BillCalculator.getTip(items) + BillCalculator.getTax(items);
	}

}
