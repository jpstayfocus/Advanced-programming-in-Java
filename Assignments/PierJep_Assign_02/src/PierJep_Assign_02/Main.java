package PierJep_Assign_02;

/**
 * Name: Jephte Pierre
 * Date: February 22, 2024
 * Description: In this assignment, we will build and run a small Library in Lennoxville.
 */

public class Main {

    public static void main(String[] args) {
        // Create library instances
        Library queenLibrary = new Library("120 Queen St."); // Instantiate Queen St. library
        Library collegeLibrary = new Library("228 College St."); // Instantiate College St. library

        // Adding books to Queen St. library
        queenLibrary.addBook(new Book("The DaVinci Code")); // Add "The DaVinci Code"
        queenLibrary.addBook(new Book("Le Petit Prince")); // Add "Le Petit Prince"
        queenLibrary.addBook(new Book("A Tale of Two Cities")); // Add "A Tale of Two Cities"
        queenLibrary.addBook(new Book("The Lord of The Rings")); // Add "The Lord of The Rings"

        // Print opening hours for both libraries
        queenLibrary.printOpeningHours(); // Print Queen St. library opening hours
        collegeLibrary.printOpeningHours(); // Print College St. library opening hours

        // Print addresses for both libraries
        queenLibrary.printAddress(); // Print Queen St. library address
        collegeLibrary.printAddress(); // Print College St. library address

        // Borrow and return books
        queenLibrary.borrowBook("The Lord of The Rings"); // Borrow "The Lord of The Rings" from Queen St. library
        queenLibrary.borrowBook("The Lord of The Rings"); // Attempt to borrow "The Lord of The Rings" again
        collegeLibrary.borrowBook("The Lord of The Rings"); // Borrow "The Lord of The Rings" from College St. library

        // Print available books
        System.out.println("Queen St. Library:"); // Print available books in Queen St. library
        queenLibrary.printAvailableBooks();
        System.out.println("College St. Library:"); // Print available books in College St. library
        collegeLibrary.printAvailableBooks();

        // Return book
        queenLibrary.returnBook("The Lord of The Rings"); // Return "The Lord of The Rings" to Queen St. library

        // Print available books again
        System.out.println("Queen St. Library:"); // Print available books in Queen St. library after returning
        queenLibrary.printAvailableBooks();
    }
}
