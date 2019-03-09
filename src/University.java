import java.io.*;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class University {


    public static void main(String[] args) throws IOException, FileNotFoundException, ageException{

        ArrayList<Student> allStyudents;
        ArrayList<SmartCard> smartCards;







    }

    private static int noOfStudents(ArrayList<Student> students){
        return students.size();
    }

    private static UndergraduateStudent registerUnderGrad(String name, Date birthday, ArrayList<Student> students) throws ageException{
        UndergraduateStudent newStudent;
        try {
          newStudent = UndergraduateStudent.registerUndergrad(name, birthday, students);

        } catch (ageException e){
            Date defaultDate = new GregorianCalendar(1190, Calendar.JANUARY, 0).getTime();
          newStudent = UndergraduateStudent.registerUndergrad("", defaultDate , students);
        }
      return newStudent;
    }

    private static PostgraduateTaughtStudent registerPostGradTaught(String name, Date birthday, ArrayList<Student> students) throws ageException{
        PostgraduateTaughtStudent newStudent;
        try {
            newStudent = PostgraduateTaughtStudent.registerStudent(name, birthday, students);
        } catch (ageException e){
            Date defaultDate = new GregorianCalendar(1190, Calendar.JANUARY, 0).getTime();
            newStudent = PostgraduateTaughtStudent.registerStudent("", defaultDate, students);
        }
        return newStudent;
    }

    private static PostgraduateResearchStudent registerPostGradResearch(String name, Date birthday, ArrayList<Student> students) throws ageException{
        PostgraduateResearchStudent newStudent;
        try {
            newStudent = PostgraduateResearchStudent.registerStudent(name, birthday, students);
        } catch (ageException e){
            Date defaultDate = new GregorianCalendar(1190, Calendar.JANUARY, 0).getTime();
            newStudent = PostgraduateResearchStudent.registerStudent("", defaultDate, students);
        }
        return newStudent;
    }

    private static void ammendStudentData(String studentID, String newName, Date newBirthday, ArrayList<Student> students){

        for (Student s: students) {
            if (s.getID().equals(studentID)){
                s.setName(newName);
                s.setBirthday(newBirthday);
            }
        }

    }

    private static void terminateStudent(String studentID, ArrayList<Student> students){
        for (Student s: students){
            if (s.getID().equals(studentID)){
                students.remove(s);
                s = null;
                //will be deleted by the garbage collector
            }
        }
    }

    private static ArrayList<Module> readinModules(String fileLocation) throws FileNotFoundException, IOException{

        BufferedReader br = new BufferedReader(new FileReader(fileLocation));

        ArrayList<Module> returnList = new ArrayList<>();

        while(br.readLine() != null ){
            String[] input = br.readLine().split(",");
            Module tempModule = new Module(input[0], input[1], Integer.valueOf(input[2]));
            returnList.add(tempModule);
        }

        return returnList;
    }


}
