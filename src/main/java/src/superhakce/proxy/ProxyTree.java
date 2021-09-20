package src.superhakce.proxy;

public class ProxyTree implements Tree {

    private GoodTree goodTree;

    public void speak(){
        System.out.println("I am ProxyTree");
        if(goodTree == null){
            goodTree = new GoodTree();
        }
        goodTree.speak();
    }


}
