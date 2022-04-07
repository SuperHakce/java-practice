package src.superhakce.spi;

import java.util.ServiceLoader;

public class Spi {

    public void run(){
        ServiceLoader<Print> serviceLoader = ServiceLoader.load(Print.class);
        for (Print print : serviceLoader) {
            print.print();
        }
    }

}
