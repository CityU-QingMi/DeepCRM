    @Test
    public void debugLogger() {
        System.gc();
        final Timer timer = new Timer("DebugLogger", LOOP_CNT);
        final String msg = "This is a test";
        timer.start();
        for (int i=0; i < LOOP_CNT; ++i) {
            logger.debug(msg);
        }
        timer.stop();
        System.out.println(timer.toString());
        assertTrue("Timer exceeded max time of " + maxTime, maxTime > timer.getElapsedNanoTime());
    }
