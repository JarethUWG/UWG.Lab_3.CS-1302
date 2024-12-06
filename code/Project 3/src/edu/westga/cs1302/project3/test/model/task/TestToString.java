package edu.westga.cs1302.project3.test.model.task;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.project3.model.Task;

class TestToString {

	@Test
	void testToStringCall() {
		Task testTask = new Task("Title", "Description");
		assertEquals("Title", testTask.toString());
	}

}
