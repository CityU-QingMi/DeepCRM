    @Test
    public void testWarnMessageSupplierThrowable() {
        logger2.disable().warn(messageSupplier, throwable);
        assertTrue(logger2.list.isEmpty());
        assertFalse(messageSupplier.invoked);

        logger2.enable().warn(messageSupplier, throwable);
        assertEquals(1, logger2.list.size());
        assertTrue(messageSupplier.invoked);

        final LogEvent event = logger2.list.get(0);
        assertEquals(Level.WARN, event.level);
        assertSame(message, event.message);
        assertSame(throwable, event.throwable);
    }
