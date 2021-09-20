package src.superhakce.jdkproxy;

public class ChinaWoman implements Woman{

    private String name;

    public ChinaWoman(){

    }

    public ChinaWoman(String name){
        super();
        this.name = name;
    }

    public void buy(){
        System.out.println("BUY BUY " + this.name);
    }

    public void speak(){
        System.out.println("SPEAK SPEAK " + this.name);
    }

}
