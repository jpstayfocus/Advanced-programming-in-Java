package Lab08A;

public class Separate  implements IType{
    public Separate(){
        System.out.println("Separate Constrructor");
    }

    @Override
    public void m1(){
        System.out.println("Separate.m1");
    }

    @Override
    public void m2(String s){
        System.out.println(s + " from separate");
    }

    @Override
    public void m3(){
        System.out.println("Separate.m3");
    }
}