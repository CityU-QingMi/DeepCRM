    @Test
    public void testDebugMarkerSupplier() {
        logger2.disable().debug(marker, supplier);
        assertTrue(logger2.list.isEmpty());
        assertFalse(supplier.invoked);

        logger2.enable().debug(marker, supplier);
        assertEquals(1, logger2.list.size());
        assertTrue(supplier.invoked);

        final LogEvent event = logger2.list.get(0);
        assertEquals(Level.DEBUG, event.level);
        assertSame(stringMessage, event.message.getFormattedMessage());
        assertSame(marker, event.marker);
    }
