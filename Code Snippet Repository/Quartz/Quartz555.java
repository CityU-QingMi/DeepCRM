    public List<OperableTrigger> getTriggersForJob(JobKey jobKey) {
        ArrayList<OperableTrigger> trigList = new ArrayList<OperableTrigger>();

        synchronized (lock) {
            List<TriggerWrapper> jobList = triggersByJob.get(jobKey);
            if(jobList != null) {
                for(TriggerWrapper tw : jobList) {
                    trigList.add((OperableTrigger) tw.trigger.clone());
                }
            }
        }

        return trigList;
    }
