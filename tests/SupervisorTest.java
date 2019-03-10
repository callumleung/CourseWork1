import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.Assert.*;

public class SupervisorTest {
    @Test
    public void getAge() throws Exception {
        Supervisor testSupervisor = new Supervisor("Richard",  new GregorianCalendar(1996, Calendar.DECEMBER, 16).getTime());
        assertEquals(testSupervisor.getAge(), 22);
    }

    @Test
    public void equals() throws Exception {
        Supervisor testSupervisor = new Supervisor("Richard",  new GregorianCalendar(1996, Calendar.DECEMBER, 16).getTime());
        Supervisor testSupervisor2 = new Supervisor("Richard",  new GregorianCalendar(1996, Calendar.DECEMBER, 16).getTime());

        assertTrue(testSupervisor.equals(testSupervisor2));
    }

}