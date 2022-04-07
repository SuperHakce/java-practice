package src.superhakce.asm;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

public class AsmOperation {

    private final static String classMessage = "D://CODE//java-practice//java-practice//Heqingjiang.class";

    public void createClass() throws Exception{
        ClassWriter classWriter = new ClassWriter(0);
        classWriter.visit(Opcodes.V1_8, Opcodes.ACC_PUBLIC, "Heqingjiang", null,
                "java/lang/Object", null);
        MethodVisitor methodVisitor = classWriter.visitMethod(Opcodes.ACC_PUBLIC,
                "<init>", "()V", null, null);
        methodVisitor.visitCode();
        methodVisitor.visitVarInsn(Opcodes.AALOAD, 0);
        methodVisitor.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/Object", "<init>", "()V");
        methodVisitor.visitInsn(Opcodes.RETURN);
        methodVisitor.visitMaxs(1, 1);
        methodVisitor.visitEnd();
        // 定义code方法
        MethodVisitor methodVisitorCode = classWriter.visitMethod(Opcodes.ACC_PUBLIC, "code", "()V",
                null, null);
        methodVisitorCode.visitCode();
        methodVisitorCode.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out",
                "Ljava/io/PrintStream;");
        methodVisitorCode.visitLdcInsn("I'm a Programmer,Just Coding.....");
        methodVisitorCode.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println",
                "(Ljava/lang/String;)V");
        methodVisitorCode.visitInsn(Opcodes.RETURN);
        methodVisitorCode.visitMaxs(2, 2);
        methodVisitorCode.visitEnd();
        // 使classWriter类已经完成
        // 将classWriter转换成字节数组写到文件里面去
        byte[] data = classWriter.toByteArray();
        File file = new File(classMessage);
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        fileOutputStream.write(data);
        fileOutputStream.close();
    }

    public void useClass() throws Exception{
        File file = new File(classMessage);
        InputStream inputStream = new FileInputStream(file);
        byte[] bytes = new byte[1024];
        int count = inputStream.read(bytes);
        MyClassLoader myClassLoader = new MyClassLoader();
        Class clazz = myClassLoader.defineMyClass(bytes, 0, count);
        System.out.println(clazz.getCanonicalName());
        Object o = clazz.newInstance();
        clazz.getMethod("code", null).invoke(o, null);
    }

}
