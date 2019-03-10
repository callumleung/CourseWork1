import org.junit.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.Assert.*;

public class SmartCardTest {
    @Test
    public void getSmartCardNumber() throws Exception {
        ArrayList<Student> students = new ArrayList<>();
        ArrayList<SmartCard> smartCards = new ArrayList<>();
        UndergraduateStudent testStudent1 = UndergraduateStudent.registerUndergrad("callum", new GregorianCalendar(1996, Calendar.MARCH, 16).getTime(), students);

        SmartCard student1SmartCard = new SmartCard(testStudent1, smartCards);
        testStudent1.registerSmartCard(student1SmartCard);

        assertEquals(student1SmartCard.getSmartCardNumber(), testStudent1.getSmartCard().getSmartCardNumber());
    }


    /** Tests that the setExpiryDate works correctly for each type of student.
     * @throws Exception
     */
    @Test
    public void getExpiryDate() throws Exception {
        ArrayList<Student> students = new ArrayList<>();
        ArrayList<SmartCard> smartCards = new ArrayList<>();
        UndergraduateStudent testStudent1 = UndergraduateStudent.registerUndergrad("callum", new GregorianCalendar(1996, Calendar.MARCH, 16).getTime(), students);
        PostgraduateTaughtStudent testStudent2 = PostgraduateTaughtStudent.registerStudent("callum", new GregorianCalendar(1996, Calendar.MARCH, 16).getTime(), students);
        PostgraduateResearchStudent testStudent3 = PostgraduateResearchStudent.registerStudent("callum", new GregorianCalendar(1996, Calendar.MARCH, 16).getTime(), students);



        SmartCard student1SmartCard = new SmartCard(testStudent1, smartCards);
        SmartCard studentSmartCard2 = new SmartCard(testStudent2, smartCards);
        SmartCard studentSmartCard3 = new SmartCard(testStudent3, smartCards);


        Calendar dateOfTest = Calendar.getInstance();
        dateOfTest.setTime(new Date());
        dateOfTest.add(Calendar.YEAR, 4);
        dateOfTest.set(Calendar.HOUR_OF_DAY, 0);
        dateOfTest.set(Calendar.MINUTE, 0);
        dateOfTest.set(Calendar.SECOND, 0);
        dateOfTest.set(Calendar.MILLISECOND, 0);
        //chane the date in the construction of the new Gregorian Calendar to be the date running the test + 4 years
        assertEquals(student1SmartCard.getExpiryDate(), dateOfTest.getTime());

        //test postgraduate taught expiry
        dateOfTest.add(Calendar.YEAR, -2);
        assertEquals(studentSmartCard2.getExpiryDate(), dateOfTest.getTime());

        //test postgraduate research expiry
        dateOfTest.add(Calendar.YEAR, 3);
        assertEquals(studentSmartCard3.getExpiryDate(), dateOfTest.getTime());

    }

}