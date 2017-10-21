    @Test
    public void testMessageWithLocks() throws Exception {
        final ReentrantLock lock = new ReentrantLock();
        lock.lock();
        final Thread thread1 = new Thread1(lock);
        thread1.start();
        ThreadDumpMessage msg;
        synchronized(this) {
            final Thread thread2 = new Thread2(this);
            thread2.start();
            try {
                Thread.sleep(200);
                msg = new ThreadDumpMessage("Testing");
            } finally {
                lock.unlock();
            }
        }

        final String message = msg.getFormattedMessage();
        //System.out.print(message);
        assertTrue("No header", message.contains("Testing"));
        assertTrue("No RUNNABLE", message.contains("RUNNABLE"));
        assertTrue("No ThreadDumpMessage", message.contains("ThreadDumpMessage"));
        //assertTrue("No Locks", message.contains("waiting on"));
        //assertTrue("No syncronizers", message.contains("locked syncrhonizers"));
    }
