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

    public Student(String name, Date birthday) {
        this.name = name;
        this.birthday = birthday;
        modules = new ArrayList<>();
        enrolledCredits = 0;
    }

    public boolean registerModule(Module m){
        if (modules.contains(m)){
            return false;
        } else {
            modules.add(m);
            enrolledCredits += m.getCredits();
            return true;
        }
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getAge(){

        return calculateAge(new Date());
    }

    public ArrayList<Module> getModules(){
        return this.modules;
    }

    public int getEnrolledCredits(){
        return enrolledCredits;
    }

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
