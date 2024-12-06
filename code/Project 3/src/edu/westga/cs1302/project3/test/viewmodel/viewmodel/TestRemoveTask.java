package edu.westga.cs1302.project3.test.viewmodel.viewmodel;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.project3.model.Task;
import edu.westga.cs1302.project3.viewmodel.ViewModel;

class TestRemoveTask {

	@Test
	void testNullSelectedTask() {
		ViewModel vm = new ViewModel();
		vm.getSelectedTask().set(null);
		assertThrows(IllegalStateException.class, ()->{vm.removeTask();});
	}
	
	@Test
	void testVaildSelectedTask() {
		ViewModel vm = new ViewModel();
		Task testTask1 = vm.getTasks().get(0);
		vm.getSelectedTask().set(testTask1);
		vm.removeTask();
		assertEquals("Remove Tasks", vm.getTasks().getValue().get(0).getTitle());
		assertEquals("Start removing tasks using the - button", vm.getTasks().getValue().get(0).getDescription());
	}
}
