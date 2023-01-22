public class Job
{
    private int ID;
    private int ArrivalTime;
    private int ServiceTime;
    private JobPriority Priority;
    private int ExpireTime;

    private int RemainedExecutionTime;
    private int RemainedTimeToExpire;

    public Job(int id, int arrivalTime, int serviceTime, JobPriority jobPriority, int expireTime)
    {
        ID = id;
        ArrivalTime = arrivalTime;
        ServiceTime = serviceTime;
        Priority = jobPriority;
        ExpireTime = expireTime;

        RemainedExecutionTime = serviceTime;
        RemainedTimeToExpire = expireTime;
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
        RemainedExecutionTime--;
    }

    public int getRemainedExecutionTime()
    {
        return RemainedExecutionTime;
    }

    public void DecreaseRemainedExpireTime()
    {
        RemainedTimeToExpire--;
    }

    public int getRemainedTimeToExpire()
    {
        return RemainedTimeToExpire;
    }

    public int getID()
    {
        return ID;
    }

    public int getExpireTime()
    {
        return ExpireTime;
    }
}
