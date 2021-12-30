package src.superhakce.jdkproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class InvocationHandlerImpl implements InvocationHandler {
    
    private Object object;

    public InvocationHandlerImpl(Object object) {
        this.object = object;
    }

    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable{
        System.out.println(method.getName() + " BEFORE --------------------------");
        Object value = method.invoke(object, args);
        System.out.println(method.getName() + " AFTER --------------------------");
        return value;
    }
    
}
