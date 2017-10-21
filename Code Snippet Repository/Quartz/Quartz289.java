    protected synchronized void releaseLock(
        String lockName, boolean fromSynchronization) throws LockException {
        lockName = lockName.intern();

        if (isLockOwner(null, lockName)) {
            
            if (fromSynchronization == false) {
                Transaction t = getTransaction();
                if (t != null) {
                    if(getLog().isDebugEnabled()) {
                        getLog().debug(
                            "Lock '" + lockName + "' is in a JTA transaction.  " + 
                            "Return deferred by: " + Thread.currentThread().getName());
                    }
                    
                    // If we are still in a transaction, then we don't want to 
                    // actually release the lock.
                    return;
                }
            }
            
            if(getLog().isDebugEnabled()) {
                getLog().debug(
                    "Lock '" + lockName + "' returned by: "
                            + Thread.currentThread().getName());
            }
            getThreadLocks().remove(lockName);
            locks.remove(lockName);
            this.notify();
        } else if (getLog().isDebugEnabled()) {
            getLog().debug(
                "Lock '" + lockName + "' attempt to return by: "
                        + Thread.currentThread().getName()
                        + " -- but not owner!",
                new Exception("stack-trace of wrongful returner"));
        }
    }
