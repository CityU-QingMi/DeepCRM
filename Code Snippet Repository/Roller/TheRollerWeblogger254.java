    public final void run() {
        
        ThreadManager mgr = WebloggerFactory.getWeblogger().getThreadManager();
        
        boolean lockAcquired = false;
        try {
            log.debug(getName()+": Attempting to acquire lease");
            
            lockAcquired = mgr.registerLease(this);
            
            // now if we have a lock then run the task
            if(lockAcquired) {
                log.debug(getName()+": Lease acquired, running task");
                this.runTask();
            } else {
                log.debug(getName()+": Lease NOT acquired, cannot continue");
                return;
            }
            
        } catch (Exception ex) {
            log.error(getName()+": Unexpected exception", ex);
        } finally {
            
            if(lockAcquired) {
                
                log.debug(getName()+": Attempting to release lease");
                
                boolean lockReleased = mgr.unregisterLease(this);
                
                if(lockReleased) {
                    log.debug(getName()+": Lease released, task finished");
                } else {
                    log.debug(getName()+": Lease NOT released, some kind of problem");
                }
            }
            
            // always release Roller session
            WebloggerFactory.getWeblogger().release();
        }
        
    }
