    @Test
    public void testCreateEventOverwritesFields() throws Exception {
        final ReusableMessageFactory factory = new ReusableMessageFactory();
        final Message message1 = factory.newMessage("text, p0={} p1={} p2={} p3={}", 1, 2, 3, 4);
        assertReusableParameterizeMessage(message1, "text, p0={} p1={} p2={} p3={}", new Object[]{
                new Integer(1), //
                new Integer(2), //
                new Integer(3), //
                new Integer(4), //
        });

        ReusableMessageFactory.release(message1);
        final Message message2 = factory.newMessage("other, A={} B={} C={} D={}", 1, 2, 3, 4);
        assertReusableParameterizeMessage(message1, "other, A={} B={} C={} D={}", new Object[]{
                new Integer(1), //
                new Integer(2), //
                new Integer(3), //
                new Integer(4), //
        });
        assertSame(message1, message2);
        ReusableMessageFactory.release(message2);
    }
