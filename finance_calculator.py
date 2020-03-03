# Program name: finance_calculators.py
# Written by T Langa
# Dated on 1/14/2020

import math
# Program prints the instructions and gives the user a choice
print("Choose either 'investment' or 'bond' from the menu below to proceed:")
print(" ")
print("investment - to calculate the amount of interest you'll earn on interest")
print("bond       - to calculate the amount you'll have to pay on a home loan")
print(" ")
decision = input("Between 'investment' or 'bond', which one would you like to calculate? ")

final_dec = decision.lower()
# Program calculates formula depending on the decision made by the user 
if final_dec == "investment":
    money_dep = input("How much money are you depositing? R")
    rate = input("How much interest rate would you like? ")
    years = input("How many years are you planning on investing for? ")
    interest = input("Would you like 'simple' or 'compound' interest? (Simple/Compound): ")
    
    # Program executes either the simple or compound formula depending on the user's decision 
    if interest.lower() == "simple":
        # Simple formula: a = money_dep*(1 + (rate/100) * years) 
        # Program then executes the formula to work out the simple interest
        final_money_dep = float(money_dep)
        s_formula = final_money_dep*(1.0 + (float(rate)/100.0)* float(years))
        smoney_earned = s_formula - final_money_dep
        print(f"The amount you will earn using an interest rate of {rate}% when depositing R{final_money_dep:.2f} will be R{smoney_earned:.2f}.")
        print(f"In total, your wealth would accumulate to R{s_formula:.2f}.")

    elif interest.lower() == "compound":
        # Compound formula: a = money_dep * (1 + (rate/100)) ^ years
        # Program then executes the formula to work out the compound interest
        final_money_dep = float(money_dep)
        final_rate = float(rate) / 100.0
        c_formula = final_money_dep * (1.0 + (float(rate)/100.0))**float(years)
        cmoney_earned = c_formula - final_money_dep
        print(f"The amount you will earn using an interest rate of {final_rate}% when depositing R{final_money_dep:.2f} will be R{cmoney_earned:.2f}")
        print(f"In total, your wealth would accumulate to R{c_formula:.2f}")
        
# Program executes the formula depending on the decision which will be bond underneath. Cast the inputs into floats so that it can be easily calculated
if final_dec == "bond":
    house_value = input("What is the value of the house? ")
    hv = float(house_value)
    int_rate = input("What is the interest rate? ")
    ir = float(int_rate)
    months = input("The number of months you plan to repay the bond? ")
    mnt = float(months)

    # Bond Formula: repay = house_value((int_rate(1 + int_rate) months ) / (((1 + int_rate) months ) - 1))
    # Program then prints the answer after working out the formula
    final_B_rate = (ir / 100.0) / 12.0
    repayment = hv*((final_B_rate*(1.0 + final_B_rate)**mnt )/(((1.0 + final_B_rate)**mnt ) - 1.0))

    print(f"The user will have to repay an amount of R{repayment:.2f} per month")

# Program executes the error message if nothing was entered
if final_dec == "" or final_dec == " ":
    print("You haven't entered anything. Please run the program again and type in either 'investment' or 'bond'.")