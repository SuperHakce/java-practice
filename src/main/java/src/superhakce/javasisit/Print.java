package src.superhakce.javasisit;

public class Print {

    private static volatile int a = 0;

    public void print(){
        a ++;
        System.out.println("WE ARE AT " + a);
    }

}
