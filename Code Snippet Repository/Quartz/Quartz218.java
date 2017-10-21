    public boolean interrupt(JobKey jobKey) throws UnableToInterruptJobException  {
        try {
            return getRemoteScheduler().interrupt(jobKey);
        } catch (RemoteException re) {
            throw new UnableToInterruptJobException(invalidateHandleCreateException(
                    "Error communicating with remote scheduler.", re));
        } catch (SchedulerException se) {
            throw new UnableToInterruptJobException(se);
        }
    }
