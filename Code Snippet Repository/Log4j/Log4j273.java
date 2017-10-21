    @Test
    public void testErrorMarkerMessageSupplierThrowable() {
        logger2.disable().error(marker, messageSupplier, throwable);
        assertTrue(logger2.list.isEmpty());
        assertFalse(messageSupplier.invoked);

        logger2.enable().error(marker, messageSupplier, throwable);
        assertEquals(1, logger2.list.size());
        assertTrue(messageSupplier.invoked);

        final LogEvent event = logger2.list.get(0);
        assertEquals(Level.ERROR, event.level);
        assertSame(marker, event.marker);
        assertSame(message, event.message);
        assertSame(throwable, event.throwable);
    }
