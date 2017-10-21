    public void shutdown() {
        
        LOG.debug("starting shutdown sequence");
        
        // trigger an immediate shutdown of any backgrounded tasks
        serviceScheduler.shutdownNow();
        
        // only stop if we are already running
        if(schedulerThread != null) {
            LOG.debug("Stopping scheduler");
            schedulerThread.interrupt();
        }
    }
