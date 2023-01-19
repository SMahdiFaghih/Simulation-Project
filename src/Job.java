public class Job
{
    private int ArrivalTime;
    private int ServiceTime;
    private JobPriority Priority;

    public Job(int arrivalTime, int serviceTime, JobPriority jobPriority)
    {
        ArrivalTime = arrivalTime;
        ServiceTime = serviceTime;
        Priority = jobPriority;
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
}
