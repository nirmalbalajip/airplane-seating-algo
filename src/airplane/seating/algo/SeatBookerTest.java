package airplane.seating.algo;

import airplane.seating.algo.model.InputData;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class SeatBookerTest {

    private final Method computeSeatingPlan = SeatBookerApp.class.getDeclaredMethod("computeSeatingPlan", InputData.class);

    public SeatBookerTest() throws NoSuchMethodException {
        this.computeSeatingPlan.setAccessible(true);
    }

    @Test
    public void testSinglePassengerSingle1DSection() throws InvocationTargetException, IllegalAccessException {
        InputData inputData = new InputData(new int[]{1, 1}, 1, 1);
        String[][] seatPlan = (String[][]) this.computeSeatingPlan.invoke(null, inputData);
        assertEquals(1, seatPlan.length);
        assertEquals(1, seatPlan[0].length);
        assertEquals("W - 1 ", seatPlan[0][0]);
    }

    @Test
    public void testSinglePassengerSingle2DSections() throws InvocationTargetException, IllegalAccessException {
        InputData inputData = new InputData(new int[]{2, 2}, 1, 1);
        String[][] seatPlan = (String[][]) this.computeSeatingPlan.invoke(null,inputData);
        assertEquals(2, seatPlan.length);
        assertEquals(2, seatPlan[0].length);
        assertEquals(2, seatPlan[1].length);
        assertEquals("W - 1 ", seatPlan[0][0]);
    }

    @Test
    public void testSinglePassengerMultiSections() throws InvocationTargetException, IllegalAccessException {
        InputData inputData = new InputData(new int[]{2, 3, 3, 4, 3, 2, 4, 3}, 4, 30);
        String[][] seatPlan = (String[][]) this.computeSeatingPlan.invoke(null, inputData);
        assertEquals(4, seatPlan.length);
        assertEquals(12, seatPlan[0].length);

        assertEquals("W - 19 ", seatPlan[0][0]);
        assertEquals("M - 25 ", seatPlan[0][1]);
        assertEquals("A - 1 ", seatPlan[0][2]);
        assertEquals("A - 2 ", seatPlan[0][3]);
        assertEquals("M - 26 ", seatPlan[0][4]);
        assertEquals("M - 27 ", seatPlan[0][5]);
        assertEquals("A - 3 ", seatPlan[0][6]);
        assertEquals("A - 4 ", seatPlan[0][7]);
        assertEquals("A - 5 ", seatPlan[0][8]);
        assertEquals("A - 6 ", seatPlan[0][9]);
        assertEquals("M - 28 ", seatPlan[0][10]);
        assertEquals("W - 20 ", seatPlan[0][11]);

        assertEquals("W - 21 ", seatPlan[1][0]);
        assertEquals("M - 29 ", seatPlan[1][1]);
        assertEquals("A - 7 ", seatPlan[1][2]);
        assertEquals("A - 8 ", seatPlan[1][3]);
        assertEquals("M - 30 ", seatPlan[1][4]);
        assertNull(seatPlan[1][5]);
        assertEquals("A - 9 ", seatPlan[1][6]);
        assertEquals("A - 10 ", seatPlan[1][7]);
        assertEquals("A - 11 ", seatPlan[1][8]);
        assertEquals("A - 12 ", seatPlan[1][9]);
        assertNull(seatPlan[1][10]);
        assertEquals("W - 22 ", seatPlan[1][11]);

        assertNull(seatPlan[2][0]);
        assertNull(seatPlan[2][1]);
        assertNull(seatPlan[2][2]);
        assertEquals("A - 13 ", seatPlan[2][3]);
        assertNull(seatPlan[2][4]);
        assertNull(seatPlan[2][5]);
        assertEquals("A - 14 ", seatPlan[2][6]);
        assertEquals("A - 15 ", seatPlan[2][7]);
        assertEquals("A - 16 ", seatPlan[2][8]);
        assertEquals("A - 17 ", seatPlan[2][9]);
        assertNull(seatPlan[2][10]);
        assertEquals("W - 23 ", seatPlan[2][11]);

        assertNull(seatPlan[3][0]);
        assertNull(seatPlan[3][1]);
        assertNull(seatPlan[3][2]);
        assertNull(seatPlan[3][3]);
        assertNull(seatPlan[3][4]);
        assertNull(seatPlan[3][5]);
        assertNull(seatPlan[3][6]);
        assertNull(seatPlan[3][7]);
        assertNull(seatPlan[3][8]);
        assertEquals("A - 18 ", seatPlan[3][9]);
        assertNull(seatPlan[3][10]);
        assertEquals("W - 24 ", seatPlan[3][11]);

    }

}
