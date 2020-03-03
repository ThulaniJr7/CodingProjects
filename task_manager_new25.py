# Program Name: task_manager_new25 
# Program By: T Langa
# Dated on: 29 Jan 2020
# Program has different functionalities depending on who is logged in and what that user wants to do

# Importing packages
import datetime
from asyncio import tasks, all_tasks
from datetime import date
import os, sys, string

def register_new_user(r):

    # Program registers a new user to the file according to the user inputted and opens the relevant files
    check = False
    while check == False:
        new_username = input("Please enter the desired new user name: ")
        existing_user = []
        existing_user_dict = []
        read_users = open('user.txt', 'r')
        existing_user = read_users.read().splitlines()
        read_users.close()
        existing_user_dict = {line.split(",")[0]:line.split(",")[1] for line in existing_user}
        # Program checks if the username is already in the text file and prints out relevant message
        if new_username in existing_user_dict:
            print (f"The username {new_username} already exists. You need to enter a different username.")
            check = False
        else:
            print(f"{new_username} is a valid username.")
            check = True
    
    # Ask for the password for the new user and check if they are the same. Once successful, print relevant message 
    confirmation = False
    while confirmation == False:
        password1 = input(f"Please type in a new password for the new user {new_username}: ")
        confirm_password = input(f"Please confirm the password for the user {new_username}: ")
        if confirm_password.lower() == password1.lower():
            ofile_1 = open('user.txt', 'a')
            ofile_1.write ("\n" + new_username + "," + password1)
            print("New username has successfully been registered.")
            ofile_1.close()
            confirmation = True
            break
        else:
            print("The Passwords you've entered do not match. Please ensure the passwords are exactly the same.")
            confirmation = False
    print("Returning back to the Menu Page...")
    displayAdminMenu(r)

def add_new_task(d):
    # Program opens the necessary files and writes them to a list so we can manipulate it
    t1 = open("tasks.txt", "r+")
    lst = []
    stng = ""
    for line in t1:
        lst.append(line)

    # User enters the required information for each question
    username_1 = input("Please enter the user you would like to assign the task to: ")
    task_name = input("Please enter the title of the task: ")
    task_desc = input("Please give a short description of what the task does: ")
    due_date = input("Please give the due date for the task: YYYY-MM-DD ")
    completed = "No"
    
    # All info on new task will be written to the tasks file

    stng = username_1 + ", " + task_name + ", " + task_desc + ", " + str(date.today()) + ", " + due_date + ", " + completed
    lst.append(stng + "\n")
    t1.write(stng)
    t1.close()

    print ("The new task has been added to the task text file")
    displayAdminMenu(d)

def view_all_task(f):

    index = 0
    # Program will print all the tasks to the screen
    t3 = open("tasks.txt", "r+")
    contents = t3.read()
    t3.close()
    print(contents)
    index += 1
    print("\n")
    displayAdminMenu(f)

def view_my_task(c):

    # Program will search for the username in text files and print out their allocated tasks
    username1 = input("Please enter your username to find the task assign to you: ")
    search = open('tasks.txt', 'r+')
    
    for line in search:
        if username1 in line:
            print(line)
            print("\n")
   
    if c == "admin":
        displayAdminMenu(c)
    else:
        displayUserMenu(c)

