    public void shutdown(boolean waitForJobsToComplete)
        throws SchedulerException {
        try {
            String schedulerName = getSchedulerName();
            
            getRemoteScheduler().shutdown(waitForJobsToComplete);

            SchedulerRepository.getInstance().remove(schedulerName);
        } catch (RemoteException re) {
            throw invalidateHandleCreateException(
                    "Error communicating with remote scheduler.", re);
        }
    }
