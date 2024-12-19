import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Reports {
    private String subject;
    private String information;
    private String username;

    // Initialized as empty list for the reports
    private static List<Reports> reportList = new ArrayList<>();

    // Create a new report object and returns it
    public static Reports createReport(String subject, String information, String username) {
        Reports report = new Reports();
        report.subject = subject;
        report.information = information;
        report.username = username;
        return report;
    }

    // Adds all the reports from wanted file and creates a list from it
    public static List<Reports> getReports(String filename) {
        // Clear the reportList before adding new reports
        reportList.clear();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String tempLine;
            while ((tempLine = br.readLine()) != null) {
                if (tempLine.startsWith(":")) {
                    tempLine = tempLine.substring(1); //Removes first collom

                    String[] parts = tempLine.split(":", 2);
                    if (parts.length >= 2) {
                        String subjectLine = parts[0];
                        String[] infoAndUser = parts[1].split(":", 2);
                        if (infoAndUser.length >= 2) {
                            String informationLine = infoAndUser[0];
                            String userLine = infoAndUser[1];

                            // Add the report to the list
                            Reports report = createReport(subjectLine, informationLine, userLine);
                            reportList.add(report);
                        } else {
                            System.out.println("Error in report format: " + tempLine);
                        }
                    } else {
                        System.out.println("Error in report format: " + tempLine);
                    }
                } else {
                    System.out.println("Line does not start with a colon: " + tempLine);
                }
            }
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "File not found");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Something went wrong\n Please Try again");
        }

        return reportList; // Return the list
    }

    // Send report to admin
    public static void sendReportToAdmin(String username) {
        Scanner scan = new Scanner(System.in);
        System.out.println("What's Your Subject? \n1.Book Error\n2.Loans\n3.Question\n4.Other");
        String subject = scan.nextLine();
        System.out.println("Please explain your problem or question\nIf it's about a Book or User, Please add the ISBN or UserName");
        String information = scan.nextLine();
        System.out.println("Do you wish to be Anonymous?\n1.Yes\n2.No");
        String user = scan.nextLine();

        subject = switch (subject) {
            case "1" -> "Book Error";
            case "2" -> "Loans";
            case "3" -> "Question";
            case "4" -> "Other";
            default -> subject;
        };

        user = switch (user) {
            case "1" -> "Anonymous";
            case "2" -> username;
            default -> username;
        };

        // Write report to the file
        try (FileWriter fileWriter = new FileWriter("ReportsFromUsers", true)) {
            fileWriter.write(":" + subject + ":" + information + ":" + user + "\n");
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
        System.out.println("Thank you\nYour report has been sent");
    }

    // Admin Reads Reports
    public static void readReports() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Here are all the Current Reports:\n");

        // Get reports from the file
        List<Reports> reports = getReports("ReportsFromUsers");

        // Print the reports
        if (reports.isEmpty()) {
            System.out.println("No reports currently.");
        } else {
            for (int i = 0; i < reports.size(); i++) {
                System.out.println((i + 1) + ". " + reports.get(i).toString());
            }

            System.out.println("Please type the number of the Report you would like to view:\n0.Exit");
            String answer = scan.nextLine();
            if (answer.equals("0")) {
                System.out.println("Exiting Reports");
            } else {
                try {
                    int reportIndex = Integer.parseInt(answer) - 1;
                    if (reportIndex >= 0 && reportIndex < reports.size()) {
                        Reports selectedReport = reports.get(reportIndex);
                        System.out.println("Subject: " + selectedReport.subject);
                        System.out.println("Information: " + selectedReport.information);
                        System.out.println("Username: " + selectedReport.username);

                        //Asks what Admin wants to happen
                        System.out.println("1. Respond to User\n2. Delete\n3. Respond to User And Delete");
                        String action = scan.nextLine();

                        switch (action) {
                            case "1":
                                respondToUser(selectedReport);
                                break;

                            case "2":
                                deleteReport(selectedReport,"ReportsFromUsers");
                                break;

                            case "3":
                                respondToUser(selectedReport);
                                deleteReport(selectedReport,"ReportsFromUsers");
                                break;

                            default:
                                System.out.println("Invalid action.");
                        }
                    } else {
                        System.out.println("Invalid report number.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input.");
                }
            }
        }
    }
    //Called if Admin wants to respond
    private static void respondToUser(Reports report) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter your response to the user:");
        String response = scan.nextLine();

        try (FileWriter fileWriter = new FileWriter("ReportResponsesToUser", true)) {
            fileWriter.write(":" + report.subject);
            fileWriter.write(":" + response);
            fileWriter.write(":" + report.username + "\n");
            System.out.println("Response has been sent to the user.");
        } catch (IOException e) {
            System.out.println("An error occurred while writing the response: " + e.getMessage());
        }
    }

    // Delete the report
    private static void deleteReport(Reports report, String filename) {
        try {
            // Read the current reports from the file
            File inputFile = new File(filename);
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            List<String> lines = new ArrayList<>();
            String line;

            // Read all lines into a list, skipping the one to delete
            while ((line = reader.readLine()) != null) {
                if (!line.equals(":" + report.subject + ":" + report.information + ":" + report.username)) {
                    lines.add(line);
                }
            }
            reader.close();

            // Rewrite the file without the deleted report
            FileWriter writer = new FileWriter(inputFile);
            for (String newLine : lines) {
                writer.write(newLine + "\n");
            }
            writer.close();
            refreshReports();

            System.out.println("Report has been deleted.");
        } catch (IOException e) {
            System.out.println("An error occurred while deleting the report: " + e.getMessage());
        }

    }

    // Override toString() to make it custom
    @Override
    public String toString() {
        return "Subject: " + subject + ", Information: " + information + ", User: " + username;
    }
    // Refreshes the list so if user is still logged in it gets the updated version
    private static void refreshReports() {
        // Clear the current report list
        reportList.clear();

        // Re-read reports from the file and update the list
        getReports("ReportsFromUsers");

        System.out.println("The report list has been refreshed.");
    }

    public static void userCheckResponses(String username) {

        Scanner scan = new Scanner(System.in);
        System.out.println("Here are all your Messages:\n");

        // Get Messages from the file
        List<Reports> reports = getReports("ReportResponsesToUser");

        // Print the Messages
        if (reports.isEmpty()) {
            System.out.println("No Messages currently.");
        } else {
            for (int i = 0; i < reports.size(); i++) {

                if (Objects.equals(reports.get(i).username, username)) {
                    System.out.println((i + 1) + ". " + reports.get(i).toString());

                }
            }

            System.out.println("Please type the number of the Message you would like to view:\n0.Exit");
            String answer = scan.nextLine();
            if (answer.equals("0")) {
                System.out.println("Exiting Messages");
            } else {
                try {
                    int reportIndex = Integer.parseInt(answer) - 1;
                    if (reportIndex >= 0 && reportIndex < reports.size()) {
                        Reports selectedReport = reports.get(reportIndex);
                        System.out.println("Subject you sent: " + selectedReport.subject);
                        System.out.println("Admin Responds: " + selectedReport.information);


                        System.out.println("1.Remove Message\n2.Exit");
                        String action = scan.nextLine();

                        switch (action) {
                            case "1":
                                deleteReport(selectedReport,"ReportResponsesToUser");
                                break;

                            case "2":
                                break;

                            default:
                                System.out.println("Invalid action.");
                        }
                    } else {
                        System.out.println("Invalid report number.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input.");
                }
            }
        }
    }
}
