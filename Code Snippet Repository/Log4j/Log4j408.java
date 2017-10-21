    @Test
    public void testNoArgs() {
        final String testMsg = "Test message {0}";
        FormattedMessage msg = new FormattedMessage(testMsg, (Object[]) null);
        String result = msg.getFormattedMessage();
        final String expected = "Test message {0}";
        assertEquals(expected, result);
        final Object[] array = null;
        msg = new FormattedMessage(testMsg, array, null);
        result = msg.getFormattedMessage();
        assertEquals(expected, result);
    }
