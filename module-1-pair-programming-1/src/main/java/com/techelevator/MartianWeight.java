package com.techelevator;

import java.util.Scanner;

/*
In case you've ever pondered how much you weigh on Mars, here's the calculation:
 	Wm = We * 0.378
 	where 'Wm' is the weight on Mars, and 'We' is the weight on Earth
 
Write a command line program which accepts a series of Earth weights from the user  
and displays each Earth weight as itself, and its Martian equivalent.

Enter a series of Earth weights (space-separated): 98 235 185
 
 98 lbs. on Earth is 37 lbs. on Mars.
 235 lbs. on Earth is 88 lbs. on Mars.
 185 lbs. on Earth is 69 lbs. on Mars. 
 */
public class MartianWeight {

    public static void main(String[] args) {
        while (true) {
            Scanner input = new Scanner(System.in);
            System.out.println("Enter a series of Earth weights (space-separated)");
            String[] numberStrings = input.nextLine().split(" ");
            for (String num : numberStrings) {
                int earthWeight = Integer.parseInt(num);
                int marsWeight = (int) (earthWeight * 0.378);
                System.out.println(earthWeight + " lbs on Earth is " + marsWeight + " lbs. on Mars.");
            }
            
            System.out.println("Do you want to make another calculation? (y/n)");
            response = userInput.nextLine();

            if (!response.equalsIgnoreCase("y")) {
                System.out.println("Thank you for using the Mars Weigth Calculator!");
                break;
            }

        }

    }
}
