package src.superhakce.javasisit;

import java.lang.instrument.Instrumentation;

/**
 * Premain
 * -javaagent:D:\CODE\java-practice\java-practice\target\java-practice-1.0-SNAPSHOT.jar
 * @author 00762453
 */
public class ApmPremain {

    public static void premain(String agentArgs, Instrumentation instrumentation){
        instrumentation.addTransformer(new ApmAgent());
    }

}
