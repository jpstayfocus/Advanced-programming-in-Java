package Lab08B;

import java.util.ArrayList;

public class Calculator {
    public static ArrayList<Object> apply(ArrayList<Number> numbers, OpClass op) {
        ArrayList<Object> result = new ArrayList<>();
        for (Number number : numbers) {
            result.add(op.op(number));
        }
        return result;
    }
    public static void main(String[] args) {
        // Creating a list of integers
        ArrayList<Number> numbersList = new ArrayList<>();
        numbersList.add(2);
        numbersList.add(3);
        numbersList.add(4);

        // Applying the square
        System.out.println("Applying the square operation:");
        ArrayList<Object> squaredList = apply(numbersList, new Square());
        System.out.println(squaredList);

        // Applying the cube
        System.out.println("Applying cube operation:");
        ArrayList<Object> cubedList = apply(numbersList, new Cube());
        System.out.println(cubedList);

        // Applying the square root operation
        System.out.println("Applying square root operation:");
        ArrayList<Object> squareRootedList = apply(numbersList, new SquareRoot());
        System.out.println(squareRootedList);
    }
}
