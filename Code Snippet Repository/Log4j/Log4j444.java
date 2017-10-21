    @Test
    public void testGetFormattedMessage_ReturnsLatestSetString() throws Exception {
        final ReusableObjectMessage msg = new ReusableObjectMessage();
        msg.set("abc");
        assertEquals("abc", msg.getFormattedMessage());
        msg.set("def");
        assertEquals("def", msg.getFormattedMessage());
        msg.set("xyz");
        assertEquals("xyz", msg.getFormattedMessage());
    }
