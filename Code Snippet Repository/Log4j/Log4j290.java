    @Test
    public void testFatalStringParamSupplier() {
        logger2.disable().fatal("abc {}", supplierArray1);
        assertTrue(logger2.list.isEmpty());
        assertFalse(supplier.invoked);

        logger2.enable().fatal("abc {}", supplierArray1);
        assertEquals(1, logger2.list.size());
        assertTrue(supplier.invoked);

        final LogEvent event = logger2.list.get(0);
        assertEquals(Level.FATAL, event.level);
        assertEquals("abc Hi", event.message.getFormattedMessage());
    }
