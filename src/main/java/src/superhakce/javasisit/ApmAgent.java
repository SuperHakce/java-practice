package src.superhakce.javasisit;

import javassist.*;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

/**
 * Agent
 * @author 00762453
 */
public class ApmAgent implements ClassFileTransformer {

    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
        if(!className.contains("MyClassLoaderOne")){
            return classfileBuffer;
        }
        ClassPool classPool = new ClassPool(true);
        classPool.appendClassPath(new LoaderClassPath(loader));
        try {
            CtClass ctClass = classPool.makeClass(new ByteArrayInputStream(classfileBuffer));
            CtMethod[] ctMethods = ctClass.getDeclaredMethods();
            for (CtMethod ctMethod : ctMethods) {
                try {
                    ctMethod.addLocalVariable("startTime", CtClass.longType);
                }catch (CannotCompileException e){
                }
                String codeStrBefore = "startTime=System.currentTimeMillis();";
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("System.out.println(")
                        .append("\"" + ctMethod.getName() + " time cost \"")
                        .append((" + (System.currentTimeMillis() - startTime) + \"毫秒\");"));
                String codeStrAfter = stringBuilder.toString();
                ctMethod.insertBefore(codeStrBefore);
                ctMethod.insertAfter(codeStrAfter);
            }
            File file = new File("./target/", ctClass.getSimpleName() + ".class");
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(ctClass.toBytecode());
            return ctClass.toBytecode();
        }catch (Exception e){
            //e.printStackTrace();
        }
        return null;
    }

}
