    @Test
    public void testErrorSupplierThrowable() {
        logger2.disable().error(supplier, throwable);
        assertTrue(logger2.list.isEmpty());
        assertFalse(supplier.invoked);

        logger2.enable().error(supplier, throwable);
        assertEquals(1, logger2.list.size());
        assertTrue(supplier.invoked);

        final LogEvent event = logger2.list.get(0);
        assertEquals(Level.ERROR, event.level);
        assertSame(stringMessage, event.message.getFormattedMessage());
        assertSame(throwable, event.throwable);
    }
