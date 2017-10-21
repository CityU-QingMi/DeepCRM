    @Test
    public void testInfoMessageSupplierThrowable() {
        logger2.disable().info(messageSupplier, throwable);
        assertTrue(logger2.list.isEmpty());
        assertFalse(messageSupplier.invoked);

        logger2.enable().info(messageSupplier, throwable);
        assertEquals(1, logger2.list.size());
        assertTrue(messageSupplier.invoked);

        final LogEvent event = logger2.list.get(0);
        assertEquals(Level.INFO, event.level);
        assertSame(message, event.message);
        assertSame(throwable, event.throwable);
    }
