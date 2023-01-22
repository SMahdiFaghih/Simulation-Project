public class Job
{
    private int ID;
    private int ArrivalTime;
    private int ServiceTime;
    private JobPriority Priority;
    private int ExpireTime;

    private int RemainedExecutionTime;
    private int RemainedTimeToExpire;

    private static int TotalSpentTimeInFirstLayerQueue;
    private static int TotalSpentTimeInRoundRobinT1Queue;
    private static int TotalSpentTimeInRoundRobinT2Queue;
    private static int TotalSpentTimeInFCFSQueue;

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

    public void IncreaseWaitTimeInQueue(JobQueueType queueType)
    {
        switch (queueType)
        {
            case FirstLayer:
                TotalSpentTimeInFirstLayerQueue++;
                break;
            case RoundRobinT1:
                TotalSpentTimeInRoundRobinT1Queue++;
                break;
            case RoundRobinT2:
                TotalSpentTimeInRoundRobinT2Queue++;
                break;
            case FCFS:
                TotalSpentTimeInFCFSQueue++;
                break;
        }
    }

    public static void PrintAverageTimeSpentInQueues(int n)
    {
        System.out.println();
        System.out.println("Average time spent in FirstLayer queue is: " + 1f * TotalSpentTimeInFirstLayerQueue / n);
        System.out.println("Average time spent in RoundRobinT1 queue is: " + 1f * TotalSpentTimeInRoundRobinT1Queue / n);
        System.out.println("Average time spent in RoundRobinT2 queue is: " + 1f * TotalSpentTimeInRoundRobinT2Queue / n);
        System.out.println("Average time spent in FCFS queue is: " + 1f * TotalSpentTimeInFCFSQueue / n);
    }
}
