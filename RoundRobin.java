import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class RoundRobin {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the number of processes:");
        int noOfProcesses = sc.nextInt();
        int[] arrivalTime = new int[noOfProcesses];
        int[] burstTime = new int[noOfProcesses];
        int[] remainingBurstTime = new int[noOfProcesses];
        int[] completionTime = new int[noOfProcesses];
        boolean[] isCompleted = new boolean[noOfProcesses];

        System.out.println("Enter the time quantum:");
        int timeQuantum = sc.nextInt();

        for (int i = 0; i < noOfProcesses; i++) {
            System.out.print("Enter the Arrival Time for Process " + (i + 1) + ": ");
            arrivalTime[i] = sc.nextInt();
            System.out.print("Enter the Burst Time for Process " + (i + 1) + ": ");
            burstTime[i] = sc.nextInt();
            remainingBurstTime[i] = burstTime[i];
            System.out.println();
        }

        int currentTime = 0;
        int completedProcesses = 0;
        Queue<Integer> processQueue = new LinkedList<>();

        // Add processes that have arrived at time 0
        for (int i = 0; i < noOfProcesses; i++) {
            if (arrivalTime[i] <= currentTime) {
                processQueue.add(i);
            }
        }

        while (completedProcesses < noOfProcesses) {
            if (processQueue.isEmpty()) {
                currentTime++;
                for (int i = 0; i < noOfProcesses; i++) {
                    if (arrivalTime[i] <= currentTime && !processQueue.contains(i) && !isCompleted[i]) {
                        processQueue.add(i);
                    }
                }
                continue;
            }

            int currentProcess = processQueue.poll();
            if (remainingBurstTime[currentProcess] > timeQuantum) {
                currentTime += timeQuantum;
                remainingBurstTime[currentProcess] -= timeQuantum;
            } else {
                currentTime += remainingBurstTime[currentProcess];
                remainingBurstTime[currentProcess] = 0;
                isCompleted[currentProcess] = true;
                completionTime[currentProcess] = currentTime;
                completedProcesses++;
            }

            // Check if new processes have arrived during the current time slice
            for (int i = 0; i < noOfProcesses; i++) {
                if (arrivalTime[i] <= currentTime && !processQueue.contains(i) && !isCompleted[i]) {
                    processQueue.add(i);
                }
            }

            // Re-add the current process if it still needs more time
            if (!isCompleted[currentProcess]) {
                processQueue.add(currentProcess);
            }
        }

        int totalTAT = 0;
        int totalWT = 0;

        System.out.println("Process\t\tBurstTime\tArrivalTime\tCompletionTime\tTurnAroundTime\tWaitingTime");
        for (int i = 0; i < noOfProcesses; i++) {
            int turnAroundTime = completionTime[i] - arrivalTime[i];
            int waitingTime = turnAroundTime - burstTime[i];
            totalTAT += turnAroundTime;
            totalWT += waitingTime;

            System.out.printf("P%d\t\t%d\t\t%d\t\t%d\t\t%d\t\t%d\n", (i + 1), burstTime[i], arrivalTime[i], completionTime[i], turnAroundTime, waitingTime);
        }

        float avgTAT = (float) totalTAT / noOfProcesses;
        float avgWT = (float) totalWT / noOfProcesses;

        System.out.printf("\nAverage Turnaround Time: %.2f\n", avgTAT);
        System.out.printf("Average Waiting Time: %.2f\n", avgWT);
    }
}
