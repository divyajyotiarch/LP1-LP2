import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.Scanner;


public class ds5 extends Thread {

	public static HashMap<Integer,Integer> mp = new HashMap<Integer,Integer>();
	public static ArrayList<Integer>deffered = new ArrayList<Integer>();

	public static void enterCS(int want)
	{

	        for(int i=0 ; i<deffered.size()-1 ; i++)
	        {
	        for(int j=i+1 ; j<deffered.size() ; j++)
	        {
	        if(mp.get(deffered.get(i)) > mp.get(deffered.get(j)))
	        {
	        int tmp = deffered.get(i);
	        deffered.set(i, deffered.get(j));
	        deffered.set(j, tmp);
	        }
	        }
	        }
	        while(true)
	        {
	        int ans = deffered.get(0);
	        if(ans == want)
	        {
	        System.out.println(want + " enters the critical section");
	        executeCS(want);
	        break;
	        }
	        else
	        {
	        System.out.println(want+" waits for site "+ ans);
	        System.out.println(want + " send REPLY to site "+ans);
	        System.out.println("Site "+ans + " enters CS");
	        System.out.println("Site "+ans + " executes in CS");
	        System.out.println("Site "+ans + " leaves CS");
	        System.out.println("==================================");
	        deffered.remove((int)0);
	        }
	        }
	        System.out.println(deffered);
	}

	public static void executeCS(int want)
	{
	        System.out.println(want + " executes the critical section");
	        leaveCS();
	}

	public static void leaveCS()
	{
	        deffered.remove(0);
	}

	public static void main(String[] args)
	{
	        Scanner sc = new Scanner(System.in);
	        System.out.println("Enter no of sites:");
	        int sites = sc.nextInt();
	        Random ran = new Random();
	        for(int i=0;i<sites;i++)
	        {
	        mp.put(i+1, ran.nextInt(sites));
	        }
	        System.out.println("Site\t|\t"+"Time Stamp");
	        for(int i:mp.keySet())
	        {
	        System.out.println(i+"\t|\t"+mp.get(i));
	        }
	        System.out.println("Enter the site that wants to enter critical section");

	        int want = sc.nextInt();
	        System.out.println();
	        System.out.println("Sending Request to remaining "+(sites-1)+" sites.....");
	        deffered.add(want);
	        String ans="Y";
	        sc.nextLine();
	        System.out.println("Is there another site that wants to enter critical section(Y/N)");
	        ans = sc.nextLine();

	        while(ans.equals("Y"))
	        {
	        deffered.add(sc.nextInt());
	        sc.nextLine();
	        System.out.println("Is there another site that wants to enter critical section(Y/N)");
	        ans = sc.nextLine();
	        }
	        for(int i=0;i<sites;i++)
	        {
	        if(!deffered.contains(i+1))
	        {
	        System.out.println("REPLY received from site "+(i+1));
	        }
	        }
	        enterCS(want);
	        System.out.println();
	        System.out.println(deffered);
	}
	
	
}
