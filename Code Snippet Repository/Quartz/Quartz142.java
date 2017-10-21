    public Date scheduleJob(Trigger trigger)
        throws SchedulerException {
        validateState();

        if (trigger == null) {
            throw new SchedulerException("Trigger cannot be null");
        }

        OperableTrigger trig = (OperableTrigger)trigger;
        
        trig.validate();

        Calendar cal = null;
        if (trigger.getCalendarName() != null) {
            cal = resources.getJobStore().retrieveCalendar(trigger.getCalendarName());
            if(cal == null) {
                throw new SchedulerException(
                    "Calendar not found: " + trigger.getCalendarName());
            }
        }
        Date ft = trig.computeFirstFireTime(cal);

        if (ft == null) {
            throw new SchedulerException(
                    "Based on configured schedule, the given trigger '" + trigger.getKey() + "' will never fire.");
        }

        resources.getJobStore().storeTrigger(trig, false);
        notifySchedulerThread(trigger.getNextFireTime().getTime());
        notifySchedulerListenersSchduled(trigger);

        return ft;
    }
