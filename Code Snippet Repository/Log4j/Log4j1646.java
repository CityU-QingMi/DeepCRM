    @Test
    public void testReconfiguration() throws Exception {
        final Configuration oldConfig = context.getConfiguration();
        final int MONITOR_INTERVAL_SECONDS = 5;
        final File file = new File("target/test-classes/" + CONFIG);
        final long orig = file.lastModified();
        final long newTime = orig + 10000;
        assertTrue("setLastModified should have succeeded.", file.setLastModified(newTime));
        TimeUnit.SECONDS.sleep(MONITOR_INTERVAL_SECONDS + 1);
        for (int i = 0; i < 17; ++i) {
            logger.debug("Reconfigure");
        }
        Thread.sleep(100);
        for (int i = 0; i < 20; i++) {
            if (context.getConfiguration() != oldConfig) {
                break;
            }
            Thread.sleep(50);
        }
        final Configuration newConfig = context.getConfiguration();
        assertNotNull("No configuration", newConfig);
        assertNotSame("Reconfiguration failed", newConfig, oldConfig);
    }
