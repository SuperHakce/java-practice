package src.superhakce.designpattern;

/**
 * 静态内部类单例模式
 * @author Super
 */
public class StaticInnerClassSingleton {

    private StaticInnerClassSingleton(){
    }

    public final static StaticInnerClassSingleton getInstance(){
        return NewInstance.INSTANCE;
    }

    private static class NewInstance{
        private final static StaticInnerClassSingleton INSTANCE = new StaticInnerClassSingleton();
    }

}
