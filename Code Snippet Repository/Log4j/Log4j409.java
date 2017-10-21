    @Test
    public void testParamNoArgs() {
        final String testMsg = "Test message {}";
        FormattedMessage msg = new FormattedMessage(testMsg, null);
        String result = msg.getFormattedMessage();
        assertEquals(testMsg, result);
        final Object[] array = null;
        msg = new FormattedMessage(testMsg, array, null);
        result = msg.getFormattedMessage();
        assertEquals(testMsg, result);
    }
