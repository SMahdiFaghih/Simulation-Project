import java.util.ArrayList;

public class SecondLayer
{
    private JobQueue RoundRobinT1 = new JobQueue(JobQueueType.RoundRobinT1, 5);
    private JobQueue RoundRobinT2 = new JobQueue(JobQueueType.RoundRobinT2, 10);
    private JobQueue FCFS = new JobQueue(JobQueueType.FCFS, 0);

    public int GetNumberOfJobsInThisLayer()
    {
        return RoundRobinT1.GetQueueSize() + RoundRobinT2.GetQueueSize() + FCFS.GetQueueSize();
    }

    public void AddJobsToThisLayer(ArrayList<Job> jobs)
    {
        RoundRobinT1.AddJobsToThisLayer(jobs);
    }

    public JobQueue SelectJobQueueToExecute() //Dispatcher in project document
    {
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
}
