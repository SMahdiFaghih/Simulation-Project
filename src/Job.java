public class Job
{
    private int ArrivalTime;
    private int ServiceTime;
    private JobPriority Priority;

    private int RemainedExecutionTime;

    public Job(int arrivalTime, int serviceTime, JobPriority jobPriority)
    {
        ArrivalTime = arrivalTime;
        ServiceTime = serviceTime;
        Priority = jobPriority;

        RemainedExecutionTime = serviceTime;
    }

    public int getArrivalTime()
    {
        return ArrivalTime;
    }

    public int getServiceTime()
    {
        return ServiceTime;
    }

    public JobPriority getPriority()
    {
        return Priority;
    }

    public void DecreaseRemainedExecutionTime()
    {
        RemainedExecutionTime --;
    }

    public int getRemainedExecutionTime()
    {
        return RemainedExecutionTime;
    }
}
