package StudentDBpackage;


import java.sql.*;
import java.util.Scanner;
import java.time.*;
import java.time.format.*;

public class Project1 {

    Scanner input = new Scanner(System.in);

    //Method to invoke mySQL to add am entry to the table
    public void addStudent(String fname, String DOB, float GPA) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/StudentsDB_Abouellil_Soqati", "Abouellil_Soqati", "omar9780O@");
            String sql = "Insert into StudentsTBL_Omar_Mohammed (FullName,DateOfBirth,GPA) values(?,?,?)";
            PreparedStatement pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, fname);
            pStmt.setString(2, DOB);
            pStmt.setFloat(3, GPA);
            int i = pStmt.executeUpdate();
            System.out.println("The Student " + fname + " has been inserted ("+i+" rows added)");
            System.out.println("");
            pStmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("Error :" + e.getMessage());
        }
        

    }

    //a method to invoke mySQL to return all table records
    public void tableView() {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/StudentsDB_Abouellil_Soqati", "Abouellil_Soqati", "omar9780O@");
            String sql = "select * from StudentsTBL_Omar_Mohammed";
            PreparedStatement pStmt = conn.prepareStatement(sql);
            ResultSet rs = pStmt.executeQuery();
            System.out.println("");
            System.out.println("=============================================================================");
            System.out.printf("| %-3s|   %-40.30s |  %-15s | %-4s |%n", "ID", "Full Name", "Date of Birth", "GPA");
            System.out.println("=============================================================================");
            while (rs.next()) {

                System.out.printf("| %-3d|   %-40.30s|   %-15s | %.2f |  %n", rs.getInt(1), rs.getString(2), rs.getDate(3), rs.getFloat(4));
                System.out.println("-----------------------------------------------------------------------------");
            }
            pStmt.close();
            rs.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    //a method to invoke my SQL to look for a name inside the table and return  any records that contain that name
    public void tableSearch(String name) {
        boolean notEmpty;
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/StudentsDB_Abouellil_Soqati", "Abouellil_Soqati", "omar9780O@");
            String sql = "select * from StudentsTBL_Omar_Mohammed where FullName like ?";
            PreparedStatement pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, name);
            ResultSet rs = pStmt.executeQuery();
            //rs.isBeforeFirst()
            System.out.println("");
            System.out.println("=============================================================================");
            System.out.printf("| %-3s|   %-40.30s |  %-15s | %-4s |%n", "ID", "Full Name", "Date of Birth", "GPA");
            System.out.println("=============================================================================");
            if (notEmpty = rs.next()) {
                while (notEmpty) {

                    System.out.printf("| %-3d|   %-40.30s|   %-15s | %.2f |  %n", rs.getInt(1), rs.getString(2), rs.getDate(3), rs.getFloat(4));
                    System.out.println("-----------------------------------------------------------------------------");
                    notEmpty = rs.next();
                }
            } else {
                System.out.println("");
                System.out.println(name.replace("%", "") + " isn't in the table");
            }
            pStmt.close();
            rs.close();
            conn.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // a method to validate Date entry in terms of format and if date exists
    public String isDateValid() {
        while (true) {

            String srt = input.next();

            try {

                LocalDate.parse(srt, DateTimeFormatter.ofPattern("uuuu-MM-dd").withResolverStyle(ResolverStyle.STRICT)); //a method that will parse the date and check for format and date's existance(Leap Year included)

                return srt;

            } catch (Exception e) {

                System.out.println("Invalid date ,please try again: ");
            }

        }

    }

    // a method to check if name entry contains any numbers or special characters(which isn't allowed)
    public String isNameValid(String fname) {
        boolean isValidName = true;
        while (isValidName) {
            if (fname.matches("^[ A-Za-z]+$")) {
                isValidName = false;

            } else {
                System.out.println("the name must only contain letters in the Alphabet, please try again: ");
                fname = input.next();
            }
        }
        return fname;
    }

    // a method to validate GPA entry if it's a float or not and if it's between 0.0 and 4.0
    public float isGPAValid() {
        float t;

        while (true) {
            input.nextLine(); // clear the buffer
            if (input.hasNextFloat()) {
                t = input.nextFloat();
                if (t <= 4.0 && t >= 0.0) {
                    return t;

                } else {
                    System.out.println("GPA must be between 0.0 and 4.0, please try again: ");
                }
            } else {

                System.out.println("GPA must be a number , try again :");

            }

        }

    }
    // a method that will check if menu input is not a number(which isn't allowed)

    public int isEntryValid() {

        while (true) {

            int t;
            if (input.hasNextInt()) {
                return t = input.nextInt();

            } else {
                System.out.println("Entry must be a number , try again :");

            }
            input.next(); // clear the buffer

        }

    }
}
