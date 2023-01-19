import java.util.ArrayList;
import java.util.Scanner;

public class Main
{
    public static ArrayList<Job> Jobs;

    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter parameters X, Y, Z in the given order");
        int x = scanner.nextInt();
        float y = scanner.nextFloat();
        int z = scanner.nextInt();

        System.out.println("Enter number of jobs");
        int n = scanner.nextInt();

        System.out.println("Enter simulation time in seconds");
        int t = scanner.nextInt();

        Jobs = JobCreator.CreateJobs(n, x, y);
    }
}
