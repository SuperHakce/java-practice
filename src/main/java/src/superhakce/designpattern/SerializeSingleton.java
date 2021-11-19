package src.superhakce.designpattern;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * 序列化饿汉式单例模式
 * @author Super
 */
public class SerializeSingleton implements Serializable {

    private SerializeSingleton(){
    }

    private final static SerializeSingleton INSTANCE = new SerializeSingleton();

    public final static SerializeSingleton getInstance(){
        return INSTANCE;
    }

    public final static void test(){
        SerializeSingleton serializeSingleton1 = null;
        SerializeSingleton serializeSingleton2 = INSTANCE;
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("SerializeSingleton.obj");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(serializeSingleton2);
            objectOutputStream.flush();
            objectOutputStream.close();

            FileInputStream fileInputStream = new FileInputStream("SerializeSingleton.obj");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            serializeSingleton1 = (SerializeSingleton) objectInputStream.readObject();
            objectInputStream.close();

            System.out.println(serializeSingleton1);
            System.out.println(serializeSingleton2);
            System.out.println(serializeSingleton1 == serializeSingleton2);
        }catch (Exception e){

        }
    }

    public Object readResolve(){
        return INSTANCE;
    }

}
