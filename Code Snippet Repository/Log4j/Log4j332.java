    @Test
    public void testLogSupplier() {
        logger2.disable().log(Level.WARN, supplier);
        assertTrue(logger2.list.isEmpty());
        assertFalse(supplier.invoked);

        logger2.enable().log(Level.WARN, supplier);
        assertEquals(1, logger2.list.size());
        assertTrue(supplier.invoked);

        final LogEvent event = logger2.list.get(0);
        assertEquals(Level.WARN, event.level);
        assertSame(stringMessage, event.message.getFormattedMessage());
    }
