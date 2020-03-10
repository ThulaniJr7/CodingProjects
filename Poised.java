package poised;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.JOptionPane;

// The program needs to run twice before adding onto 
public class Poised
{

    // Main Method
    public static void main(String[] args) throws FileNotFoundException, IOException
    {

        //Creating the Project object
        NewProject[] pobj = new NewProject[5];
        int pNum = 0;
        
        //Creating the Architect object
        NewArchitect[] aobj = new NewArchitect[5];
        int aNum = 0;

        //Creating the Contractor object
        NewContractor[] cobj = new NewContractor[5];
        int cNum = 0;

        //Importing current date and the correct format
        String timestamp = new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime());
        char option = JOptionPane.showInputDialog("What would you like to do: \n A) Add a Project \n B) Add an Architect \n C) Add a Contractor \n D) Finalize a Project \n Z) Edit \n E) Exit").toLowerCase().charAt(0);

        //While loop to make sure that the menu keeps showing up until E is selected
        while (option != 'e' || option != 'E')
        {
            // If condition for what the user wants to do
            if (option == 'a' || option == 'A')
            {
                // If condition to check the statement if its true
                if (pNum > 4)
                {
                    System.out.println("There are no more projects that could be added right now. Please try again later.");
                }
                else
                {
                    // Creating a new Project object by calling the method
                    NewProject plink = new NewProject();
                    pobj[pNum] = new NewProject(plink.pnum, plink.pname, plink.building, plink.address, plink.erfnum, plink.tcost, plink.cpaid, plink.date, plink.cusname, plink.cuscpnum, plink.cusemail, plink.cusaddress);
                    System.out.println("Your project has been added and your project number is " + pNum );
                    pNum++;
                }
            }
            
            // If condition for what the user wants to do
            if (option == 'b' || option == 'B')
            {
                // If condition to check the statement if its true
                if (aNum > 4)
                {
                    System.out.println("No more Architects may be added");
                } 
                else
                {
                    // Creating a new Architect object by calling the method
                    NewArchitect alink = new NewArchitect();
                    aobj[aNum] = new NewArchitect(alink.name, alink.num, alink.email, alink.address);
                    aNum++;
                }
            }

            // If condition for what the user wants to do
            if (option == 'c' || option == 'C')
            {
                // If condition to check the statement if its true
                if (cNum > 4)
                {
                    System.out.println("No more Contracters may be added");
                }
                else
                {
                    // Creating a new Contractor object by calling the method
                    NewContractor clink = new NewContractor();
                    cobj[cNum] = new NewContractor(clink.name, clink.num, clink.email, clink.address);
                    cNum++;
                }
            }

            // If condition for what the user wants to do
            if (option == 'd' || option == 'D')
            {
                //Allows code to write to the text document
                PrintWriter writer = new PrintWriter("Completed project.txt");

                int projnum = Integer.parseInt(JOptionPane.showInputDialog("Please enter the project you want to finalize"));
                
                // If condition to check the statement
                if (pobj[projnum].getCost() > 0.00)
                {
                    // Print message and write to the text file
                    System.out.println("You still owe R" + pobj[projnum].getCost());
                    writer.write("You still owe R" + pobj[projnum].getCost() + "\n");
                    writer.write("Name: " + pobj[projnum].cusname + "\n");
                    writer.write("Email: " + pobj[projnum].cusemail + "\n");
                    writer.write("Phone number: " + pobj[projnum].cuscpnum + "\n");
                    writer.write("Address: " + pobj[projnum].cusaddress + "\n");
                    writer.write("Project number: " + pobj[projnum].pnum + "\n");
                    writer.write("Project name: " + pobj[projnum].pname + "\n");
                    writer.write("Building type: " + pobj[projnum].building + "\n");
                    writer.write("ERF Number: " + pobj[projnum].erfnum + "\n");
                    writer.write("Total cost: " + pobj[projnum].tcost + "\n");
                    writer.write("Due Date: " + pobj[projnum].date + "\n");
                    writer.write(timestamp);
                    System.out.println("");
                    
                    //  Close the required text file
                    writer.close();
                } 
                else
                {
                    // Write to the required text file 
                    writer.println("Name: " + pobj[projnum].cusname + "\n");
                    writer.write("Email: " + pobj[projnum].cusemail + "\n");
                    writer.write("Phone number: " + pobj[projnum].cuscpnum + "\n");
                    writer.write("Address: " + pobj[projnum].cusaddress + "\n");
                    writer.write("Project number: " + pobj[projnum].pnum + "\n");
                    writer.write("Project name: " + pobj[projnum].pname + "\n");
                    writer.write("Building type: " + pobj[projnum].building + "\n");
                    writer.write("ERF Number: " + pobj[projnum].erfnum + "\n");
                    writer.write("Total cost: " + pobj[projnum].tcost + "\n");
                    writer.write("Due Date: " + pobj[projnum].date + "\n");
                    writer.write(timestamp);
                    System.out.println("");
                    
                    // Close the required text file 
                    writer.close();
                }
            }

            // If condition for what the user wants to do
            if (option == 'z' || option == 'Z')
            {
                char option2 = JOptionPane.showInputDialog("What would you like to change: \n A) A Projects Due Date \n B) The Total Price of the Project \n C) A Contractors Information \n E) Exit").toLowerCase().charAt(0);

                // While loop to check if the user exited the program
                while (option2 != 'e' || option2 == 'E')
                {
                    //  Allow user input to select the project
                    int projnum2 = Integer.parseInt(JOptionPane.showInputDialog("Please enter which project you want to change: "));
                    
                    // If condition for what the user wants to do
                    if (option2 == 'a' || option2 == 'A')
                    {
                        // If condition to check the project
                        if (pobj[projnum2].date.isEmpty())
                        {
                            // Print out relevant message 
                            System.out.println("This project does no exist.");
                        }
                        else
                        {     
                            // Allow user to change the due date and print out relevant message 
                            String change = JOptionPane.showInputDialog("Please enter the new due date using this format (YYYY/MM/DD): ");
                            System.out.println("The project due date has been changed.");
                            System.out.println("Old due date: " + pobj[projnum2].date);
                            pobj[projnum2].date = change;
                            System.out.println("New due date: " + pobj[projnum2].date);
                        }
                    }

                    // If condition for what the user wants to do
                    if (option2 == 'b' || option2 == 'B')
                    {
                        //  Take in the new total amount input
                        double change = Double.parseDouble(JOptionPane.showInputDialog("Enter the New Amount: "));
                        
                        // Asking for user input and changing the total cost of the project and displaying it
                        System.out.println("The project total cost has been changed." );
                        System.out.println("The old total cost was R" + pobj[projnum2].tcost);
                        pobj[projnum2].tcost = change;
                        System.out.println("The new total cost is R" + pobj[projnum2].tcost);
                    }

                    // If condition for what the user wants to do
                    if (option2 == 'c' || option2 == 'C')
                    {
                        // Asking for user input and changing the Contractor details 
                        cobj[projnum2].name = JOptionPane.showInputDialog("Please enter the new contracter name");
                        cobj[projnum2].num = Integer.parseInt(JOptionPane.showInputDialog("Please enter the new contracter phone number"));
                        cobj[projnum2].email = JOptionPane.showInputDialog("Please enter the new contracter email address");
                        cobj[projnum2].address = JOptionPane.showInputDialog("Please enter the new contracter physical address");
                    }
                }
            }
            option = JOptionPane.showInputDialog("What would you like to do. A) Add a project B) Add an Architect C) Add a Contracter D) Finalize a Project Z) Edit E) Exit").toLowerCase().charAt(0);
        }
    }
}
