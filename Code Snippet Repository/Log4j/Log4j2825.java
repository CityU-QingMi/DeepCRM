        public static OldCachedClock instance() {
            // LOG4J2-819: use lazy initialization of threads
            if (instance == null) {
                synchronized (INSTANCE_LOCK) {
                    if (instance == null) {
                        instance = new OldCachedClock();
                    }
                }
            }
            return instance;
        }
