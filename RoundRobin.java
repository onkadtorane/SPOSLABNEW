import java.util.*;

public class RoundRobin {
    // Method to calculate completion time, turnaround time, and waiting time
    public static void roundRobin(int[] arrivalTime, int[] burstTime,int n, int quantum){
        int[] completionTime = new int[n];
        int[] remainingTime = new int[n];
        int[] turnaroundTime = new int[n];
        int[] waitingTime = new int[n];

        // Copy burst times into remaining time array
        for(int i = 0; i < n;i++){
            remainingTime[i] = burstTime[i];
        }

        int time = 0; // Tracks the current time in the system
        boolean allProcessesDone;

        // Main loop to simulate Round Robin scheduling
        while(true){
            allProcessesDone = true;

            for(int i = 0;i < n;i++){ // If the process has remaining burst time
                if(remainingTime[i] > 0){
                    allProcessesDone = false;

                    //Process runs for 'quantum' time or its ramaining time, whichever is smaller 
                    int timeSlice = Math.min(remainingTime[i], quantum);

                    //Increase current time
                    time += timeSlice;

                    //Reduce remaining time of the current process
                    remainingTime[i] -= timeSlice;

                    //If process is completed
                    if(remainingTime[i] == 0){
                        completionTime[i] = time;
                        turnaroundTime[i] = completionTime[i] - arrivalTime[i];
                        waitingTime[i] = turnaroundTime[i] - burstTime[i];
                    }
                }
            }

            // If all processes are completed, exit the loop
            if(allProcessesDone){
                break;
            }
        }

        //Print the result in a table format
        System.out.println("Process ID | Arrival Time | Burst Time | Completion Time | Turnaround Time | Waiting Time");
        for(int i = 0; i < n; i++){
            System.out.printf("%-12d %-13d %-11d %-16d %-17d %-13d\n", i+1,arrivalTime[i],burstTime[i], completionTime[i], turnaroundTime[i], waitingTime[i]);
        }
    }
    
    public static void main(String[] args) {
        // Example process details: Arrival Time and Burst Time
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number of processes : ");
        int n = sc.nextInt();
        int[] arrivalTime = new int[n]; // Arrival times of processes
        int[] burstTime =  new int[n];  // Burst times of processes

        System.out.println("Enter the process details :");
        for(int i = 0; i < n; i++){
            System.out.print("Enter arrival time of Process "+(i+1)+" : ");
            arrivalTime[i] = sc.nextInt();
            System.out.print("Enter burst time of Process "+(i+1)+" : ");
            burstTime[i] = sc.nextInt();
        }
        System.out.print("Enter the time Quantum : ");
        int quantum = sc.nextInt();
        
        // Call Round Robin scheduling function
        roundRobin(arrivalTime, burstTime, n, quantum);
    }
}
