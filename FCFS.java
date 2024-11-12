import java.util.*;

public class FCFS{
    public static void main(String arg[]){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the no. of process: ");
        int n = sc.nextInt();
        int pid[] = new int[n];
        int at[] = new int[n];
        int bt[] = new int[n];
        int ct[] = new int[n];
        int tt[] = new int[n];
        int wt[] = new int[n];
        int temp;
        float avgwt = 0, avgtt = 0;
        
        for(int i=0;i<n;i++){
            System.out.println("Enter process "+(i+1)+" arrival time: ");
            at[i] = sc.nextInt();
            System.out.println("Enter process "+(i+1)+" burst time: ");
            bt[i] = sc.nextInt();
            pid[i] = i + 1;
        }

        //sorting according to arrival times
        for(int i = 0; i<n;i++){
            for(int j =0;j < n-(i+1); j++){
                if( at[j] > at[j+1]){
                    temp = at[j];
                    at[j] = at[j+1];
                    at[j+1] = temp;
                    temp = bt[j];
                    bt[j] = bt[j+1];
                    bt[j+1] = temp;
                    temp = pid[j];
                    pid[j] = pid[j+1];
                    pid[j+1] = temp;
                }
            }
        }

        //finding completion times
        for(int i = 0;i < n;i++){
            if(i == 0){
                ct[i] = at[i] + bt[i];
            }
            else{
                if(at[i] > ct[i-1]){
                    ct[i] = at[i] + bt[i];
                }
                else{
                    ct[i] = ct[i-1] + bt[i];
                }
            }
            tt[i] = ct[i] - at[i];
            wt[i] = tt[i] - bt[i];
            avgwt += wt[i];
            avgtt += tt[i];
        }

        System.out.println("\n id\tarrival time\tburst time\tcompletion time\t turnaround time\twaiting time");
        for(int i = 0;i < n;i++){
            System.out.println(pid[i] + "\t"+at[i]+"\t"+bt[i]+"\t"+ct[i]+"\t"+tt[i]+"\t"+wt[i]);
        }
        sc.close();
        System.out.println("\nAverage Wainting time: "+(avgwt/n));
        System.out.println("Average turnaround time: "+(avgtt/n));
    }
}