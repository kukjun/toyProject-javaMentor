package process;

import enrolment.student.Student;

public class LoginProcess {
    private Student[] students;

    public LoginProcess(Student[] students) {
        this.students = students;
    }

    public Student login(String id, String password) {
        for (Student student : students) {
            if (student.getId().equals(id) && student.getPassword().equals(password)) {
                return student;
            }
        }
        return null;
    }
}
