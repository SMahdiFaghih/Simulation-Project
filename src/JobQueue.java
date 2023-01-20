import java.util.ArrayList;

public class JobQueue
{
    private JobQueueType JobQueueType;
    private ArrayList<Job> Jobs = new ArrayList<>();
    private int QuantumTime;

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

    public void CheckExpiredJobs()
    {
        ArrayList<Job> expiredJobs = new ArrayList<>();
        for (Job job : Jobs)
        {
            job.DecreaseRemainedExpireTime();
            if (job.getRemainedTimeToExpire() == 0)
            {
                System.out.println("Job Expired in Second Layer in " + JobQueueType + " queue!");
                expiredJobs.add(job);
            }
        }
        Jobs.removeAll(expiredJobs);
    }
}
