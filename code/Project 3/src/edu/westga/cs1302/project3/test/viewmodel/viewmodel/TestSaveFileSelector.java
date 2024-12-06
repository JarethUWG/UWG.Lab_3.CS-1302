package edu.westga.cs1302.project3.test.viewmodel.viewmodel;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.project3.viewmodel.ViewModel;

class TestSaveFileSelector {
	private static final String DATA_FILE = "tasks.txt";

	@Test
	void testFailWhenUnwritable() {
		File fileRemover = new File(DATA_FILE);
		fileRemover.delete();
		File testFile = new File(DATA_FILE);
		try {
			testFile.createNewFile();
		} catch (IOException shouldNotHapped) {
			
		}
		testFile.setReadOnly();
		ViewModel vm = new ViewModel();		
		vm.getSaveFile().setValue(testFile);
		assertFalse(vm.saveFileSelector());
		testFile.delete();
		fileRemover.delete();
	}
	
	@Test
	void testPassWhenWritable() {
		File fileRemover = new File(DATA_FILE);
		fileRemover.delete();
		File testFile = new File(DATA_FILE);
		try {
			testFile.createNewFile();
		} catch (IOException shouldNotHapped) {
			
		}
		ViewModel vm = new ViewModel();		
		vm.getSaveFile().setValue(testFile);
		vm.saveFileSelector();
		String resultString = "";
		try (Scanner reader = new Scanner(testFile)) {
			String baseLine1 = reader.nextLine();
			String baseLine2 = reader.nextLine();
			resultString = baseLine1 + baseLine2;
		} catch (FileNotFoundException shouldNotHappen) {
		}
		String expectedString = "Add Tasks\tStart adding tasks using the + button" + 
		"Remove Tasks\tStart removing tasks using the - button";
		assertEquals(expectedString, resultString);
		assertTrue(vm.saveFileSelector());
		testFile.delete();
		fileRemover.delete();
	}

}
