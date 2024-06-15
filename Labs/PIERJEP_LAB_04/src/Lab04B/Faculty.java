/**
 * Faculty.java
 *
 */

package Lab04B;

public class Faculty extends Instructor {
    public Faculty(int age, int unreadMail) {
        super(age, unreadMail);
    }

    @Override
    public void cope() {
        addToEccentricities(30);
    }

    @Override
    public int respect() {
        return getAge() + getEccentricities();
    }
}