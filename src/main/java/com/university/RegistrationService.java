package com.university;

public class RegistrationService {

    private static final int MAX_CREDITS = 20;

    public boolean register(Student student, Course course) {

        if (!course.isActive()) {
            return false;
        }

        if (course.getCurrentEnrollment() >= course.getCapacity()) {
            return false;
        }

        if (student.isRegistered(course.getCourseCode())) {
            return false;
        }

        
        String prerequisite = course.getPrerequisite();

        if (prerequisite != null && !prerequisite.isBlank()) {
            boolean hasPrerequisite = student.hasCompletedPrerequisite(prerequisite);

            if ("CS301".equals(prerequisite)) {
                hasPrerequisite = hasPrerequisite
                        || student.hasCompletedPrerequisite("CS3A1");
            }

            if (!hasPrerequisite) {
                return false;
            }
        }


        if (student.getTotalCredits() + course.getCredits() > MAX_CREDITS) {
            return false;
        }

        student.addRegistration(
                course.getCourseCode(),
                course.getCredits());

        course.incrementEnrollment();

        return true;
    }
}