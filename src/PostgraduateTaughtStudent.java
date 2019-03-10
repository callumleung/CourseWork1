import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class PostgraduateTaughtStudent extends Student{

    private static final int FULL_NUMBER_CREDITS = 180;
    private static final int MIN_AGE = 20;
    private static final int PASS_PERCENTAGE = 50;
    private String ID;
    private ArrayList<Module> registeredModules;


    /** Private constructor. Use registerStudent to create an instance of this class.
     * @param name String. name of the student.
     * @param birthday Date. The date of birth of the student.
     * @param s Arraylist of students already registered. Required to ensure uniqueness of the studentID.
     */
    private PostgraduateTaughtStudent(String name, Date birthday, ArrayList<Student> s) {
        super(name, birthday);
        this.ID = createStudentID(s);
    }

    /** Creates a student ID starting with T followed by 4 random digits.
     * @param students A list of all existing students to ensure uniqueness of the ID.
     * @return a String starting with T followed by 4 digits.
     */
    @Override
    String createStudentID(ArrayList<Student> students){
        //start Postgrad taught id's with a T
        StringBuilder sb = new StringBuilder();
        sb.append('T');

        //two options, either track the last allocated id and increment, or assign random and check against list of ids
        //go with random and check
        Random random = new Random();
        Boolean isUnique = false;

        while (!isUnique) {
            while (sb.length() < 5) {
                int n = random.nextInt(10);
                sb.append(n);
            }

            if (students.size() == 0){
                isUnique = true;
            }
            //check against preexisting id's
            //performance deteriorates the large the body of students.
            for (Student s: students){
                if (s instanceof UndergraduateStudent){
                    if (s.getID() == sb.toString()){
                        break;
                    }
                }
            }
            isUnique = true;
        }
        return sb.toString();
    }

    /** Creates a postgraduate taught student and ensures the student is old enough for this type of student.
     * @param name String, name of the student to be created.
     * @param birthday Date object with the student's date of birth.
     * @param students Arraylist with all preexisting students
     * @return a PostgraduateTaughtStudent
     * @throws ageException Thrown if the age of the student does not satisfy the minimum age requirements for the class.
     */
    public static PostgraduateTaughtStudent registerStudent(String name, Date birthday, ArrayList<Student> students) throws ageException{

        PostgraduateTaughtStudent s = new PostgraduateTaughtStudent(name, birthday, students);

        if (s.getAge() < MIN_AGE) {
            throw new ageException("New student is not old enough");
        }
        return s;


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

   /* *//**
     * @return Returns the number of credits needed for full enrollment.
     *//*
    public int getFullNumberCredits() {
        return FULL_NUMBER_CREDITS;
    }

    *//**
     * @return Return's the minimum age requirement for this type of student.
     *//*
    public int getMinAge() {
        return MIN_AGE;
    }

    *//**
     * @return Returns the pass mark for this type of student.
     *//*
    public int getPassPercentage() {
        return PASS_PERCENTAGE;
    }
*/

    /**
     * @return Returns the student's ID
     */
    @Override
    public String getID(){
        return this.ID;
    }
}
