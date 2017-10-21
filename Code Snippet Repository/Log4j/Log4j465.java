    @Test
    public void formatTo_usesCachedMessageString() throws Exception {

        final ThreadDumpMessage message = new ThreadDumpMessage("");
        final String initial = message.getFormattedMessage();
        assertFalse("no ThreadWithCountDownLatch thread yet", initial.contains("ThreadWithCountDownLatch"));

        final CountDownLatch started = new CountDownLatch(1);
        final CountDownLatch keepAlive = new CountDownLatch(1);
        final ThreadWithCountDownLatch thread = new ThreadWithCountDownLatch(started, keepAlive);
        thread.start();
        started.await(); // ensure thread is running

        final StringBuilder result = new StringBuilder();
        message.formatTo(result);
        assertFalse("no ThreadWithCountDownLatch captured",
                result.toString().contains("ThreadWithCountDownLatch"));
        assertEquals(initial, result.toString());
        keepAlive.countDown(); // allow thread to die
    }
