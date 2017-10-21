    @Test
    public void testCreateEventReturnsSameInstance() throws Exception {
        final ReusableMessageFactory factory = new ReusableMessageFactory();
        final Message message1 = factory.newMessage("text, p0={} p1={} p2={} p3={}", 1, 2, 3, 4);

        ReusableMessageFactory.release(message1);
        final Message message2 = factory.newMessage("text, p0={} p1={} p2={} p3={}", 9, 8, 7, 6);
        assertSame(message1, message2);

        ReusableMessageFactory.release(message2);
        final Message message3 = factory.newMessage("text, AAA={} BBB={} p2={} p3={}", 9, 8, 7, 6);
        assertSame(message2, message3);
        ReusableMessageFactory.release(message3);
    }
