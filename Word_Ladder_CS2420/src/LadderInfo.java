/**
 * This class creates WordInfo objects which allows the program
 * to keep track of words, their partial ladder, and number of moves in the word ladder.
 */
public class LadderInfo implements Comparable<LadderInfo> {

    public String word;  //last word in the word ladder
    public int moves;    //number of in the word ladder
    public String ladder;// printalbe representation of ladder
    public int priority; // the score of the given word
    public String targetWord; // the word that we are trying to get to.
    private double totalEnq; //number of enqueues the ladder has to this point

    public LadderInfo(String word, int moves, String ladder, String targetWord, double totalEnq){
        this.word = word;
        this.moves = moves;
        this.ladder = ladder;
        this.targetWord = targetWord;
        this.priority = getPrority(word);
        this.totalEnq = totalEnq;

    }

    /**
     * @author
     * a function that calculates the priority of the word in the ladderInfo class
     *
     * @param word the word we wish to check the priority of
     * @return the integer value of the priority of the word
     */
    public int getPrority (String word){
        //set priority to the integer value of the length of the end word
        this.priority = word.length()+1;
        for (int x = 0; x < word.length(); x++) {
            if(word.charAt(x) == targetWord.charAt(x)){
                this.priority--;//subtract one from the priority if the new word has matching letter to the ending word
            }
        }
        this.priority = this.priority + moves;//add the moves we already made to the priority
        return this.priority;
    }

    /**
     * @author Spencer Potter
     * gets the total enqueue number
     * @return a double which is the total enqueu number
     */
    public double getTotalEnq(){
        return totalEnq;
    }

    /**
     * @author Spencer Potter
     * @param b2 a ladderInfo class we want to compare to the current class
     * @return an integer value that represents whether the other ladder is greater then, equal to or less then the
     * curernt ladder
     */
    @Override
    public int compareTo(LadderInfo b2){
        if(this.priority > b2.priority){
            return 1;
        }
        if(this.priority== b2.priority){
            return 0;
        }
        return -1;
    }

    public String toString(){
        return  "Moves " +moves  + " ["+ ladder +"] ";
    }
}


