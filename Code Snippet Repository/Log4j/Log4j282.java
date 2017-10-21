    @Test
    public void testFatalMarkerMessageSupplier() {
        logger2.disable().fatal(marker, messageSupplier);
        assertTrue(logger2.list.isEmpty());
        assertFalse(messageSupplier.invoked);

        logger2.enable().fatal(marker, messageSupplier);
        assertEquals(1, logger2.list.size());
        assertTrue(messageSupplier.invoked);

        final LogEvent event = logger2.list.get(0);
        assertEquals(Level.FATAL, event.level);
        assertSame(message, event.message);
        assertSame(marker, event.marker);
    }
