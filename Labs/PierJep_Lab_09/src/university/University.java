/**
 * Name: Jephte Pierre
 * Date: March 29, 2024
 * Description: Implements the Observer pattern using deprecated Java packages for simplicity.
 * Includes classes: Course, Prof, Student, and Secretary, each fulfilling specific roles within the pattern.
 */


package university;

import java.util.*;

record Course(String courseName, Prof professor) {
    /**
     * Constructs a Course object with the given name and professor.
     *
     * @param courseName The name of the course.
     * @param professor  The professor teaching the course.
     */
    Course {
    }

    /**
     * Retrieves the name of the course.
     *
     * @return The name of the course.
     */
    @Override
    public String courseName() {
        return courseName;
    }

    /**
     * Retrieves the professor of the course.
     *
     * @return The professor of the course.
     */
    @Override
    public Prof professor() {
        return professor;
    }
}

/**
 * Prof class represents a professor with a name, room number, midterm date, and a list of students.
 */
class Prof extends Observable {
    private final String name;
    private final List<Student> students = new ArrayList<>();

    /**
     * Constructs a Prof object with the given name.
     * @param name The name of the professor.
     */
    public Prof(String name) {
        this.name = name;
    }

    /**
     * Retrieves the name of the professor.
     * @return The name of the professor.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the midterm date and notifies observers.
     * @param date The date of the midterm.
     */
    public void setMidterm(Date date) {
        setChanged();
        notifyObservers(date);
    }

    /**
     * Sets the room number.
     * @param roomNumber The room number.
     */
    public void setRoomNumber(int roomNumber) {
    }

    /**
     * Adds a student to the list of students taking the midterm.
     * @param student The student taking the midterm.
     */
    public void takingMidterm(Student student) {
        students.add(student);
    }

    /**
     * Prints all students taking the midterm.
     */
    public void printTakingMidterm() {
        System.out.println("Students taking midterm:");
        for (Student student : students) {
            System.out.println(student.getName());
        }
    }
}

/**
 * Student class represents a student with a name, room number, and associated course.
 */
class Student implements Observer {
    private final String name;

    /**
     * Constructs a Student object with the given name and course.
     * @param name The name of the student.
     * @param course The course associated with the student.
     */
    public Student(String name, Course course) {
        this.name = name;
    }

    /**
     * Retrieves the name of the student.
     * @return The name of the student.
     */
    public String getName() {
        return name;
    }

    /**
     * Updates the student with information from the observable.
     * @param o The observable object.
     * @param arg The argument passed to the notifyObservers method.
     */
    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof Integer) {
            int roomNumber = (Integer) arg;
            System.out.println("Student " + name + " has a midterm in room number: " + roomNumber);
            return;
        }
        System.out.println("Student " + name + " says ..Doh got it dude!");
        System.out.println(((Prof) o).getName() + " says " + arg);
        ((Prof) o).takingMidterm(this);
    }
}

/**
 * Secretary class represents a secretary with a date.
 */
class Secretary implements Observer {

    /**
     * Registers the secretary to be notified by the professor.
     * @param p The professor to register with.
     */
    public void registerToProf(Prof p) {
        p.addObserver(this);
    }

    /**
     * Updates the secretary with information from the observable.
     * @param o The observable object.
     * @param arg The argument passed to the notifyObservers method.
     */
    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof Integer) {
            return;
        }
        Date date = (Date) arg;
        Random rand = new Random();
        int roomNumber = rand.nextInt(10) + 1;
        ((Prof) o).setRoomNumber(roomNumber);
    }
}

/**
 * University class contains the main method to test the implementation.
 */
public class University {
    public static void main(String[] args) {
        // Create a Prof
        Prof professor = new Prof("Dr. Smith");

        // Create a Course
        Course course = new Course("Computer Science", professor);

        // Create Students
        Student kramer = new Student("Kramer", course);
        Student elaine = new Student("Elaine", course);
        Student jerry = new Student("Jerry", course);
        Student george = new Student("George", course);

        // Create a Secretary
        Secretary secretary = new Secretary();
        secretary.registerToProf(professor);

        // Register Students to the Course
        course.professor().takingMidterm(kramer);
        course.professor().takingMidterm(elaine);
        course.professor().takingMidterm(jerry);
        course.professor().takingMidterm(george);

        // Set the midterm date by Prof
        Date midtermDate = new Date();
        professor.setMidterm(midtermDate);

        // Print all students taking the midterm
        professor.printTakingMidterm();
    }
}

