/** Lab04A/AgeComparator.java
 *
 *
 */

package Lab04A;

import java.util.Comparator;

public class AgeComparator implements Comparator<Person> {
    @Override
    public int compare(Person arg0, Person arg1) {
        return arg0.getAge() - arg1.getAge();
    }
}