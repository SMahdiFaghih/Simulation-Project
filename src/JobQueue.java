import java.util.ArrayList;

public class JobQueue
{
    private JobQueueType JobQueueType;
    private ArrayList<Job> Jobs = new ArrayList<>();
    private int QuantumTime;

    private int CumulativeQueueLength;

    public JobQueue(JobQueueType jobQueueType, int quantumTime)
    {
        JobQueueType = jobQueueType;
        QuantumTime = quantumTime;
    }

    public int GetQueueSize()
    {
        return Jobs.size();
    }

    public Job GetNextJobToExecute()
    {
        Job job = Jobs.get(0);
        Jobs.remove(job);
        System.out.println("Selected Job has ID " + job.getID());
        return job;
    }

    public void AddJobToThisLayer(Job job)
    {
        Jobs.add(job);
    }

    public void AddJobsToThisLayer(ArrayList<Job> jobs)
    {
        Jobs.addAll(jobs);
    }

    public int GetQuantumTime()
    {
        return QuantumTime;
    }

    public JobQueueType GetJobQueueType()
    {
        return JobQueueType;
    }

    public ArrayList<Job> CheckExpiredJobs()
    {
        ArrayList<Job> expiredJobs = new ArrayList<>();
        for (Job job : Jobs)
        {
            job.IncreaseWaitTimeInQueue(JobQueueType);
            job.DecreaseRemainedExpireTime();
            if (job.getRemainedTimeToExpire() == 0)
            {
                System.out.println("Job with ID " + job.getID() + " Expired in Second Layer in " + JobQueueType + " queue!");
                expiredJobs.add(job);
            }
        }
        Jobs.removeAll(expiredJobs);
        return expiredJobs;
    }

    public void StoreQueueLength()
    {
        CumulativeQueueLength += Jobs.size();
    }

    public void PrintAverageQueueLength(int t)
    {
        System.out.println(JobQueueType + " Average queue length is: " + 1f * CumulativeQueueLength / t);
    }
}
