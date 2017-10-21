    public void pauseTrigger(TriggerKey triggerKey)
        throws SchedulerException {
        try {
            getRemoteScheduler()
                    .pauseTrigger(triggerKey);
        } catch (RemoteException re) {
            throw invalidateHandleCreateException(
                    "Error communicating with remote scheduler.", re);
        }
    }
