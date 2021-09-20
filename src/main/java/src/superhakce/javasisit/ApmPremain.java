package src.superhakce.javasisit;

import java.lang.instrument.Instrumentation;

/**
 * Premain
 * @author 00762453
 */
public class ApmPremain {

    public static void premain(String agentArgs, Instrumentation instrumentation){
        instrumentation.addTransformer(new ApmAgent());
    }

}
