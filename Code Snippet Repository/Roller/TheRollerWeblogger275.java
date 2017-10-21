    public void run() {
        try {
            manager.getReadWriteLock().writeLock().lock();
            mLogger.debug("Starting search index operation");
            doRun();
            mLogger.debug("Search index operation complete");

        } catch (Exception e) {
            mLogger.error("Error acquiring write lock on index", e);
            
        } finally {
            manager.getReadWriteLock().writeLock().unlock();
        }
        manager.resetSharedReader();
    }
