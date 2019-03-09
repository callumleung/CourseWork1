import com.sun.org.apache.xpath.internal.operations.Mod;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

/**
 * Created by Callum on 21/02/2019.
 */
public class PostgraduateResearchStudent extends Student{

    private static final int FULL_NUMBER_CREDITS = 0;
    private static final int MIN_AGE = 20;
    private String ID;
    private Supervisor supervisor;
    //TODO create supervisors and add field for supervisor
    //TODO create method to return  name of supervisor

    public ArrayList<Module> getRegisteredModules(){
        return new ArrayList<>();
    }

    public String getID(){
        return ID;
    }
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

    public static PostgraduateResearchStudent registerStudent(String name, Date birthday, ArrayList<Student> students) throws ageException{

        PostgraduateResearchStudent s = new PostgraduateResearchStudent(name, birthday, students);

        if (s.getAge() < MIN_AGE) {
            throw new ageException("New student is not old enough");
        }
        return s;

    }

}
