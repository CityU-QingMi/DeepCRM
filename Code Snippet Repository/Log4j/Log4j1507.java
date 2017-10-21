    public static CachedClock instance() {
        // LOG4J2-819: use lazy initialization of threads
        CachedClock result = instance;
        if (result == null) {
            synchronized (INSTANCE_LOCK) {
                result = instance;
                if (result == null) {
                    instance = result = new CachedClock();
                }
            }
        }
        return result;
    }
