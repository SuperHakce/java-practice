package src.superhakce.annotation;

import java.util.Arrays;

public class Test {

    public static void main(String[] args) {
        Test test = new Test();
        test.run();
    }

    public void run(){
        Tree tree = new Tree();
        SonTree sonTree = new SonTree();
        Arrays.stream(tree.getClass().getAnnotations()).forEach(k -> {
            if(k instanceof AnnoationTest){
                System.out.println("tree OK");
            }
        });
        Arrays.stream(sonTree.getClass().getAnnotations()).forEach(k -> {
            if(k instanceof AnnoationTest){
                System.out.println("sonTree OK");
            }
        });
    }

}
