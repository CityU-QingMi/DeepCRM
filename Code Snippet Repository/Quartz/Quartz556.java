    protected ArrayList<TriggerWrapper> getTriggerWrappersForJob(JobKey jobKey) {
        ArrayList<TriggerWrapper> trigList = new ArrayList<TriggerWrapper>();

        synchronized (lock) {
            List<TriggerWrapper> jobList = triggersByJob.get(jobKey);
            if(jobList != null) {
                for(TriggerWrapper trigger : jobList) {
                    trigList.add(trigger);
                }
            }
        }

        return trigList;
    }
