package edu.westga.cs1302.password_generator.test.viewmodel;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.password_generator.viewmodel.ViewModel;

public class TestConstructor {
	
	@Test
	void standardCall() {
		ViewModel testModel = new ViewModel();
		assertFalse(testModel.digitsProperty().get());
		assertFalse(testModel.lowerProperty().get());
		assertFalse(testModel.upperProperty().get());
		assertNull(testModel.lengthProperty().get());
		assertNull(testModel.outputProperty().get());
	}
}
