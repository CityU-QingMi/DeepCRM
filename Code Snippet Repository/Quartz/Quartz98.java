    public void triggerJob(OperableTrigger trig) throws SchedulerException {
        validateState();

        trig.computeFirstFireTime(null);

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
