        private OldCachedClock() {
            final Thread updater = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        final long time = System.currentTimeMillis();
                        millis = time;

                        // avoid explicit dependency on sun.misc.Util
                        LockSupport.parkNanos(1000 * 1000);
                    }
                }
            }, "Clock Updater Thread");
            updater.setDaemon(true);
            updater.start();
        }
