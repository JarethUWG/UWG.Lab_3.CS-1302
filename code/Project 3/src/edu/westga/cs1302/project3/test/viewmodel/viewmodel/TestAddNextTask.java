package edu.westga.cs1302.project3.test.viewmodel.viewmodel;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.project3.viewmodel.ViewModel;

class TestAddNextTask {

	@Test
	void testNullTitle() {
		ViewModel vm = new ViewModel();
		vm.getTaskDescription().setValue("First");
		assertThrows(IllegalArgumentException.class, ()->{vm.addNextTask();});
	}
	
	@Test
	void testNullDescription() {
		ViewModel vm = new ViewModel();
		vm.getTaskTitle().setValue("testTask1");
		assertThrows(IllegalArgumentException.class, ()->{vm.addNextTask();});
	}
	
	@Test
	void testRepeatTitle() {
		ViewModel vm = new ViewModel();
		vm.getTaskTitle().setValue("Add Tasks");
		vm.getTaskDescription().setValue("First");
		assertThrows(IllegalStateException.class, ()->{vm.addNextTask();});
	}
	
	@Test
	void testValidAddition() {
		ViewModel vm = new ViewModel();
		vm.getTaskTitle().setValue("testTask1");
		vm.getTaskDescription().setValue("First");
		vm.addNextTask();
		assertEquals("testTask1", vm.getTasks().getValue().get(0).getTitle());
		assertEquals("First", vm.getTasks().getValue().get(0).getDescription());
	}

}
