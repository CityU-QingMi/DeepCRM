    public Date rescheduleJob(TriggerKey triggerKey,
            Trigger newTrigger) throws SchedulerException {
        validateState();

        if (triggerKey == null) {
            throw new IllegalArgumentException("triggerKey cannot be null");
        }
        if (newTrigger == null) {
            throw new IllegalArgumentException("newTrigger cannot be null");
        }

        OperableTrigger trig = (OperableTrigger)newTrigger;
        Trigger oldTrigger = getTrigger(triggerKey);
        if (oldTrigger == null) {
            return null;
        } else {
            trig.setJobKey(oldTrigger.getJobKey());
        }
        trig.validate();

        Calendar cal = null;
        if (newTrigger.getCalendarName() != null) {
            cal = resources.getJobStore().retrieveCalendar(
                    newTrigger.getCalendarName());
        }
        Date ft = trig.computeFirstFireTime(cal);

        if (ft == null) {
            throw new SchedulerException(
                    "Based on configured schedule, the given trigger will never fire.");
        }
        
        if (resources.getJobStore().replaceTrigger(triggerKey, trig)) {
            notifySchedulerThread(newTrigger.getNextFireTime().getTime());
            notifySchedulerListenersUnscheduled(triggerKey);
            notifySchedulerListenersSchduled(newTrigger);
        } else {
            return null;
        }

        return ft;
        
    }
