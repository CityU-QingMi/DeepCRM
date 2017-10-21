    @Test
    public void testGetFormat_ReturnsLatestSetString() throws Exception {
        final ReusableSimpleMessage msg = new ReusableSimpleMessage();
        msg.set("abc");
        assertEquals("abc", msg.getFormat());
        msg.set("def");
        assertEquals("def", msg.getFormat());
        msg.set("xyz");
        assertEquals("xyz", msg.getFormat());
    }
