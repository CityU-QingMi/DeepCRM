    @Test
    public void debugDisabled() {
        System.gc();
        final Timer timer = new Timer("DebugDisabled", LOOP_CNT);
        timer.start();
        for (int i=0; i < LOOP_CNT; ++i) {
            logger.isDebugEnabled();
        }
        timer.stop();
        System.out.println(timer.toString());
        assertTrue("Timer exceeded max time of " + maxTime, maxTime > timer.getElapsedNanoTime());
    }
