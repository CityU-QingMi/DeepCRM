    @Test
    public void testNotSafeWithMutableParams() {
        final String testMsg = "Test message {}";
        final Mutable param = new Mutable().set("abc");
        final ReusableParameterizedMessage msg = new ReusableParameterizedMessage();
        msg.set(testMsg, param);

        // modify parameter before calling msg.getFormattedMessage
        param.set("XYZ");
        final String actual = msg.getFormattedMessage();
        assertEquals("Should use current param value", "Test message XYZ", actual);

        // modify parameter after calling msg.getFormattedMessage
        param.set("000");
        final String after = msg.getFormattedMessage();
        assertEquals("Renders again", "Test message 000", after);
    }
