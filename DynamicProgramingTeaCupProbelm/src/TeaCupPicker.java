import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import java.util.Arrays;

public class TeaCupPicker {
    private static int[] teaCup= {1,3,5,9,10,15,17,18,19,22,25,27};//array with teacup value
    static int[] sets;

    public TeaCupPicker() {
    }

    static int teaCupsReq( int amt){
        int[] T = new int[amt+1];
        sets = new int[amt+1];


        for(int x = 0; x <= amt; x++){
            T[x]=x;
        }

        int maxValue = -1;
        for(int x = 2; x <= amt; x++){
            for(int y = 1; y<=x; y++){
                if(y<13) {
                    int tempPrice = teaCup[y - 1] + T[x - y];
                    if(tempPrice>maxValue){
                        maxValue = tempPrice;
                        sets[x]=y;
                    }
                    T[x] = maxValue;
                }
                else{
                    break;
                }
            }
        }

        return T[amt];
    }

    public static void printTeaCupSet( int total){
        while(total > 0){
            System.out.print(sets[total] + " ");
            total = total - sets[total];
        }
    }




    public static void printAll(int amt){
        int soFar[] = new int[amt];
        printAll(amt, soFar, amt, 0);
    }

    static void printAll (int amt, int soFar[], int currentSize, int index){
        if(currentSize < 0){
            return;
        }
        if(currentSize  == 0){
            for( int x = 0; x<index; x++){
                System.out.print(soFar[x] + " ");
            }
            System.out.println();
            return;
        }
        int prev;
        if(index == 0){
            prev = 1;
        }
        else{
            prev = soFar[index - 1];
        }

        for (int y = prev; y <=amt; y++){
            soFar[index] = y;

            printAll(amt, soFar, currentSize-y, index+1);
        }

    }

}
