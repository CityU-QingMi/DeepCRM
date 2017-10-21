    @Test
    public void testThrowable() {
        final String testMsg = "Test message {}";
        final ReusableParameterizedMessage msg = new ReusableParameterizedMessage();
        final Throwable EXCEPTION1 = new IllegalAccessError("#1");
        msg.set(testMsg, "msg", EXCEPTION1);
        assertSame(EXCEPTION1, msg.getThrowable());

        final Throwable EXCEPTION2 = new UnsupportedOperationException("#2");
        msg.set(testMsg, "msgs", EXCEPTION2);
        assertSame(EXCEPTION2, msg.getThrowable());
    }
