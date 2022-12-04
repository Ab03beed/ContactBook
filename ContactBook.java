package student.miun.abda2100;

import java.io.*;
import java.util.*;

public class ContactBook {
    //The main list for each contactBook.
    ArrayList<Person> contactsList = new ArrayList<>();
    Scanner scan = new Scanner(System.in);

    void Add(){
        String firstName, lastName, signature;
        int length;
        String postAddress, postNumber, postTown;

        //Create new Person and new Address
        Person newContact = new Person();
        Address newAddress = new Address();

        //Getting input from the user.
        System.out.print("Enter firstname > ");
        firstName = scan.nextLine();

        System.out.print("Enter lastname > ");
        lastName = scan.nextLine();

        signature = createSignature(firstName, lastName);

        System.out.print("Enter the length > ");
        length = scan.nextInt();

        scan.nextLine();

        System.out.print("Enter the post address > ");
        postAddress = scan.nextLine();

        System.out.print("Enter the post number > ");
        postNumber = scan.nextLine();

        System.out.print("Enter the post town > ");
        postTown = scan.nextLine();

        System.out.println();


        //Checking if same contact found.
        boolean sameContact = checkContact(firstName, lastName, length);
        String userChoice = "";

        while(sameContact){

            System.out.println("Same contact founded in your contacts!");
            System.out.println("Do you want to change some values (y) or cancel (n) ?");
            System.out.print("> ");

            userChoice = scan.nextLine();
            userChoice = userChoice.toLowerCase();

            if(userChoice.equals("y")){
                System.out.print("Enter firstname > ");
                firstName = scan.nextLine();

                System.out.print("Enter lastname > ");
                lastName = scan.nextLine();

                signature = createSignature(firstName, lastName);

                System.out.print("Enter the length > ");
                length = scan.nextInt();

                scan.nextLine();

                System.out.print("Enter the post address > ");
                postAddress = scan.nextLine();

                System.out.print("Enter the post number > ");
                postNumber = scan.nextLine();

                System.out.print("Enter the post town > ");
                postTown = scan.nextLine();

                System.out.println();
            }
            else if(userChoice.equals("n")){
                break;
            }


            sameContact = checkContact(firstName, lastName, length);

        }

        //Setting the contact info when the contact is unique
        if(!sameContact){
            newContact.setSignature(signature);
            newContact.setFirstName(firstName);
            newContact.setLastName(lastName);
            newContact.setLength(length);
            newAddress.setPostAddress(postAddress);
            newAddress.setPostNumber(postNumber);
            newAddress.setPostTown(postTown);
            newContact.setAddress(newAddress);
            contactsList.add(newContact);
        }

    }

    void Display(){

        // Checking if the contactList is empty.
        if(!contactsList.isEmpty()){
            System.out.println("***** NAME LIST *****");
            System.out.println("Number of persons in list: " + contactsList.size());

            System.out.printf("%-15s %-15s %-30s %-10s",  "NR", "Sign", "Name", "Length [m]");
            System.out.println();

            //Printing out the contactList one by one.
            for(int index = 0; index < contactsList.size(); index++){
                Person currentPerson = contactsList.get(index);

                System.out.printf("%-15s %-15s %-30s %-10s",  (index+1), currentPerson.getSignature(), currentPerson.getFirstName() +" "+ currentPerson.getLastName(), currentPerson.getLength()/100.00);
                System.out.println();

                //Displaying only 5 contacts at once.
                if((index+1) % 5 == 0){
                    System.out.print("\nPress enter to show next page! >");
                    scan.nextLine();
                    System.out.println();
                }

            }
        }else{
            System.out.println("\nThe contact book is empty");
        }

    }

    void Search(){

        Person currentPerson;
        String searchValue;


        System.out.print("Enter signature to search for > ");
        searchValue = scan.nextLine();
        searchValue = searchValue.toLowerCase();

        //New arrayList for the founded contacts.
        ArrayList<Person> foundContacts = new ArrayList<>();

        for(int index = 0; index < contactsList.size(); index++){
            currentPerson = contactsList.get(index);

            String currentSignature = currentPerson.getSignature();

            //Every signature that contains searchValue will be added to the foundContacts.
            if(currentSignature.contains(searchValue)){
                foundContacts.add(currentPerson);
            }

        }

        //Printing out the foundContacts list
        if(!foundContacts.isEmpty()){
            System.out.printf("\n%-15s %-15s %-30s %-10s",  "NR", "Sign", "Name", "Length [m]");
            System.out.println();

            for(int index = 0; index < foundContacts.size(); index++){
                currentPerson = foundContacts.get(index);

                System.out.printf("%-15s %-15s %-30s %-10s",  (index+1), currentPerson.getSignature(), currentPerson.getFirstName() +" "+ currentPerson.getLastName(), currentPerson.getLength()/100.00);
                System.out.println();

                if((index+1) % 5 == 0){
                    System.out.print("\nPress enter to show next page! >");
                    scan.nextLine();
                    System.out.println();
                }

            }

        }else{
            System.out.println("\nThere is no Person with the signature.");
        }


    }

