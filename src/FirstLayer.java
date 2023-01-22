import java.util.ArrayList;

public class FirstLayer
{
    private ArrayList<Job> PriorityQueue = new ArrayList<>();
    private int CumulativeQueueLength;

    public void AddJobsToThisLayer(ArrayList<Job> jobs)
    {
        PriorityQueue.addAll(jobs);
    }

    public ArrayList<Job> GetHighestPriorityJobsInThisLayer(int k)
    {
        ArrayList<Job> selectedJobs = new ArrayList<>();
        for (int i = 0; i < PriorityQueue.size() && selectedJobs.size() < k; i++)
        {
            if (PriorityQueue.get(i).getPriority() == JobPriority.High)
            {
                selectedJobs.add(PriorityQueue.get(i));
            }
        }
        PriorityQueue.removeAll(selectedJobs);

        if (selectedJobs.size() < k)
        {
            for (int i = 0; i < PriorityQueue.size() && selectedJobs.size() < k; i++)
            {
                selectedJobs.add(PriorityQueue.get(i));
            }
        }
        PriorityQueue.removeAll(selectedJobs);

        return selectedJobs;
    }

    public ArrayList<Job> CheckExpiredJobs()
    {
        ArrayList<Job> expiredJobs = new ArrayList<>();
        for (Job job : PriorityQueue)
        {
            job.IncreaseWaitTimeInQueue(JobQueueType.FirstLayer);
            job.DecreaseRemainedExpireTime();
            if (job.getRemainedTimeToExpire() == 0)
            {
                System.out.println("Job with ID " + job.getID() + " Expired in First Layer in PriorityQueue!");
                expiredJobs.add(job);
            }
        }
        PriorityQueue.removeAll(expiredJobs);
        return expiredJobs;
    }

    public void StoreQueueLength()
    {
        CumulativeQueueLength += PriorityQueue.size();
    }

    public void PrintAverageQueueLength(int t)
    {
        System.out.println("FirstLayer Average queue length is: " + 1f * CumulativeQueueLength / t);
    }
}
