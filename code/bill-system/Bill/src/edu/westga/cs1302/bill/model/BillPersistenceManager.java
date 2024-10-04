package edu.westga.cs1302.bill.model;

import java.io.FileWriter;
import java.io.IOException;

/** Supports saving and loading bill data,
 * 
 * @author CS 1302
 * @version Fall 2024
 */
public class BillPersistenceManager {
	
	public static final String DATA_FILE = "data.txt";
	
	/** Save the bill!
	 * 
	 * Writes all bill data to DATA_FILE
	 * 
	 * @precondition bill != null
	 * @postcondition none
	 * 
	 * @param bill the bill to save
	 * @throws IOException if issues occur in writing
	 * @throws IllegalArgumentException when a null bill is given
	 */
	public static void saveBillData(Bill bill) throws IOException, IllegalArgumentException {
		if (bill == null) {
			throw new IllegalArgumentException("Must provide a valid bill");
		}
		try (FileWriter writer = new FileWriter(DATA_FILE)) {
				writer.write(bill.getServerName() + "," + bill.getSize() + System.lineSeparator());
			for (BillItem currentBillItem : bill.getItems()) {
				if (currentBillItem != null) {
					writer.write(currentBillItem.getName() + "," + currentBillItem.getAmount() + System.lineSeparator());
				}
			}
		}
	}

	/** Load the bill!
	 * 
	 * Reads from DATA_FILE
	 * File is assumed to use the same format as saveBillData
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the bill loaded
	 */
	public static Bill loadBillData() {
		return null;
	}

}
