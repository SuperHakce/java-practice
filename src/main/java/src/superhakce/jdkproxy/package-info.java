// 1.通过实现 InvocationHandler 接口创建自己的调用处理器；
// 2.通过为 Proxy 类指定 ClassLoader 对象和一组 interface 来创建动态代理类；
// 3.通过反射机制获得动态代理类的构造函数，其唯一参数类型是调用处理器接口类型；
// 4.通过构造函数创建动态代理类实例，构造时调用处理器对象作为参数被传入。

package src.superhakce.jdkproxy;