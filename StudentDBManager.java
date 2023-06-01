/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package StudentDBpackage;
import java.sql.*;
import java.util.Scanner;
import java.time.*;
import java.time.format.*;

public class StudentDBManager {
    public static void main(String[]args){
        Scanner input = new Scanner(System.in); // Scanner decleration
        System.out.println(">>      Welcome to SalesDB database program     <<"); //Welcome Message
        System.out.println(">>  This program was made by Mohammed Soqati  <<");
        System.out.println("------------------------------------------------------------------------");
        
        System.out.println("Mehthod to Add Mohammed has been invoked!");
        addStudent("Mohammed Soqati","2001-03-29");
        System.out.println("Mehthod to Add Omar has been invoked!");
        addStudent("Omar Abouellil","1998-08-12");
        System.out.println("Mehthod to Add Hamad has been invoked!");
        addStudent("Hamad Allaboun","2001-01-10");
        tableView();
        
    }
    public static void addStudent(String Name, String HD) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/SalesDB", "root", "FIras21332");
            String sql = "Insert into employee (Name,Hired_Date) values(?,?)";
            PreparedStatement pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, Name);
            pStmt.setString(2, HD);
            
            int i = pStmt.executeUpdate();
            System.out.println("The Employee " + Name + " has been inserted ("+i+" rows added)");
            System.out.println("");
            pStmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("Error :" + e.getMessage());
        }
        

    }
    public static void tableView() {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/SalesDB", "root", "FIras21332");
            String sql = "select * from Employee";
            PreparedStatement pStmt = conn.prepareStatement(sql);
            ResultSet rs = pStmt.executeQuery();
            System.out.println("");
            System.out.println("=============================================================================");
            System.out.printf("| %-3s|   %-40.30s |  %-15s |%n", "ID", "Full Name", "Hired Date");
            System.out.println("=============================================================================");
            while (rs.next()) {

                System.out.printf("| %-3d|   %-40.30s|   %-15s  |  %n", rs.getInt(1), rs.getString(2), rs.getString(3));
                System.out.println("-----------------------------------------------------------------------------");
            }
            pStmt.close();
            rs.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
