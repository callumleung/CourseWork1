import org.junit.Test;

import static org.junit.Assert.*;

public class ModuleTest {
    @Test
    public void equals() throws Exception {
        Module testModule1 = new Module("CSC8001", "Programming and Data Structures", 20);
        Module testModule2 = new Module("CSC8001", "Programming and Data Structures", 20);

        assertTrue(testModule1.equals(testModule2));
    }

    @Test
    public void toStringTest() throws Exception {
        Module testModule1 = new Module("CSC8001", "Programming and Data Structures", 20);

        assertEquals(testModule1.toString(), "CSC8001, Programming and Data Structures, 20");
    }

}