import java.util.ArrayList;
import java.util.Random;

public class JobCreator
{
    private static final Random _random = new Random();

    public static ArrayList<Job> CreateJobs(int n, int x, float y)
    {
        ArrayList<Job> jobs = new ArrayList<>();

        jobs.add(new Job(0, GenerateServiceTime(y), GenerateJobPriority())); //First job arrives at 0
        for (int i = 1; i < n; i++)
        {
            jobs.add(new Job(jobs.get(i - 1).getArrivalTime() + GenerateInterArrivalTime(x), GenerateServiceTime(y), GenerateJobPriority()));
        }
        return jobs;
    }

    private static int GenerateInterArrivalTime(int x)
    {
        double l = Math.exp(-x);
        int k = 0;
        double p = 1.0;
        do
        {
            p = p * _random.nextDouble();
            k++;
        } while (p > l);
        return k - 1;
    }

    private static int GenerateServiceTime(float y)
    {
        return (int) Math.ceil(Math.log(_random.nextDouble()) / (-y));
    }

    private static JobPriority GenerateJobPriority()
    {
        float randomValue = _random.nextFloat();
        if (randomValue < 0.7)
        {
            return JobPriority.Low;
        }
        if (randomValue < 0.9)
        {
            return JobPriority.Normal;
        }
        return JobPriority.High;
    }
}
