    public void start() throws SchedulerException {

        if (shuttingDown|| closed) {
            throw new SchedulerException(
                    "The Scheduler cannot be restarted after shutdown() has been called.");
        }

        // QTZ-212 : calling new schedulerStarting() method on the listeners
        // right after entering start()
        notifySchedulerListenersStarting();

        if (initialStart == null) {
            initialStart = new Date();
            this.resources.getJobStore().schedulerStarted();            
            startPlugins();
        } else {
            resources.getJobStore().schedulerResumed();
        }

        schedThread.togglePause(false);

        getLog().info(
                "Scheduler " + resources.getUniqueIdentifier() + " started.");
        
        notifySchedulerListenersStarted();
    }
