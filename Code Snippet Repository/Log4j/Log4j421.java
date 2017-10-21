    @Test
    public void testNoArgs() {
        final String testMsg = "Test message {0}";
        MessageFormatMessage msg = new MessageFormatMessage(testMsg, (Object[]) null);
        String result = msg.getFormattedMessage();
        String expected = "Test message {0}";
        assertEquals(expected, result);
        final Object[] array = null;
        msg = new MessageFormatMessage(testMsg, array, null);
        result = msg.getFormattedMessage();
        expected = "Test message null";
        assertEquals(expected, result);
    }
