package Lab08A;

public class Base {
    private static int m1CallCount = 0;

    public Base(){
        System.out.println("Base constructor");
    }

    public void m1(){
        m1CallCount++;
        System.out.println("m1 is called " + m1CallCount + " times.");
    }

    public void m2(String s){
        System.out.println(s + " from Base");
    }

    public void m3(){
        System.out.println("Base.m3");
    }
}
