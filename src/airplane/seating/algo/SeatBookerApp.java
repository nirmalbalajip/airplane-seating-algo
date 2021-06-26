package airplane.seating.algo;

import airplane.seating.algo.model.InputData;
import airplane.seating.algo.model.SeatType;
import airplane.seating.algo.util.SeatBookerUtil;

import java.util.Arrays;
import java.util.Scanner;

import static airplane.seating.algo.util.SeatBookerUtil.checkIfAisleSeat;
import static airplane.seating.algo.util.SeatBookerUtil.checkIfWindowSeat;

public class SeatBookerApp {

    public static void main(String[] args) throws Exception {
        InputData inputData = fetchInputs();
        String[][] seatPlan = computeSeatingPlan(inputData);
        SeatBookerUtil.printSeatPlan(seatPlan);
    }

    private static InputData fetchInputs() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the 2D array that represents the rows and columns: (format: [[3,2], [4,3], [2,3], [3,4]])");
        String inputArray = scanner.nextLine();
        String sanitisedInputArray = inputArray.replaceAll("[\\[\\]\\s]", "");
        String[] arrayElements = sanitisedInputArray.split(",");
        if (arrayElements.length % 2 != 0) {
            throw new IllegalArgumentException("Invalid Array input provided");
        }
        int[] sectionsData = Arrays.stream(arrayElements).mapToInt(Integer::parseInt).toArray();
        int noOfSections = arrayElements.length / 2;

        System.out.print("Enter the number of passengers: ");
        int passengerCount = scanner.nextInt();
        scanner.close();

        return new InputData(sectionsData, noOfSections, passengerCount);
    }

    private static String[][] computeSeatingPlan(InputData inputData) throws Exception {
        // **************  COMPUTING SEATS CAPACITY  ************** //
        int[] sectionsData = inputData.getSectionsData();
        int noOfSections = inputData.getNoOfSections();
        int passengerCount = inputData.getPassengerCount();
        int windowSeats = sectionsData[1] + sectionsData[sectionsData.length - 1]; // number of rows in leftmost section + number of rows in rightmost section
        int endSeats = 0;
        int totalSeats = 0;
        int maxRowCount = sectionsData[0];
        int sumOfColumnCount = 0;
        for (int i = 0; i < noOfSections; i++) {
            int rowCount = sectionsData[2 * i + 1], columnCount = sectionsData[2 * i];
            totalSeats += rowCount * columnCount;
            if (columnCount > 0) {
                endSeats = endSeats + 2 * rowCount;
            }
            if (maxRowCount < rowCount) {
                maxRowCount = rowCount;
            }
            sumOfColumnCount += columnCount;
        }
        int aisleSeats = endSeats - windowSeats;
        if (passengerCount > totalSeats) {
            throw new Exception("Passenger Count is more than total available seats");
        }

        // **************  COMPUTING SEAT PLAN  ************** //
        String[][] seatPlan = new String[maxRowCount][sumOfColumnCount];
        int aisleSeatCounter = 1;
        int windowSeatCounter = 1;
        int middleSeatCounter = 1;
        for (int row = 0; row < maxRowCount; row++) {
            int currentSection = 0;
            int currentColumnInCurrentSection = 0;

            for (int column = 0; column < sumOfColumnCount; column++) {
                currentColumnInCurrentSection++;
                int rowCountOfCurrentSection = sectionsData[2 * currentSection + 1];
                int columnCountOfCurrentSection = sectionsData[2 * currentSection];

                if (rowCountOfCurrentSection > row) {
                    int passengerNumber = 0;
                    SeatType seatType = null;

                    //check for seat availability and check if a seat is Aisle/Window/Middle
                    if (aisleSeatCounter <= aisleSeats && checkIfAisleSeat(column, sumOfColumnCount, currentColumnInCurrentSection, columnCountOfCurrentSection)) {
                        seatType = SeatType.A;
                        passengerNumber = aisleSeatCounter;
                        aisleSeatCounter++;
                    } else if (passengerCount > aisleSeats && windowSeatCounter <= windowSeats && checkIfWindowSeat(column, sumOfColumnCount)) {
                        seatType = SeatType.W;
                        passengerNumber = aisleSeats + windowSeatCounter;
                        windowSeatCounter++;
                    } else if (passengerCount > (aisleSeats + windowSeats) && middleSeatCounter <= (totalSeats - endSeats)) {
                        seatType = SeatType.M;
                        passengerNumber = aisleSeats + windowSeats + middleSeatCounter;
                        middleSeatCounter++;
                    }
                    if (passengerNumber <= passengerCount && null != seatType) {
                        seatPlan[row][column] = String.format("%s - %d ", seatType.name(), passengerNumber);
                    }
                }

                if (currentColumnInCurrentSection == columnCountOfCurrentSection) {
                    currentSection++;
                    currentColumnInCurrentSection = 0;
                }
            }
        }
        return seatPlan;
    }
}