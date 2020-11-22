import java.io.File;

public class test {

    public static void main(String[] args) {
        File a = new File("F://aaa//bbb");
        long length = a.length();
        System.out.println(length);
    }

}
