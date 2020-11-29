package cn.ruanda.C190604;



public class Singleton{
    private Singleton(){

    }
    private static Singleton instance = new Singleton();
    public static  Singleton getInstance(){
        return instance;
    }
}

/*
public class Singleton{

    private static Singleton instance = null;
    private Singleton(){

    }
    public static synchronized Singleton getInstance(){
        if (instance==null)instance=new Singleton();
        return instance;

    }
}
*/
