    public void shutdown() throws SchedulerException {
        try {
            String schedulerName = getSchedulerName();
            
            getRemoteScheduler().shutdown();
            
            SchedulerRepository.getInstance().remove(schedulerName);
        } catch (RemoteException re) {
            throw invalidateHandleCreateException(
                    "Error communicating with remote scheduler.", re);
        }
    }
