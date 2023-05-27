package lab;

public class ComputerLabs {
    public static void main(String[] args) {
        String empty = "_____";

        String[][] labsAndStations = {
            {empty, empty, empty, empty, empty},
            {empty, empty, empty, empty, empty, empty},
            {empty, empty, empty, empty},
            {empty, empty, empty},
        };

        System.out.println("");
        System.out.println("LAB STATUS");
        System.out.println("Lab #\tComputer Stations");
        for (int labNo = 0; labNo < labsAndStations.length; labNo++) {
            System.out.print(labNo + 1 + "\t");
            for (int stationNo = 0; stationNo < labsAndStations[labNo].length; stationNo++) {
                System.out.print(stationNo + 1 + ": " + labsAndStations[labNo][stationNo] + "  ");
            }
            System.out.println("");
        }
    }
}