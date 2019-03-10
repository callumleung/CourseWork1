import org.junit.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.Assert.*;

public class PostgraduateTaughtStudentTest {
    @Test
    public void registerStudent() throws Exception {
        ArrayList<Student> students = new ArrayList<>();
        PostgraduateTaughtStudent testStudent1 = PostgraduateTaughtStudent.registerStudent("callum", new GregorianCalendar(1996, Calendar.MARCH, 16).getTime(), students);
        PostgraduateTaughtStudent testStudent2 = PostgraduateTaughtStudent.registerStudent("callum", new GregorianCalendar(1996, Calendar.MARCH, 16).getTime(), students);

        assertEquals(testStudent1.getName(), "callum");
        assertNotEquals(testStudent1.getID(), testStudent2.getID());
        //tests that the calculate age works correctly.
        assertEquals(testStudent1.getAge(), 22);
        //tests that the studentID has been correctly generated.
        assertTrue(testStudent1.getID().matches("T[0-9]{4}"));
    }

    @Test
    public void getRegisteredModulesTest() throws Exception {
        ArrayList<Student> students = new ArrayList<>();
        Student testStudent1 =PostgraduateTaughtStudent.registerStudent("callum", new GregorianCalendar(1996, Calendar.MARCH, 16).getTime(), students);
        Module testModule1 = new Module("CSC8001", "Programming and Data Structures", 20);
        Module testModule2 = new Module("CSC8002", "Advanced Programming", 20);
        testStudent1.registerModule(testModule1);
        testStudent1.registerModule(testModule2);

        ArrayList<Module> testModules = testStudent1.getRegisteredModules();
        assertTrue(testModules.contains(testModule1));
        assertTrue(testModules.contains(testModule2));
    }



}