import enrolment.lecture.Lecture;
import enrolment.student.Student;
import enrolment.ui.UI;
import process.LoginProcess;
import process.UserEnrolmentProcess;

public class Execute {
    public static void main(String[] args) throws Exception {
        // lecture 등록
        Lecture[] lectures = new Lecture[4];
        lectures[0] = new Lecture("데이터베이스", "월요일 오전", 39, 40);
        lectures[1] = new Lecture("운영체제", "화요일 오전", 40, 40);
        lectures[2] = new Lecture("자료구조", "월요일 오전", 21, 40);
        lectures[3] = new Lecture("네트워크", "수요일 오전", 4, 40);

        // student 등록
        Student[] students = new Student[2];
        students[0] = new Student("kukjun", "1234", "이국준");
        students[1] = new Student("heeyoung", "4321", "임희영");

        // process.LoginProcess
        LoginProcess loginProcess = new LoginProcess(students);

        // process.UserEnrolmentProcess
        UserEnrolmentProcess userEnrolmentProcess = new UserEnrolmentProcess(lectures);

        UI ui = new UI(loginProcess, userEnrolmentProcess);

        ui.mainMenuUI();
    }
}