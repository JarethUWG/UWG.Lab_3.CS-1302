package edu.westga.cs1302.project3.test.model.taskpersistencemanager;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.project3.model.TaskPersistenceManager;

class TestConstructor {
	private static final String DATA_FILE = "tasks.txt";
	
	@Test
	void testNullPath() {
		assertThrows(IllegalArgumentException.class, ()->{new TaskPersistenceManager(null);});
	}
	
	@Test
	void testValidPath() {
		TaskPersistenceManager testPersistence = new TaskPersistenceManager(DATA_FILE);
		assertNotNull(testPersistence);
	}

}
