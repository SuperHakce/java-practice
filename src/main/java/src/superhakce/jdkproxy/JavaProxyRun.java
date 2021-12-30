package src.superhakce.jdkproxy;

import sun.misc.ProxyGenerator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.lang.reflect.Proxy;

public class JavaProxyRun {

    public void run() throws Exception{
        Woman woman = new ChinaWoman("钟碧泉");
        InvocationHandlerImpl invocationHandler = new InvocationHandlerImpl(woman);
        ClassLoader classLoader = woman.getClass().getClassLoader();
        Class[] interfaces = woman.getClass().getInterfaces();
        Woman proxyWoman = (Woman) Proxy.newProxyInstance(classLoader, interfaces, invocationHandler);
        proxyWoman.buy();
        proxyWoman.speak();

        Class[] a = {Woman.class, Proxy.class};
        byte[] proxyClassFile = ProxyGenerator.generateProxyClass(
                "src.superhakce.test", a, 17);
        File file = new File("D://CODE//java-practice//java-practice//test.class");
        OutputStream outputStream = new FileOutputStream(file);
        outputStream.write(proxyClassFile);
        outputStream.close();
        System.out.println("OK");
    }

}
