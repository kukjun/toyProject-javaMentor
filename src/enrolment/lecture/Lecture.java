package enrolment.lecture;

public class Lecture {
    private String name;
    private String time;
    private int currentNumber;
    private int maxNumber;

    public Lecture(String name, String time, int currentNumber, int maxNumber) {
        this.name = name;
        this.time = time;
        this.currentNumber = currentNumber;
        this.maxNumber = maxNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getCurrentNumber() {
        return currentNumber;
    }

    public void setCurrentNumber(int currentNumber) {
        this.currentNumber = currentNumber;
    }

    public int getMaxNumber() {
        return maxNumber;
    }

    public void setMaxNumber(int maxNumber) {
        this.maxNumber = maxNumber;
    }

    public boolean checkNumber() {
        return maxNumber > currentNumber;
    }

    public void plusCurrentNumber() {
        currentNumber++;
    }

    public boolean checkTime(String time) {
        if (this.time.equals(time)) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Lecture{" +
                "name='" + name + '\'' +
                ", time='" + time + '\'' +
                ", currentNumber=" + currentNumber +
                ", maxNumber=" + maxNumber +
                '}';
    }
}
