    @Test
    public void testLogMarkerMessageSupplier() {
        logger2.disable().log(Level.WARN, marker, messageSupplier);
        assertTrue(logger2.list.isEmpty());
        assertFalse(messageSupplier.invoked);

        logger2.enable().log(Level.WARN, marker, messageSupplier);
        assertEquals(1, logger2.list.size());
        assertTrue(messageSupplier.invoked);

        final LogEvent event = logger2.list.get(0);
        assertEquals(Level.WARN, event.level);
        assertSame(message, event.message);
        assertSame(marker, event.marker);
    }
