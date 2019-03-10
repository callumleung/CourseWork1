import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.Assert.*;

public class UndergraduateStudentTest {
    @Test
    public void getRegisteredModulesTest() throws Exception {
        ArrayList<Student> students = new ArrayList<>();
        UndergraduateStudent testStudent1 = UndergraduateStudent.registerUndergrad("callum", new GregorianCalendar(1996, Calendar.MARCH, 16).getTime(), students);
        Module testModule1 = new Module("CSC8001", "Programming and Data Structures", 20);
        Module testModule2 = new Module("CSC8002", "Advanced Programming", 20);
        testStudent1.registerModule(testModule1);
        testStudent1.registerModule(testModule2);

        ArrayList<Module> testModules = testStudent1.getRegisteredModules();
        assertTrue(testModules.contains(testModule1));
        assertTrue(testModules.contains(testModule2));
    }


    @Test
    public void registerUndergradTest() throws ageException{
        ArrayList<Student> students = new ArrayList<>();
        Student testStudent1 = UndergraduateStudent.registerUndergrad("callum", new GregorianCalendar(1996, Calendar.MARCH, 16).getTime(), students);
        Student testStudent2 = UndergraduateStudent.registerUndergrad("callum", new GregorianCalendar(1996, Calendar.MARCH, 16).getTime(), students);

        Module testModule = new Module("CSC8001", "Programming and Data Structures", 20);
        testStudent1.registerModule(testModule);

        assertEquals(testStudent1.getName(), "callum");
        assertNotEquals(testStudent1.getID(), testStudent2.getID());

        assertEquals(testStudent1.getName(), "callum");
        //tests that the calculate age works correctly.
        assertEquals(testStudent1.getAge(), 22);
        //tests that the studentID has been correctly generated.
        assertTrue(testStudent1.getID().matches("U[0-9]{4}"));
    }



    @Test
    public void validNumberOfCreditsTest() throws Exception {
        ArrayList<Student> students = new ArrayList<>();
        Student testStudent1 = UndergraduateStudent.registerUndergrad("callum", new GregorianCalendar(1996, Calendar.MARCH, 16).getTime(), students);
        Module testModule1 = new Module("CSC8001", "Programming and Data Structures", 20);
        Module testModule2 = new Module("CSC8002", "Advanced Programming", 20);
        testStudent1.registerModule(testModule1);
        testStudent1.registerModule(testModule2);

        assertFalse(testStudent1.validNumberOfCredits());

    }


}