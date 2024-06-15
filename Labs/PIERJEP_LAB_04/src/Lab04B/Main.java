/**
 * Name: Jephte Pierre
 * Date: February 18, 2024
 * Description: ava project aimed at simulating how different types of instructors in a university setting cope
 * with stress induced by unread mail.
 */

package Lab04B;

public class Main {
    public static void main(String[] args) {
        Instructor[] instructors = new Instructor[3];
        instructors[0] = new Faculty(40, 100);
        instructors[1] = new Grad(30, 150);
        instructors[2] = new Lecturer(35, 200);

        for (int i = 1; i <= 10; i++) {
            for (Instructor instructor : instructors) {
                instructor.getMail(i * 10 + 50);
            }
        }

        for (Instructor instructor : instructors) {
            System.out.println("Instructor: " + instructor.getClass().getSimpleName());
            System.out.println("Unread Mail: " + instructor.getUnreadMail());
            System.out.println("Eccentricities: " + instructor.getEccentricities());
            System.out.println("Stress: " + instructor.stress());
            System.out.println("Respect: " + instructor.respect());
            System.out.println();
        }
    }
}
