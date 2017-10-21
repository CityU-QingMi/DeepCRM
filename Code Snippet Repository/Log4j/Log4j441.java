    private void assertReusableParameterizeMessage(final Message message, final String txt, final Object[] params) {
        assertTrue(message instanceof ReusableParameterizedMessage);
        final ReusableParameterizedMessage msg = (ReusableParameterizedMessage) message;
        assertTrue("reserved", msg.reserved);

        assertEquals(txt, msg.getFormat());
        assertEquals("count", msg.getParameterCount(), params.length);
        final Object[] messageParams = msg.getParameters();
        for (int i = 0; i < params.length; i++) {
            assertEquals(messageParams[i], params[i]);
        }
    }
