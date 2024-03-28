package SeamCarving;

public class test {
    public void printHelloworld() {
        System.out.println("hello world");
    }

    public void printHi() {
        test t = new test();
        t.printHelloworld();
    }


    public static void main(String[] args) {
        test t = new test();
        t.printHelloworld();
        t.printHi();

    }
}
