import com.sun.org.apache.xpath.internal.operations.Mod;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
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


    /**
     * @return a Supervisor object with a copy of the supervisor.
     */
    public Supervisor getSupervisor() {
        return new Supervisor(this.supervisor.getName(), this.supervisor.getDateOfBirth());
    }

    /**Set's the research student's supervisor to the passed supervisor object.
     * @param supervisor Supervisor to be set.
     */
    public void setSupervisor(Supervisor supervisor) {
        this.supervisor = supervisor;
    }

    /** Necessitated by the parent class, however research students take no modules so here we simply return an empty
     *  array.
     * @return A new ArrayList.
     */
    @Override
    public ArrayList<Module> getRegisteredModules(){
        return new ArrayList<>();
    }

    /**
     * @return The research student's ID
     */
    @Override
    public String getID(){
        return ID;
    }

    /** Private constructor, forcing the use of an object factory.
     * @param name String of the student's name
     * @param birthday Date object of the students birthday
     * @param allStudents Arraylist with all preexisting students.
     */
    private PostgraduateResearchStudent(String name, Date birthday, ArrayList<Student> allStudents) {
        super(name, birthday);
        this.ID = createStudentID(allStudents);
    }

    /** Creates a student ID starting with R followed by 4 random digits.
     * @param students A list of all existing students to ensure uniqueness of the ID.
     * @return a String starting with R followed by 4 digits.
     */
    @Override
    String createStudentID(ArrayList<Student> students){
        //start postgrad reasearch with R
        StringBuilder sb = new StringBuilder();
        sb.append('R');

        //two options, either track the last allocated id and increment, or assign random and check against list of ids
        //go with random and check
        Random random = new Random();
        Boolean isUnique = false;

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


    /** A method that returns true if the student is registered for a full number of credits
     * @return boolean, true if equal to FULL_NUMBER_CREDITS, false otherwise
     */
    @Override
    public boolean validNumberOfCredits() {
        if (this.getEnrolledCredits() != FULL_NUMBER_CREDITS){
            return false;
        } else {
            return true;
        }
    }

    /** Creates a postgraduate research student and ensures the student is old enough for this type of student.
     * @param name String, name of the student to be created.
     * @param birthday Date object with the student's date of birth.
     * @param students Arraylist with all preexisting students
     * @return a PostGraduateResearchStudent
     * @throws ageException Thrown if the age of the student does not satisfy the minimum age requirements for the class.
     */
    public static PostgraduateResearchStudent registerStudent(String name, Date birthday, ArrayList<Student> students) throws ageException{

        PostgraduateResearchStudent s = new PostgraduateResearchStudent(name, birthday, students);

        if (s.getAge() < MIN_AGE) {
            throw new ageException("New student is not old enough");
        }
        return s;

    }


}


