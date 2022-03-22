package enrolment.ui;

import enrolment.exception.NotExpectMenuValueException;
import enrolment.exception.RegisterException;
import enrolment.lecture.Lecture;
import enrolment.student.Student;
import process.LoginProcess;
import process.UserEnrolmentProcess;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

enum MainInput {
    USER_LOGIN, EXIT;

    // 인덱스 한정 예외처리
    static MainInput getIndex(int index) {
        MainInput[] mainInputs = MainInput.values();
        return mainInputs[index];
    }
}

enum UserInput {
    REGISTER, EXIT;

    static UserInput getIndex(int index) {
        UserInput[] userInputs = UserInput.values();
        return userInputs[index];
    }
}

public class UI {
    LoginProcess loginProcess;
    UserEnrolmentProcess userEnrolmentProcess;
    BufferedReader br;
    MainInput mainInput;
    UserInput userInput;


    public UI(LoginProcess loginProcess, UserEnrolmentProcess userEnrolmentProcess) {
        this.loginProcess = loginProcess;
        this.userEnrolmentProcess = userEnrolmentProcess;
        br = new BufferedReader(new InputStreamReader(System.in));
    }

    public void mainMenuUI() {
        System.out.println("수강신청 프로그램에 오신것을 환영합니다!");
        do {
            printMainMenu();
            try {
                mainInput = readMainMenu();

                switch (mainInput) {
                    case USER_LOGIN:
                        userLoginMenuUI();
                        break;
                    case EXIT:
                        break;
                    default:
                        System.out.println("예외 값 입력");
                        break;
                }
            } catch (NumberFormatException ne) {
                System.out.println(("NumberFormatException 발생 - 숫자만 입력해주세요"));
            } catch (IndexOutOfBoundsException ie) {
                System.out.println("IndexOutOfBoundsException 발생 - 선택지에 있는 값만 입력해주세요");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } while (mainInput != MainInput.EXIT);
        System.out.println("프로그램을 종료하겠습니다.");
    }

    private void userLoginMenuUI() {
        if (tryLogin(5)) {
            do {
                printLoginMenu();
                try {
                    userInput = readUserMenu();

                    switch (userInput) {
                        case REGISTER:
                            try {
                                registerUI();
                            } catch (RegisterException re) {
                                System.out.println("수강 신청 실패");
                                System.out.println("실패사유: " + re.getMessage());
                            }
                            break;
                        case EXIT:
                            break;
                        default:
                            System.out.println("예외 값 입력");
                            break;
                    }
                } catch (NumberFormatException ne) {
                    System.out.println(("NumberFormatException 발생 - 숫자만 입력해주세요"));
                } catch (IndexOutOfBoundsException ie) {
                    System.out.println("IndexOutOfBoundsException 발생 - 선택지에 있는 값만 입력해주세요");
                } catch (IOException e) {
                    e.printStackTrace();
                }

            } while (userInput != UserInput.EXIT);
        } else {
            System.out.println("횟수 초과로 초기 화면으로 돌아갑니다.");
        }
    }


    private void printMainMenu() {
        System.out.println("--------------- Main Page ---------------");
        System.out.println("선택지를 입력하세요");
        System.out.println("1. 학생 로그인 하기");
        System.out.println("2. 종료하기");
        System.out.println("입력칸 : ");
    }


    // 예외처리 다시
    private MainInput readMainMenu() throws NumberFormatException, IndexOutOfBoundsException, IOException {
        MainInput inMainInput = null;
        String input = br.readLine();

        int integerInput = Integer.parseInt(input);
        inMainInput = MainInput.getIndex(integerInput - 1);

        return inMainInput;
    }

    private boolean tryLogin(int maxCount) {
        try {
            for (int count = 1; count <= maxCount; count++) {
                System.out.println("id, password 를 순서대로 입력하세요.");
                String id = br.readLine();
                String password = br.readLine();
                Student currentStudent = loginProcess.login(id, password);
                if (currentStudent == null) {
                    System.out.println("로그인 실패. (실행횟수/가능횟수) : " + count + "/" + maxCount);
                } else {
                    userEnrolmentProcess.setStudent(currentStudent);
                    System.out.println(currentStudent.getName() + "님, 수강신청 프로그램에 오신것을 환영합니다!");
                    return true;
                }
            }
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

    }

    private void printLoginMenu() {
        System.out.println("--------------- User Page ---------------");
        System.out.println("선택지를 입력하세요");
        System.out.println("1. 수강 등록하기");
        System.out.println("2. 이전으로 돌아가기");
        System.out.println("입력칸 : ");
    }

    private UserInput readUserMenu() throws IOException, NumberFormatException, IndexOutOfBoundsException {
        UserInput inUserInput = null;
        String input = br.readLine();
        int integerInput = Integer.parseInt(input);
        inUserInput = UserInput.getIndex(integerInput - 1);
        return inUserInput;
    }

    private void registerUI() throws RegisterException {
        Lecture[] lectures = userEnrolmentProcess.getLectures();
        for (Lecture lecture : lectures) {
            System.out.println(lecture.toString());
        }
        System.out.println("수강할 과목명을 입력하세요");
        String lectureName = readLectureName();
        if (userEnrolmentProcess.register(lectureName)) {
            System.out.println("수강 신청 완료");
        }
    }

    private String readLectureName() {
        String lectureName = null;
        try {
            lectureName = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lectureName;

    }


}
