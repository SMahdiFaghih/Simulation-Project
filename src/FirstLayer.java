import java.util.ArrayList;

public class FirstLayer
{
    private ArrayList<Job> PriorityQueue = new ArrayList<>();

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

    public void CheckExpiredJobs()
    {
        ArrayList<Job> expiredJobs = new ArrayList<>();
        for (Job job : PriorityQueue)
        {
            job.DecreaseRemainedExpireTime();
            if (job.getRemainedTimeToExpire() == 0)
            {
                System.out.println("Job Expired is First Layer in PriorityQueue!");
                expiredJobs.add(job);
            }
        }
        PriorityQueue.removeAll(expiredJobs);
    }
}
