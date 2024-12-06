package edu.westga.cs1302.project3.test.model.taskpersistencemanager;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.project3.model.Task;
import edu.westga.cs1302.project3.model.TaskManager;
import edu.westga.cs1302.project3.model.TaskPersistenceManager;

class TestLoadTaskData {
	private static final String DATA_FILE = "tasks.txt";
	
	@Test
	void testTooHighImproperFormatting() {
		File fileRemover = new File(DATA_FILE);
		fileRemover.delete();
		try (FileWriter writer = new FileWriter(DATA_FILE)) {
			writer.write("Title\tDescription\tExcessText");
		} catch (IOException shouldNotHapped) {
			
		}
		TaskPersistenceManager testPersistence = new TaskPersistenceManager(DATA_FILE);
		assertThrows(ArrayIndexOutOfBoundsException.class, ()->{testPersistence.loadTaskData();});
		fileRemover.delete();
	}
	
	@Test
	void testTooLowImproperFormatting() {
		File fileRemover = new File(DATA_FILE);
		fileRemover.delete();
		try (FileWriter writer = new FileWriter(DATA_FILE)) {
			writer.write("Title\t");
		} catch (IOException shouldNotHapped) {
			
		}
		TaskPersistenceManager testPersistence = new TaskPersistenceManager(DATA_FILE);
		assertThrows(ArrayIndexOutOfBoundsException.class, ()->{testPersistence.loadTaskData();});
		fileRemover.delete();
	}
	
	@Test
	void testLoadOneTask() {
		File fileRemover = new File(DATA_FILE);
		fileRemover.delete();
		try (FileWriter writer = new FileWriter(DATA_FILE)) {
			writer.write("Title\tDescription");
		} catch (IOException shouldNotHapped) {
			
		}
		TaskPersistenceManager testPersistence = new TaskPersistenceManager(DATA_FILE);
		TaskManager testManager = testPersistence.loadTaskData();
		Task resultTask1 = testManager.getTasks().get(0);
		assertEquals("Title", resultTask1.getTitle());
		assertEquals("Description", resultTask1.getDescription());
		fileRemover.delete();
	}
	
	@Test
	void testLoadMultipleTasks() {
		File fileRemover = new File(DATA_FILE);
		fileRemover.delete();
		try (FileWriter writer = new FileWriter(DATA_FILE)) {
			writer.write("Title1\tDescription1\nTitle2\tDescription2\nTitle3\tDescription3");
		} catch (IOException shouldNotHapped) {
			
		}
		TaskPersistenceManager testPersistence = new TaskPersistenceManager(DATA_FILE);
		TaskManager testManager = testPersistence.loadTaskData();
		Task resultTask1 = testManager.getTasks().get(0);
		Task resultTask2 = testManager.getTasks().get(1);
		Task resultTask3 = testManager.getTasks().get(2);
		assertEquals("Title1", resultTask1.getTitle());
		assertEquals("Description1", resultTask1.getDescription());
		assertEquals("Title2", resultTask2.getTitle());
		assertEquals("Description2", resultTask2.getDescription());
		assertEquals("Title3", resultTask3.getTitle());
		assertEquals("Description3", resultTask3.getDescription());
		fileRemover.delete();
	}
		
}

