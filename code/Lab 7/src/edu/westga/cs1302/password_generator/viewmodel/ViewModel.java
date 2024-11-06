package edu.westga.cs1302.password_generator.viewmodel;

import edu.westga.cs1302.password_generator.model.PasswordGenerator;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * The Class MainWindow.
 * 
 * @author Jareth Batty
 * @version Fall 2024
 */
public class ViewModel {
	private BooleanProperty digitsProperty;
	private BooleanProperty lowerProperty;
	private BooleanProperty upperProperty;
	private StringProperty lengthProperty;
	private StringProperty outputProperty;
	
	private PasswordGenerator generator;

	/**
	 * Instantiates a new student info view model.
	 */
	public ViewModel() {
		this.digitsProperty = new SimpleBooleanProperty();
		this.lowerProperty = new SimpleBooleanProperty();
		this.upperProperty = new SimpleBooleanProperty();
		this.lengthProperty = new SimpleStringProperty();
		this.outputProperty = new SimpleStringProperty();
	}

	/**
	 * Gets the digits property.
	 *
	 * @return the digitsProperty
	 */
	public BooleanProperty nameProperty() {
		return this.digitsProperty;
	}

	/**
	 * Gets the lower property.
	 *
	 * @return the lowerProperty
	 */
	public BooleanProperty lowerProperty() {
		return this.lowerProperty;
	}
	
	/**
	 * Gets the upper property.
	 *
	 * @return the upperProperty
	 */
	public BooleanProperty upperProperty() {
		return this.upperProperty;
	}

	/**
	 * Gets the length property.
	 *
	 * @return the lengthProperty
	 */
	public StringProperty lengthProperty() {
		return this.lengthProperty;
	}

	/**
	 * Feeds password generating information from the GUI to the model
	 */
	public void viewModelGeneratePassword() {
    	int minimumLength = -1;
    	
    	try {
    		minimumLength = Integer.parseInt(this.lengthProperty.get());
    	} catch (NumberFormatException numberError) {
    		return;
    	}
    	
    	try {
    		this.generator.setMinimumLength(minimumLength);
    	} catch (IllegalArgumentException invalidLengthError) {
    		return;
    	}
    	
    	this.generator.setMustHaveAtLeastOneDigit(this.digitsProperty.get());
    	this.generator.setMustHaveAtLeastOneLowerCaseLetter(this.lowerProperty.get());
    	this.generator.setMustHaveAtLeastOneUpperCaseLetter(this.upperProperty.get());
    	
    	String password = this.generator.generatePassword();
    	
    	this.outputProperty.set(password);
    }
}

