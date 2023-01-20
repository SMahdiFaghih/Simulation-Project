import java.util.ArrayList;

public class SecondLayer
{
    private ArrayList<Job> RoundRobinT1 = new ArrayList<>();
    private ArrayList<Job> RoundRobinT2 = new ArrayList<>();
    private ArrayList<Job> FCFS = new ArrayList<>();

    public int GetNumberOfJobsInThisLayer()
    {
        return RoundRobinT1.size() + RoundRobinT2.size() + FCFS.size();
    }

    public void AddJobsToThisLayer(ArrayList<Job> jobs)
    {
        RoundRobinT1.addAll(jobs);
    }
}
