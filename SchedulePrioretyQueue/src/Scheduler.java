import java.util.ArrayList;

public class Scheduler {
    public void makeSchedule(String scheduleName, ArrayList<Task> tasks){
        PriorityQueue<Task> t = new PriorityQueue<>();
        int time = 1;
        int lateTasks = 0;
        int lateTime = 0;
        for(Task task : tasks){
            if (task.start == time) {
                t.insert(task);
            }
        }
        //first line of the output
        System.out.println(scheduleName);
        while(!t.isEmpty()){
            Task curr = t.deleteMin();
            curr.duration--;
            System.out.print("Time: "+time+ " "+ curr.toString());

            //add the task back into the queue if it is not finished
            if(curr.duration != 0){
                t.insert(curr);
            }
            //if it is finished output ** and calculate if it was late or not
            else {
                System.out.print(" ** ");
                if(curr.deadline< time){
                    int taskLateTime = time-curr.deadline;
                    lateTime = lateTime + taskLateTime;
                    lateTasks++;
                    System.out.print("Late: " + taskLateTime);
                }
            }
            System.out.println();
            //add to the time
            time++;
            //add any tasks that now should be added into the priority queue
            //also the if statement is to make sure that we arent going throught the for loop more times then needed
            if(tasks.size() >= time) {
                for (Task task : tasks) {
                    if (task.start == time && task.duration > 0) {
                        t.insert(task);
                    }
                }
            }

        }
        System.out.println("Tasks late: " + lateTasks + " total minutes late : " + lateTime);
        System.out.println();
    }
}
