package contacts;

public class Main {
    public static void main(String[] args) {
        String filename = "";
        if (args.length != 0) {
            filename = args[0];
        }
        new App().start(filename);
    }
}