    void Delete(){

        Person currentPerson;

        String searchValue;
        boolean foundContactToDelete = false;

        System.out.print("Enter signature to delete > ");
        searchValue = scan.nextLine();


        for(int index = 0; index < contactsList.size(); index++){
            currentPerson = contactsList.get(index);

            String tempSignature = currentPerson.getSignature();
            tempSignature = tempSignature.toLowerCase();

            // when the tempSignatue equals the searchValue, the person removes from the list.
            if(tempSignature.equals(searchValue)){
                contactsList.remove(index);
                foundContactToDelete = true;
            }

        }

        //Printing the message the says if deleted or not.
        if(foundContactToDelete){
            System.out.println("\nThe contact with the signature: " + searchValue + " has been deleted.");
        }else{
            System.out.println("\nThere is no contact by the signature " + searchValue + ".");
        }

    }

    void SortByLastName(){

        String lastName1, lastName2;

        for(int i = 0; i < (contactsList.size() -1); i++){

            lastName1 = contactsList.get(i).getLastName();

            for(int j = (i+1); j < contactsList.size(); j++){

                lastName2 = contactsList.get(j).getLastName();

                //comparing 2 lastNames and swapping them if compare func returns > 0
                if(lastName1.compareTo(lastName2) > 0){
                    Person tempPerson = contactsList.get(i);
                    contactsList.set(i, contactsList.get(j));
                    contactsList.set(j, tempPerson);

                    lastName1 = contactsList.get(i).getLastName();

                }

            }

            //If 2 lastnames are the same, so it sorts by firstName.
            if(contactsList.get(i).getLastName().compareTo(contactsList.get(i+1).getLastName()) == 0){

                if(contactsList.get(i).getFirstName().compareTo(contactsList.get(i+1).getFirstName()) > 0){

                    Person tempPerson = contactsList.get(i);
                    contactsList.set(i, contactsList.get(i+1));
                    contactsList.set((i+1), tempPerson);

                }

            }

        }


    }

    void SortBySignature(){

        String signature1, signature2;

        for(int i = 0; i < (contactsList.size() - 1); i++){

            signature1 = contactsList.get(i).getSignature();

            for(int j = (i + 1); j < contactsList.size(); j++){

                signature2 = contactsList.get(j).getSignature();

                if(signature1.compareTo(signature2) > 0){

                    Person tempPerson = contactsList.get(i);
                    contactsList.set(i, contactsList.get(j));
                    contactsList.set(j, tempPerson);

                    signature1 = contactsList.get(i).getSignature();

                }
            }
        }

    }

    void SortByLength(){

        int length1, length2;

        for(int i = 0; i < (contactsList.size() - 1); i++){

            length1 = contactsList.get(i).getLength();

            for(int j = (i+1); j < contactsList.size(); j++){

                length2 = contactsList.get(j).getLength();

                if(length1 > length2){

                    Person tempPerson = contactsList.get(i);
                    contactsList.set(i, contactsList.get(j));
                    contactsList.set(j, tempPerson);

                    length1 = contactsList.get(i).getLength();
                }

            }
        }

    }

    void SortRandomly(){
        ArrayList<Person> tempList = new ArrayList<>();
        Random rand = new Random();

        //Adding all contacts to a tempList.
        for (Person p: contactsList){
            tempList.add(p);
        }


        //Set the size to listSize.
        int size = tempList.size();


        for (int index = 0; index < size; index++){

            //generate a random number between 0 and tempList size.
            int randNum = rand.nextInt(tempList.size());

            //Setting the contact at randNum in tempList to contactsList.
            contactsList.set(index, tempList.get(randNum));

            //Removing the contact from the tempList so we dont copy same contact twice.
            tempList.remove(randNum);
        }

    }

    void SaveList() {
        String fileName;
        int shiftingKey = 0;

        System.out.print("Enter the file name? write the name without file type! ");
        fileName = scan.nextLine();

        try {
            FileWriter writer = new FileWriter("D:\\Code\\Java\\Project\\Project\\src\\student\\miun\\abda2100\\TextFiles\\" + fileName + ".txt");

            System.out.print("Enter the encrypt key 0<->95 > ");
            shiftingKey = scan.nextInt();
            scan.nextLine();

            while(shiftingKey > 95){
                System.out.print("Enter the encrypt key 0<->95 > ");
                shiftingKey = scan.nextInt();
                scan.nextLine();
            }

            for (int index = 0; index < contactsList.size(); index++) {
                Person currentPerson = contactsList.get(index);
                Address currentAddress = currentPerson.getAddress();

                String contact = currentPerson.getFirstName() + "|" + currentPerson.getLastName() + "|" + currentPerson.getSignature() + "|" + currentPerson.getLength() + "|"
                        + currentAddress.getPostAddress() + "|" + currentAddress.getPostNumber() + "|" + currentAddress.getPostTown();

                String encryptedContact = Encrypt(shiftingKey ,contact);

                writer.write(encryptedContact + '\n');
            }

            writer.close();

        } catch (IOException exception) {
            exception.printStackTrace();
        }

    }

