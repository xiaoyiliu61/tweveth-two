package cn.ruanda.C190604;

public class Student {
    String name;
    String sex;
    int age=2;




    void study(){

    }
    interface Animal{
        void eat();
    }
    interface Feline extends Animal{}
    public class HouseCat implements Feline{
        public void eat(){

        }
    }
}
