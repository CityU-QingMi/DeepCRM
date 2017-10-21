    public void registerListener(final StatusListener listener) {
        listenersLock.writeLock().lock();
        try {
            listeners.add(listener);
            final Level lvl = listener.getStatusLevel();
            if (listenersLevel < lvl.intLevel()) {
                listenersLevel = lvl.intLevel();
            }
        } finally {
            listenersLock.writeLock().unlock();
        }
    }
