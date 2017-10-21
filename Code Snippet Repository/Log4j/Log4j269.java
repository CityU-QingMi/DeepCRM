    @Test
    public void testDebugMarkerMessageSupplier() {
        logger2.disable().debug(marker, messageSupplier);
        assertTrue(logger2.list.isEmpty());
        assertFalse(messageSupplier.invoked);

        logger2.enable().debug(marker, messageSupplier);
        assertEquals(1, logger2.list.size());
        assertTrue(messageSupplier.invoked);

        final LogEvent event = logger2.list.get(0);
        assertEquals(Level.DEBUG, event.level);
        assertSame(message, event.message);
        assertSame(marker, event.marker);
    }
