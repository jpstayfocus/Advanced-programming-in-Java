package Lab08B;

public class Cube implements OpClass{
    @Override
    public Object op(Object arg){
        double number = ((Number) arg).doubleValue();
        return number * number * number;
    }
}
