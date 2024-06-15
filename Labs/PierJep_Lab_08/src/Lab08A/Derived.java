package Lab08A;

public class Derived extends Base implements IType {
    public Derived(){
        super();
        System.out.println("Derived Constructor");
    }

    @Override
    public void m1(){
        System.out.println("Derived.m1");
    }

    @Override
    public void m3(){
        System.out.println("Derived.m3");
    }
}
