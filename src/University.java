import java.io.*;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class University {

    public static void main(String[] args) throws IOException, FileNotFoundException, ageException{

        ArrayList<Student> allStudents = new ArrayList<>();
        ArrayList<SmartCard> smartCards = new ArrayList<>();
        ArrayList<Module> modules = readinModules("E:\\Programming\\Java\\MscCompSci\\CSC8002\\CourseWork1\\src\\Modules");


        Student testStudent1 = registerUnderGrad("callum", new GregorianCalendar(1996, Calendar.MARCH, 16).getTime(), allStudents);

        testStudent1.registerModule(modules.get(0));
        System.out.println(testStudent1.getID());


    }

    /**
     * @param students an Arraylist of all students
     * @return Returns an integer equal to the number of registered students.
     */
    private static int noOfStudents(ArrayList<Student> students){
        return students.size();
    }

    /** Creates a new undergraduate student. Returns null if the student is too young
     * @param name Name of the student to be created.
     * @param birthday Date object of the students birthday
     * @param students Arraylist of all currently registered students.
     * @return Returns an undergraduate student.
     * @throws ageException Thrown if the student does not meet the minimum age requirements.
     */
    private static UndergraduateStudent registerUnderGrad(String name, Date birthday, ArrayList<Student> students) throws ageException{
        UndergraduateStudent newStudent;
        try {
            newStudent = UndergraduateStudent.registerUndergrad(name, birthday, students);

        } catch (ageException e){
            newStudent = null;
        }
      return newStudent;
    }

    /** Creates a new Postgraduate Taught student. Returns null if the student is too young.
     * @param name Name of the student to be created.
     * @param birthday Date object of the students birthday
     * @param students Arraylist of all currently registered students.
     * @return Returns an undergraduate student.
     * @throws ageException Thrown if the student does not meet the minimum age requirements.
     */
    private static PostgraduateTaughtStudent registerPostGradTaught(String name, Date birthday, ArrayList<Student> students) throws ageException{
        PostgraduateTaughtStudent newStudent;
        try {
            newStudent = PostgraduateTaughtStudent.registerStudent(name, birthday, students);
        } catch (ageException e){
            newStudent = null;
        }
        return newStudent;
    }

    /** Creates a new Postgraduate Research student. Returns null if the student is too young.
     * @param name Name of the student to be created.
     * @param birthday Date object of the students birthday
     * @param students Arraylist of all currently registered students.
     * @return Returns an undergraduate student.
     * @throws ageException Thrown if the student does not meet the minimum age requirements.
     */
    private static PostgraduateResearchStudent registerPostGradResearch(String name, Date birthday, ArrayList<Student> students) throws ageException{
        PostgraduateResearchStudent newStudent;
        try {
            newStudent = PostgraduateResearchStudent.registerStudent(name, birthday, students);
        } catch (ageException e){
            newStudent = null;
        }
        return newStudent;
    }

    /** Allows the modification of preexisting student information.
     * @param studentID The ID of the student to be altered.
     * @param newName The new name of the student.
     * @param students list of existing students.
     */
    private static void ammendStudentData(String studentID, String newName, ArrayList<Student> students){

        for (Student s: students) {
            if (s.getID().equals(studentID)){
                    s.setName(newName);

            }
        }

    }

    /** Removes a student from the existing list of registered students and sets the student to null, allowing for
     * deletion from memory.
     * @param studentID The student to be removed.
     * @param students Arraylist of existing students.
     */
    private static void terminateStudent(String studentID, ArrayList<Student> students){
        for (Student s: students){
            if (s.getID().equals(studentID)){
                students.remove(s);
                s = null;
                //will be deleted by the garbage collector
            }
        }
    }

    /** Reads in modules from a text file in format "module code, module title, credits"
     * @param fileLocation the loction of the text file containing module information.
     * @return An arraylist of modules.
     * @throws FileNotFoundException
     * @throws IOException
     */
    private static ArrayList<Module> readinModules(String fileLocation) throws FileNotFoundException, IOException{

        ArrayList<Module> returnList = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileLocation));


            while(br.readLine() != null ){
                String[] input = br.readLine().split(", ");
                Module tempModule = new Module(input[0], input[1], Integer.valueOf(input[2]));
                returnList.add(tempModule);
            }
            br.close();
        } catch (FileNotFoundException e){
            System.out.println("File not found.");
        }



        return returnList;
    }


}
