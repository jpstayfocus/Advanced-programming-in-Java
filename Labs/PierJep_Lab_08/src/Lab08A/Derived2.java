package Lab08A;

public class Derived2 extends Derived{
    public Derived2(){
        super();
        System.out.println("Derived2 Constructor");
    }

    @Override
    public void m2(String s){
        System.out.println(s + " from Derived2");
    }

    public void m4(){
        System.out.println("Derived2.m4");
    }
}