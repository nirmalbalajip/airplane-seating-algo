package airplane.seating.algo.util;

import org.junit.Assert;
import org.junit.Test;

public class SeatBookerUtilTest {

    @Test
    public void testCheckIfWindowSeatTrueScenario() throws Exception {
        boolean singleColumnResult = SeatBookerUtil.checkIfWindowSeat(0, 1);
        Assert.assertTrue(singleColumnResult);
        boolean multiColumnResult = SeatBookerUtil.checkIfWindowSeat(7, 8);
        Assert.assertTrue(multiColumnResult);
    }

    @Test
    public void testCheckIfWindowSeatFalseScenario() throws Exception {
        boolean threeColumnsResult = SeatBookerUtil.checkIfWindowSeat(1, 3);
        Assert.assertFalse(threeColumnsResult);
        boolean multiColumnResult = SeatBookerUtil.checkIfWindowSeat(6, 8);
        Assert.assertFalse(multiColumnResult);
    }

    @Test
    public void testCheckIfAisleSeatTrueScenario() throws Exception {
        boolean result = SeatBookerUtil.checkIfAisleSeat(1, 12, 2, 2);
        Assert.assertTrue(result);
        result = SeatBookerUtil.checkIfAisleSeat(8, 12, 1, 4);
        Assert.assertTrue(result);
    }

    @Test
    public void testCheckIfAisleSeatFalseScenario() throws Exception {
        boolean result = SeatBookerUtil.checkIfAisleSeat(0, 12, 1, 2);
        Assert.assertFalse(result);
        result = SeatBookerUtil.checkIfAisleSeat(11, 12, 4, 4);
        Assert.assertFalse(result);
    }

    @Test
    public void testPrintSeatPlan() {
        try {
            String[][] input = {{"a", "b"}, {"c", "d"}};
            SeatBookerUtil.printSeatPlan(input);
        } catch (Exception e) {
            Assert.fail();
        }
    }
}