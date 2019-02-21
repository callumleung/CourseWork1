import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class PostgraduateTaughtStudent extends Student{

    private static final int FULL_NUMBER_CREDITS = 180;
    private static final int MIN_AGE = 20;
    private static final int PASS_PERCENTAGE = 50;
    private String ID;

    private PostgraduateTaughtStudent(String name, Date birthday, ArrayList<Student> s) {
        super(name, birthday);
        this.ID = createStudentID(s);
    }


    String createStudentID(ArrayList<Student> students){
        //start Postgrad taught id's with a T
        StringBuilder sb = new StringBuilder();
        sb.append('T');

        //two options, either track the last allocated id and increment, or assign random and check against list of ids
        //go with random and check
        Random random = new Random();
        Boolean isUnique = true;

        while (!isUnique) {
            while (sb.length() < 5) {
                int n = random.nextInt(10);
                sb.append(n);
            }

            //check against preexisting id's
            //performance deteriorates the large the body of students.
            for (Student s: students){
                if (s instanceof UndergraduateStudent){
                    if (((UndergraduateStudent) s).getID() == sb.toString()){
                        isUnique = true;
                    }
                }
            }
        }
        return sb.toString();
    }

    public PostgraduateTaughtStudent registerStudent(String name, Date birthday, ArrayList<Student> students){

        if (this.getAge() >= MIN_AGE) {
            PostgraduateTaughtStudent s = new PostgraduateTaughtStudent(name, birthday, students);
            return s;
        }

        //else return a student with null name

        else {
            return new PostgraduateTaughtStudent("", new Date(), students);
        }


    }

    @Override
    public boolean validNumberOfCredits() {
        if (this.getEnrolledCredits() != FULL_NUMBER_CREDITS){
            return false;
        } else {
            return true;
        }
    }

    public static int getFullNumberCredits() {
        return FULL_NUMBER_CREDITS;
    }

    public static int getMinAge() {
        return MIN_AGE;
    }

    public static int getPassPercentage() {
        return PASS_PERCENTAGE;
    }
}
