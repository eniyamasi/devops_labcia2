package com.university;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RegistrationServiceTest {

    @Test
    void shouldRegisterStudentForValidCourse() {

        Student student = new Student("S101", "Anita");

        Course course = new Course(
                "CS301",
                "Data Structures",
                4,
                30,
                true,
                null
        );

        RegistrationService service = new RegistrationService();

        boolean result = service.register(student, course);

        assertTrue(result);
        assertTrue(student.isRegistered("CS301"));
        assertEquals(4, student.getTotalCredits());
        assertEquals(1, course.getCurrentEnrollment());
    }

    @Test
    void shouldRejectInactiveCourse() {

        Student student = new Student("S101", "Anita");

        Course course = new Course(
                "CS301",
                "Data Structures",
                4,
                30,
                false,
                null
        );

        RegistrationService service = new RegistrationService();

        boolean result = service.register(student, course);

        assertFalse(result);
        assertFalse(student.isRegistered("CS301"));
    }

    @Test
    void shouldRejectMissingPrerequisite() {

        Student student = new Student("S101", "Anita");

        Course course = new Course(
                "CS401",
                "Advanced Algorithms",
                4,
                30,
                true,
                "CS301"
        );

        RegistrationService service = new RegistrationService();

        boolean result = service.register(student, course);

        assertFalse(result);
        assertFalse(student.isRegistered("CS401"));
    }

    @Test
    void shouldAllowRegistrationWhenPrerequisiteIsCompleted() {

        Student student = new Student("S101", "Anita");
        student.addCompletedPrerequisite("CS301");

        Course course = new Course(
                "CS401",
                "Advanced Algorithms",
                4,
                30,
                true,
                "CS301"
        );

        RegistrationService service = new RegistrationService();

        boolean result = service.register(student, course);

        assertTrue(result);
        assertTrue(student.isRegistered("CS401"));
    }

    @Test
    void shouldRejectDuplicateRegistration() {

        Student student = new Student("S101", "Anita");

        Course course = new Course(
                "CS301",
                "Data Structures",
                4,
                30,
                true,
                null
        );

        RegistrationService service = new RegistrationService();

        assertTrue(service.register(student, course));

        boolean secondAttempt = service.register(student, course);

        assertFalse(secondAttempt);
        assertEquals(4, student.getTotalCredits());
        assertEquals(1, course.getCurrentEnrollment());
    }

    @Test
    void shouldRejectRegistrationWhenCreditLimitIsExceeded() {

        Student student = new Student("S101", "Anita");

        // Simulate 20 existing credits
        student.addRegistration("CS101", 4);
        student.addRegistration("CS102", 4);
        student.addRegistration("CS201", 4);
        student.addRegistration("CS202", 4);
        student.addRegistration("CS203", 4);
  
        Course course = new Course(
                "CS301",
                "Data Structures",
                4,
                30,
                true,
                null
        );

        RegistrationService service = new RegistrationService();

        boolean result = service.register(student, course);

        assertFalse(result);
        assertFalse(student.isRegistered("CS301"));
    }
    
    @Test
    void shouldAllowRegistrationForLastAvailableSeat() {

        Student student = new Student("S102", "Ravi");

        Course course = new Course(
                "CS302",
                "Operating Systems",
                4,
                30,
                true,
                null
        );

        // Simulate 29 students already enrolled in a course of capacity 30.
        for (int i = 0; i < 29; i++) {
            course.incrementEnrollment();
        }

        RegistrationService service = new RegistrationService();

        boolean result = service.register(student, course);

        assertTrue(result);
        assertTrue(student.isRegistered("CS302"));
        assertEquals(4, student.getTotalCredits());
        assertEquals(30, course.getCurrentEnrollment());
    }

    @Test
    void shouldRejectRegistrationWhenCourseIsFullWithoutChangingState() {

        Student student = new Student("S103", "Priya");

        Course course = new Course(
                "CS303",
                "Database Systems",
                4,
                30,
                true,
                null
        );

        // Simulate a full course.
        for (int i = 0; i < 30; i++) {
            course.incrementEnrollment();
        }

        RegistrationService service = new RegistrationService();

        boolean result = service.register(student, course);

        assertFalse(result);
        assertFalse(student.isRegistered("CS303"));
        assertEquals(0, student.getTotalCredits());
        assertEquals(30, course.getCurrentEnrollment());
    }
    
    @Test
    void shouldAcceptCS301AsCompletedPrerequisite() {
        Student student = new Student("S104", "Anita");
        student.addCompletedPrerequisite("CS301");

        Course course = new Course(
                "CS401", "Advanced Algorithms", 4, 30, true, "CS301"
        );

        RegistrationService service = new RegistrationService();

        assertTrue(service.register(student, course));
        assertTrue(student.isRegistered("CS401"));
        assertEquals(4, student.getTotalCredits());
        assertEquals(1, course.getCurrentEnrollment());
    }

    @Test
    void shouldAcceptCS3A1AsAlternativeCompletedPrerequisite() {
        Student student = new Student("S105", "Ravi");
        student.addCompletedPrerequisite("CS3A1");

        Course course = new Course(
                "CS401", "Advanced Algorithms", 4, 30, true, "CS301"
        );

        RegistrationService service = new RegistrationService();

        assertTrue(service.register(student, course));
        assertTrue(student.isRegistered("CS401"));
        assertEquals(4, student.getTotalCredits());
        assertEquals(1, course.getCurrentEnrollment());
    }

    @Test
    void shouldRejectWhenNeitherPrerequisiteIsCompletedWithoutChangingState() {
        Student student = new Student("S106", "Priya");

        Course course = new Course(
                "CS401", "Advanced Algorithms", 4, 30, true, "CS301"
        );

        RegistrationService service = new RegistrationService();

        assertFalse(service.register(student, course));
        assertFalse(student.isRegistered("CS401"));
        assertEquals(0, student.getTotalCredits());
        assertEquals(0, course.getCurrentEnrollment());
    }

}