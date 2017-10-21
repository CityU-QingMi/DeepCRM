    private boolean removeTrigger(TriggerKey key, boolean removeOrphanedJob) {

        boolean found;

        synchronized (lock) {
            // remove from triggers by FQN map
            TriggerWrapper tw = triggersByKey.remove(key);
            found = tw != null;
            if (found) {
                // remove from triggers by group
                HashMap<TriggerKey, TriggerWrapper> grpMap = triggersByGroup.get(key.getGroup());
                if (grpMap != null) {
                    grpMap.remove(key);
                    if (grpMap.size() == 0) {
                        triggersByGroup.remove(key.getGroup());
                    }
                }
                //remove from triggers by job
                List<TriggerWrapper> jobList = triggersByJob.get(tw.jobKey);
                if(jobList != null) {
                    jobList.remove(tw);
                    if(jobList.isEmpty()) {
                        triggersByJob.remove(tw.jobKey);
                    }
                }
               
                timeTriggers.remove(tw);

                if (removeOrphanedJob) {
                    JobWrapper jw = jobsByKey.get(tw.jobKey);
                    List<OperableTrigger> trigs = getTriggersForJob(tw.jobKey);
                    if ((trigs == null || trigs.size() == 0) && !jw.jobDetail.isDurable()) {
                        if (removeJob(jw.key)) {
                            signaler.notifySchedulerListenersJobDeleted(jw.key);
                        }
                    }
                }
            }
        }

        return found;
    }
