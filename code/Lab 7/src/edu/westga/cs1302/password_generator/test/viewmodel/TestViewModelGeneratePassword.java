package edu.westga.cs1302.password_generator.test.viewmodel;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.password_generator.viewmodel.ViewModel;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;

class TestViewModelGeneratePassword {

	@Test
	void whenPassedBadLength() {
		ViewModel testModel = new ViewModel();
		SimpleBooleanProperty digitsProperty = new SimpleBooleanProperty();
		SimpleBooleanProperty lowerProperty = new SimpleBooleanProperty();
		SimpleBooleanProperty upperProperty = new SimpleBooleanProperty();
		SimpleStringProperty lengthProperty = new SimpleStringProperty();
		SimpleStringProperty outputProperty = new SimpleStringProperty();
		digitsProperty.bindBidirectional(testModel.digitsProperty());
		lowerProperty.bindBidirectional(testModel.lowerProperty());
		upperProperty.bindBidirectional(testModel.upperProperty());
		lengthProperty.bindBidirectional(testModel.lengthProperty());
		outputProperty.bindBidirectional(testModel.outputProperty());
		digitsProperty.set(false);
		lowerProperty.set(false);
		upperProperty.set(false);
		lengthProperty.set("Bad");
		outputProperty.set("");
		testModel.viewModelGeneratePassword();
		String result = testModel.outputProperty().get();
		assertEquals("", result);
	}
	
	@Test
	void whenPassedOutOfRangeLength() {
		ViewModel testModel = new ViewModel();
		SimpleBooleanProperty digitsProperty = new SimpleBooleanProperty();
		SimpleBooleanProperty lowerProperty = new SimpleBooleanProperty();
		SimpleBooleanProperty upperProperty = new SimpleBooleanProperty();
		SimpleStringProperty lengthProperty = new SimpleStringProperty();
		SimpleStringProperty outputProperty = new SimpleStringProperty();
		digitsProperty.bindBidirectional(testModel.digitsProperty());
		lowerProperty.bindBidirectional(testModel.lowerProperty());
		upperProperty.bindBidirectional(testModel.upperProperty());
		lengthProperty.bindBidirectional(testModel.lengthProperty());
		outputProperty.bindBidirectional(testModel.outputProperty());
		digitsProperty.set(false);
		lowerProperty.set(false);
		upperProperty.set(false);
		lengthProperty.set("0");
		outputProperty.set("");
		testModel.viewModelGeneratePassword();
		String result = testModel.outputProperty().get();
		assertEquals("", result);
	}
	
	@Test
	void whenDigitsOnly() {
		ViewModel testModel = new ViewModel();
		SimpleBooleanProperty digitsProperty = new SimpleBooleanProperty();
		SimpleBooleanProperty lowerProperty = new SimpleBooleanProperty();
		SimpleBooleanProperty upperProperty = new SimpleBooleanProperty();
		SimpleStringProperty lengthProperty = new SimpleStringProperty();
		SimpleStringProperty outputProperty = new SimpleStringProperty();
		digitsProperty.bindBidirectional(testModel.digitsProperty());
		lowerProperty.bindBidirectional(testModel.lowerProperty());
		upperProperty.bindBidirectional(testModel.upperProperty());
		lengthProperty.bindBidirectional(testModel.lengthProperty());
		outputProperty.bindBidirectional(testModel.outputProperty());
		digitsProperty.set(true);
		lowerProperty.set(false);
		upperProperty.set(false);
		lengthProperty.set("1");
		outputProperty.set("");
		testModel.viewModelGeneratePassword();
		boolean containsNumbers = testModel.outputProperty().get().matches("\\d");
		int length = testModel.outputProperty().get().length();
		assertTrue(containsNumbers);
		assertEquals(1, length);
	}
	
	@Test
	void whenLowerOnly() {
		ViewModel testModel = new ViewModel();
		SimpleBooleanProperty digitsProperty = new SimpleBooleanProperty();
		SimpleBooleanProperty lowerProperty = new SimpleBooleanProperty();
		SimpleBooleanProperty upperProperty = new SimpleBooleanProperty();
		SimpleStringProperty lengthProperty = new SimpleStringProperty();
		SimpleStringProperty outputProperty = new SimpleStringProperty();
		digitsProperty.bindBidirectional(testModel.digitsProperty());
		lowerProperty.bindBidirectional(testModel.lowerProperty());
		upperProperty.bindBidirectional(testModel.upperProperty());
		lengthProperty.bindBidirectional(testModel.lengthProperty());
		outputProperty.bindBidirectional(testModel.outputProperty());
		digitsProperty.set(false);
		lowerProperty.set(true);
		upperProperty.set(false);
		lengthProperty.set("1");
		outputProperty.set("");
		testModel.viewModelGeneratePassword();
		boolean containsLowers = testModel.outputProperty().get().matches("[a-z]");
		int length = testModel.outputProperty().get().length();
		assertTrue(containsLowers);
		assertEquals(1, length);
	}
	
	@Test
	void whenUppersOnly() {
		ViewModel testModel = new ViewModel();
		SimpleBooleanProperty digitsProperty = new SimpleBooleanProperty();
		SimpleBooleanProperty lowerProperty = new SimpleBooleanProperty();
		SimpleBooleanProperty upperProperty = new SimpleBooleanProperty();
		SimpleStringProperty lengthProperty = new SimpleStringProperty();
		SimpleStringProperty outputProperty = new SimpleStringProperty();
		digitsProperty.bindBidirectional(testModel.digitsProperty());
		lowerProperty.bindBidirectional(testModel.lowerProperty());
		upperProperty.bindBidirectional(testModel.upperProperty());
		lengthProperty.bindBidirectional(testModel.lengthProperty());
		outputProperty.bindBidirectional(testModel.outputProperty());
		digitsProperty.set(false);
		lowerProperty.set(false);
		upperProperty.set(true);
		lengthProperty.set("1");
		outputProperty.set("");
		testModel.viewModelGeneratePassword();
		boolean containsUppers = testModel.outputProperty().get().matches("[A-Z]");
		int length = testModel.outputProperty().get().length();
		assertTrue(containsUppers);
		assertEquals(1, length);
	}
	
	@Test
	void whenAllCases() {
		ViewModel testModel = new ViewModel();
		SimpleBooleanProperty digitsProperty = new SimpleBooleanProperty();
		SimpleBooleanProperty lowerProperty = new SimpleBooleanProperty();
		SimpleBooleanProperty upperProperty = new SimpleBooleanProperty();
		SimpleStringProperty lengthProperty = new SimpleStringProperty();
		SimpleStringProperty outputProperty = new SimpleStringProperty();
		digitsProperty.bindBidirectional(testModel.digitsProperty());
		lowerProperty.bindBidirectional(testModel.lowerProperty());
		upperProperty.bindBidirectional(testModel.upperProperty());
		lengthProperty.bindBidirectional(testModel.lengthProperty());
		outputProperty.bindBidirectional(testModel.outputProperty());
		digitsProperty.set(true);
		lowerProperty.set(true);
		upperProperty.set(true);
		lengthProperty.set("3");
		outputProperty.set("");
		testModel.viewModelGeneratePassword();
		boolean containsNumbers = testModel.outputProperty().get().matches(".*\\d.*");
		boolean containsLowers = testModel.outputProperty().get().matches(".*[a-z].*");
		boolean containsUppers = testModel.outputProperty().get().matches(".*[A-Z].*");
		boolean length = (3 <= testModel.outputProperty().get().length());
		assertTrue(containsNumbers);
		assertTrue(containsLowers);
		assertTrue(containsUppers);
		assertTrue(length);
	}

}