    void LoadList(){
        contactsList.clear();

        String fileName;
        int shiftingKey = 0;

        System.out.print("Enter the file name you want to load from? write the name without file type! " );
        fileName = scan.nextLine();

        try {
            FileReader fileReader = new FileReader("D:\\Code\\Java\\Project\\Project\\src\\student\\miun\\abda2100\\TextFiles\\" + fileName + ".txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            System.out.print("Enter the decrypt key > ");
            shiftingKey = scan.nextInt();
            scan.nextLine();

            String line, signature;

            String splitChar = Encrypt(shiftingKey, "|");


            while((line = bufferedReader.readLine()) !=null){

                ArrayList<String> contactInfo = new ArrayList<>();
                String info = "";
                for(int index = 0; index < line.length(); index++){
                    char currentChar = line.charAt(index);

                    if(currentChar == splitChar.charAt(0) ){

                        contactInfo.add(info);
                        info = "";
                    }else{
                        info += currentChar;
                    }
                }
                contactInfo.add(info);

                Person newContact = new Person();
                Address newAddress = new Address();

                newContact.setFirstName(Decrypt(shiftingKey, contactInfo.get(0)));
                newContact.setLastName(Decrypt(shiftingKey, contactInfo.get(1)));

                signature = createSignature(Decrypt(shiftingKey,contactInfo.get(0)), Decrypt(shiftingKey,contactInfo.get(1)));

                newContact.setSignature(signature);


                newContact.setLength(Integer.parseInt(Decrypt(shiftingKey, contactInfo.get(3))));

                newAddress.setPostAddress( Decrypt(shiftingKey, contactInfo.get(4)));
                newAddress.setPostNumber( Decrypt(shiftingKey, contactInfo.get(5)));
                newAddress.setPostTown( Decrypt(shiftingKey, contactInfo.get(6)));

                newContact.setAddress(newAddress);

                contactsList.add(newContact);

            }
            bufferedReader.close();


        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    private String createSignature(String firstName, String lastName){

        String signature;

        if(firstName.length() < 3){

            if(firstName.length() == 2){
                firstName += "x";
            }else if(firstName.length() == 1){
                firstName += "xx";
            }

        }

        if(lastName.length() < 3){

            if(lastName.length() == 2){
                lastName += "x";
            }else if(lastName.length() == 1){
                lastName += "xx";
            }

        }

        signature = firstName.substring(0,3).toLowerCase() + lastName.substring(0,3).toLowerCase();

        int counter = 1;

        for(int index = 0; index < contactsList.size(); index++){
            Person currentPerson = contactsList.get(index);

            String currentSign = currentPerson.getSignature().substring(0,6);

            if(currentSign.equals(signature)){
                counter++;
            }

        }


        if(counter < 10){
            signature += "0" + String.valueOf(counter) ;
        }else{
            signature += String.valueOf(counter);
        }



        return signature;
    }

    private boolean checkContact(String firstName, String lastName, int length){

        boolean sameContact = false;

        String currentFName, currentLName;
        int currentLength;

        firstName = firstName.toLowerCase();
        lastName = lastName.toLowerCase();

        for(Person currentPerson: contactsList){

            currentFName = currentPerson.getFirstName().toLowerCase();
            currentLName = currentPerson.getLastName().toLowerCase();
            currentLength = currentPerson.getLength();


            if((firstName.equals(currentFName)) && (lastName.equals(currentLName)) && length == currentLength){
                sameContact = true;
                break;
            }
        }


        return sameContact;
    }

    private String Encrypt(int encryptKey, String line){
        String encryptedString = "";
        char currentChar;

        if(encryptKey == 0){
            return line;
        }else{
            for(int index = 0; index < line.length(); index++){
                currentChar = line.charAt(index);

                int newCharValue = currentChar + encryptKey;

                char newChar;

                if(newCharValue > 126){
                    newChar = (char) (31 + (newCharValue - 126));

                }
                else{
                    newChar = (char)  (newCharValue);
                }
                encryptedString += newChar;
            }

            return encryptedString;
        }


    }

    private String Decrypt(int decryptKey, String line){
        String decryptedString = "";
        char currentChar;

        if(decryptKey == 0){
            return line;
        }else{
            for(int index = 0; index < line.length(); index++){
                currentChar = line.charAt(index);

                int newCharValue = currentChar - decryptKey;

                char newChar;

                if(newCharValue < 32){
                    newChar = (char) (127 - (32 - newCharValue));

                }
                else{
                    newChar = (char)  (newCharValue);
                }
                decryptedString += newChar;
            }


            return decryptedString;
        }

    }


}