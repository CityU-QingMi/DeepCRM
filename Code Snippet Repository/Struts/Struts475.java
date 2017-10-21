    protected static LoggerFactory getLoggerFactory() {
        lock.readLock().lock();
        try {
            if (factory != null) {
                return factory;
            }
        } finally {
            lock.readLock().unlock();
        }
        lock.writeLock().lock();
        try {
            if (factory == null) {
                createLoggerFactory();
            }
            return factory;
        } finally {
            lock.writeLock().unlock();
        }
    }
