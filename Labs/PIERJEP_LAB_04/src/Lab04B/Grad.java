/**
 * Lab04B/Grad.java
 *
 */
package Lab04B;

public class Grad extends Instructor {
    public Grad(int age, int unreadMail) {
        super(age, unreadMail);
    }

    @Override
    public void cope() {
        if (Math.random() < 0.5) {
            addToEccentricities(3);
        } else {
            addToEccentricities(-3);
        }
    }

    @Override
    public int stress() {
        return Math.min(super.stress(), 1500);
    }
}


