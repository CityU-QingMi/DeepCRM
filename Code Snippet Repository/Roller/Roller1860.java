    public final void run() {
        try {
            manager.getReadWriteLock().readLock().lock();
            doRun();

        } catch (Exception e) {
            mLogger.info("Error acquiring read lock on index", e);
        } finally {
            manager.getReadWriteLock().readLock().unlock();
        }
    }
