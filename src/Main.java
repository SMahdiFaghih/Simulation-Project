import java.util.ArrayList;
import java.util.Scanner;

public class Main
{
    private static ArrayList<Job> Jobs;
    private static FirstLayer FirstLayer = new FirstLayer();
    private static SecondLayer SecondLayer = new SecondLayer();
    private static ArrayList<Job> CompletedJobs = new ArrayList<>();

    private static int k = 10;
    private static int intervalToMoveJobsToSecondLayer = 10;
    private static int remainedTimeToCheckMovingJobsToSecondLayer = 0;

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
        ExecuteSimulation(t);
    }

    private static void ExecuteSimulation(int t)
    {
        for (int i = 0; i < t; i++)
        {
            AddArrivedJobsToFirstLayer(i);
            CheckAddingJobsFromFirstLayerToSecondLayer();
        }
    }

    private static void AddArrivedJobsToFirstLayer(int currentTime)
    {
        ArrayList<Job> arrivedJobs = new ArrayList<>();
        for (Job job : Jobs)
        {
            if (job.getArrivalTime() == currentTime)
            {
                arrivedJobs.add(job);
            }
        }
        FirstLayer.AddJobsToThisLayer(arrivedJobs);
        Jobs.removeAll(arrivedJobs);
    }

    private static void CheckAddingJobsFromFirstLayerToSecondLayer() //JobLoader in project document
    {
        if (remainedTimeToCheckMovingJobsToSecondLayer == 0)
        {
            if (SecondLayer.GetNumberOfJobsInThisLayer() < k)
            {
                SecondLayer.AddJobsToThisLayer(FirstLayer.GetHighestPriorityJobsInThisLayer(k));
            }
            remainedTimeToCheckMovingJobsToSecondLayer = intervalToMoveJobsToSecondLayer;
        }
        else
        {
            remainedTimeToCheckMovingJobsToSecondLayer --;
        }
    }
}
