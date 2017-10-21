    public boolean deleteJob(JobKey jobKey) throws SchedulerException {
        validateState();

        boolean result = false;
        
        List<? extends Trigger> triggers = getTriggersOfJob(jobKey);
        for (Trigger trigger : triggers) {
            if (!unscheduleJob(trigger.getKey())) {
                StringBuilder sb = new StringBuilder().append(
                        "Unable to unschedule trigger [").append(
                        trigger.getKey()).append("] while deleting job [")
                        .append(jobKey).append(
                                "]");
                throw new SchedulerException(sb.toString());
            }
            result = true;
        }

        result = resources.getJobStore().removeJob(jobKey) || result;
        if (result) {
            notifySchedulerThread(0L);
            notifySchedulerListenersJobDeleted(jobKey);
        }
        return result;
    }
