package edu.westga.cs1302.bill.test.model.bill_persistance_manager;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.bill.model.Bill;
import edu.westga.cs1302.bill.model.BillItem;
import edu.westga.cs1302.bill.model.BillPersistenceManager;

class TestSaveBillData {

	private static final String DATA_FILE = "data.txt";

	@Test
	void testNullBill() {
		TestSaveBillData.clearFiles();
		assertThrows(IllegalArgumentException.class, ()-> { BillPersistenceManager.saveBillData(null);});
		TestSaveBillData.clearFiles();
	}	
	
	// Cannot put null objects in bill tests omitted
	
	@Test
	void testDefaultBill() {
		TestSaveBillData.clearFiles();
		String lineOneStorage = null;
		Bill testBill = new Bill();
		try {
			BillPersistenceManager.saveBillData(testBill);
		} catch (IOException e) {
			// This error shouldn't happen in this case
		}
		File testFile = new File(DATA_FILE);
		try (Scanner reader = new Scanner(testFile);) {
			lineOneStorage = reader.nextLine();
		} catch (FileNotFoundException e) {
			// This error shouldn't happen in this case
		}
		assertEquals("No Server Set", lineOneStorage);
		TestSaveBillData.clearFiles();
	}
	
	@Test
	void testServerNameGiven() {
		TestSaveBillData.clearFiles();
		String lineOneStorage = null;
		Bill testBill = new Bill();
		testBill.setServerName("Bob");
		try {
			BillPersistenceManager.saveBillData(testBill);
		} catch (IOException e) {
			// This error shouldn't happen in this case
		}
		File testFile = new File(DATA_FILE);
		try (Scanner reader = new Scanner(testFile);) {
			lineOneStorage = reader.nextLine();
		} catch (FileNotFoundException e) {
			// This error shouldn't happen in this case
		}
		assertEquals("Bob", lineOneStorage);
		TestSaveBillData.clearFiles();
	}
	
	@Test
	void testOneBillItem() {
		TestSaveBillData.clearFiles();
		String lineOneStorage = null;
		String lineTwoStorage = null;
		BillItem testItem1 = new BillItem("Name1", 1.00);
		Bill testBill = new Bill();
		testBill.addItem(testItem1);
		try {
			BillPersistenceManager.saveBillData(testBill);
		} catch (IOException e) {
			// This error shouldn't happen in this case
		}
		File testFile = new File(DATA_FILE);
		try (Scanner reader = new Scanner(testFile);) {
			lineOneStorage = reader.nextLine();
			lineTwoStorage = reader.nextLine();
		} catch (FileNotFoundException e) {
			// This error shouldn't happen in this case
		}
		assertEquals("No Server Set", lineOneStorage);
		assertEquals("Name1,1.0", lineTwoStorage);
		TestSaveBillData.clearFiles();
	}	
	
	@Test
	void testMultipleBillItems() {
		TestSaveBillData.clearFiles();
		String lineOneStorage = null;
		String lineTwoStorage = null;
		String lineThreeStorage = null;
		String lineFourStorage = null;
		BillItem testItem1 = new BillItem("Name1", 1.00);
		BillItem testItem2 = new BillItem("Name2", 2.00);
		BillItem testItem3 = new BillItem("Name3", 3.00);
		Bill testBill = new Bill();
		testBill.addItem(testItem1);
		testBill.addItem(testItem2);
		testBill.addItem(testItem3);
		try {
			BillPersistenceManager.saveBillData(testBill);
		} catch (IOException e) {
			// This error shouldn't happen in this case
		}
		File testFile = new File(DATA_FILE);
		try (Scanner reader = new Scanner(testFile);) {
			lineOneStorage = reader.nextLine();
			lineTwoStorage = reader.nextLine();
			lineThreeStorage = reader.nextLine();
			lineFourStorage = reader.nextLine();
		} catch (FileNotFoundException e) {
			// This error shouldn't happen in this case
		}
		assertEquals("No Server Set", lineOneStorage);
		assertEquals("Name1,1.0", lineTwoStorage);
		assertEquals("Name2,2.0", lineThreeStorage);
		assertEquals("Name3,3.0", lineFourStorage);
		TestSaveBillData.clearFiles();
	}
	
	//Non-test used to clear files before and after the tests are run
	static void clearFiles() {
	File fileCleaner = new File(DATA_FILE);
	fileCleaner.delete();
	}
}
