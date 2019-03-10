import com.sun.xml.internal.bind.v2.model.core.ID;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public abstract class Student implements Person{

    private String name;
    private Date birthday;
    private int enrolledCredits;
    private ArrayList<Module> modules;

    /** Returns a copy of the supervisors date of birth since Date is mutable.
     * @return Date matching the date of the birth.
     */
    public Date getBirthday() {
        return new Date(birthday.getTime());
    }

    /** Default constructor
     * @param name String name of the student.
     * @param birthday Date object of the student's bithday.
     */
    public Student(String name, Date birthday) {
        this.name = name;
        this.birthday = birthday;
        modules = new ArrayList<>();
        enrolledCredits = 0;
    }

    /** Registers the student of a module and returns true if successfully added to the student and false otherwise.
     *  The enrolledCredits field of student is also incremented by the amount of the registered module.
     * @param m The module to be registered.
     * @return a boolean true of false.
     */
    boolean registerModule(Module m){
        if (modules.contains(m)){
            return false;
        } else {
            modules.add(m);
            enrolledCredits += m.getCredits();
            return true;
        }
    }

    /** Sets the student's name.
     * @param name String that will be set as student's name.
     */
    void setName(String name) {
        this.name = name;
    }

    /** Sets the student's name to a supplied Date.
     * @param birthday Date object to be set as student's birthday.
     */
    void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    /**
     * @return String of student's name.
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Calculates the difference between the current date and the date of birth.
     * @return int of the difference in years.
     */
    @Override
    public int getAge(){

        return calculateAge(new Date());
    }

    /**
     * @return an arraylist copy of the list of registered modules.
     */
    public ArrayList<Module> getModules(){
        return new ArrayList<>(this.modules);
    }

    /**
     * @return the total of credits currently taken by the student.
     */
    int getEnrolledCredits(){
        return enrolledCredits;
    }

    /**
     * Calculates the difference between the current date and the date of birth.
     * @return int of the difference in years.
     */
    private int calculateAge(Date currentDate){
        DateFormat dateFormatter = new SimpleDateFormat("yyyyMMdd");
        int birthdayInt = Integer.parseInt(dateFormatter.format(this.birthday));
        int currentdayInt = Integer.parseInt(dateFormatter.format(currentDate));
        int age = (currentdayInt - birthdayInt) / 1000;
        return age;
    }

    public abstract String getID();

    abstract String createStudentID(ArrayList<Student> s);

    abstract public boolean validNumberOfCredits();

    abstract public ArrayList<Module> getRegisteredModules();

}
