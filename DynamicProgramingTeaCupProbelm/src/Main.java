import java.util.Scanner;

public class Main {
    public static void main (String[] args){
        System.out.println("Enter the amount of teacups: ");
        Scanner input = new Scanner(System.in);
        int teacupAmount = input.nextInt();
        TeaCupPicker teaCup = new TeaCupPicker();
        int answer = teaCup.teaCupsReq(teacupAmount);

        System.out.println();
        System.out.println("Part 1 output:");
        teaCup.printAll(teacupAmount);

        System.out.println();
        System.out.println("Part 2 output: ");

        System.out.print("Best Sum for(" + teacupAmount + " teacups): $" + answer + " " );
        teaCup.printTeaCupSet(teacupAmount);




    }
}
