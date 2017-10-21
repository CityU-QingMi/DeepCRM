    @Test
    public void testInfoMarkerSupplierThrowable() {
        logger2.disable().info(marker, supplier, throwable);
        assertTrue(logger2.list.isEmpty());
        assertFalse(supplier.invoked);

        logger2.enable().info(marker, supplier, throwable);
        assertEquals(1, logger2.list.size());
        assertTrue(supplier.invoked);

        final LogEvent event = logger2.list.get(0);
        assertEquals(Level.INFO, event.level);
        assertSame(marker, event.marker);
        assertSame(stringMessage, event.message.getFormattedMessage());
        assertSame(throwable, event.throwable);
    }
