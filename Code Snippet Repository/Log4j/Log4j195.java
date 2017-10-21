    public void removeListener(final StatusListener listener) {
        closeSilently(listener);
        listenersLock.writeLock().lock();
        try {
            listeners.remove(listener);
            int lowest = Level.toLevel(DEFAULT_STATUS_LEVEL, Level.WARN).intLevel();
            for (final StatusListener statusListener : listeners) {
                final int level = statusListener.getStatusLevel().intLevel();
                if (lowest < level) {
                    lowest = level;
                }
            }
            listenersLevel = lowest;
        } finally {
            listenersLock.writeLock().unlock();
        }
    }
