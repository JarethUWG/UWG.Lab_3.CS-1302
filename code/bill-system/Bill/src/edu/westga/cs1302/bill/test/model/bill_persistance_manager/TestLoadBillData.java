package edu.westga.cs1302.bill.test.model.bill_persistance_manager;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.bill.model.Bill;
import edu.westga.cs1302.bill.model.BillItem;
import edu.westga.cs1302.bill.model.BillPersistenceManager;

class TestLoadBillData {

	@Test
	void testWhenNoFile() {
		TestSaveBillData.clearFiles();
		assertThrows(FileNotFoundException.class, ()-> {BillPersistenceManager.loadBillData();});
		TestSaveBillData.clearFiles();
	}
	
	@Test
	void testDefaultBill() {
		TestSaveBillData.clearFiles();
		Bill inputBill = new Bill();
		Bill outputBill = new Bill();
		try {
			BillPersistenceManager.saveBillData(inputBill);
		} catch (IllegalArgumentException e) {
			// This error shouldn't happen in this case
			e.printStackTrace();
		} catch (IOException e) {
			// This error shouldn't happen in this case
			e.printStackTrace();
		}
		try {
			outputBill = BillPersistenceManager.loadBillData();
		} catch (FileNotFoundException e) {
			// This error shouldn't happen in this case
			e.printStackTrace();
		} catch (IOException e) {
			// This error shouldn't happen in this case
			e.printStackTrace();
		}
		assertEquals("No Server Set", outputBill.getServerName());
		assertEquals(0, outputBill.getSize());
		TestSaveBillData.clearFiles();
	}
	
	@Test
	void testServerNameGiven() {
		TestSaveBillData.clearFiles();
		Bill inputBill = new Bill();
		Bill outputBill = new Bill();
		inputBill.setServerName("Bob");
		try {
			BillPersistenceManager.saveBillData(inputBill);
		} catch (IllegalArgumentException e) {
			// This error shouldn't happen in this case
			e.printStackTrace();
		} catch (IOException e) {
			// This error shouldn't happen in this case
			e.printStackTrace();
		}
		try {
			outputBill = BillPersistenceManager.loadBillData();
		} catch (FileNotFoundException e) {
			// This error shouldn't happen in this case
			e.printStackTrace();
		} catch (IOException e) {
			// This error shouldn't happen in this case
			e.printStackTrace();
		}
		assertEquals("Bob", outputBill.getServerName());
		assertEquals(0, outputBill.getSize());
		TestSaveBillData.clearFiles();
	}
	
	@Test
	void testOneBillItem() {
		TestSaveBillData.clearFiles();
		BillItem testItem1 = new BillItem("Name1", 1.00);
		Bill inputBill = new Bill();
		Bill outputBill = new Bill();
		inputBill.addItem(testItem1);
		try {
			BillPersistenceManager.saveBillData(inputBill);
		} catch (IllegalArgumentException e) {
			// This error shouldn't happen in this case
			e.printStackTrace();
		} catch (IOException e) {
			// This error shouldn't happen in this case
			e.printStackTrace();
		}
		try {
			outputBill = BillPersistenceManager.loadBillData();
		} catch (FileNotFoundException e) {
			// This error shouldn't happen in this case
			e.printStackTrace();
		} catch (IOException e) {
			// This error shouldn't happen in this case
			e.printStackTrace();
		}
		assertEquals("No Server Set", outputBill.getServerName());
		assertEquals(1, outputBill.getSize());
		assertEquals("Name1", outputBill.getItems()[0].getName());
		assertEquals(1.0, outputBill.getItems()[0].getAmount());
		TestSaveBillData.clearFiles();
	}
	
	@Test
	void testManyBillItems() {
		TestSaveBillData.clearFiles();
		BillItem testItem1 = new BillItem("Name1", 1.00);
		BillItem testItem2 = new BillItem("Name2", 2.00);
		BillItem testItem3 = new BillItem("Name3", 3.00);
		Bill inputBill = new Bill();
		Bill outputBill = new Bill();
		inputBill.addItem(testItem1);
		inputBill.addItem(testItem2);
		inputBill.addItem(testItem3);
		try {
			BillPersistenceManager.saveBillData(inputBill);
		} catch (IllegalArgumentException e) {
			// This error shouldn't happen in this case
			e.printStackTrace();
		} catch (IOException e) {
			// This error shouldn't happen in this case
			e.printStackTrace();
		}
		try {
			outputBill = BillPersistenceManager.loadBillData();
		} catch (FileNotFoundException e) {
			// This error shouldn't happen in this case
			e.printStackTrace();
		} catch (IOException e) {
			// This error shouldn't happen in this case
			e.printStackTrace();
		}
		assertEquals("No Server Set", outputBill.getServerName());
		assertEquals(3, outputBill.getSize());
		assertEquals("Name1", outputBill.getItems()[0].getName());
		assertEquals(1.0, outputBill.getItems()[0].getAmount());
		assertEquals("Name2", outputBill.getItems()[1].getName());
		assertEquals(2.0, outputBill.getItems()[1].getAmount());
		assertEquals("Name3", outputBill.getItems()[2].getName());
		assertEquals(3.0, outputBill.getItems()[2].getAmount());
		TestSaveBillData.clearFiles();
	}
	
	

}
