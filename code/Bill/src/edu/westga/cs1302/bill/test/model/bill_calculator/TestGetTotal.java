package edu.westga.cs1302.bill.test.model.bill_calculator;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.bill.model.BillCalculator;
import edu.westga.cs1302.bill.model.BillItem;

class TestGetTotal {

	@Test
	void testWhenPassedNull() {
		assertThrows(NullPointerException.class, () -> {
			BillCalculator.getTotal(null);
		});
	}
	
	@Test
	void testWhenPassedEmptyArray() {
		BillItem[] items = new BillItem[3];
		assertEquals(0.0, BillCalculator.getTotal(items), 0.001);
	}
	
	@Test
	void testWhenPassedOneItemArray() {
		BillItem[] items = new BillItem[3];
		BillItem item1 = new BillItem("thing1", 10.5);
		items[0] = item1;
		assertEquals(13.65, BillCalculator.getTotal(items), 0.001);
	}
	
	@Test
	void testWhenPassedTwoItemArray() {
		BillItem[] items = new BillItem[3];
		BillItem item1 = new BillItem("thing1", 10.5);
		BillItem item2 = new BillItem("thing2", 5.1);
		items[0] = item1;
		items[1] = item2;
		assertEquals(20.28, BillCalculator.getTotal(items), 0.001);
	}
	
	@Test
	void testWhenPassedThreeItemArray() {
		BillItem[] items = new BillItem[3];
		BillItem item1 = new BillItem("thing1", 10.5);
		BillItem item2 = new BillItem("thing2", 5.1);
		BillItem item3 = new BillItem("thing3", 15.4);
		items[0] = item1;
		items[1] = item2;
		items[2] = item3;
		assertEquals(40.3, BillCalculator.getTotal(items), 0.001);
	}

}
