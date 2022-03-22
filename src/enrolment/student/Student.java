package enrolment.student;

public class Student {

    // field
    private String id;
    private String password;
    private String name;
    private String[] lectureNames;

    public Student(String id, String password, String name) {
        this.id = id;
        this.password = password;
        this.name = name;
        lectureNames = new String[4];
    }

    // getter, setter method
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getLectureNames() {
        return lectureNames;
    }

    public void setLectureNames(String[] lectureNames) {
        this.lectureNames = lectureNames;
    }

    // created method

    public boolean addLecture(String lectureName) {
        for (int i = 0; i < lectureNames.length; i++) {
            if (lectureNames[i] == null) {
                lectureNames[i] = lectureName;
                return true;
            }
        }
        return false;
    }

}
