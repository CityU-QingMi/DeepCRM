    @Test
    public void testErrorMessageSupplier() {
        logger2.disable().error(messageSupplier);
        assertTrue(logger2.list.isEmpty());
        assertFalse(messageSupplier.invoked);

        logger2.enable().error(messageSupplier);
        assertEquals(1, logger2.list.size());
        assertTrue(messageSupplier.invoked);

        final LogEvent event = logger2.list.get(0);
        assertEquals(Level.ERROR, event.level);
        assertSame(message, event.message);
    }
