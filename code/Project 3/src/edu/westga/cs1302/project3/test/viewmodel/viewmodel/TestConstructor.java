package edu.westga.cs1302.project3.test.viewmodel.viewmodel;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.project3.viewmodel.ViewModel;

class TestConstructor {

	@Test
	void testConstructor() {
		ViewModel vm = new ViewModel();
		assertNotNull(vm.getLoadFile());
		assertNotNull(vm.getSaveFile());
		assertNotNull(vm.getSelectedTask());
		assertNotNull(vm.getTasks());
		assertNotNull(vm.getTaskTitle());
		assertNotNull(vm.getTaskDescription());
		assertEquals("Add Tasks", vm.getTasks().get(0).getTitle());
		assertEquals("Start adding tasks using the + button", vm.getTasks().get(0).getDescription());
		assertEquals("Remove Tasks", vm.getTasks().get(1).getTitle());
		assertEquals("Start removing tasks using the - button", vm.getTasks().get(1).getDescription());
	}

}
