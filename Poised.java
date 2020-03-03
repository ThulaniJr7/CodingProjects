//package poised;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.*;
import javax.swing.JOptionPane;

public class Poised
{
//	Main Method
    public static void main(String[] args) throws FileNotFoundException, IOException
    {
//      Creates a new text document so we can write to it
        FileWriter writer = new FileWriter("Completed project.txt");

//		Creating the objects that will be needed
        newProject[] pobj = new newProject[5];
        int pNum = 0;

        newArchitect[] aobj = new newArchitect[5];
        int aNum = 0;

        newContractor[] cobj = new newContractor[5];
        int cNum = 0;
        
//		Importing the current date
        String timeStamp = new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime());
        
//    	Display message and ask for the user input
        char option = JOptionPane.showInputDialog("What would you like to do. A) Add a project B) Add an Architect C) Add a Contracter D) Finalize a Project Z) Edit E) Exit").toLowerCase().charAt(0);
        
//		While loop to make sure that the menu keeps showing up until E is inputted
        while (option != 'e'|| option != 'E')
        {
        	
//			Condition for what the user inputted
            if (option == 'a' || option == 'A')
            {

//            	If statement to check the condition
                if (pNum > 4)
                {
//                  print out relevant message
                    System.out.println("There are no more projects that could be added right now. Please try again later");
                } 
                else
                {
//                	Call the method to allow a new project to be added and execute it effectively
                    newProject plink = new newProject();
                    pobj[pNum] = new newProject(plink.pNum, plink.pname, plink.building, plink.address, plink.erfnum, plink.tcost, plink.cpaid, plink.date, plink.cusname, plink.cuscpnum, plink.cusemail, plink.cusaddress);
                    pNum++;
                    
//                  print out relevant message
                    System.out.println("Your project has been added");
                }
            }

//			Condition for what the user inputted
            if (option == 'b' || option == 'B')
            {
//            	If statement to check the condition
                if (aNum > 4)
                {
//                  print out relevant message
                    System.out.println("No more Architects may be added");
                } 
                else
                {
//                	Call the method to allow a new project to be added and execute it effectively
                    newArchitect alink = new newArchitect();
                    aobj[aNum] = new newArchitect(alink.name, alink.num, alink.email, alink.address);
                    aNum++;
                }
            }

//			Condition for what the user inputted
            if (option == 'c' || option == 'C')
            {
//            	If statement to check the condition
                if (cNum > 4)
                {
//                  print out relevant message
                    System.out.println("No more Contractors may be added");
                } 
                else
                {
//                	Call the method to allow a new project to be added and execute it effectively
                    newContractor clink = new newContractor();
                    cobj[cNum] = new newContractor(clink.name, clink.num, clink.email, clink.address);
                    cNum++;
                }
            }

//			Condition for what the user inputted
            if (option == 'd' || option == 'D')
            {
                int projnum = Integer.parseInt(JOptionPane.showInputDialog("Please enter the project you want to finilize"));
                
//            	If statement to check the condition
                if (pobj[projnum].getCost() > 0.00)
                {
//                  print out relevant message
                    System.out.println("You still owe R" + pobj[projnum].getCost());
                } 
                else
                {
//                	Write to the text file the relevant information
                    int randomNum = (int)(Math.random()*5);
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
                    writer.write(aobj[ran].toString() + "\n");
                    writer.write(cobj[ran].toString() + "\n");
                    writer.write(timeStamp);   
                }
            }

//			Condition for what the user inputted
            if (option == 'z' || option == 'Z')
            {
//            	Display message and ask for the user input
                char option2 = JOptionPane.showInputDialog("What would you like to change A) A projects Due Date B) The total price of the project C) A Contractors information E) Exit").toLowerCase().charAt(0);

                while (option2 != 'e' || option2 != 'E')
                {
//                	Display message and ask for the user input
                    int projnum2 = Integer.parseInt(JOptionPane.showInputDialog("Please enter which project you want to change"));
                    
//        			Condition for what the user inputted
                    if (option2 == 'a' || option2 == 'A')
                    {
                        if (pobj[projnum2].date.isEmpty())
                        {
//                          print out relevant message
                            System.out.println("This project does no exist");
                        } 
                        else
                        {
//                			Display message and ask for the user input
                            String change = JOptionPane.showInputDialog("Please enter the new due date using this format YYYY/MM/DD");
                            System.out.println("The project due date has been changed");
                            System.out.println("Old due date: " + pobj[projnum2].date);
                            pobj[projnum2].date = change;
                            System.out.println("New due date: " + pobj[projnum2].date);
                        }
                    }
                    
//        			Condition for what the user inputted
                    if (option2 == 'b' || option2 == 'B')
                    {
//                    	Display message and ask for the user input
                        double change = Double.parseDouble(JOptionPane.showInputDialog("Enter the new amount"));

//                      print out relevant message
                        System.out.println("The project total cost has been changed");
                        System.out.println("The old total cost was R" + pobj[projnum2].tcost);
                        pobj[projnum2].tcost = change;
                        System.out.println("The new total cost is R" + pobj[projnum2].tcost);
                    }
                    
                    
//        			Condition for what the user inputted
                    if (option2 == 'c' || option2 == 'C')
                    {
//                    	Display message and ask for the user input
                        cobj[projnum2].name = JOptionPane.showInputDialog("Please enter the new contracter name");
                        cobj[projnum2].num = Integer.parseInt(JOptionPane.showInputDialog("Please enter the new contracter phone number"));
                        cobj[projnum2].email = JOptionPane.showInputDialog("Please enter the new contracter email address");
                        cobj[projnum2].address = JOptionPane.showInputDialog("Please enter the new contracter physical address");
                    }
                }
            }
//        	Display message and ask for the user input
            option = JOptionPane.showInputDialog("What would you like to do. A) Add a project B) Add an Architect C) Add a Contracter D) Finalize a Project Z) Edit E) Exit").charAt(0);
        }
    }
}
