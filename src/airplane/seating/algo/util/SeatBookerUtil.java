package airplane.seating.algo.util;

public class SeatBookerUtil {

    public static void printSeatPlan(String[][] seatPlan) {
        System.out.println("------------------------------------------------------------------------------------------------------------------------");
        for (String[] strings : seatPlan) {
            for (String string : strings) {
                System.out.printf("%8s", (null != string) ? string : "");
                System.out.print("| ");
            }
            System.out.println();
            System.out.println("------------------------------------------------------------------------------------------------------------------------");
        }
    }

    public static boolean checkIfWindowSeat(int columnIndex, int totalColumns) {
        return columnIndex == 0 || columnIndex == totalColumns - 1; // extreme ends of the plane
    }

    public static boolean checkIfAisleSeat(int columnIndex, int totalColumns, int currentColumnInCurrentSection, int columnCountOfCurrentSection) {
        if (checkIfWindowSeat(columnIndex, totalColumns)) {
            return false;
        } else {
            return currentColumnInCurrentSection == 1 || currentColumnInCurrentSection == columnCountOfCurrentSection; // column should be on either end of a section
        }

    }
}
