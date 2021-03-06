import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Supervisor implements Person {

    private String name;
    private Date dateOfBirth;


    /**Constructor that takes name and date of the birth as arguments.
     * @param name String.
     * @param dateOfBirth Date object.
     */
    public Supervisor(String name, Date dateOfBirth) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * @return String with the name of the supervisor.
     */
    @Override
    public String getName() {
        return this.name;
    }

    /** Returns a copy of the supervisors date of birth since Date is mutable.
     * @return Date matching the date of the birth.
     */
    public Date getDateOfBirth() {
        return new Date(dateOfBirth.getTime());
    }

    /** Calls calculateAge and returns the evaluated age of the supervisor.
     * @return int equal to the supervisors age at the time of the call.
     */
    @Override
    public int getAge(){
        return calculateAge();
    }

    @Override
    public boolean equals(Object obj){

        if (obj == null){
            return false;
        } else if (obj instanceof Supervisor){
            if (((Supervisor) obj).getName().equals(this.name) && ((Supervisor) obj).getDateOfBirth().equals(this.dateOfBirth)){
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode(){
        int hash = 3;
        hash = hash + this.name.hashCode()*37;
        hash = hash + this.dateOfBirth.hashCode()*37;
        return hash;
    }
    /**
     * Calculates the difference between the current date and the date of birth.
     * @return int of the difference in years.
     */
    private int calculateAge(){

        Date currentDate = new Date();
        DateFormat dateFormatter = new SimpleDateFormat("yyyyMMdd");
        int birthdayInt = Integer.parseInt(dateFormatter.format(this.dateOfBirth));
        int currentdayInt = Integer.parseInt(dateFormatter.format(currentDate));
        int age = (currentdayInt - birthdayInt) / 10000;
        return age;
    }
}
