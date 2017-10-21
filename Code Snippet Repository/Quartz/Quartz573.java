    public void storeJobsAndTriggers(
            Map<JobDetail, Set<? extends Trigger>> triggersAndJobs, boolean replace)
            throws JobPersistenceException {

        synchronized (lock) {
            // make sure there are no collisions...
            if(!replace) {
                for(Entry<JobDetail, Set<? extends Trigger>> e: triggersAndJobs.entrySet()) {
                    if(checkExists(e.getKey().getKey()))
                        throw new ObjectAlreadyExistsException(e.getKey());
                    for(Trigger trigger: e.getValue()) {
                        if(checkExists(trigger.getKey()))
                            throw new ObjectAlreadyExistsException(trigger);
                    }
                }
            }
            // do bulk add...
            for(Entry<JobDetail, Set<? extends Trigger>> e: triggersAndJobs.entrySet()) {
                storeJob(e.getKey(), true);
                for(Trigger trigger: e.getValue()) {
                    storeTrigger((OperableTrigger) trigger, true);
                }
            }
        }
        
    }
