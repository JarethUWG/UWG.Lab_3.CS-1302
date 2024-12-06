package edu.westga.cs1302.project3.test.model.taskpersistencemanager;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.project3.model.Task;
import edu.westga.cs1302.project3.model.TaskManager;
import edu.westga.cs1302.project3.model.TaskPersistenceManager;

class TestSaveTaskData {
	private static final String DATA_FILE = "tasks.txt";

	@Test
	void testNullManager() {
		TaskPersistenceManager testPersistence = new TaskPersistenceManager(DATA_FILE);
		assertThrows(IllegalArgumentException.class, ()->{testPersistence.saveTaskData(null);});
	}
	
	@Test
	void testUnwritable() {
		File fileRemover = new File(DATA_FILE);
		fileRemover.delete();
		File testFile = new File(DATA_FILE);
		try {
			testFile.createNewFile();
		} catch (IOException shouldNotHappen) {
		}
		testFile.setReadOnly();
		ArrayList<Task> testTasks = new ArrayList<Task>();
		Task testTask1 = new Task("task1", "First");
		testTasks.add(testTask1);
		TaskManager testManager = new TaskManager(testTasks);
		TaskPersistenceManager testPersistence = new TaskPersistenceManager(DATA_FILE);	
		assertThrows(IOException.class, ()->{testPersistence.saveTaskData(testManager);});
		testFile.delete();
		fileRemover.delete();
	}
	
	@Test
	void testEmptyManager() {
		File fileRemover = new File(DATA_FILE);
		fileRemover.delete();
		File testFile = new File(DATA_FILE);
		TaskManager testManager = new TaskManager(null);
		
		TaskPersistenceManager testPersistence = new TaskPersistenceManager(DATA_FILE);
		try {
			testPersistence.saveTaskData(testManager);
		} catch (Exception shouldNotHappen){		
		}
		try (Scanner reader = new Scanner(testFile)) {
			assertFalse(reader.hasNextLine());
		} catch (Exception shouldNotHappen) {
			
		}
		fileRemover.delete();
	}
	
	@Test
	void testOneTaskManager() {
		File fileRemover = new File(DATA_FILE);
		fileRemover.delete();
		File testFile = new File(DATA_FILE);
		ArrayList<Task> testTasks = new ArrayList<Task>();
		Task testTask1 = new Task("task1", "First");
		testTasks.add(testTask1);
		Task resultTask1 = new Task("Blank", "Blank");
		TaskManager testManager = new TaskManager(testTasks);
		TaskPersistenceManager testPersistence = new TaskPersistenceManager(DATA_FILE);
		try {
			testPersistence.saveTaskData(testManager);
		} catch (Exception shouldNotHappen){		
		}
		try (Scanner reader = new Scanner(testFile)) {
			String baseLine = reader.nextLine();
			String strippedLine = baseLine.strip();
			String[] parts = strippedLine.split("\t");
			resultTask1 = new Task(parts[0], parts[1]);
		} catch (Exception shouldNotHappen) {
			
		}
		assertEquals("task1", resultTask1.getTitle());
		assertEquals("First", resultTask1.getDescription());
		fileRemover.delete();
	}
	
	@Test
	void testMutilpleTasksManager() {
		File fileRemover = new File(DATA_FILE);
		fileRemover.delete();
		File testFile = new File(DATA_FILE);
		ArrayList<Task> testTasks = new ArrayList<Task>();
		Task testTask1 = new Task("task1", "First");
		Task testTask2 = new Task("task2", "Second");
		Task testTask3 = new Task("task3", "Third");
		testTasks.add(testTask1);
		testTasks.add(testTask2);
		testTasks.add(testTask3);
		Task resultTask1 = new Task("Blank", "Blank");
		Task resultTask2 = new Task("Blank", "Blank");
		Task resultTask3 = new Task("Blank", "Blank");
		TaskManager testManager = new TaskManager(testTasks);
		TaskPersistenceManager testPersistence = new TaskPersistenceManager(DATA_FILE);
		try {
			testPersistence.saveTaskData(testManager);
		} catch (Exception shouldNotHappen){		
		}
		try (Scanner reader = new Scanner(testFile)) {
			for (int index = 0; reader.hasNextLine(); index ++) {
				String baseLine = reader.nextLine();
				String strippedLine = baseLine.strip();
				String[] parts = strippedLine.split("\t");
				if (index == 0) {
					resultTask1 = new Task(parts[0], parts[1]);
				} else if (index == 1) {
					resultTask2 = new Task(parts[0], parts[1]);
				} else {
					resultTask3 = new Task(parts[0], parts[1]);
				}
			}
		} catch (Exception shouldNotHappen) {
			
		}
		assertEquals("task1", resultTask1.getTitle());
		assertEquals("First", resultTask1.getDescription());
		assertEquals("task2", resultTask2.getTitle());
		assertEquals("Second", resultTask2.getDescription());
		assertEquals("task3", resultTask3.getTitle());
		assertEquals("Third", resultTask3.getDescription());
		fileRemover.delete();
	}

}
