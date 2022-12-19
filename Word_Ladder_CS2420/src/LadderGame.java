import java.util.HashMap;
import java.util.Scanner;
import java.util.Random;
import java.io.File;
import java.util.ArrayList;

public class LadderGame {
    static int MaxWordSize = 15;  //Max legnth word allowed
    ArrayList<String>[] allList;  // Array of ArrayLists of words of each length.
    Random random;  // Random number generator

    /**
     *  Creates separate ArrayLists for words of each length
     * @param dictionaryFileName  Contains all words to be used in word ladder in alpha order
     */
    public LadderGame(String dictionaryFileName) {
        random = new Random();
        allList = new ArrayList[MaxWordSize];
        for (int i = 0; i < MaxWordSize; i++)
            allList[i] = new ArrayList<String>();

        try {
            Scanner reader = new Scanner(new File(dictionaryFileName));
            while (reader.hasNext()) {
                String word = reader.next();
                if (word.length()< MaxWordSize) allList[word.length()].add(word);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    /**
     * @author Spencer Potter
     * Given starting and ending words, it calls the priority queue function
     * and the brute force function. Then it formats the output
     * @param startWord  Beginning word of word ladder
     * @param endWord  Ending word on word ladder
     * @return void
     */
    public void play(String startWord, String endWord) {

        //setting up the list
        ArrayList list = setUpList(startWord);
        System.out.println("Seeking a solution from " + startWord + " ->" + endWord + " Size of List " + list.size());
        if(validWords(startWord,endWord,list)) {
            LadderInfo prioLadder = PriorityQueueMethod(startWord, endWord, list);
            //checking to see if the priority ladder got canceled due to taking to many enqueues
            if(prioLadder.getTotalEnq() > 5000){
                System.out.print("A* SOLUTION    " + "No Solution Found After 5000 attempts");
                System.out.println();
            }
            else {
                System.out.print("A* SOLUTION    " + prioLadder.toString());
                System.out.print("Total Enqueues: ");
                System.out.printf("%.0f", prioLadder.getTotalEnq());
                System.out.println();
            }


            list = setUpList(startWord);
            LadderInfo bruteLadder = bruteForceMethod(startWord, endWord, list);
            //checking to see if the priority ladder got canceled due to taking to many enqueues
            if(bruteLadder.getTotalEnq()>5000){
                System.out.print("BRUTE SOLUTION " + "No Solution Found After 5000 attempts");
                System.out.println();
            }
            else {
                System.out.print("BRUTE SOLUTION " + bruteLadder.toString());
                System.out.print("Total Enqueues: ");
                System.out.printf("%.0f", bruteLadder.getTotalEnq());
                System.out.println();
            }

            //calling the compareLadders function to output the compared data
            compareLadders(prioLadder, bruteLadder);
            System.out.println();
        }

    }

    /**
     * @author Spencer Potter
     *
     * this function compares the 2 outputs from the priority queue and the brute force queue
     * @param prio the final ladderInfo of the priority queue list
     * @param brute the final ladderInfo of the brute force queue list
     */
    public void compareLadders (LadderInfo prio, LadderInfo brute){
        //determining and outputing the length of each ladder
        if (prio.moves == brute.moves){
            System.out.print("Same Length ");
        }
        else{
            System.out.print("A* Length: " + prio.moves + " Brute Length: " + brute.moves + " ");
        }
        //calculating the work difference and percentage of work that A* does compared to brute
        //System.out.print("Work Compare: " + prio.getTotalEnq() + " " + brute.getTotalEnq() + " ");
        System.out.print("Work Compare: ");
        System.out.printf("%.0f", prio.getTotalEnq());
        System.out.print(" ");
        System.out.printf("%.0f", brute.getTotalEnq());
        System.out.print(" ");
        double percentageDiff = ((prio.getTotalEnq()/ brute.getTotalEnq()) * 100);
        System.out.printf("%.0f",percentageDiff);
        System.out.print("%");
        System.out.println();
        return;
    }

    /**
     * Spencer Potter
     * This method checks to make sure the starting and ending word are in the dictionary and if they are the same
     * length
     *
     * @param startWord the starting word in the word ladder
     * @param endWord the word we want to end with in the word ladder
     * @param list A list of all the valid words in the dictionary
     * @return a boolean that represents if the words are valid in the list or not
     */
    public boolean validWords (String startWord, String endWord, ArrayList list){
        if (startWord.length() != endWord.length()) {
            System.out.println("Words are not the same length");
            System.out.println();
            return false;
        }

        if (!list.contains(endWord)) {
            System.out.println("the end word is not found in the dictionary");
            System.out.println();
            return false;
        }
        return true;
    }

    /**
     * @Author Spencer Potter
     *
     * this word creates the dictionary list
     * @param startWord the first word in the word latter
     * @return an array list full of the dictionary words
     */
    public ArrayList setUpList (String startWord){
        if (startWord.length() >= MaxWordSize) return null;
        ArrayList list = new ArrayList();
        ArrayList<String> l = allList[startWord.length()];
        list = (ArrayList) l.clone();
        return list;
    }

    /**
     * @author Spencer Potter
     * a word ladder function that uses a priority queue avl tree. It finds the most efficent way from the starting word
     * to the ending word
     *
     * @param startWord the starting word in the word ladder
     * @param endWord the word we wish to get to in the word ladder
     * @param list a list of the words in the dictionary
     * @return a ladderInfo item with the most effeicient way to get from the starting word to the ending word
     */
    public LadderInfo PriorityQueueMethod (String startWord, String endWord, ArrayList list) {
        AVLTree<LadderInfo> t = new AVLTree<>();


        //setting up the ladder
        int totalEnq = 0;
        LadderInfo initLadder = new LadderInfo(startWord, 0, startWord, endWord, totalEnq );
        t.insert(initLadder);
        boolean done = false;



        //getting an arraylist of LadderInfos to track the position of the words.
        HashMap<String, Integer> wordPosition = new HashMap<>();

        while (!t.isEmpty() && !done) {
            //get the highest priority node
            t.deleteMin();
            LadderInfo curr = t.getDeletedValue();

            //check to see if there has been sufficent enqueues
            if(totalEnq > 5000){
                curr = new LadderInfo(startWord, 0, startWord, endWord, totalEnq);
                return curr;
            }



            for (int x = 0; x < curr.word.length(); x++) {
                for (char y = 'a'; y <= 'z'; y++) {
                    //getting a newWord to check
                    String newWord = curr.word.substring(0, x) + y + curr.word.substring(x + 1);
                    if (list.contains(newWord)) {
                        LadderInfo nextLadder = new LadderInfo(newWord, curr.moves + 1, curr.ladder + " " + newWord, endWord, totalEnq);
                        //this checks to see if the new word has already been used in the current ladder
                        //if it has but the new position is earlier then the last time used it will still use the word
                        if (wordPosition.containsKey(newWord)) {
                            if (wordPosition.get(newWord) <= nextLadder.moves) {
                                //skips this iteration of the loop
                                continue;
                            }
                        }

                        //puts the word and its position into a hashmap
                        wordPosition.put(newWord, nextLadder.moves);

                        //checks to see if the new word is our targer word
                            if (newWord.equals(endWord)) {
                                done = true;
                                return nextLadder;
                            }
                            t.insert(nextLadder);
                            totalEnq++;
                    }
                }
            }
        }
        //if it gets to here that means no solution was found and to return the initial Ladder
        System.out.println("No Solution Found");
        return initLadder;
    }


    /**
     * @author Spencer Potter
     * a word ladder function that uses a normal queue. It finds the most efficent way from the starting word
     * to the ending word using the brute force method
     *
     * @param startWord the starting word in the word ladder
     * @param endWord the word we wish to get to in the word ladder
     * @param list a list of the words in the dictionary
     * @return a ladderInfo item with the most effeicient way to get from the starting word to the ending word
     */
    public LadderInfo bruteForceMethod(String startWord, String endWord, ArrayList list){

        Queue<LadderInfo> q = new Queue<>();
        // Solve the word ladder problem

        //setting up the ladder
        LadderInfo initLadder = new LadderInfo(startWord, 0, startWord, endWord, 0);
        q.enqueue(initLadder);
        boolean done = false;
        int totalEnq = 0;

        while (!q.isEmpty() && !done) {

            LadderInfo curr = q.dequeue();

            //check to see if there has been sufficent enqueues
            if(totalEnq > 5000){
                curr = new LadderInfo(startWord, 0, startWord, endWord, totalEnq);
                return curr;
            }


            //these nested for loops are finding every possible word in the list that is off by 1 letter
            for (int x = 0; x < curr.word.length(); x++) {
                for (char y = 'a'; y <= 'z'; y++) {
                    //getting a newWord to check
                    String newWord = curr.word.substring(0,x) + y + curr.word.substring(x+1);
                    //check to see if the new word is in the list
                    if(list.contains(newWord)){
                        LadderInfo nextLadder = new LadderInfo(newWord, curr.moves+1, curr.ladder + " " + newWord, endWord, totalEnq);
                        list.remove(newWord);
                        if(newWord.equals(endWord)){
                            done = true;
                            return nextLadder;
                        }
                        q.enqueue(nextLadder);
                        totalEnq++;
                    }
                }
            }
        }
        //if it gets down here that means there was no solutions
        System.out.println("No Solution Found");
        return initLadder;
    }


    /**
     * Find a word ladder between random words of length len
     * @param len  Length of words in desired word ladder
     */
    public void play(int len) {
       if (len >= MaxWordSize) return;
        ArrayList<String> list = allList[len];
        String firstWord = list.get(random.nextInt(list.size()));
        String lastWord = list.get(random.nextInt(list.size()));
        play(firstWord, lastWord );
    }

}

