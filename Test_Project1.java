package StudentDBpackage;



import java.util.Scanner;

public class Test_Project1 {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in); // Scanner decleration
        Project1 p = new Project1(); // creating an object of the Project1 class
        System.out.println(">>      Welcome to student database program     <<"); //Welcome Message
        System.out.println(">>  This program was made by Omar Abouellil & Mohammed Soqati  <<");
        System.out.println("------------------------------------------------------------------------");
        //***********************************************************      
        boolean running = true; //checker for main while loop
        String DOB; //Used for date entry
        //***********************************************************
        while (running) {       //menu selection using while and switch
            System.out.println("Participating Students|   SID   |Group Number|      Email Address     |");
            System.out.println("Mohammed Sami Soqati  |441010207|     2      |s441010207@st.uqu.edu.sa|");
            System.out.println("Omar Amjad Abouellil  |441018030|     2      |s441018030@st.uqu.edu.sa|");
            System.out.println("========================================================================");
            System.out.println("The menu ...");
            System.out.println("1: Add a new Student.");
            System.out.println("2: Display all Student records.");
            System.out.println("3: Search for a Student by name.");
            System.out.println("4: Exit the program.");
            System.out.print("Choose any of the following commands: ");
            int choice = p.isEntryValid(); // value for User's choice from the menu
            //***********************************************************    
            switch (choice) { //switch case for every command
                case 1: // code for Adding a student
                    System.out.println("Please enter Student's full name: ");
                    String fname = input.nextLine(); //make user insert student's name
                    fname = p.isNameValid(fname); //check if user input is only Alphabet Characters including whitespace
                    System.out.println("Please enter Student's Birth Date[yyyy-MM-dd]: ");
                    DOB = p.isDateValid(); //make user insert studens's birth date  
                    System.out.println("Please enter Student's GPA: ");
                    //float GPA = input.nextFloat(); //make user insert student's GPA
                    float GPA = p.isGPAValid(); //check if user input is between 0.0 and 4.0
                    p.addStudent(fname, DOB, GPA); //invoke method to add information to the table
                    break; //exit after student entry(return to main menu)
                //***********************************************************            
                case 2: //code to view all content of the table
                    p.tableView(); //invoke method to retrive and view all content of the table
                    break; //exit after table view(return to main menu)
                //***********************************************************            
                case 3: //code to search name in the table
                    System.out.println("Please enter a name to search for: ");
                    String name = "%" + p.isNameValid(input.nextLine()) + "%"; //search user input anywhere within the full name
                    p.tableSearch(name); //invoke method to search for a name in the table
                    break; //exit after table search(return to main menu)
                //***********************************************************            
                case 4: //code to exit the program
                    System.out.println("finishing...");
                    running = false; //change running value to false to exit while loop
                    break; //exit after exit invocation
                //***********************************************************            
                default: //if number isn't between 1 and 4 display error message and let user try again
                    System.out.println("Invalid Choice, choose between 1 and 4");
                    System.out.println("");
            }

        }

    }
}
