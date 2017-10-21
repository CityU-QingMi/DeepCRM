    @SuppressWarnings("")
    public void triggerJob(JobKey jobKey, JobDataMap data) throws SchedulerException {
        validateState();

        OperableTrigger trig = (OperableTrigger) newTrigger().withIdentity(newTriggerId(), Scheduler.DEFAULT_GROUP).forJob(jobKey).build();
        trig.computeFirstFireTime(null);
        if(data != null) {
            trig.setJobDataMap(data);
        }

        boolean collision = true;
        while (collision) {
            try {
                resources.getJobStore().storeTrigger(trig, false);
                collision = false;
            } catch (ObjectAlreadyExistsException oaee) {
                trig.setKey(new TriggerKey(newTriggerId(), Scheduler.DEFAULT_GROUP));
            }
        }

        notifySchedulerThread(trig.getNextFireTime().getTime());
        notifySchedulerListenersSchduled(trig);
    }
