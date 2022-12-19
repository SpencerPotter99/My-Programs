import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;
import java.util.Random;

public class WritePoetry {
    public void WritePoem(String fileName, String startingWord, int lengthOfPoem ) throws FileNotFoundException {
        HashTable hashTable = createHashTable(fileName);
        System.out.println(hashTable.toString(lengthOfPoem));

        StringBuilder sb = new StringBuilder(startingWord);
        String myWord = startingWord;
        for(int x = 0; x<lengthOfPoem; x++){
            myWord = pickNextWord(myWord, hashTable);
            if(myWord.equals(".") || myWord.equals(",") || myWord.equals("!") || myWord.equals("?")) {
                sb.append(myWord + "\n");
            }
            else{
                sb.append(" " + myWord);
            }
        }
        if(myWord.contains(".")){
            System.out.println(sb+ "\n");
            return;
        }
        sb.append(".\n");
        System.out.println(sb);




    }
    public HashTable createHashTable(String fileName) throws FileNotFoundException {
        //opening the file
        File file = new File(fileName);
        Scanner input = new Scanner(file);

        //reading the file word by word and intering it into the hash table
        String currWord = input.next();
        currWord = currWord.toLowerCase();
        WordFreqInfo wf = new WordFreqInfo(currWord, 0);
        HashTable<String, WordFreqInfo> hashTable = new HashTable<>();
        hashTable.insert(currWord,wf);

        while (input.hasNext()){
            String nextWord = input.next();
            nextWord = nextWord.toLowerCase();
            wf.updateFollows(nextWord);
            if(hashTable.contains(nextWord)){
                wf = hashTable.find(nextWord);

            }
            else {
                wf = new WordFreqInfo(nextWord,0);
                hashTable.insert(nextWord, wf);
            }
            currWord=nextWord;
        }

        return hashTable;
    }

    public String pickNextWord(String myWord, HashTable<String, WordFreqInfo> hashTable){
        WordFreqInfo wf = hashTable.find(myWord);
        Random rand = new Random();

        int totalSum = 0;

        for (WordFreqInfo.Freq freq : wf.followList) {
            totalSum = totalSum + freq.followCt;
        }

        int randomNum = rand.nextInt(totalSum);
        int sum = 0;
        int x = 0;
        while(sum < randomNum){
            sum = sum + wf.followList.get(x++).followCt;
        }
        String nextWord = wf.followList.get(Math.max(0, x-1)).follow;
        return nextWord;


    }
}
