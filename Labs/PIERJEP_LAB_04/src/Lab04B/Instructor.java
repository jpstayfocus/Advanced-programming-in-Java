/**
 * Lab04B/Instructor.java
 *
 */

package Lab04B;

public abstract class Instructor {
    private int age;
    private int unreadMail;
    private int eccentricities;

    /**
     * Constructor to initialize an Instructor with age and unread mail.
     * @param age The age of the instructor.
     * @param unreadMail The number of unread mail.
     */
    public Instructor(int age, int unreadMail) {
        this.age = age;
        this.unreadMail = unreadMail;
        this.eccentricities = 0;
    }

    /**
     * Getter method to retrieve the age of the instructor.
     * @return The age of the instructor.
     */
    public int getAge() {
        return age;
    }

    /**
     * Getter method to retrieve the number of unread mail.
     * @return The number of unread mail.
     */
    public int getUnreadMail() {
        return unreadMail;
    }

    /**
     * Getter method to retrieve the eccentricities of the instructor.
     * @return The eccentricities of the instructor.
     */
    public int getEccentricities() {
        return eccentricities;
    }

    /**
     * Abstract method to be implemented by subclasses to cope with stress.
     */
    public abstract void cope();

    /**
     * Method to calculate the stress level of the instructor.
     * @return The stress level of the instructor.
     */
    public int stress() {
        return Math.min(unreadMail, 1000);
    }

    /**
     * Method to calculate the respect level of the instructor.
     * @return The respect level of the instructor.
     */
    public int respect() {
        int respect = age - eccentricities;
        return respect > 0 ? respect : 0;
    }

    /**
     * Method to reduce the amount of unread mail.
     * @param readMail The number of mail to be marked as read.
     */
    public void reduceMail(int readMail) {
        unreadMail -= readMail;
        if (unreadMail < 0) {
            unreadMail = 0;
        }
    }

    /**
     * Method to add to the eccentricities of the instructor.
     * @param eccentricities The eccentricities to be added.
     */
    public void addToEccentricities(int eccentricities) {
        this.eccentricities += eccentricities;
        if (this.eccentricities < 0) {
            this.eccentricities = 0;
        }
    }

    /**
     * Method to simulate receiving new mail.
     * @param newMail The number of new mail received.
     */
    public void getMail(int newMail) {
        unreadMail += newMail;
        if (Math.random() < 0.2) {
            if (Math.random() < 0.5) {
                addToEccentricities(2);
            } else {
                addToEccentricities(-2);
            }
        }
        if (stress() > respect()) {
            cope();
        }
    }
}



