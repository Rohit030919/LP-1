import java.util.*;

public class SJF{
	public static void main(String args[]){
		Scanner sc = new Scanner(System.in);

		System.out.println("Enter the Number Of Processes:");
		int noOfProcess = sc.nextInt();

		int[] arrivalTime = new int[noOfProcess];
		int[] burstTime = new int[noOfProcess];
		int[] completionTime = new int[noOfProcess];
		boolean[] isCompleted = new boolean[noOfProcess];

		int i;
		for(i = 0; i<noOfProcess; i++){
			System.out.println("Enter the Arrival Time for Process:" + (i+1) + ":");
			arrivalTime[i] = sc.nextInt();
			System.out.println("Enter the Burst Time for Process:" + (i+1) + ":");
			burstTime[i] = sc.nextInt();
			System.out.println();
		}

		int currentTime = 0;
		int completedProcess = 0;
		while(completedProcess<noOfProcess){
			int shortestProcessIndex = -1;
			int minBurstTime = Integer.MAX_VALUE;
			//find the process with the minimum burst time which has arrived but not yet completed
			for(i=0;i<noOfProcess;i++){
				if(!isCompleted[i] && arrivalTime[i] <= currentTime && burstTime[i] < minBurstTime){
					minBurstTime = burstTime[i];
					shortestProcessIndex = i;
				}
			}
			if(shortestProcessIndex != -1){
				currentTime += burstTime[shortestProcessIndex];
				completionTime[shortestProcessIndex] = currentTime;
				isCompleted[shortestProcessIndex] = true;
				completedProcess++;
				
			}else{
				currentTime++;
			}
			
		}

		int totalTAT = 0;
		int totalWT = 0;
		System.out.println("Process\t\tArrivalTime\tBurstTime\tCompletionTime\tTurnAroundTime\tWaitingTime");
		for(i=0;i<noOfProcess;i++){

			int turnAroundTime = completionTime[i] - arrivalTime[i];
			int waitingTime = turnAroundTime - burstTime[i];

			totalTAT += turnAroundTime;
			totalWT += waitingTime;

			System.out.printf("P%d\t\t%d\t\t%d\t\t%d\t\t%d\t\t%d\n", (i+1), arrivalTime[i], burstTime[i], completionTime[i], turnAroundTime, waitingTime);
		}

		float avgtat = (float)totalTAT/noOfProcess;
		float avgwt = (float)totalWT/noOfProcess;
		System.out.println("Average Turn Around Time is:"+avgtat);
		System.out.println("Average Waiting Time is:"+avgwt);

	}
}