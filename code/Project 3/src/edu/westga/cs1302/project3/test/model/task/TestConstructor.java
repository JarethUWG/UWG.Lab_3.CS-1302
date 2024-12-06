package edu.westga.cs1302.project3.test.model.task;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.project3.model.Task;

public class TestConstructor {
	
	@Test
	void testNullTitle() {
		assertThrows(IllegalArgumentException.class, ()->{new Task(null, "Description");});
	}
	
	@Test
	void testNullDescription() {
		assertThrows(IllegalArgumentException.class, ()->{new Task("Title", null);});
	}
	
	@Test
	void testBlankTitle() {
		assertThrows(IllegalArgumentException.class, ()->{new Task(" ", "Description");});
	}
	
	@Test
	void testBlankDescription() {
		assertThrows(IllegalArgumentException.class, ()->{new Task("Title", " ");});
	}
	
	@Test
	void testEmptyTitle() {
		assertThrows(IllegalArgumentException.class, ()->{new Task("", "Description");});
	}
	
	@Test
	void testEmptyDescription() {
		assertThrows(IllegalArgumentException.class, ()->{new Task("Title", "");});
	}
	
	@Test
	void testValidInput() {
		Task testTask = new Task("Title", "Description");
		assertEquals("Title", testTask.getTitle());
		assertEquals("Description", testTask.getDescription());
	}

}
