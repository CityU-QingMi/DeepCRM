    @Test
    public void testFatalSupplier() {
        logger2.disable().fatal(supplier);
        assertTrue(logger2.list.isEmpty());
        assertFalse(supplier.invoked);

        logger2.enable().fatal(supplier);
        assertEquals(1, logger2.list.size());
        assertTrue(supplier.invoked);

        final LogEvent event = logger2.list.get(0);
        assertEquals(Level.FATAL, event.level);
        assertSame(stringMessage, event.message.getFormattedMessage());
    }
