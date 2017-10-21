    @Test
    public void testRouter() throws Exception {
        final Logger logger = LogManager.getLogger(AsyncAppenderQueueFullPolicyTest.class);

        assertEquals(4, asyncAppender.getQueueCapacity());
        logger.error("event 1 - gets taken off the queue");
        logger.warn("event 2");
        logger.info("event 3");
        logger.info("event 4");
        while (asyncAppender.getQueueRemainingCapacity() == 0) {
            Thread.yield(); // wait until background thread takes one element off the queue
        }
        logger.info("event 5 - now the queue is full");
        assertEquals("queue remaining capacity", 0, asyncAppender.getQueueRemainingCapacity());
        assertEquals("EventRouter invocations", 0, policy.queueFull.get());

        final Thread release = new Thread("AsyncAppenderReleaser") {
            @Override
            public void run() {
                while (policy.queueFull.get() == 0) {
                    try {
                        Thread.sleep(10L);
                    } catch (final InterruptedException ignored) {
                        //ignored
                    }
                }
                blockingAppender.running = false;
            }
        };
        release.setDaemon(true);
        release.start();
        logger.fatal("this blocks until queue space available");
        assertEquals(1, policy.queueFull.get());
    }
