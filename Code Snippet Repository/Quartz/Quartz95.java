    public boolean unscheduleJob(TriggerKey triggerKey) throws SchedulerException {
        validateState();

        if (resources.getJobStore().removeTrigger(triggerKey)) {
            notifySchedulerThread(0L);
            notifySchedulerListenersUnscheduled(triggerKey);
        } else {
            return false;
        }

        return true;
    }
