    private CachedClock() {
        final Thread updater = new Log4jThread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    final long time = System.currentTimeMillis();
                    millis = time;

                    // avoid explicit dependency on sun.misc.Util
                    LockSupport.parkNanos(1000 * 1000);
                }
            }
        }, "CachedClock Updater Thread");
        updater.setDaemon(true);
        updater.start();
    }
