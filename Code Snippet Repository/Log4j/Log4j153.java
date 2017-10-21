    public ConcurrentMap<String, L> getLoggersInContext(final LoggerContext context) {
        ConcurrentMap<String, L> loggers;
        lock.readLock ().lock ();
        try {
            loggers = registry.get (context);
        } finally {
            lock.readLock ().unlock ();
        }

        if (loggers != null) {
            return loggers;
        } else {
            lock.writeLock ().lock ();
            try {
                loggers = registry.get (context);
                if (loggers == null) {
                    loggers = new ConcurrentHashMap<> ();
                    registry.put (context, loggers);
                }
                return loggers;
            } finally {
                lock.writeLock ().unlock ();
            }
        }
    }
