    public void reset() {
        listenersLock.writeLock().lock();
        try {
            for (final StatusListener listener : listeners) {
                closeSilently(listener);
            }
        } finally {
            listeners.clear();
            listenersLock.writeLock().unlock();
            // note this should certainly come after the unlock to avoid unnecessary nested locking
            clear();
        }
    }
