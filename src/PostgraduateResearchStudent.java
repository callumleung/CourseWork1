import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

/**
 * Created by Callum on 21/02/2019.
 */
public class PostgraduateResearchStudent extends Student{

    private final int FULL_NUMBER_CREDITS = 0;
    private final int MIN_AGE = 20;
    private String ID;
    //TODO create supervisors and add field for supervisor
    //TODO create method to return  name of supervisor

    private PostgraduateResearchStudent(String name, Date birthday, ArrayList<Student> s) {
        super(name, birthday);
        this.ID = createStudentID(s);
    }

    String createStudentID(ArrayList<Student> students){
        //start postgrad reasearch with R
        StringBuilder sb = new StringBuilder();
        sb.append('R');

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


    public boolean validNumberOfCredits() {
        if (this.getEnrolledCredits() != FULL_NUMBER_CREDITS){
            return false;
        } else {
            return true;
        }
    }

    public PostgraduateResearchStudent registerStudent(String name, Date birthday, ArrayList<Student> students){

        if (this.getAge() >= MIN_AGE) {
            PostgraduateResearchStudent s = new PostgraduateResearchStudent(name, birthday, students);
            return s;
        }

        //else return a student with null name

        else {
            return new PostgraduateResearchStudent("", new Date(), students);
        }


    }

}
