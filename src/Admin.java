import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Admin extends Person {
    Scanner scan = new Scanner(System.in);

    public Admin(String name, int yearOfBirth, String userName, int password) {
        super(name, yearOfBirth, userName, password);
    }

    public Admin() {
    }

    //Borrar un miembro ya existente.
    public void RemoveMember(String fileConMiembros){
        System.out.println("Which member do you want to remove? ");
        String toRemove = scan.next();
        //
        ArrayList<String> updatedLines = new ArrayList<>();

        //File file = new File("src/fileConMiembros");

        try (BufferedReader reader = new BufferedReader(new FileReader(fileConMiembros))) {
            String line;

            while ((line = reader.readLine()) != null) {
                if (!line.startsWith(toRemove)) {
                    updatedLines.add(line);
                }
            }
        } catch (IOException e) {
            System.out.println("Kunde inte läsa filen " + e.getMessage());
            return;
        }

        // Sobrescribir el archivo con las líneas actualizadas
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileConMiembros))) {
            for (String line : updatedLines) {
                writer.write(line);
                writer.newLine();
            }
            System.out.println("Member " + toRemove + " has been removed (if it existed).");
        } catch (IOException e) {
            System.out.println("Error writing to the file: " + e.getMessage());
        }
    }
}
