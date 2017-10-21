    @Test
    public void testNoArgs() {
        final String testMsg = "Test message {}";
        final ReusableParameterizedMessage msg = new ReusableParameterizedMessage();
        msg.set(testMsg, (Object[]) null);
        String result = msg.getFormattedMessage();
        assertEquals(testMsg, result);

        msg.set(testMsg, null);
        result = msg.getFormattedMessage();
        assertEquals(testMsg, result);

        msg.set(testMsg, null, null);
        result = msg.getFormattedMessage();
        assertEquals("Test message null", result);
    }
