import java.time.Year;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class UndergraduateStudent extends Student{

    private static final int FULL_NUMBER_CREDITS = 120;
    private static final int MIN_AGE = 17;
    private static final int PASS_PERCENTAGE = 40;
    private String ID;
    private ArrayList<Module> registeredModules;

    public void addModule(Module m){
        if (!this.registeredModules.contains(m)){
            this.registeredModules.add(m);
        }
    }


    private UndergraduateStudent(String name, Date birthday,ArrayList<Student> s) throws ageException {
        super(name, birthday);
        this.ID = createStudentID(s);
    }

    public static UndergraduateStudent registerUndergrad(String name, Date birthday, ArrayList<Student> students) throws ageException{

        UndergraduateStudent s = new UndergraduateStudent(name, birthday, students);
        if (s.getAge() < MIN_AGE) {
            throw new ageException("New student is not old enough");
        }
        return s;
    }

    String createStudentID(ArrayList<Student> students){
        //start undergraduate id's with a U
        StringBuilder sb = new StringBuilder();
        sb.append('U');

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

    @Override
    public boolean validNumberOfCredits() {
        if (this.getEnrolledCredits() != FULL_NUMBER_CREDITS){
            return false;
        } else {
            return true;
        }
    }

    public String getID() {
        return ID;
    }

    public ArrayList<Module> getRegisteredModules(){
        return this.registeredModules;
    }
}
