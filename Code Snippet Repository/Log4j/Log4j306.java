    @Test
    public void testTraceMessageSupplier() {
        logger2.disable().trace(messageSupplier);
        assertTrue(logger2.list.isEmpty());
        assertFalse(messageSupplier.invoked);

        logger2.enable().trace(messageSupplier);
        assertEquals(1, logger2.list.size());
        assertTrue(messageSupplier.invoked);

        final LogEvent event = logger2.list.get(0);
        assertEquals(Level.TRACE, event.level);
        assertSame(message, event.message);
    }
