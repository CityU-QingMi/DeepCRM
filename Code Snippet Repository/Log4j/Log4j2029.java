    @Test
    public void testReconfig() throws InterruptedException {

        final Updater updater = new Updater();
        for (int i = 0; i < THREAD_COUNT; ++i) {
            threads[i] = new LoggerThread(i);
            threads[i].setDaemon(true);
        }
        for (int i = 0; i < THREAD_COUNT; ++i) {

            threads[i].start();
        }
        updater.setDaemon(true);
        updater.start();
        Thread.sleep(100);
        boolean stillWaiting = true;
        for (int i = 0; i < 200; ++i) {
            int index = 0;
            for (; index < THREAD_COUNT; ++index) {
                if (!finished[index]) {
                    break;
                }
            }
            if (index == THREAD_COUNT) {
                stillWaiting = false;
                break;
            }
            Thread.sleep(100);
        }
        updater.shutdown = true;
        if (stillWaiting) {
            final ThreadDumpMessage message = new ThreadDumpMessage("Waiting");
            System.err.print(message.getFormattedMessage());
        }
        for (int i = 0; i < THREAD_COUNT; ++i) {
            if (threads[i].isAlive()) {
                threads[i].interrupt();
            }
        }
        assertFalse("loggerThread didn't finish", stillWaiting);

    }
