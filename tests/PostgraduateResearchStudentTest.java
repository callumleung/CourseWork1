import org.junit.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.Assert.*;

public class PostgraduateResearchStudentTest {


    /**Test setting and getting a supervisor.
     * @throws Exception
     */
    @Test
    public void setAndGetSupervisor() throws Exception {
        ArrayList<Student> students = new ArrayList<>();
        PostgraduateResearchStudent testStudent1 = PostgraduateResearchStudent.registerStudent("callum", new GregorianCalendar(1996, Calendar.MARCH, 16).getTime(), students);
        Supervisor testSupervisor = new Supervisor("Richard",  new GregorianCalendar(1950, Calendar.MARCH, 16).getTime());
        testStudent1.setSupervisor(testSupervisor);

        assertEquals(testSupervisor, testStudent1.getSupervisor());
    }

    @Test
    public void getRegisteredModules() throws Exception {
        ArrayList<Student> students = new ArrayList<>();
        Student testStudent1 = PostgraduateResearchStudent.registerStudent("callum", new GregorianCalendar(1996, Calendar.MARCH, 16).getTime(), students);
        Module testModule1 = new Module("CSC8001", "Programming and Data Structures", 20);
        Module testModule2 = new Module("CSC8002", "Advanced Programming", 20);
        testStudent1.registerModule(testModule1);
        testStudent1.registerModule(testModule2);

        assertEquals(testStudent1.getRegisteredModules().size(), 0);
    }

    @Test
    public void validNumberOfCredits() throws Exception {
        ArrayList<Student> students = new ArrayList<>();
        Student testStudent1 = PostgraduateResearchStudent.registerStudent("callum", new GregorianCalendar(1996, Calendar.MARCH, 16).getTime(), students);


        assertTrue(testStudent1.getEnrolledCredits() == 0);
    }

    @Test
    public void registerStudent() throws Exception {
        ArrayList<Student> students = new ArrayList<>();
        Student testStudent1 = PostgraduateResearchStudent.registerStudent("callum", new GregorianCalendar(1996, Calendar.MARCH, 16).getTime(), students);
        Student testStudent2 = PostgraduateResearchStudent.registerStudent("callum", new GregorianCalendar(1996, Calendar.MARCH, 16).getTime(), students);

        assertEquals(testStudent1.getName(), "callum");
        assertNotEquals(testStudent1.getID(), testStudent2.getID());
        //tests that the calculate age works correctly.
        assertEquals(testStudent1.getAge(), 22);
        //tests that the studentID has been correctly generated.
        assertTrue(testStudent1.getID().matches("R[0-9]{4}"));
    }

}