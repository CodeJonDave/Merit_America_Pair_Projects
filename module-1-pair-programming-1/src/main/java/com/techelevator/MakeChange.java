package com.techelevator;

import java.util.Scanner;

/*
 Write a command line program which prompts the user for the total bill, and the amount tendered. It should then
 display the change required.

 Please enter the amount of the bill: 23.65
 Please enter the amount tendered: 100.00
 The change required is 76.35
 */
public class MakeChange {

    public static void main(String[] args) {
        while (true) {
            Scanner input = new Scanner(System.in);
         
            System.out.println("Please enter the amount of the bill: ");
            double bill = Double.parseDouble(input.nextLine());
         
            System.out.println("Please enter the amount tendered:");
            double amount = Double.parseDouble(input.nextLine());
            
            System.out.println("The change required is " + (amount - bill));

            System.out.println("Do you want to make another calculation? (y/n)");
            response = userInput.nextLine();

            if (!response.equalsIgnoreCase("y")) {
                System.out.println("Thank you for using this service!");
                break;
            }

        }

    }
}
