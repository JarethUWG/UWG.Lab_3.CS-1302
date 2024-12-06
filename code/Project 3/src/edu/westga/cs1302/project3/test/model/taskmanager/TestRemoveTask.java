package edu.westga.cs1302.project3.test.model.taskmanager;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.project3.model.Task;
import edu.westga.cs1302.project3.model.TaskManager;

class TestRemoveTask {

	@Test
	void testNullTask() {
		TaskManager testManager = new TaskManager(null);
		assertThrows(IllegalArgumentException.class, ()->{testManager.removeTask(null);});
	}
	
	@Test
	void testRemoveTaskNotInManager() {
		TaskManager testManager = new TaskManager(null);
		Task testTask1 = new Task("task1", "First");
		Task testTask2 = new Task("task2", "Second");
		testManager.addTask(testTask1);
		assertThrows(IllegalArgumentException.class, ()->{testManager.removeTask(testTask2);});
	}
	
	@Test
	void testValidRemoval() {
		TaskManager testManager = new TaskManager(null);
		Task testTask1 = new Task("task1", "First");
		Task testTask2 = new Task("task2", "Second");
		testManager.addTask(testTask1);
		testManager.addTask(testTask2);
		testManager.removeTask(testTask1);
		assertEquals(1, testManager.getTasks().size());
		assertEquals(1, testManager.getLookUpTasks().size());
		assertEquals("task2", testManager.getTasks().get(0).getTitle());
		assertEquals("Second", testManager.getTasks().get(0).getDescription());
		assertEquals("task2", testManager.getLookUpTasks().get("task2").getTitle());
		assertEquals("Second", testManager.getLookUpTasks().get("task2").getDescription());
	}

}
