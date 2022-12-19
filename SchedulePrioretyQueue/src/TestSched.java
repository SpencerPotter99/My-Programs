import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class TestSched {

    /**
     * @author Spencer Potter
     * this method opens and reads a file that contains the information for the schedule.
     * It then puts that information into 3 different arrays to be sorted using the different prio queue algorithms
     *
     * @param filename - A string with the file name to open
     * @param task1 - an array list of tasks
     * @param task2 - an array list of tasks1
     * @param task3 - an array list of task2
     * @throws FileNotFoundException
     */
    public static void readTasks(String filename,
                                 ArrayList<Task> task1, ArrayList<Task> task2, ArrayList<Task> task3) throws FileNotFoundException {
        // Create lists where base type is different
        //reading the file
        File file = new File(filename);
        Scanner scanner = new Scanner(file);

        int taskID = 0;
        //reading line by line
        while(scanner.hasNextLine()){
            String line = scanner.nextLine();
            if(line.isEmpty()){
                break;
            }
            taskID ++;
            Scanner taskNum = new Scanner(line);
            int[] taskNumbers = new int[3];
            for(int x = 0; x<3; x++){
                String num = taskNum.next();
                taskNumbers[x] = Integer.parseInt(num);
            }

            Task task = new Task(taskID, taskNumbers[0], taskNumbers[1], taskNumbers[2]);
            Task1 deadlineTask = new Task1(taskID, taskNumbers[0], taskNumbers[1], taskNumbers[2]);
            Task2 wildTask = new Task2(taskID, taskNumbers[0], taskNumbers[1], taskNumbers[2]);
            task1.add(deadlineTask);
            task2.add(task);
            task3.add(wildTask);
        }

    }

    public static void main(String args[]) throws FileNotFoundException {
        Scheduler s = new Scheduler();
        String [] files = {"taskset1.txt","taskset2.txt","taskset3.txt","taskset4.txt","taskset5.txt" };
        for (String f : files) {
            ArrayList<Task> t1 = new ArrayList();    // elements are Task1
            ArrayList<Task> t2 = new ArrayList();    // elements are Task2
            ArrayList<Task> t3 = new ArrayList();    // elements are Task3
            readTasks(f, t1, t2, t3);

            s.makeSchedule("Deadline Priority "+ f, t1);
            s.makeSchedule("Startime Priority " + f, t2);
            s.makeSchedule("Wild and Crazy priority " + f, t3);
       }

    }
}
