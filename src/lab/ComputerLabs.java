package lab;

import java.util.Scanner;

public class ComputerLabs {
    private static Scanner input;

    private static int[][] labsAndStations = {
        {0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0},
        {0, 0, 0},
    };

    // Make sure the user enters an integer
    private static int getIntFromPrompt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String userInput = input.next();
    
            try {
                return Integer.parseInt(userInput);
            } catch (Exception err) {
                System.out.print("Make sure you enter a whole number. ");
            }   
        }
    }

    // Make sure the user enters an integer within a certain range
    private static int getIntInRangeFromPrompt(String prompt, int min, int max) {
        while (true) {
            int input = getIntFromPrompt(prompt);
            if (input < min || input > max) {
                System.out.print("Make sure you enter a number between " + min + " and " + max + ". ");
            } else {
                return input;
            }
        }
    }

    // Make sure the user enters valid lab, station, and user ID numbers

    private static int getLabNoFromPrompt(String prompt) {
        return getIntInRangeFromPrompt(prompt, 1, 4);
    }

    private static int getStationNoFromPrompt(String prompt, int stationCount) {
        return getIntInRangeFromPrompt(prompt, 1, stationCount);
    }

    private static int getUserIdFromPrompt(String prompt) {
        return getIntInRangeFromPrompt(prompt, 10000, 99999);
    }

    // Get total number of stations in a given lab
    private static int stationCountInLab(int labNo) {
        return labsAndStations[labNo - 1].length;
    }

    // Find user by ID
    private static String searchForUser(int userId) {
        for (int labNo = 0; labNo < labsAndStations.length; labNo++) {
            for (int stationNo = 0; stationNo < labsAndStations[labNo].length; stationNo++) {
                if (labsAndStations[labNo][stationNo] == userId) {
                    return "User with ID " + userId + " is logged in at lab " + (labNo + 1) + ", station " + (stationNo + 1) + ".";
                }
            }
        }
        // If there are no matches, say so
        return "User with ID " + userId + " is logged off or doesn't exist.";
    }

    public static void main(String[] args) {
        // Vars that will get used repeatedly
        int inputLab;
        int inputStation;
        int inputUserId;
        int userIdAtStation;

        // The delimeter allows spaces in user input
        input = new Scanner(System.in).useDelimiter("\n");

        // Program loop
        while (true) {
            // Show lab/station table
            System.out.println("");
            System.out.println("LAB STATUS");
            System.out.println("Lab #\tComputer Stations");
            for (int labNo = 0; labNo < labsAndStations.length; labNo++) {
                System.out.print((labNo + 1) + "\t");
                for (int stationNo = 0; stationNo < labsAndStations[labNo].length; stationNo++) {
                    String userId;
                    if (labsAndStations[labNo][stationNo] == 0) {
                        userId = "_____";
                    } else {
                        userId = Integer.toString(labsAndStations[labNo][stationNo]);
                    }
                    System.out.print((stationNo + 1) + ": " + userId + "  ");
                }
                System.out.println("");
            }

            // Show menu and receive user selection
            System.out.println("");
            System.out.println("MAIN MENU");
            System.out.println("0) Quit");
            System.out.println("1) Simulate login");
            System.out.println("2) Simulate logout");
            System.out.println("3) Search");
            System.out.println("");

            int option = getIntFromPrompt("Enter a number to choose an option: ");

            // Perform task based on user selection
            switch(option) {
                case 0:
                    System.out.print("Goodbye.");
                    System.exit(0);
                    break;
                case 1:
                    inputUserId = getUserIdFromPrompt("Enter the five-digit ID number of the user logging in: ");
                    inputLab = getLabNoFromPrompt("Enter the lab number the user is logging in from (1-4): ");
                    inputStation = getStationNoFromPrompt("Enter the computer station number the user is logging into (1-" + stationCountInLab(inputLab) + "): ", stationCountInLab(inputLab));
                    labsAndStations[inputLab - 1][inputStation - 1] = inputUserId;
                    System.out.println("User with ID " + inputUserId + " is now logged in.");
                    break;
                case 2:
                    inputLab = getLabNoFromPrompt("Enter the lab number the user is logged in at (1-4): ");
                    inputStation = getStationNoFromPrompt("Enter the computer station number the user is logged in at (1-" + stationCountInLab(inputLab) + "): ", stationCountInLab(inputLab));
                    userIdAtStation = labsAndStations[inputLab - 1][inputStation - 1];
                    if (userIdAtStation == 0) {
                        System.out.println("There's no one logged in at that station.");
                    } else {
                        labsAndStations[inputLab - 1][inputStation - 1] = 0;
                        System.out.println("Logged out user with ID " + userIdAtStation + ".");
                    }
                    break;
                case 3:
                    inputUserId = getUserIdFromPrompt("Enter the five-digit ID number of the user to find: ");
                    System.out.println(searchForUser(inputUserId));
                    break;
                default:
                    System.out.println("Invalid input. Please try again.");
            }
        }
    }
}