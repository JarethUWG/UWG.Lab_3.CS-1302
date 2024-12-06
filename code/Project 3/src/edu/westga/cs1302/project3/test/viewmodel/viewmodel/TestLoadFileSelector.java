package edu.westga.cs1302.project3.test.viewmodel.viewmodel;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.project3.viewmodel.ViewModel;

class TestLoadFileSelector {
	private static final String DATA_FILE = "tasks.txt";
	
	@Test
	void testFailWhenOutOfRange() {
		File fileRemover = new File(DATA_FILE);
		fileRemover.delete();
		File testFile = new File(DATA_FILE);
		try {
			testFile.createNewFile();
		} catch (IOException shouldNotHapped) {
			
		}
		ViewModel vm = new ViewModel();
		try (FileWriter writer = new FileWriter(DATA_FILE)) {
			writer.write("Title\tDescription\tExcessText");
		} catch (IOException shouldNotHapped) {
			
		}
		vm.getLoadFile().setValue(testFile);
		assertFalse(vm.loadFileSelector());
		testFile.delete();
		fileRemover.delete();
	}
	
	@Test
	void testPassAndModifiyInRange() {
		File fileRemover = new File(DATA_FILE);
		fileRemover.delete();
		File testFile = new File(DATA_FILE);
		try {
			testFile.createNewFile();
		} catch (IOException shouldNotHapped) {
			
		}
		ViewModel vm = new ViewModel();
		try (FileWriter writer = new FileWriter(DATA_FILE)) {
			writer.write("Title\tDescription");
		} catch (IOException shouldNotHapped) {
			
		}
		vm.getLoadFile().setValue(testFile);
		vm.loadFileSelector();
		assertEquals("Title", vm.getTasks().getValue().get(0).getTitle());
		assertEquals("Description", vm.getTasks().getValue().get(0).getDescription());
		assertTrue(vm.loadFileSelector());
		testFile.delete();
		fileRemover.delete();
	}


}
