    public void scheduleJobs(Map<JobDetail, Set<? extends Trigger>> triggersAndJobs, boolean replace)  throws SchedulerException  {
        validateState();

        // make sure all triggers refer to their associated job
        for(Entry<JobDetail, Set<? extends Trigger>> e: triggersAndJobs.entrySet()) {
            JobDetail job = e.getKey();
            if(job == null) // there can be one of these (for adding a bulk set of triggers for pre-existing jobs)
                continue;
            Set<? extends Trigger> triggers = e.getValue();
            if(triggers == null) // this is possible because the job may be durable, and not yet be having triggers
                continue;
            for(Trigger trigger: triggers) {
                OperableTrigger opt = (OperableTrigger)trigger;
                opt.setJobKey(job.getKey());

                opt.validate();

                Calendar cal = null;
                if (trigger.getCalendarName() != null) {
                    cal = resources.getJobStore().retrieveCalendar(trigger.getCalendarName());
                    if(cal == null) {
                        throw new SchedulerException(
                            "Calendar '" + trigger.getCalendarName() + "' not found for trigger: " + trigger.getKey());
                    }
                }
                Date ft = opt.computeFirstFireTime(cal);

                if (ft == null) {
                    throw new SchedulerException(
                            "Based on configured schedule, the given trigger will never fire.");
                }                
            }
        }

        resources.getJobStore().storeJobsAndTriggers(triggersAndJobs, replace);
        notifySchedulerThread(0L);
        for(JobDetail job: triggersAndJobs.keySet())
            notifySchedulerListenersJobAdded(job);
    }
