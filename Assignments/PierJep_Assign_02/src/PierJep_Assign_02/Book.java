package PierJep_Assign_02;

/**
 * Represents a Book with a title and its borrowed status.
 */

public class Book {
    private final String title; // Title of the book
    private boolean borrowed; // Indicates whether the book is borrowed or not

    /**
     * Constructor to create a Book with a specified title.
     * Initially, the book is not borrowed.
     * @param title The title of the book.
     */
    public Book(String title) {
        this.title = title; // Set the title
        this.borrowed = false; // Initially, the book is not borrowed
    }

    /**
     * Marks the book as borrowed.
     */
    public void borrow() {
        this.borrowed = true; // Set the borrowed status to true
    }

    /**
     * Marks the book as returned.
     */
    public void returnBook() {
        this.borrowed = false; // Set the borrowed status to false
    }

    /**
     * Checks if the book is currently borrowed.
     * @return true if the book is borrowed, false otherwise.
     */
    public boolean isBorrowed() {
        return borrowed; // Return the borrowed status
    }

    /**
     * Gets the title of the book.
     * @return The title of the book.
     */
    public String getTitle() {
        return title; // Return the title of the book
    }
}
