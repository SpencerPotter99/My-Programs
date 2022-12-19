public class Task implements Comparable<Task> {
    public int ID;
    public int start;
    public int deadline;
    public int duration;
    public Task() {
        this.ID = 0;
        this.start = 0;
        this.deadline = 0;
        this.duration = 0;
    }


    public Task(int ID, int start, int deadline, int duration) {
        this.ID = ID;
        this.start = start;
        this.deadline = deadline;
        this.duration = duration;
    }

    public String toString() {
        return "Task " + ID;
    }

    public String toStringL() {
        return "Task " + ID + "[" + start + "-" + deadline + "] " + duration;
    }

    /**
     * @author Spencer Potter
     * this compares the first tasks starting time to the second
     * if they are tied then it looks at whose deadline is sooner
     *
     * @param t2 is a task that will be compared to this task
     * @return an int that represents which task has the greater priority
     */
    public int compareTo(Task t2) {
        //System.out.println("NO compareTo");
        // Supply your own comparator method
        if(start> t2.start){
            return 1;
        }
        if(start == t2.start){
            if(deadline< t2.deadline){
                return -1;
            }
            return 1;
        }
        return -1;
    }

}
