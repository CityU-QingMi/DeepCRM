    public boolean removeJob(JobKey jobKey) {

        boolean found = false;

        synchronized (lock) {
            List<OperableTrigger> triggersOfJob = getTriggersForJob(jobKey);
            for (OperableTrigger trig: triggersOfJob) {
                this.removeTrigger(trig.getKey());
                found = true;
            }
            
            found = (jobsByKey.remove(jobKey) != null) | found;
            if (found) {

                HashMap<JobKey, JobWrapper> grpMap = jobsByGroup.get(jobKey.getGroup());
                if (grpMap != null) {
                    grpMap.remove(jobKey);
                    if (grpMap.size() == 0) {
                        jobsByGroup.remove(jobKey.getGroup());
                    }
                }
            }
        }

        return found;
    }
