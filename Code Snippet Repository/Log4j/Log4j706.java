    protected final void replaceManager(final T manager) {
        this.writeLock.lock();
        try {
            final T old = this.getManager();
            if (!manager.isRunning()) {
                manager.startup();
            }
            this.manager = manager;
            old.close();
        } finally {
            this.writeLock.unlock();
        }
    }
