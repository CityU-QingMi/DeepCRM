    @Test
    public void testDebugMarkerMessageSupplierThrowable() {
        logger2.disable().debug(marker, messageSupplier, throwable);
        assertTrue(logger2.list.isEmpty());
        assertFalse(messageSupplier.invoked);

        logger2.enable().debug(marker, messageSupplier, throwable);
        assertEquals(1, logger2.list.size());
        assertTrue(messageSupplier.invoked);

        final LogEvent event = logger2.list.get(0);
        assertEquals(Level.DEBUG, event.level);
        assertSame(marker, event.marker);
        assertSame(message, event.message);
        assertSame(throwable, event.throwable);
    }
