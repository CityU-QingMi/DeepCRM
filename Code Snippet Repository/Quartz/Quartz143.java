    public void addJob(JobDetail jobDetail, boolean replace, boolean storeNonDurableWhileAwaitingScheduling) throws SchedulerException {
        validateState();

        if (!storeNonDurableWhileAwaitingScheduling && !jobDetail.isDurable()) {
            throw new SchedulerException(
                    "Jobs added with no trigger must be durable.");
        }

        resources.getJobStore().storeJob(jobDetail, replace);
        notifySchedulerThread(0L);
        notifySchedulerListenersJobAdded(jobDetail);
    }
