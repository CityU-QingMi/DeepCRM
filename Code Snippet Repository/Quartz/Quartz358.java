    protected <T> T retryExecuteInNonManagedTXLock(String lockName, TransactionCallback<T> txCallback) {
        for (int retry = 1; !shutdown; retry++) {
            try {
                return executeInNonManagedTXLock(lockName, txCallback, null);
            } catch (JobPersistenceException jpe) {
                if(retry % 4 == 0) {
                    schedSignaler.notifySchedulerListenersError("An error occurred while " + txCallback, jpe);
                }
            } catch (RuntimeException e) {
                getLog().error("retryExecuteInNonManagedTXLock: RuntimeException " + e.getMessage(), e);
            }
            try {
                Thread.sleep(getDbRetryInterval()); // retry every N seconds (the db connection must be failed)
            } catch (InterruptedException e) {
                throw new IllegalStateException("Received interrupted exception", e);
            }
        }
        throw new IllegalStateException("JobStore is shutdown - aborting retry");
    }
