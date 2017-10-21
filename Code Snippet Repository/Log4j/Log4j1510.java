    public static CoarseCachedClock instance() {
        // LOG4J2-819: use lazy initialization of threads
        CoarseCachedClock result = instance;
        if (result == null) {
            synchronized (INSTANCE_LOCK) {
                result = instance;
                if (result == null) {
                    instance = result = new CoarseCachedClock();
                }
            }
        }
        return result;
    }
