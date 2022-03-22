package process;

import enrolment.exception.RegisterException;
import enrolment.lecture.Lecture;
import enrolment.student.Student;

// 학생과 수강 강좌들을 모두 저장하고 관련 연산을 도와주는 것
public class UserEnrolmentProcess {
    private Student student;
    private Lecture[] lectures;

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public void setLectures(Lecture[] lectures) {
        this.lectures = lectures;
    }

    public Lecture[] getLectures() {
        return lectures;
    }


    public UserEnrolmentProcess(Lecture[] lectures) {
        this.lectures = lectures;
    }

    // 기능 2. 수강신청
    public boolean register(String lectureName) throws RegisterException {
        Lecture lecture = findLecture(lectureName);

        // 과목이 존재하지 않는 경우
        if (lecture == null) {
            throw new RegisterException("과목이 존재하지 않음");
        }

        // 이미 동일한 과목을 수강신청하는 경우
        if (hasLecture(lecture)) {
            throw new RegisterException("이미 동일한 과목을 수강신청하고 있음");
        }

        // 만약 숫자가 최대 숫자를 초과했을 경우
        if (!lecture.checkNumber()) {
            throw new RegisterException("숫자가 최대 숫자를 초과함");
        }

        // 만약 동일한 시간의 강의인 경우
        if (ifEqualTime(lecture)) {
            throw new RegisterException("동일한 시간의 강의가 있음");
        }

        student.addLecture(lecture.getName());
        lecture.plusCurrentNumber();
        return true;
    }


    private boolean hasLecture(Lecture lecture) {
        String[] studentLectureNames = student.getLectureNames();
        for (String studentLectureName : studentLectureNames) {
            // 만약 해당 강좌를 이미 수강하고 있는 경우
            if (lecture.getName().equals(studentLectureName)) {
                return true;
            }
        }
        // 수강하지 않는 경우
        return false;
    }

    // 이름으로 해당 강의가 존재하는지 확인하고 존재하면 반환
    private Lecture findLecture(String lectureName) {
        for (Lecture lecture : lectures) {
            if (lecture.getName().equals(lectureName)) {
                return lecture;
            }
        }
        return null;
    }

    // 동일한 시간에 강의가 있는지 확인하고, 있으면 ture
    private boolean ifEqualTime(Lecture lecture) {
        String[] studentLectureNames = student.getLectureNames();
        for (String studentLectureName : studentLectureNames) {
            if (studentLectureName != null) {

                Lecture hasLecture = findLecture(studentLectureName);
                if (hasLecture != null) {
                    if (hasLecture.getTime().equals(lecture.getTime())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }


}
