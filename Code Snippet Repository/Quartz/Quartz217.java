    protected RemotableQuartzScheduler getRemoteScheduler()
        throws SchedulerException {
        if (rsched != null) {
            return rsched;
        }

        try {
            Registry registry = LocateRegistry.getRegistry(rmiHost, rmiPort);

            rsched = (RemotableQuartzScheduler) registry.lookup(schedId);

        } catch (Exception e) {
            SchedulerException initException = new SchedulerException(
                    "Could not get handle to remote scheduler: "
                            + e.getMessage(), e);
            throw initException;
        }

        return rsched;
    }
