import java.util.*;

public class Priority {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int x, n, p[], pp[], bt[], wt[], tt[], awt, atat;

        p = new int[5]; // pid
        pp = new int[5]; // priority
        bt = new int[5]; // burst time
        wt = new int[5]; // waiting time
        tt = new int[5]; // turnaround time

        System.out.print("Enter the number of process :");
        n = sc.nextInt();
        System.out.print("\n\t Enter burst time : time priorities \n");

        for (int i = 0; i < n; i++) {
            System.out.println("Process[" + (i + 1) + "]:");
            bt[i] = sc.nextInt();
            pp[i] = sc.nextInt();
            p[i] = i + 1;
        }

        // sorting on the basis of priority
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (pp[i] > pp[j]) {
                    x = pp[i];
                    pp[i] = pp[j];
                    pp[j] = x;

                    x = bt[i];
                    bt[i] = bt[j];
                    bt[j] = x;

                    x = p[i];
                    p[i] = p[j];
                    p[j] = x;
                }
            }
        }
        wt[0] = 0;
        awt = 0;
        tt[0] = bt[0];
        atat = tt[0];

        for (int i = 1; i < n; i++) {
            wt[i] = tt[i - 1];
            awt += wt[i];
            tt[i] = wt[i] + bt[i];
            atat += tt[i];
        }

        // Displaying the process
        System.out.println("\nProcess \t Burst Time \t Wait Time \t Turn Around Time \t Priority \n");
        for(int i = 0;i<n;i++){
            System.out.print("\n    "+p[i]+"\t\t    "+bt[i]+"\t\t   "+wt[i]+"\t\t   "+tt[i]+"\t\t   "+pp[i]+"\n");
        }
        awt /= n;
        atat /= n;
        System.out.println("Average wait time : "+awt);
        System.out.println("Average Turn Around Time : "+atat);
    }

}
