package airplane.seating.algo.model;

public class InputData {

    private final int[] sectionsData;
    private final int noOfSections;
    private final int passengerCount;

    public InputData(int[] sectionsData, int noOfSections, int passengerCount) {
        this.sectionsData = sectionsData;
        this.noOfSections = noOfSections;
        this.passengerCount = passengerCount;
    }

    public int[] getSectionsData() {
        return sectionsData;
    }

    public int getNoOfSections() {
        return noOfSections;
    }

    public int getPassengerCount() {
        return passengerCount;
    }
}
