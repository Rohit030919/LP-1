import java.util.*;

public class FCFS{
	public static void main(String args[]){
		Scanner sc = new Scanner(System.in);

		System.out.println("Enter the Number of Processes:");
		int noOfProcess = sc.nextInt();

		int[] arrivalTime = new int[noOfProcess];
		int[] burstTime = new int[noOfProcess];
		int i;
		for(i=0; i<noOfProcess; i++){
			System.out.println("Enter the Arrival time for Process:" + (i+1) + ":");
			arrivalTime[i] = sc.nextInt();
			System.out.println("Enter the Burst time for Process:" + (i+1) + ":");
			burstTime[i] = sc.nextInt();
			System.out.println();
		}
		int[] completionTime = new int[noOfProcess];
		int[] turnAroundTime = new int[noOfProcess];
		int[] waitingTime = new int[noOfProcess];

		completionTime[0] =  arrivalTime[0] + burstTime[0];
		for(i=1; i<noOfProcess; i++){
			completionTime[i] = Math.max(completionTime[i-1],arrivalTime[i]) + burstTime[i];
		}

		for(i=0; i<noOfProcess; i++){
			turnAroundTime[i] = completionTime[i] - arrivalTime[i];
			waitingTime[i] = turnAroundTime[i] - burstTime[i];
		}
		
		float avgTAT=0, avgWT=0;
		for(i=0; i<noOfProcess; i++){
			avgTAT += turnAroundTime[i];
			avgWT += waitingTime[i];
		}
		avgTAT /= noOfProcess;
		avgWT /= noOfProcess;

		System.out.println("Process\t\tArrivalTime\tBurstTime\tCompletionTime\tTurnAroundTime\tWaitingTime");
		for(i=0; i<noOfProcess; i++){
			System.out.printf("P%d\t\t%d\t\t%d\t\t%d\t\t%d\t\t%d\n",(i+1), arrivalTime[i], burstTime[i], completionTime[i], turnAroundTime[i], waitingTime[i]);
		}
		System.out.println("Average Turn Around Time is :" +avgTAT);
		System.out.println("Average Waiting Time is:" +avgWT);
	}
}