    public boolean unscheduleJobs(List<TriggerKey> triggerKeys) throws SchedulerException  {
        validateState();

        boolean result = false;
        
        result = resources.getJobStore().removeTriggers(triggerKeys);
        notifySchedulerThread(0L);
        for(TriggerKey key: triggerKeys)
            notifySchedulerListenersUnscheduled(key);
        return result;
    }
