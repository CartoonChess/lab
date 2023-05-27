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

    // Make sure the user enters a five-digit number
    private static int getUserIdFromPrompt(String prompt) {
        while (true) {
            int userId = getIntFromPrompt(prompt);
            if (userId < 10000 || userId > 99999) {
                System.out.print("Make sure you enter a number between 10000 and 99999. ");
            } else {
                return userId;
            }
        }
    }

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

            switch(option) {
                case 0:
                    System.out.print("Goodbye.");
                    System.exit(0);
                    break;
                case 1:
                    // TODO: Error checking lab, stn # (case 2 too)
                    inputUserId = getUserIdFromPrompt("Enter the five-digit ID number of the user logging in: ");
                    inputLab = getIntFromPrompt("Enter the lab number the user is logging in from (1-4): ");
                    inputStation = getIntFromPrompt("Enter the computer station number the user is logging into (1-6): ");
                    labsAndStations[inputLab - 1][inputStation - 1] = inputUserId;
                    break;
                case 2:
                    inputLab = getIntFromPrompt("Enter the lab number the user is logged in at (1-4): ");
                    inputStation = getIntFromPrompt("Enter the computer station number the user is logged in at (1-6): ");
                    labsAndStations[inputLab - 1][inputStation - 1] = 0;
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