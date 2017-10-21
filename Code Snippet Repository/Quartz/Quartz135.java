    public void startDelayed(final int seconds) throws SchedulerException
    {
        if (shuttingDown || closed) {
            throw new SchedulerException(
                    "The Scheduler cannot be restarted after shutdown() has been called.");
        }

        Thread t = new Thread(new Runnable() {
            public void run() {
                try { Thread.sleep(seconds * 1000L); }
                catch(InterruptedException ignore) {}
                try { start(); }
                catch(SchedulerException se) {
                    getLog().error("Unable to start scheduler after startup delay.", se);
                }
            }
        });
        t.start();
    }
