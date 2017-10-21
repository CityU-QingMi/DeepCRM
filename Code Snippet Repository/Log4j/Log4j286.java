    @Test
    public void testFatalMarkerSupplier() {
        logger2.disable().fatal(marker, supplier);
        assertTrue(logger2.list.isEmpty());
        assertFalse(supplier.invoked);

        logger2.enable().fatal(marker, supplier);
        assertEquals(1, logger2.list.size());
        assertTrue(supplier.invoked);

        final LogEvent event = logger2.list.get(0);
        assertEquals(Level.FATAL, event.level);
        assertSame(stringMessage, event.message.getFormattedMessage());
        assertSame(marker, event.marker);
    }
