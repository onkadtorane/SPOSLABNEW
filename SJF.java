import java.util.*;

class Process {
    int id; 
    int burstTime;
    int arrivalTime;
    int completionTime;
    int waitingTime;
    int turnaroundTime;

    public Process(int id, int burstTime, int arrivalTime){
        this.id = id;
        this.burstTime = burstTime;
        this.arrivalTime = arrivalTime;
    }
}

public class SJF{
    public static void calculateTimes(List<Process> processes){
        int currentTime = 0;
        processes.sort(Comparator.comparingInt(p -> p.arrivalTime));

        List<Process> completedProcesses = new ArrayList<>();

        while(completedProcesses.size() < processes.size()){
            Process shortestJob = null ;
            for(Process p : processes){
                if(!completedProcesses.contains(p) && p.arrivalTime <= currentTime){
                    if(shortestJob == null || p.burstTime < shortestJob.burstTime) {
                        shortestJob = p;
                    }
                }
            }

            if(shortestJob != null){
                int waitingTime = currentTime - shortestJob.arrivalTime;
                int turnaroundTime = waitingTime + shortestJob.burstTime;
                int completionTime = currentTime + shortestJob.burstTime;

                shortestJob.waitingTime = waitingTime;
                shortestJob.turnaroundTime = turnaroundTime;
                shortestJob.completionTime = completionTime;

                currentTime += shortestJob.burstTime;

                completedProcesses.add(shortestJob);
            } else {
                currentTime ++;
            }
        }
    }

    public static void calculateAverageTimes(List<Process> processes){
        int totalWaitingTime = 0;
        int totalTurnaroundTime = 0;
        int n = processes.size();

        for(Process p : processes){
            totalWaitingTime += p.waitingTime;
            totalTurnaroundTime += p.turnaroundTime;
        }

        System.out.println("\n Average Waiting Time : "+ (double)totalWaitingTime/n);
        System.out.println("\nAverage TurnAround TIme :"+ (double) totalTurnaroundTime/n);
    }

    public static void main(String[] args) {
        List<Process> processes = new ArrayList<>();
        processes.add(new Process(1, 6, 0));
        processes.add(new Process(2, 8, 1));
        processes.add(new Process(3, 7, 2));
        processes.add(new Process(4, 3, 3));

        calculateTimes(processes);

        System.out.println("Process ID\tBurst Time\tArrival Time\tCompletion Time\tWaiting Time\tTurnaround Time");
        for(Process p : processes){
            System.out.println(p.id + "\t\t" + p.burstTime + "\t\t" + p.arrivalTime + "\t\t" + p.completionTime + "\t\t" + p.waitingTime + "\t\t" + p.turnaroundTime);
        }

        calculateAverageTimes(processes);
    }
}