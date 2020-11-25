package cn.ruanda.C190604;

public class Singleton {
    //饿汉式单例
    private Singleton(){}
    private static Singleton instance=new Singleton();
    public static Singleton getInstance(){
        return instance;
    }

}

/*public class Singleton1{
    private static Singleton1 instance1=null;
    private Singleton1(){}
    public static synchronized Singleton1 getInstance1(){
        if (instance1==null) instance1=new Singleton1();
        return instance1;
    }
}*/
