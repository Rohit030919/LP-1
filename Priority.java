import java.util.Scanner;

public class Priority {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the number of processes:");
        int noOfProcess = sc.nextInt();

        int[] arrivalTime = new int[noOfProcess];
        int[] burstTime = new int[noOfProcess];
        int[] priority = new int[noOfProcess];
        int[] completionTime = new int[noOfProcess];
        int[] turnAroundTime = new int[noOfProcess];
        int[] waitingTime = new int[noOfProcess];
        boolean[] isCompleted = new boolean[noOfProcess];

        for (int i = 0; i < noOfProcess; i++) {
            System.out.print("Enter the Arrival Time for Process" + (i + 1) + ": ");
            arrivalTime[i] = sc.nextInt();
            System.out.print("Enter the Burst Time for Process" + (i + 1) + ": ");
            burstTime[i] = sc.nextInt();
            System.out.print("Enter the Priority for Process" + (i + 1) + ": ");
            priority[i] = sc.nextInt();
            System.out.println();
        }

        int currentTime = 0;
        int completedProcesses = 0;

        while (completedProcesses < noOfProcess) {
            int highestPriorityIndex = -1;
            int maxPriority = Integer.MIN_VALUE;

            // Find the process with the highest priority that has arrived and is not completed
            for (int i = 0; i < noOfProcess; i++) {
                if (!isCompleted[i] && arrivalTime[i] <= currentTime && priority[i] > maxPriority) {
                    maxPriority = priority[i];
                    highestPriorityIndex = i;
                }
            }

            // Process the highest-priority task
            if (highestPriorityIndex != -1) {
                currentTime += burstTime[highestPriorityIndex];
                completionTime[highestPriorityIndex] = currentTime;
                isCompleted[highestPriorityIndex] = true;
                completedProcesses++;
            } else {
                currentTime++; // Increment time if no process is available
            }
        }

        int totalTAT = 0;
        int totalWT = 0;

        System.out.println("Process\t\tBurstTime\tArrivalTime\tPriority\tCompletionTime\tTurnAroundTime\tWaitingTime");
        for (int i = 0; i < noOfProcess; i++) {
            turnAroundTime[i] = completionTime[i] - arrivalTime[i];
            waitingTime[i] = turnAroundTime[i] - burstTime[i];
            totalTAT += turnAroundTime[i];
            totalWT += waitingTime[i];

            System.out.printf("P%d\t\t%d\t\t%d\t\t%d\t\t%d\t\t%d\t\t%d\n",
                    (i + 1), burstTime[i], arrivalTime[i], priority[i], completionTime[i], turnAroundTime[i], waitingTime[i]);
        }

        float avgTAT = (float) totalTAT / noOfProcess;
        float avgWT = (float) totalWT / noOfProcess;

        System.out.printf("\nAverage Turnaround Time: %.2f\n", avgTAT);
        System.out.printf("Average Waiting Time: %.2f\n", avgWT);
    }
}
