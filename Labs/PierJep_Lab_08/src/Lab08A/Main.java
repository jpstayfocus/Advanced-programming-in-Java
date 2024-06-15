/**
 * Name: Jephte Pierre
 * Date: March 25, 2024
 * * Description: Using the Base class of some Derived classes to iterate over an array of Base-type objects.
 */

package Lab08A;

import java.util.ArrayList;

public class Main{
    public static void main(String[] args) {
        // initialization sequence
        System.out.println("Initialization Sequence");
        Base base = new Base();
        System.out.println();
        Derived derived = new Derived();
        System.out.println();
        Derived2 derived2 = new Derived2();
        System.out.println();
        Separate separate = new Separate();
        System.out.println();

        // Calling Methods
        System.out.println("Calling Methods:");
        base.m1();
        base.m1();
        derived.m1();
        derived.m2("Hello");
        derived.m3();
        derived2.m2("Hello");
        derived2.m3();
        derived2.m4();
        separate.m1();
        separate.m2("Hello");
        separate.m3();
        System.out.println();

        // ArrayList of Objects
        System.out.println("Arraylist of Objects:");
        ArrayList<Base> objectList = new ArrayList<>();

        // Fill the list with an assortment of base, derived, derived2
        for(int i = 0; i < 10; i++){
            if(i%3 == 0){
                objectList.add(new Derived());
            }
            else if(i%3 == 1){
                objectList.add(new Derived2());
            }
            else
            {
                objectList.add(new Base());
            }
        }

        for (Base obj : objectList){
            obj.m2("Calling M2 on  " + obj.getClass().getSimpleName());
            System.out.println();
        }
    }
}