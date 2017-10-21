    public Date scheduleJob(JobDetail jobDetail,
            Trigger trigger) throws SchedulerException {
        validateState();

        if (jobDetail == null) {
            throw new SchedulerException("JobDetail cannot be null");
        }
        
        if (trigger == null) {
            throw new SchedulerException("Trigger cannot be null");
        }
        
        if (jobDetail.getKey() == null) {
            throw new SchedulerException("Job's key cannot be null");
        }

        if (jobDetail.getJobClass() == null) {
            throw new SchedulerException("Job's class cannot be null");
        }
        
        OperableTrigger trig = (OperableTrigger)trigger;

        if (trigger.getJobKey() == null) {
            trig.setJobKey(jobDetail.getKey());
        } else if (!trigger.getJobKey().equals(jobDetail.getKey())) {
            throw new SchedulerException(
                "Trigger does not reference given job!");
        }

        trig.validate();

        Calendar cal = null;
        if (trigger.getCalendarName() != null) {
            cal = resources.getJobStore().retrieveCalendar(trigger.getCalendarName());
        }
        Date ft = trig.computeFirstFireTime(cal);

        if (ft == null) {
            throw new SchedulerException(
                    "Based on configured schedule, the given trigger '" + trigger.getKey() + "' will never fire.");
        }

        resources.getJobStore().storeJobAndTrigger(jobDetail, trig);
        notifySchedulerListenersJobAdded(jobDetail);
        notifySchedulerThread(trigger.getNextFireTime().getTime());
        notifySchedulerListenersSchduled(trigger);

        return ft;
    }
