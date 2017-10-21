    @Test
    public void testReconfiguration() throws Exception {
        final Configuration oldConfig = context.getConfiguration();
        final int MONITOR_INTERVAL_SECONDS = 5;
        final File file = new File(CONFIG);
        final long orig = file.lastModified();
        final long newTime = orig + 10000;
        assertTrue("setLastModified should have succeeded.", file.setLastModified(newTime));
        TimeUnit.SECONDS.sleep(MONITOR_INTERVAL_SECONDS + 1);
        for (int i = 0; i < 17; ++i) {
            logger.debug("Reconfigure");
        }
        final int loopCount = 0;
        Configuration newConfig;
        do {
            Thread.sleep(100);
            newConfig = context.getConfiguration();
        } while (newConfig == oldConfig && loopCount < 5);
        assertNotSame("Reconfiguration failed", newConfig, oldConfig);
    }
