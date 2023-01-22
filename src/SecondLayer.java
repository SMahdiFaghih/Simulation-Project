import java.util.ArrayList;
import java.util.Random;

public class SecondLayer
{
    private JobQueue RoundRobinT1 = new JobQueue(JobQueueType.RoundRobinT1, 5);
    private JobQueue RoundRobinT2 = new JobQueue(JobQueueType.RoundRobinT2, 10);
    private JobQueue FCFS = new JobQueue(JobQueueType.FCFS, 0);

    private static final Random _random = new Random();

    public int GetNumberOfJobsInThisLayer()
    {
        return RoundRobinT1.GetQueueSize() + RoundRobinT2.GetQueueSize() + FCFS.GetQueueSize();
    }

    public void AddJobsToThisLayer(ArrayList<Job> jobs)
    {
        System.out.println("Adding " + jobs.size() + " job(s) from FirstLayer to the SecondLayer");
        RoundRobinT1.AddJobsToThisLayer(jobs);
    }

    public JobQueue SelectJobQueueToExecute() //Dispatcher in project document
    {
        float randomValue = _random.nextFloat();
        if (randomValue < 0.8)
        {
            if (RoundRobinT1.GetQueueSize() > 0)
            {
                return RoundRobinT1;
            }
        }
        else if (randomValue < 0.9)
        {
            if (RoundRobinT2.GetQueueSize() > 0)
            {
                return RoundRobinT2;
            }
        }
        else
        {
            if (FCFS.GetQueueSize() > 0)
            {
                return FCFS;
            }
        }

        if (RoundRobinT1.GetQueueSize() > 0)
        {
            return RoundRobinT1;
        }
        if (RoundRobinT2.GetQueueSize() > 0)
        {
            return RoundRobinT2;
        }
        if (FCFS.GetQueueSize() > 0)
        {
            return FCFS;
        }

        return null;
    }

    public void AddJobToRoundRobinT2Queue(Job job)
    {
        RoundRobinT2.AddJobToThisLayer(job);
    }

    public void AddJobToFCFSQueue(Job job)
    {
        FCFS.AddJobToThisLayer(job);
    }

    public ArrayList<Job> CheckExpiredJobs()
    {
        ArrayList<Job> expiredJobs = new ArrayList<>();
        expiredJobs.addAll(RoundRobinT1.CheckExpiredJobs());
        expiredJobs.addAll(RoundRobinT2.CheckExpiredJobs());
        expiredJobs.addAll(FCFS.CheckExpiredJobs());
        return expiredJobs;
    }

    public void StoreQueueLength()
    {
        RoundRobinT1.StoreQueueLength();
        RoundRobinT2.StoreQueueLength();
        FCFS.StoreQueueLength();
    }

    public void PrintAverageQueueLength(int t)
    {
        RoundRobinT1.PrintAverageQueueLength(t);
        RoundRobinT2.PrintAverageQueueLength(t);
        FCFS.PrintAverageQueueLength(t);
    }
}
