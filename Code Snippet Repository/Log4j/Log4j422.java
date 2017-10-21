    @Test
    public void testUnsafeWithMutableParams() { // LOG4J2-763
        final String testMsg = "Test message {0}";
        final Mutable param = new Mutable().set("abc");
        final MessageFormatMessage msg = new MessageFormatMessage(testMsg, param);

        // modify parameter before calling msg.getFormattedMessage
        param.set("XYZ");
        final String actual = msg.getFormattedMessage();
        assertEquals("Expected most recent param value", "Test message XYZ", actual);
    }
