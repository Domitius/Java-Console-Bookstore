/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package burrisfroggy;
import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
/**
 *
 * @author Gavin Burris
 */
public class BurrisFroggy {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        // Whats left to do is decide if you want to throw some of these functions into other classes. Only if the function involve the objects heavily and makes since logically.
        
        // This is a universal Scanner that multiple functions will use.
        Scanner keyboardInput = new Scanner(System.in);
        
        // Linking files
        File bookFile = new File("Books.txt");
        File gameFile = new File("Games.txt");
        
        // Creating ArrayLists with Files
        ArrayList<Book> bookList = getFileContents(bookFile);
        ArrayList<Game> gameList = getFileContents(gameFile);
        
        System.out.println("WELCOME TO FROGGY’S BOOK STORE!\n");
        
        // Creating customer
        Customer customer = createCustomer(keyboardInput);
        
        // Displaying the Menu to the customer.
        System.out.printf("Hi %s, here is what we have available%n", customer.getName());
        displayMenu(bookList, gameList);
        
        // Collecting the customers order.
        CollectOrder(customer, keyboardInput, bookList, gameList);
        
        // Displaying and creating the recipept.
        createReceipt(customer.getName(), displayReceipt(customer.shoppingCart, customer.getName()));
    }
    
    private static void CollectOrder(Customer cust, Scanner sc, ArrayList<Book> e, ArrayList<Game> r) {
        boolean shoppingFlag = true;
        while (shoppingFlag) {
            boolean findObject = false;
            while (!findObject) {
                // Collecting desired ItemCode from user
                System.out.printf("What would you like? %n");
                String userInput = sc.next();
                
                // Loop through all the books in the ArrayList until we find the right one.
                for (Book book : e) {
                    if (book.getItemCode().equalsIgnoreCase(userInput) ) {
                        findObject = true;
                        System.out.printf("You Chose %s , How many would you like?%n", book.name);
                        int quanity = getInt(sc);
                        // Make a new CartItem with the book and quanity.
                        CartItem cartItem = new CartItem(book, quanity);
                        cust.shoppingCart.add(cartItem);
                    }
                }
                   
                // Loop through all the games in the ArrayList until we find the right one.
                for (Game game: r) {
                    if (game.getItemCode().equalsIgnoreCase(userInput) ) {
                        findObject = true;
                        System.out.printf("You Chose %s , How many would you like?%n", game.name);
                        int quanity = getInt(sc);
                        // Make a new CartItem with the game and quanity.
                        CartItem cartItem = new CartItem(game, quanity);
                        cust.shoppingCart.add(cartItem);
                    }
                }

                if (!findObject) {
                    System.out.printf("%s is not an available Item Code. Please pick a code from the menu:%n", userInput);
                    displayMenu(e, r);
                }
            }    
                
            System.out.printf("Would you like another item? Y or N%n");
            // Collecting the first character of user input in case they mistype.
            Character userChoiceChar = sc.next().charAt(0);
            // Casting as a String for comparison reasons.
            String userChoice = userChoiceChar.toString();
            
            boolean userChoiceFlag = true;
            while (userChoiceFlag) {
                if (userChoice.equalsIgnoreCase("Y")) {
                    shoppingFlag = true;
                    userChoiceFlag = false;
                } else if (userChoice.equalsIgnoreCase("N")) {
                    shoppingFlag = false;
                    userChoiceFlag = false;
                // If the user does not type Y or N, they will be in this loop until so.
                } else {
                    System.out.println("Please Choose an option: ");
                    userChoiceFlag = true;
                    userChoiceChar = sc.next().charAt(0);
                    userChoice = userChoiceChar.toString();
                }
            }
        }
    }
    
    // This function makes sure the input is an int and will loop until the user inputs an int.
    public static int getInt(Scanner sc) {
        boolean isInt = false;
        while (!isInt) {
            String num;
            num = sc.next();
            try {
                Integer.parseInt(num);
                isInt = true;
                return Integer.parseInt(num);
            } catch (NumberFormatException e) {
                System.out.println(num + " is not a valid number. Try again");
            }
        }
        // Fail code
        return -1;
    }
    
    // Displays the menu
    protected static void displayMenu(ArrayList<Book> e, ArrayList<Game> r) {
        displayArrayListBooks(e);
        displayArrayListGames(r);
    }
    
    // Returns an ArrayList with the file object you send it, Ex. Books.txt returns an ArrayList<Book> with the files content
    public static ArrayList getFileContents(File fn) {
        // Making sure the file exists before we work on it
        if (fn.exists()) {
            try {
                String fileName = fn.getName();
                Scanner fileScanner = new Scanner(fn);
                
                // If you send a book file
                if (fileName.equalsIgnoreCase("books.txt")) {
                    ArrayList<Book> bookArray = new ArrayList();
                    while (fileScanner.hasNext()) {
                        String[] textArray = fileScanner.nextLine().split(",");
                        Book book = new Book(textArray[0], textArray[1], textArray[2], Double.parseDouble(textArray[3]), textArray[4]);
                        
                        bookArray.add(book);
                    }
                    // return the ArrayList<Book>
                    return bookArray;
                    
                // If you send a game file    
                } else if (fileName.equalsIgnoreCase("games.txt")){
                    ArrayList<Game> gameArray = new ArrayList();
                    while (fileScanner.hasNext()) {
                        // Splitting the lines by , and store the values in an array
                        // 0 is Item code, 1 is name, 2 is description, 3 is price, 4 is rating
                        String[] textArray = fileScanner.nextLine().split(",");
                        Game game = new Game(textArray[0], textArray[1], textArray[2], Double.parseDouble(textArray[3]), textArray[4]);
                       
                        gameArray.add(game);
                    }
                    // return ArrayList<Game>
                    return gameArray;
                }                
            } catch (FileNotFoundException e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println(fn.getName() + " does not exsist");
        }
        return null;
    }
    
    // These two functions are program specfic. display arraylist contents with formatting. (Used in the displayMenu function).
    protected static void displayArrayListBooks(ArrayList<Book> e) {
        System.out.printf("Books Available: %n");
        for (int i = 0; i < e.size(); i++) {
            System.out.printf("  %s%n", e.get(i).displayBook());
        }
    }
    
    protected static void displayArrayListGames(ArrayList<Game> e) {
        System.out.printf("Games Available: %n");
        for (int i = 0; i < e.size(); i++) {
            System.out.printf("  %s%n", e.get(i).displayGame());
        }
    }
    
    // This function returns the receipt in a string with formatting. This function also displays the receipt.
    private static String displayReceipt(ArrayList<CartItem> cartArray, String customerName) {
        String text;
       
        // Start collecting the receipt.
        text = "\nName: " + customerName;
        
        // Displaying indivdual items.
        for (CartItem item: cartArray) {
            double itemSub = (item.getQuanity() * item.getItemPurchased().price);
            text += "\n\nItem: " + item.getItemPurchased().name + "\nQTY: " + item.getQuanity() + "\nSubtotal: " + String.format("%.02f", itemSub);
        }
        
        // Calculate Total Subtotal.
        double subtotal = 0;
        for (CartItem item: cartArray) {
            subtotal += (item.getItemPurchased().price * item.getQuanity());
        }
        
        // Calculate tax w/ pre determined tax rate.
        double tax;
        tax = subtotal * .076;
        
        double total = tax + subtotal;
        text += "\n\nTax: " + String.format("%.02f", tax) + "\nSubtotal: " + String.format("%.02f", subtotal) + "\n\nTotal: " + String.format("%.02f", total);
        
        // Display the receipt.
        System.out.println(text);
        // Return the receipt.
        return text;
    }
    
    // This function creates a file with the name passed to it and the contents held within receipt.
    private static void createReceipt(String customerName, String receipt) {
        // New File Object with customers name.
        File file = new File(customerName + ".txt");
        
        // Try to create the file. (per instructions we will not worry about duplicates now).
        try { 
            file.createNewFile();
        } catch (IOException e) {
            // If error, display error message.
            System.out.println(e.getMessage());
        }
        
        try {
            // try to create a printwriter object with the file.
            try (PrintWriter pw = new PrintWriter(file)) {
                // Write to the receipt to the file.
                pw.print(receipt);
            }
        } catch (FileNotFoundException e) {
            // Display error if one arises.
            System.out.println(e.getMessage());
        }
    }
    
    // This function creates a customer object.
    protected static Customer createCustomer(Scanner sc) {
        // Collecting Customer info.
        System.out.println("What is your name?");
        String name = sc.nextLine();
        Customer customer = new Customer(name);
        return customer;
    }
}