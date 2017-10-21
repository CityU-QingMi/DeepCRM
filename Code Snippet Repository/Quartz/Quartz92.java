    public boolean deleteJobs(List<JobKey> jobKeys)  throws SchedulerException {
        validateState();

        boolean result = false;
        
        result = resources.getJobStore().removeJobs(jobKeys);
        notifySchedulerThread(0L);
        for(JobKey key: jobKeys)
            notifySchedulerListenersJobDeleted(key);
        return result;
    }
