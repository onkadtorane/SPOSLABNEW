import java.util.Arrays;

public class Nextfit {
    //Fucntion to allocate nextfit block as per Next fit algorithm
    static void nextfit(int blockSize[], int m, int processSize[], int n){
        // Stores block id of the block allocated to a process
        int allocation[] = new int[n];
        int j = 0;
        // Initially no block is assigned to any process
        Arrays.fill(allocation, -1);

        // pick each process and find suitable blocks according to its size and assign it 
        for(int i = 0;i < n;i++){
            int count = 0;
            while(j < m){
                count++;   // makes sure that for every process we traverse through entire array maximum once only. This avoids the problem of going into infinite loop if memory is not avaliable
                if(blockSize[j] >= processSize[i]){

                    //allocate block j to p[i] process
                    allocation[i] = j;

                    // Reduce available memory in this block.
                    blockSize[j] -= processSize[i];

                    break;
                }
                //mod m will help in traversing the block from starting block after we reach the end.
                j = (j + 1) % m;
            }
        }
        System.out.print("\nProcess No.\tProcess Size\tBlock No.\n");
        for(int i = 0; i < n;i++){
            System.out.print(i + 1 + "\t\t" + processSize[i]+"\t\t");
            if(allocation[i] != -1)
                System.out.print(allocation[i] + 1);
            else 
                System.out.print("Not Allocated");
            System.out.println("");
        }
    }

    public static void main(String[] args) {
        int blockSize[] = {5,10,20};
        int processSize[] = {10,20,5};
        int m = blockSize.length;
        int n = processSize.length;
        nextfit(blockSize, m, processSize, n);
    }
}