def generate_new_report(g):
    from datetime import date
    # Program gets the current day and time
    today = str(date.today())
    task_overview = open("task_overview.txt","w")
    user_overview = open("user_overview.txt","w")
    tasks = open("tasks.txt","r")
    users = open("user.txt","r")
    yes = 0
    no = 0
    overdue = 0
    task_counter = 0
    task_list = []
    for line in tasks: 
        split_task = line.split(",") 
        task_counter += 1
        task_list.append(split_task)
        if split_task[5] == "Yes":
            yes += 1
        elif split_task[5] == "No" or " No\n":
            no += 1
            date = split_task[4].strip(" ") 
            if today > date:
                overdue +=1
    total = no + yes
    percentage = no / total
    percentage_overdue = overdue / no
    task_overview.seek(0,2)
    task_overview.write(f"There are total of {yes} completed tasks\n")
    task_overview.write(f"There are total of {no} uncompleted tasks\n")
    task_overview.write(f"There are a total of {overdue} overdue tasks\n")
    task_overview.write(f"{(percentage*100):.2f}% of tasks are incomplete\n") 
    task_overview.write(f"{(percentage_overdue*100):.2f}% of uncompleted tasks are overdue.")

    user_counter = 0
    user_list = []
    another_lst = []
    complete_counter = 0
    overdue = 0
    for line in users: 
        line_strip = line.strip("\n")
        split_line = line_strip.split(",")
        user_list.append(split_line[0])
    for usernames in user_list:
        for index in range (0 ,len(task_list)):
            if f'{usernames}' in f"{task_list[index]}" and " Yes" in f"{task_list[index]}":  
                user_counter +=1
                complete_counter += 1
            elif f'{usernames}' in f"{task_list[index]}":
                user_counter +=1
                date = task_list[index][4].strip(" ")
                if today > date:
                    overdue +=1

        another_lst.append(f"{usernames}") 
        another_lst.append(user_counter)
        another_lst.append(complete_counter)
        another_lst.append(overdue)
        complete_counter = 0 # used to reset counter for the next user each loop
        user_counter = 0
        overdue = 0
    try:
        it = iter(range(0, len(another_lst)))
        i = 0
        counter = 1
        while i in it: 
            if i >= counter:
                for j in range(0, 3):
                    i = next(it)
            user_overview.write(f"username {another_lst[i]}.\n")
            user_overview.write(f"has {another_lst[i+1]} tasks.\n")     
            math = another_lst[i+1] / total 
            user_overview.write(f"which is {(math*100):.2f}% of the total tasks.\n")
            complete = another_lst[i+2] / another_lst[i+1] # works out the complete percentage
            user_overview.write(f"With {(complete*100):.2f}% of the tasks being completed.\n")
            user_overview.write(f"With {((1-complete)*100):.2f}% of the tasks being uncompleted.\n")
            incomplete = another_lst[i+1] - another_lst[i+2]
            if incomplete == 0:
                overdue_percentage = 0.00
            else:
                overdue_percentage = another_lst[i+3] / incomplete
            user_overview.write(f"Of the {incomplete} incomplete task {(overdue_percentage*100):.2f}% are overdue.\n\n")
            i += 1
    except: 
        print("User and Task reports have been succesfully generated.")
    finally:
        task_overview.close()
        user_overview.close()
        users.close()

    displayAdminMenu(g)

def showStatistics(s):
    t3 = open("tasks.txt", "r+")
    contents = t3.read()
    print (contents)
    t3.close()

    t4 = open("user.txt" , "r+")
    contents2 = t4.read()
    print (contents2)
    t4.close()

    print("\n")

    displayAdminMenu(s)

def displayAdminMenu(a):
    # Program prints the admin menu
    print("Welcome Admin. Please select from the menu below as to what you would like to do. ")

    menu2 = """
    Please select one of following options (R-DS):
    R)   Register User
    A)   Add Task
    VA)  View All Tasks
    VM)  View My Tasks
    GR)  Generate Reports
    DS)  Display Statistics
    L)   Log Off
    E)   Exit
    """
    option = str(input(menu2))
    
    if option.lower() == "r":
        register_new_user(a)

    elif option.lower() == "a":
        add_new_task(a)

    elif option.lower() == "va":
        view_all_task(a)

    elif option.lower() == "vm":
        view_my_task(a)

    elif option.lower() == "gr":
        generate_new_report(a)

    elif option.lower() == "ds":
        showStatistics(a)

    elif option.lower() == "l":
        startUpMenu()

    elif option.lower() == "e":
        print("You are now exiting the program. Goodbye :) ")

    else:
        print("This is not an option. Please try again using the stipulated options." + "\n")
        displayAdminMenu(a)

def startUpMenu():
    print("Welcome to the Home Page. Please enter your Username and corresponding Password to proceed. ")
    # Program asks user to input their details
    commence = 'False'
    
    lst = ofile_1.splitlines()   
    count = 0
    
    while commence == "False":
        username = input("Please Enter Your Username: ")
        password = input("Please Enter Your Password: ")

        if username == "admin" and password == "adm1n":
            commence = "True"
            displayAdminMenu(username)
            break

        for line in range (1,11):

            slen = len(lst[count])
            c_spot = lst[count].find(",")
            nam = str(lst[count])[0:c_spot]
            pas = str(lst[count])[c_spot+1:slen]
            count += 1

            if count > 9:
                count = 0

            if username == nam and password == pas:
                commence = "True"
                displayUserMenu(username)
                break

        print("The details entered aren't valid. Please ensure all the details are correct and try again. ")

def displayUserMenu(b):
    # Program shows the specific users menu
    print("Welcome User. Please select from the menu below as to what you would like to do. ")

    menu1 = """
    Please Select one of the following options (VA-E):
    VA)  View All Tasks
    VM)  View My Tasks
    L)   Log Off
    E)   Exit
    """

    option = str(input(menu1))

    if option.lower() == "va":
        view_all_task(b)

    if option.lower() == "vm":
        view_my_task(b)

    if option.lower() == "l":
        startUpMenu()

    if option.lower() == "e":
        print("You are now exiting the program. Goodbye :) ")
    
    else:
        print("This is not an option. Please try again using the stipulated options." + "\n")
        displayUserMenu(b)

t1 = open("tasks.txt", "r+")
ofile = t1.read()
t2 = open("user.txt", "r+")
ofile_1 = t2.read()

startUpMenu()
