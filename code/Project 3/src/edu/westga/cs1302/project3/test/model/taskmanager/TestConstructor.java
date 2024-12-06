package edu.westga.cs1302.project3.test.model.taskmanager;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.project3.model.Task;
import edu.westga.cs1302.project3.model.TaskManager;


class TestConstructor {

	@Test
	void testNullTasks() {
		TaskManager testManager = new TaskManager(null);
		assertTrue(testManager.getTasks().isEmpty());
		assertTrue(testManager.getLookUpTasks().isEmpty());
	}
	
	@Test
	void testEmptyTasks() {
		ArrayList<Task> testTasks = new ArrayList<Task>();
		TaskManager testManager = new TaskManager(testTasks);
		assertTrue(testManager.getTasks().isEmpty());
		assertTrue(testManager.getLookUpTasks().isEmpty());
	}
	
	@Test
	void testOneTask() {
		ArrayList<Task> testTasks = new ArrayList<Task>();
		Task testTask1 = new Task("task1", "First");
		testTasks.add(testTask1);
		TaskManager testManager = new TaskManager(testTasks);
		assertEquals(1, testManager.getTasks().size());
		assertEquals(1, testManager.getLookUpTasks().size());
		assertEquals("task1", testManager.getTasks().get(0).getTitle());
		assertEquals("First", testManager.getTasks().get(0).getDescription());
		assertEquals("task1", testManager.getLookUpTasks().get("task1").getTitle());
		assertEquals("First", testManager.getLookUpTasks().get("task1").getDescription());
	}
	
	@Test
	void testMultipleTasks() {
		ArrayList<Task> testTasks = new ArrayList<Task>();
		Task testTask1 = new Task("task1", "First");
		Task testTask2 = new Task("task2", "Second");
		Task testTask3 = new Task("task3", "Third");
		testTasks.add(testTask1);
		testTasks.add(testTask2);
		testTasks.add(testTask3);
		TaskManager testManager = new TaskManager(testTasks);
		assertEquals(3, testManager.getTasks().size());
		assertEquals(3, testManager.getLookUpTasks().size());
		assertEquals("task1", testManager.getTasks().get(0).getTitle());
		assertEquals("First", testManager.getTasks().get(0).getDescription());
		assertEquals("task1", testManager.getLookUpTasks().get("task1").getTitle());
		assertEquals("First", testManager.getLookUpTasks().get("task1").getDescription());
		assertEquals("task2", testManager.getTasks().get(1).getTitle());
		assertEquals("Second", testManager.getTasks().get(1).getDescription());
		assertEquals("task2", testManager.getLookUpTasks().get("task2").getTitle());
		assertEquals("Second", testManager.getLookUpTasks().get("task2").getDescription());
		assertEquals("task3", testManager.getTasks().get(2).getTitle());
		assertEquals("Third", testManager.getTasks().get(2).getDescription());
		assertEquals("task3", testManager.getLookUpTasks().get("task3").getTitle());
		assertEquals("Third", testManager.getLookUpTasks().get("task3").getDescription());
	}
	
	@Test
	void testRepeatTaskTitle() {
		ArrayList<Task> testTasks = new ArrayList<Task>();
		Task testTask1 = new Task("task1", "First");
		Task testTask2 = new Task("task2.5", "Second");
		Task testTask3 = new Task("task2.5", "Third");
		testTasks.add(testTask1);
		testTasks.add(testTask2);
		testTasks.add(testTask3);		
		assertThrows(IllegalArgumentException.class, ()->{new TaskManager(testTasks);});
	}

}
