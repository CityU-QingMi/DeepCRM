    public void initialize(QuartzScheduler sched)
        throws SchedulerException {
        this.qs = sched;

        Job job = null;
        JobDetail jobDetail = firedTriggerBundle.getJobDetail();

        try {
            job = sched.getJobFactory().newJob(firedTriggerBundle, scheduler);
        } catch (SchedulerException se) {
            sched.notifySchedulerListenersError(
                    "An error occured instantiating job to be executed. job= '"
                            + jobDetail.getKey() + "'", se);
            throw se;
        } catch (Throwable ncdfe) { // such as NoClassDefFoundError
            SchedulerException se = new SchedulerException(
                    "Problem instantiating class '"
                            + jobDetail.getJobClass().getName() + "' - ", ncdfe);
            sched.notifySchedulerListenersError(
                    "An error occured instantiating job to be executed. job= '"
                            + jobDetail.getKey() + "'", se);
            throw se;
        }

        this.jec = new JobExecutionContextImpl(scheduler, firedTriggerBundle, job);
    }
