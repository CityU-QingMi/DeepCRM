    @Test
    public void testCreateEventReturnsThreadLocalInstance() throws Exception {
        final ReusableLogEventFactory factory = new ReusableLogEventFactory();
        final LogEvent[] event1 = new LogEvent[1];
        final LogEvent[] event2 = new LogEvent[1];
        final Thread t1 = new Thread("THREAD 1") {
            @Override
            public void run() {
                event1[0] = callCreateEvent(factory, "a", Level.DEBUG, new SimpleMessage("abc"), null);
            }
        };
        final Thread t2 = new Thread("Thread 2") {
            @Override
            public void run() {
                event2[0] = callCreateEvent(factory, "b", Level.INFO, new SimpleMessage("xyz"), null);
            }
        };
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        assertNotNull(event1[0]);
        assertNotNull(event2[0]);
        assertNotSame(event1[0], event2[0]);
        assertEquals("logger", "a", event1[0].getLoggerName());
        assertEquals("level", Level.DEBUG, event1[0].getLevel());
        assertEquals("msg", new SimpleMessage("abc"), event1[0].getMessage());
        assertEquals("thread name", "THREAD 1", event1[0].getThreadName());
        assertEquals("tid", t1.getId(), event1[0].getThreadId());

        assertEquals("logger", "b", event2[0].getLoggerName());
        assertEquals("level", Level.INFO, event2[0].getLevel());
        assertEquals("msg", new SimpleMessage("xyz"), event2[0].getMessage());
        assertEquals("thread name", "Thread 2", event2[0].getThreadName());
        assertEquals("tid", t2.getId(), event2[0].getThreadId());
        ReusableLogEventFactory.release(event1[0]);
        ReusableLogEventFactory.release(event2[0]);
    }
