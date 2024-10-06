package edu.westga.cs1302.bill.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

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
	 * @throws FileNotFoundException inputed file not found at location
	 * @throws IOException formatting issues exist in inputed file
	 */
	public static Bill loadBillData() throws FileNotFoundException, IOException {
		File inputFile = new File(DATA_FILE);
		String serverName = "PLACEHOLDER";
		ArrayList<BillItem> items = new ArrayList<BillItem>();
		try (Scanner reader = new Scanner(inputFile)) {
			for (int currentIndex = 0; reader.hasNextLine(); currentIndex++) {
				String baseLine = reader.nextLine();
				String strippedLine = baseLine.strip();
				String[] parts = strippedLine.split(",");
				try {
					if (currentIndex == 0) {
						if (parts[0].isBlank() || parts[0] == null || parts[0].contains(",")) {
							throw new IOException("Invalid or missing server name on line " + (currentIndex + 1));
						} else {
							serverName = parts[0];
						}

					} else {
						double parsedCost = Double.parseDouble(parts[1]);
						BillItem currentItem = new BillItem(parts[0], parsedCost);
						items.add(currentItem);
					}	
				} catch (NumberFormatException numError) {
					throw new IOException("Unable to read cost on line " + (currentIndex + 1));
				} catch (IllegalArgumentException billDataError) {
					throw new IOException("Invalid Bill information on line " + (currentIndex + 1));
				} catch (IndexOutOfBoundsException fileFormatError) {
					throw new IOException("Improper formatting of information on line " + (currentIndex + 1));
				}
			}
		}
		Bill loadedBill = new Bill();
		for (BillItem item: items) {
			loadedBill.addItem(item);
		}
		loadedBill.setServerName(serverName);
		return loadedBill;
	}

}
