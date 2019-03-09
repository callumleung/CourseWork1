import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Supervisor implements Person {

    private String name;
    private Date dateOfBirth;


    public Supervisor(String name, Date dateOfBirth) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public String getName() {
        return name;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public int getAge(){
        return calculateAge(this.dateOfBirth);
    }

    private int calculateAge(Date currentDate){
        DateFormat dateFormatter = new SimpleDateFormat("yyyyMMdd");
        int birthdayInt = Integer.parseInt(dateFormatter.format(this.dateOfBirth));
        int currentdayInt = Integer.parseInt(dateFormatter.format(currentDate));
        int age = (currentdayInt - birthdayInt) / 1000;
        return age;
    }
}
