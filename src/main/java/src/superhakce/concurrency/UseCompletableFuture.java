package src.superhakce.concurrency;

import java.util.concurrent.CompletableFuture;

public class UseCompletableFuture {

    public void runCompletableFuture() throws Exception{
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(UseCompletableFuture::returnName);
        completableFuture.thenAccept((result) -> {
            System.out.println(result);
        });
        completableFuture.exceptionally((e) -> {
           e.printStackTrace();
           return null;
        });
        System.out.println("---------------------------");
        Thread.sleep(2000L);
    }

    public void runCompletableFutureSerializable() throws Exception{
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(UseCompletableFuture::runOne);
        completableFuture.thenApplyAsync((code) -> {
            return UseCompletableFuture.runTwo();
        });
        Thread.sleep(2000L);
    }

    public void runCompletableFutureAnyOne(){
        CompletableFuture completableFuture = CompletableFuture.allOf(CompletableFuture.supplyAsync(UseCompletableFuture
                ::runOne), CompletableFuture.supplyAsync(UseCompletableFuture::runTwo));
        completableFuture.thenAccept((result) -> {
           System.out.println(result);
        });
    }

    private static String returnName(){
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
        }
        if (Math.random() < 0.3) {
            throw new RuntimeException("return name failed!");
        }
        return "heqingjiang";
    }

    private static String runOne(){
        System.out.println("One Run");
        return "One Run";
    }

    private static String runTwo() {
        try {
            Thread.sleep(200L);
        }catch (Exception e){

        }
        System.out.println("Two Run");
        return "Two Run";
    }

}
