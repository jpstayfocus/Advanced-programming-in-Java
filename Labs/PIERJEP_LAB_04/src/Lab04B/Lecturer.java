/**
 * Lab04B/Lecturer.java
 *
 */
package Lab04B;

public class Lecturer extends Instructor {
    public Lecturer(int age, int unreadMail) {
        super(age, unreadMail);
    }

    @Override
    public void cope() {
        reduceMail((int) (0.6 * getUnreadMail()));
    }
}

