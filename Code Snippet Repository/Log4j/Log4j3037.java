    @Test
    public void testInitFailure() throws Exception {
        willThrow(new IllegalStateException(Strings.EMPTY)).given(initializer).start();

        try {
            this.listener.contextInitialized(this.event);
            fail("Expected a RuntimeException.");
        } catch (final RuntimeException e) {
            assertEquals("The message is not correct.", "Failed to initialize Log4j properly.", e.getMessage());
        }
    }
