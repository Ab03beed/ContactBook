package student.miun.abda2100;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        String userChoice;
        boolean exit = false;

        ContactBook myContactBook = new ContactBook();

        while(!exit){
            System.out.println("\n(******** Contact book ********) \n" +
                    "1: Add new contact \n" +
                    "2: Display all your contacts \n" +
                    "3: Search in your contacts \n" +
                    "4: Delete someone form your contacts \n" +
                    "5: Sort by last name \n" +
                    "6: Sort by signature \n" +
                    "7: Sort by length \n" +
                    "8: Sort randomly \n" +
                    "9: Save the list to a text file \n" +
                    "10: Load list from a text file \n" +
                    "0: Exit the program \n"
            );

            System.out.print("Enter >");
            userChoice = scan.nextLine();

            switch(userChoice){
                case "1":
                    myContactBook.Add();
                    break;

                case "2":
                    myContactBook.Display();
                    break;

                case "3":
                    myContactBook.Search();
                    break;

                case "4":
                    myContactBook.Delete();
                    break;

                case "5":
                    myContactBook.SortByLastName();
                    break;

                case "6":
                    myContactBook.SortBySignature();
                    break;

                case "7":
                    myContactBook.SortByLength();
                    break;

                case "8":
                    myContactBook.SortRandomly();
                    break;

                case "9":
                    myContactBook.SaveList();
                    break;

                case "10":
                    myContactBook.LoadList();
                    break;

                case "0":
                    exit = true;
                    System.out.println("Good bye");
                    break;

                default:
                    System.out.println("\""+userChoice+ "\" is an invalid option. \nTry one of the following commands");
                    break;
            }
        }
    }
}
