    @Test
    public void testFatalStringParam2Suppliers() {
        logger2.disable().fatal("abc {}{}", supplierArray2);
        assertTrue(logger2.list.isEmpty());
        assertFalse(supplier.invoked);
        assertFalse(supplier2.invoked);

        logger2.enable().fatal("abc {}{}", supplierArray2);
        assertEquals(1, logger2.list.size());
        assertTrue(supplier.invoked);
        assertTrue(supplier2.invoked);

        final LogEvent event = logger2.list.get(0);
        assertEquals(Level.FATAL, event.level);
        assertEquals("abc HiHi", event.message.getFormattedMessage());
    }
