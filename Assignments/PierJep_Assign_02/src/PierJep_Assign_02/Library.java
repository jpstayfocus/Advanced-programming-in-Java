package PierJep_Assign_02;

import java.util.ArrayList;

/**
 * Represents a Library that manages a collection of books.
 */
public class Library {
    private final ArrayList<Book> bookCollection; // Collection of books in the library
    private final String address; // Address of the library

    /**
     * Constructor to create a Library with a specified address.
     * @param address The address of the library.
     */
    public Library(String address) {
        this.address = address;
        this.bookCollection = new ArrayList<>(); // Initialize the collection
    }

    /**
     * Prints the opening hours of the library.
     */
    public void printOpeningHours() {
        System.out.println("Opening Hours: 9AM-5PM");
    }

    /**
     * Prints the address of the library.
     */
    public void printAddress() {
        System.out.println("Address: " + address);
    }

    /**
     * Adds a book to the library's collection.
     * @param book The book to be added.
     */
    public void addBook(Book book) {
        bookCollection.add(book); // Add the book to the collection
    }

    /**
     * Allows a book to be borrowed from the library.
     * @param title The title of the book to be borrowed.
     */
    public void borrowBook(String title) {
        for (Book book : bookCollection) {
            // Check if the book exists in the collection and is available for borrowing
            if (book.getTitle().equals(title) && !book.isBorrowed()) {
                book.borrow(); // Mark the book as borrowed
                System.out.println("Book \"" + title + "\" borrowed successfully.");
                return;
            }
        }
        System.out.println("Book \"" + title + "\" not available for borrowing.");
    }

    /**
     * Allows a book to be returned to the library.
     * @param title The title of the book to be returned.
     */
    public void returnBook(String title) {
        for (Book book : bookCollection) {
            // Check if the book exists in the collection and has been borrowed
            if (book.getTitle().equals(title) && book.isBorrowed()) {
                book.returnBook(); // Mark the book as returned
                System.out.println("Book \"" + title + "\" returned successfully.");
                return;
            }
        }
        System.out.println("Book \"" + title + "\" was not borrowed from this library.");
    }

    /**
     * Prints the titles of available books in the library.
     */
    public void printAvailableBooks() {
        System.out.println("Available Books:");
        for (Book book : bookCollection) {
            if (!book.isBorrowed()) { // Check if the book is available
                System.out.println("- " + book.getTitle());
            }
        }
    }
}
