import java.time.Year;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class UndergraduateStudent extends Student{

    private static final int FULL_NUMBER_CREDITS = 120;
    private static final int MIN_AGE = 17;
    private static final int PASS_PERCENTAGE = 40;
    private String ID;
    private SmartCard smartCard;

    /** Constructor
     * @param name String the name of the student to be created.
     * @param birthday Date object of students date of birth.
     * @param s Arraylist containing existing students.
     * @throws ageException Exception thrown if the created student does not meet the age requirements.
     */
    private UndergraduateStudent(String name, Date birthday,ArrayList<Student> s) throws ageException {
        super(name, birthday);
        this.ID = createStudentID(s);

    }

    /** Creates a postgraduate research student and ensures the student is old enough for this type of student.
     * @param name String, name of the student to be created.
     * @param birthday Date object with the student's date of birth.
     * @param students Arraylist with all preexisting students
     * @return a
     * @throws ageException Thrown if the age of the student does not satisfy the minimum age requirements for the class.
     */
    public static UndergraduateStudent registerUndergrad(String name, Date birthday, ArrayList<Student> students) throws ageException{

        UndergraduateStudent s = new UndergraduateStudent(name, birthday, students);
        if (s.getAge() < MIN_AGE) {
            throw new ageException("New student is not old enough");
        }

        return s;
    }

    public void registerSmartCard(SmartCard smartCard){
        this.smartCard = smartCard;
    }

    public SmartCard getSmartCard() {
        return new SmartCard(this.smartCard);
    }

    /** Creates a student ID starting with U followed by 4 random digits.
     * @param students A list of all existing students to ensure uniqueness of the ID.
     * @return a String starting with R followed by 4 digits.
     */
    @Override
    String createStudentID(ArrayList<Student> students){
        //start undergraduate id's with a U
        StringBuilder sb = new StringBuilder();
        sb.append('U');

        //two options, either track the last allocated id and increment, or assign random and check against list of ids
        //go with random and check
        Random random = new Random();
        Boolean isUnique = false;

        while (!isUnique) {
            while (sb.length() < 5) {
                int n = random.nextInt(10);
                sb.append(n);
            }

            if (students.size() == 0 ) {
                isUnique = true;
            }
                //check against preexisting id's
                //performance deteriorates the large the body of students.
            for (Student s : students) {
                if (s.getID().charAt(0) == 'U') {
                    if (s.getID().equals(sb.toString())) {
                            break;
                    }
                }
            }
            isUnique = true;

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

    /**
     * @return Return's the minimum age requirement for this type of student.
     */
    public static int getMinAge() {
        return MIN_AGE;
    }

    /**
     * @return Returns the pass mark for this type of student.
     */
    public static int getPassPercentage() {
        return PASS_PERCENTAGE;
    }

    /**
     * @return Return's the undergraduates studentID
     */
    @Override
    public String getID() {
        return ID;
    }

   /* *//**
     * @return Returns a copy of the arraylist of the modules the student is registered on.
     *//*
    @Override
    public ArrayList<Module> getRegisteredModules(){
        return new ArrayList<>(this.registeredModules);
    }*/
}
