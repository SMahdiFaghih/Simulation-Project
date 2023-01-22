public class JobExecutor
{
    private Job ExecutingJob;
    private int RemainedQuantumTime;
    private JobQueueType ExecutingJobQueueType;

    private int TotalExecutingTime = 0;

    public void setExecutingJob(JobQueue jobQueue)
    {
        ExecutingJob = jobQueue.GetNextJobToExecute();
        RemainedQuantumTime = jobQueue.GetQuantumTime();
        ExecutingJobQueueType = jobQueue.GetJobQueueType();
    }

    public Job CheckIfJobIsCompleted()
    {
        if (ExecutingJob == null)
        {
            return null;
        }

        ExecutingJob.DecreaseRemainedExecutionTime();
        if (ExecutingJob.getRemainedExecutionTime() == 0)
        {
            Job job = ExecutingJob;
            ExecutingJob = null;
            return job;
        }

        return null;
    }

    public Job CheckIfJobQuantumTimeIsOver()
    {
        if (ExecutingJob == null || ExecutingJobQueueType == JobQueueType.FCFS)
        {
            return null;
        }

        RemainedQuantumTime--;
        if (RemainedQuantumTime == 0)
        {
            return ExecutingJob;
        }

        return null;
    }

    public boolean IsExecutingJob()
    {
        return ExecutingJob != null && ExecutingJob.getRemainedExecutionTime() > 0 && RemainedQuantumTime > 0;
    }

    public JobQueueType GetExecutingJobQueueType()
    {
        return ExecutingJobQueueType;
    }

    public void IncreaseTotalExecutingTime()
    {
        TotalExecutingTime++;
    }

    public int getTotalExecutingTime()
    {
        return TotalExecutingTime;
    }
}
