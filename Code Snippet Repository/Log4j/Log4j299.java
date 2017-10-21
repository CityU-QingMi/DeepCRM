    @Test
    public void testInfoSupplier() {
        logger2.disable().info(supplier);
        assertTrue(logger2.list.isEmpty());
        assertFalse(supplier.invoked);

        logger2.enable().info(supplier);
        assertEquals(1, logger2.list.size());
        assertTrue(supplier.invoked);

        final LogEvent event = logger2.list.get(0);
        assertEquals(Level.INFO, event.level);
        assertSame(stringMessage, event.message.getFormattedMessage());
    }
