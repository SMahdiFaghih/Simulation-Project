import java.util.ArrayList;
import java.util.Scanner;

public class Main
{
    private static ArrayList<Job> Jobs;
    private static FirstLayer FirstLayer = new FirstLayer();
    private static SecondLayer SecondLayer = new SecondLayer();
    private static JobExecutor JobExecutor = new JobExecutor();
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
        System.out.println("Created Jobs info (Number, ArrivalTime, ServiceTime, Priority)");
        for (int i = 0; i < Jobs.size(); i++)
        {
            System.out.println((i + 1) + ". " + Jobs.get(i).getArrivalTime() + " " + Jobs.get(i).getServiceTime() + " " + Jobs.get(i).getPriority());
        }

        ExecuteSimulation(t);
    }

    private static void ExecuteSimulation(int t)
    {
        for (int i = 0; i < t; i++)
        {
            AddArrivedJobsToFirstLayer(i);
            CheckAddingJobsFromFirstLayerToSecondLayer();
            CheckJobExecutor();
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
            remainedTimeToCheckMovingJobsToSecondLayer--;
        }
    }

    private static void CheckJobExecutor()
    {
        Job completedJob = JobExecutor.CheckIfJobIsCompleted();
        if (completedJob != null)
        {
            CompletedJobs.add(completedJob);
        }

        Job timeOutJob = JobExecutor.CheckIfJobQuantumTimeIsOver();
        if (timeOutJob != null)
        {
            JobQueueType jobQueueType = JobExecutor.GetExecutingJobQueueType();
            if (jobQueueType == JobQueueType.RoundRobinT1)
            {
                SecondLayer.AddJobToRoundRobinT2Queue(timeOutJob);
            }
            else if (jobQueueType == JobQueueType.RoundRobinT2)
            {
                SecondLayer.AddJobToFCFSQueue(timeOutJob);
            }
        }

        if (!JobExecutor.IsExecutingJob())
        {
            JobQueue queueToSelectNextJobToExecute = SecondLayer.SelectJobQueueToExecute();
            if (queueToSelectNextJobToExecute != null)
            {
                JobExecutor.setExecutingJob(queueToSelectNextJobToExecute);
            }
        }
    }
}
