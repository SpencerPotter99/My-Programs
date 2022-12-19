import java.io.FileNotFoundException;

public class PoetryMain {
    public static void main(String[] args) throws FileNotFoundException {

        WritePoetry poem = new WritePoetry();
        poem.WritePoem("green.txt", "sam", 20);
        poem.WritePoem("Lester.txt", "lester", 30);
        poem.WritePoem("HowMany.txt", "how", 30);
        poem.WritePoem("Zebra.txt", "are", 50);
    }

}