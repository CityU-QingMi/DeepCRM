    @Test
    public void testTraceMarkerSupplier() {
        logger2.disable().trace(marker, supplier);
        assertTrue(logger2.list.isEmpty());
        assertFalse(supplier.invoked);

        logger2.enable().trace(marker, supplier);
        assertEquals(1, logger2.list.size());
        assertTrue(supplier.invoked);

        final LogEvent event = logger2.list.get(0);
        assertEquals(Level.TRACE, event.level);
        assertSame(stringMessage, event.message.getFormattedMessage());
        assertSame(marker, event.marker);
    }
