/**
 * LexicographicComparator.java
 *
 */

package Lab04A;

import java.util.Comparator;

public class LexicographicComparator implements Comparator<Person> {
    @Override
    public int compare(Person arg0, Person arg1) {
        return arg0.getName().compareTo(arg1.getName());
    }
}
