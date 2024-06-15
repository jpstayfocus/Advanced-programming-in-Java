/**
 * Name: Jephte Pierre
 * Date: February 18, 2024
 * Description: This Java program demonstrates the use of the Comparator interface to sort objects of type Person
 * based on different criteria such as age and name.
 */

package Lab04A;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Person p1 = new Person("John", 30);
        Person p2 = new Person("Alice", 25);
        Person p3 = new Person("Bob", 35);
        Person p4 = new Person("Mary", 28);
        Person p5 = new Person("David", 40);

        List<Person> people = Arrays.asList(p1, p2, p3, p4, p5);

        System.out.println("Before sorting:");
        System.out.println(people);

        Collections.sort(people, new LexicographicComparator());
        System.out.println("After sorting lexicographically:");
        System.out.println(people);

        Collections.sort(people, new AgeComparator());
        System.out.println("After sorting by age:");
        System.out.println(people);
    }
}
