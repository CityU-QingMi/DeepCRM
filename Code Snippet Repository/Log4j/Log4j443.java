    @Test
    public void testCreateEventReturnsThreadLocalInstance() throws Exception {
        final ReusableMessageFactory factory = new ReusableMessageFactory();
        final Message[] message1 = new Message[1];
        final Message[] message2 = new Message[1];
        final Thread t1 = new Thread("THREAD 1") {
            @Override
            public void run() {
                message1[0] = factory.newMessage("text, p0={} p1={} p2={} p3={}", 1, 2, 3, 4);
            }
        };
        final Thread t2 = new Thread("Thread 2") {
            @Override
            public void run() {
                message2[0] = factory.newMessage("other, A={} B={} C={} D={}", 1, 2, 3, 4);
            }
        };
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        assertNotNull(message1[0]);
        assertNotNull(message2[0]);
        assertNotSame(message1[0], message2[0]);
        assertReusableParameterizeMessage(message1[0], "text, p0={} p1={} p2={} p3={}", new Object[]{
                new Integer(1), //
                new Integer(2), //
                new Integer(3), //
                new Integer(4), //
        });

        assertReusableParameterizeMessage(message2[0], "other, A={} B={} C={} D={}", new Object[]{
                new Integer(1), //
                new Integer(2), //
                new Integer(3), //
                new Integer(4), //
        });
        ReusableMessageFactory.release(message1[0]);
        ReusableMessageFactory.release(message2[0]);
    }
