package Lab08B;

public class SquareRoot implements OpClass{
    @Override
    public Object op(Object arg){
        double number = ((Number) arg).doubleValue();
        return Math.sqrt(number);
    }
}