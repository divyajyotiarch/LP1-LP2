import java.util.Scanner;

class Process {

    int processId;
    boolean active;

    public Process(int processId) {
        this.processId = processId;
        active = true;
    }
}

public class RingElection {
    
    private Scanner consoleInput;
    private Process[] process;
    private int NosOfProcess;

    public RingElection() {
        
        consoleInput = new Scanner(System.in);
    }

    public void getInput() {
        System.out.println("Enter number of process : ");
        NosOfProcess = consoleInput.nextInt();
        process = new Process[NosOfProcess];

        for (int i = 0; i < NosOfProcess; i++) {
            System.out.print("Enter process Id of process " + i + ": ");
            int pid = consoleInput.nextInt();
            initializeProcess(i, pid);
        }
        sortProcess();
        putOutput();
    }

    private void initializeProcess(int i, int pid) {
        process[i] = new Process(pid);
    }

    public void conductElection() {
                
        try{
            Thread.sleep(2000);
        }catch(Exception e ){
            System.out.println(e);
        }
        System.out.println("The process  "+ process[getMax()].processId +" has failed");
        process[NosOfProcess-1].active = false;
        
        System.out.println("Election has started");
        System.out.println("Enter the process which will initiate the election : ");
        int initiatorProcess = consoleInput.nextInt();
        for(int i = 0; i< NosOfProcess; i++){
            if(process[i].processId == initiatorProcess){
                initiatorProcess = i;
                break;
            }
        }
        int prev = initiatorProcess;
        int next = prev+1;
       
        while(true){
            if(process[next].active) {
                System.out.println("Process "+ process[prev].processId +" sending message to process "+process[next].processId );
                prev = next;
            }
            next = (next+1) % NosOfProcess;
            
            if(next == initiatorProcess) {
                break;
            }
        }
        System.out.println("Process "+ process[getMax()].processId +" is elected");
       } 
    
    
    public void putOutput(){
        System.out.println("Displaying process Id and its status ");   
        for(int i = 0; i < NosOfProcess; i++){
            System.out.print(process[i].processId +", active: "+ process[i].active);
            if(i == getMax()){
                System.out.print(", Current Coordinator\n");
            }else {
                System.out.print("\n");
            }
            
        }
    }
    
    private void sortProcess() {
        for (int i = 0; i < NosOfProcess - 1; i++) {
            for (int j = 0; j < (NosOfProcess - i) -1; j++) {
                if (process[j].processId > process[j + 1].processId) {
                    int temp = process[j].processId;
                    process[j].processId = process[j + 1].processId;
                    process[j + 1].processId = temp;

                }
            }
        }
    }
    
    private int getMax(){
        int max = 0, indexOfMax = 0;
        for(int i = 0; i < NosOfProcess; i++){
            if(process[i].active && max <= process[i].processId ) {
                max = process[i].processId;
                indexOfMax = i;
            }
        }
        return indexOfMax;
    }

    public static void main(String arg[]) {
        
        RingElection ringElection = new RingElection();
        ringElection.getInput();
        ringElection.conductElection();
    
    }
} 
