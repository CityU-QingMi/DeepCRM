    public boolean deleteJob(JobKey jobKey)
        throws SchedulerException {
        try {
            return getRemoteScheduler()
                    .deleteJob(jobKey);
        } catch (RemoteException re) {
            throw invalidateHandleCreateException(
                    "Error communicating with remote scheduler.", re);
        }
    }
