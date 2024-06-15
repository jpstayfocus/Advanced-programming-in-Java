/**
 * Name: Jephte Pierre
 * Date: March 29, 2024
 * Description: Building a tripleton is exactly the same as a singleton.
 * making the constructor private, and add a public static method called `getInstance`
 * to return an instance. In this case, the method will create new instances while it can,
 * and once it has created all three of them, it will return the 3rd instance.
 * This Tripleton class should also have a method called `getInstanceN` to retrieve the Nth instance.
 */


package tripleton;

public class Tripleton {
    private static final int MAX_INSTANCES = 3;
    private static int instanceCount = 0;
    private static final Tripleton[] instances = new Tripleton[MAX_INSTANCES];

    // Private constructor to prevent instantiation from outside
    private Tripleton() {}

    /**
     * Returns an instance of Tripleton.
     *
     * @return An instance of Tripleton.
     */
    public static Tripleton getInstance() {
        if (instanceCount < MAX_INSTANCES) {
            instances[instanceCount] = new Tripleton();
            instanceCount++;
        }
        return instances[instanceCount - 1];
    }

    /**
     * Returns the Nth instance of Tripleton.
     *
     * @param n The index of the instance to retrieve (1-based index).
     * @return The Nth instance of Tripleton.
     * @throws IllegalArgumentException if the index is invalid.
     */
    public static Tripleton getInstanceN(int n) {
        if (n > 0 && n <= MAX_INSTANCES) {
            return instances[n - 1];
        } else {
            throw new IllegalArgumentException("Invalid instance number");
        }
    }

    public static void main(String[] args) {
        // Testing Tripleton class
        Tripleton instance1 = Tripleton.getInstance();
        Tripleton instance2 = Tripleton.getInstance();
        Tripleton instance3 = Tripleton.getInstance();
        Tripleton instance4 = Tripleton.getInstance();

        System.out.println("Instance 1: " + instance1);
        System.out.println("Instance 2: " + instance2);
        System.out.println("Instance 3: " + instance3);
        System.out.println("Instance 4: " + instance4);

        System.out.println("Instance 4 is equal to Instance 3: " + (instance4 == instance3));

        // Test getting the Nth instance
        System.out.println("#-------------#----------------#-----------------#");

        System.out.println("Instance 1: " + Tripleton.getInstanceN(1));
        System.out.println("Instance 2: " + Tripleton.getInstanceN(2));
        System.out.println("Instance 3: " + Tripleton.getInstanceN(3));
    }
}
