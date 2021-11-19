package src.superhakce.designpattern;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Objects;

/**
 * 懒汉式单例模式
 * @author Super
 */
public class LazyInnerClassSingleton {

    private LazyInnerClassSingleton(){
        if(Objects.nonNull(INSTANCE)){
            throw new RuntimeException("不容许创建多个实例");
        }
    }

    private volatile static LazyInnerClassSingleton INSTANCE = null;

    public static LazyInnerClassSingleton createInstance(){
        if(Objects.isNull(INSTANCE)){
            synchronized (LazyInnerClassSingleton.class){
                if(Objects.isNull(INSTANCE)){
                    INSTANCE = new LazyInnerClassSingleton();
                }
            }
        }
        return INSTANCE;
    }

    public final static void test() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        LazyInnerClassSingleton lazyInnerClassSingleton = LazyInnerClassSingleton.createInstance();
        Class<?> clazz = LazyInnerClassSingleton.class;
        Constructor constructor = clazz.getDeclaredConstructor(null);
        constructor.setAccessible(true);
        LazyInnerClassSingleton lazyNew = (LazyInnerClassSingleton) constructor.newInstance();
        System.out.println(lazyInnerClassSingleton == lazyNew);
    }

}
