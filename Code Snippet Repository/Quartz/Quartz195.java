    public void createRemoteScheduler(String schedulerName,
            String schedulerInstanceId, String rmiBindName, String rmiHost, int rmiPort)
        throws SchedulerException {

        String uid = (rmiBindName != null) ? rmiBindName :
            QuartzSchedulerResources.getUniqueIdentifier(
                schedulerName, schedulerInstanceId);

        RemoteScheduler remoteScheduler = new RemoteScheduler(uid, rmiHost, rmiPort);

        SchedulerRepository schedRep = SchedulerRepository.getInstance();
        schedRep.bind(remoteScheduler);
        initialized = true;
    }
