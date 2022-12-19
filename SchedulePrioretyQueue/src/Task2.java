public class Task2 extends Task{
    public Task2 (int ID, int start, int deadline, int duration) {
        super(ID,start,deadline,duration);
    }
    // Prioirity is shortest deadline and longest duration

    /**
     * @author Spencer Potter
     * this method compares two tasks based on there deadlines and the duration of time they take
     *
     * @param t2 is a task that will be compared to this task
     * @return return the deadline and duration difference between the two functions
     */
    @Override
    public int compareTo(Task t2) {
        int deadlineNum = deadline-t2.deadline;
        int durrationNum = duration - t2.duration;
        return deadlineNum + durrationNum;
    }
}
