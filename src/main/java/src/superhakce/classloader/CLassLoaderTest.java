package src.superhakce.classloader;

public class CLassLoaderTest {

    public void run() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        MyClassLoaderOne myClassLoaderOne = new MyClassLoaderOne("D:\\CODE\\java-practice\\java-practice\\Heqingjiang.class");
        MyClassLoaderTwo myClassLoaderTwo = new MyClassLoaderTwo("D:\\CODE\\java-practice\\java-practice\\Heqingjiang.class");
        Class class1 = myClassLoaderOne.loadClass("Heqingjiang");
        Class class2 = myClassLoaderTwo.loadClass("Heqingjiang");
        System.out.println(class1 == class2);
        System.out.println(class1.equals(class2));
    }

}
