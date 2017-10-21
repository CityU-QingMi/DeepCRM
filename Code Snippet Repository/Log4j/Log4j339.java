    @Test
    public void testDebugStringParamSupplier() {
        logger2.disable().debug("abc {}", supplierArray1);
        assertTrue(logger2.list.isEmpty());
        assertFalse(supplier.invoked);

        logger2.enable().debug("abc {}", supplierArray1);
        assertEquals(1, logger2.list.size());
        assertTrue(supplier.invoked);

        final LogEvent event = logger2.list.get(0);
        assertEquals(Level.DEBUG, event.level);
        assertEquals("abc Hi", event.message.getFormattedMessage());
    }
