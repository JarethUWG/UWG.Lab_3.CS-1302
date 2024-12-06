package edu.westga.cs1302.project3.test.model.taskmanager;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.project3.model.Task;
import edu.westga.cs1302.project3.model.TaskManager;

class TestAddTask {

	@Test
	void testNullTask() {
		TaskManager testManager = new TaskManager(null);
		assertThrows(IllegalArgumentException.class, ()->{testManager.addTask(null);});
	}
	
	@Test
	void testRepeatedTitleTasks() {
		TaskManager testManager = new TaskManager(null);
		Task testTask1 = new Task("task1.5", "First");
		Task testTask2 = new Task("task1.5", "Second");
		testManager.addTask(testTask1);
		assertThrows(IllegalStateException.class, ()->{testManager.addTask(testTask2);});
	}
	
	@Test
	void testRepeatedDescriptionTasks() {
		TaskManager testManager = new TaskManager(null);
		Task testTask1 = new Task("task1", "Description");
		Task testTask2 = new Task("task2", "Description");
		testManager.addTask(testTask1);
		testManager.addTask(testTask2);
		assertEquals(2, testManager.getTasks().size());
		assertEquals(2, testManager.getLookUpTasks().size());
		assertEquals("task2", testManager.getTasks().get(0).getTitle());
		assertEquals("Description", testManager.getTasks().get(0).getDescription());
		assertEquals("task1", testManager.getLookUpTasks().get("task1").getTitle());
		assertEquals("Description", testManager.getLookUpTasks().get("task1").getDescription());
		assertEquals("task1", testManager.getTasks().get(1).getTitle());
		assertEquals("Description", testManager.getTasks().get(1).getDescription());
		assertEquals("task2", testManager.getLookUpTasks().get("task2").getTitle());
		assertEquals("Description", testManager.getLookUpTasks().get("task2").getDescription());
	}
	
	@Test
	void testVaildTasks() {
		TaskManager testManager = new TaskManager(null);
		Task testTask1 = new Task("task1", "First");
		Task testTask2 = new Task("task2", "Second");
		testManager.addTask(testTask1);
		testManager.addTask(testTask2);
		assertEquals(2, testManager.getTasks().size());
		assertEquals(2, testManager.getLookUpTasks().size());
		assertEquals("task2", testManager.getTasks().get(0).getTitle());
		assertEquals("Second", testManager.getTasks().get(0).getDescription());
		assertEquals("task1", testManager.getLookUpTasks().get("task1").getTitle());
		assertEquals("First", testManager.getLookUpTasks().get("task1").getDescription());
		assertEquals("task1", testManager.getTasks().get(1).getTitle());
		assertEquals("First", testManager.getTasks().get(1).getDescription());
		assertEquals("task2", testManager.getLookUpTasks().get("task2").getTitle());
		assertEquals("Second", testManager.getLookUpTasks().get("task2").getDescription());
	}

}
