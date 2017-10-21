    public static void oneSecondThrottle() {
        // Throttle one entry per second per weblog because time-
        // stamp in MySQL and other DBs has only 1 sec resolution
        if (THROTTLE) {
            try {
                synchronized (RollerAtomHandler.class) {
                    Thread.sleep(RollerConstants.SEC_IN_MS);
                }
            } catch (Exception ignored) {}
        }
    }
