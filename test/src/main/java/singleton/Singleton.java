package singleton;

public class Singleton {

    private static class InnerSingleton{
        private static Singleton singleton = new Singleton();
    }

    private Singleton(){}

    public static Singleton getInstance(){
        return InnerSingleton.singleton;
    }
}
