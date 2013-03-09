/* 
Author: Pascal Bruno (pascal@pascalbruno.com)
Date: 03/09/13
Description: This program is used to test the ReadNumbers.java class
*/

import java.util.Scanner;

public class TestReadNumbers
{
    public static void main(String[] args) 
    {
        final String SENTINEL = "$";
        String userInput;
        Scanner keyboard = new Scanner(System.in);
        
        while (true)
            {
                System.out.println("Enter string of integers you would like to read. Enter '$' to exit");
                System.out.print("> ");
                userInput = keyboard.nextLine();

                if (userInput.equals(SENTINEL))
                    return;
            
                ReadNumbers testObject = new ReadNumbers(userInput);
            
                testObject.initialize();
                testObject.readNum();
            }
    }
}