package edu.westga.cs1302.cms.test.model.student;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.cms.model.Student;

class TestConstructor {

	@Test
	void testNullName() {
		assertThrows(IllegalArgumentException.class, ()->{new Student(null, 90);});
	}

	@Test
	void testNameTooShort() {
		assertThrows(IllegalArgumentException.class, ()->{new Student("12", 90);});
	}
	
	@Test
	void testMinimumLengthName() {
		Student result = new Student("123", 90);
		
		assertEquals("123", result.getName(), "checking the name of the student");
		assertEquals(90, result.getGrade(), "checking the grade of the student");
	}
	
	@Test
	void testAboveMinimumLengthName() {
		Student result = new Student("1234", 90);
		
		assertEquals("1234", result.getName(), "checking the name of the student");
		assertEquals(90, result.getGrade(), "checking the grade of the student");
	}
	
	@Test
	void testGradeOneBelowMinimumBoundary() {
		assertThrows(IllegalArgumentException.class, ()->{new Student("123", -1);});
	}
	
	@Test
	void testGradeAtMinimumBoundary() {
		Student result = new Student("123", 0);
		
		assertEquals("123", result.getName(), "checking the name of the student");
		assertEquals(0, result.getGrade(), "checking the grade of the student");
	}
	
	@Test
	void testGradeOneAboveMinimumBoundary() {
		Student result = new Student("123", 1);
		
		assertEquals("123", result.getName(), "checking the name of the student");
		assertEquals(1, result.getGrade(), "checking the grade of the student");
	}
	
	@Test
	void testGradeOneBelowMaximumBoundary() {
		Student result = new Student("123", 99);
		
		assertEquals("123", result.getName(), "checking the name of the student");
		assertEquals(99, result.getGrade(), "checking the grade of the student");
	}
	
	@Test
	void testGradeAtMaximumBoundary() {
		Student result = new Student("123", 100);
		
		assertEquals("123", result.getName(), "checking the name of the student");
		assertEquals(100, result.getGrade(), "checking the grade of the student");
	}
	
	@Test
	void testGradeOneAboveMaximumBoundary() {
		assertThrows(IllegalArgumentException.class, ()->{new Student("123", 101);});
	}
}
