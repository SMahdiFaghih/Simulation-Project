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
        float z = scanner.nextFloat();

        System.out.println("Enter number of jobs");
        int n = scanner.nextInt();

        System.out.println("Enter simulation time in seconds");
        int t = scanner.nextInt();

        Jobs = JobCreator.CreateJobs(n, x, y, z);
        System.out.println("Created Jobs info (Number, ArrivalTime, ServiceTime, Priority, ExpireTime)");
        for (Job job : Jobs)
        {
            System.out.println(job.getID() + ". " + job.getArrivalTime() + " " + job.getServiceTime() + " " + job.getPriority() + " " + job.getExpireTime());
        }
        System.out.println();

        ExecuteSimulation(t);
    }

    private static void ExecuteSimulation(int t)
    {
        for (int i = 0; i < t; i++)
        {
            System.out.println("************************************ Time: " + i);
            AddArrivedJobsToFirstLayer(i);
            CheckAddingJobsFromFirstLayerToSecondLayer();
            CheckJobExecutor();
            CheckExpiredJobs();
        }
    }

    private static void AddArrivedJobsToFirstLayer(int currentTime)
    {
        ArrayList<Job> arrivedJobs = new ArrayList<>();
        for (Job job : Jobs)
        {
            if (job.getArrivalTime() == currentTime)
            {
                System.out.println("Job with ID " + job.getID() + " has arrived and is added into the FirstLayer");
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
            System.out.println("Job with ID " + completedJob.getID() + " completed");
            CompletedJobs.add(completedJob);
        }

        Job timeOutJob = JobExecutor.CheckIfJobQuantumTimeIsOver();
        if (timeOutJob != null)
        {
            JobQueueType jobQueueType = JobExecutor.GetExecutingJobQueueType();
            System.out.println("Job with ID " + timeOutJob.getID() + " timeout in " + jobQueueType + " queue and will be moved to the next queue");
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
                System.out.println("Selected next Job to execute from " + queueToSelectNextJobToExecute.GetJobQueueType() + " queue");
                JobExecutor.setExecutingJob(queueToSelectNextJobToExecute);
            }
            else
            {
                System.out.println("JobExecutor is free but there is no job in SecondLayer to execute!");
            }
        }
    }

    private static void CheckExpiredJobs()
    {
        FirstLayer.CheckExpiredJobs();
        SecondLayer.CheckExpiredJobs();
    }
}
