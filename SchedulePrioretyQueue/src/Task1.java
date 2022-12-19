
public class Task1 extends Task {
    public Task1(int ID, int start, int deadline, int duration) {
        super(ID,start,deadline,duration);
    }
    // Prioirity is deadline

    /**
     * @author Spencer
     * this compares the tasks based on deadline
     *
     * @param t2 is a task that will be compared to this task
     * @return returns the differnce between the two deadlines
     */
    @Override
    public int compareTo(Task t2) {
         //System.out.println("Using Task1 compareTo");
         return deadline-t2.deadline;
      }
}
