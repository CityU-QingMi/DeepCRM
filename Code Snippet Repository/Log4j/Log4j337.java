    @Test
    public void testLogMarkerStringParamSupplier() {
        logger2.disable().log(Level.WARN, marker, "abc {}", supplierArray1);
        assertTrue(logger2.list.isEmpty());
        assertFalse(supplier.invoked);

        logger2.enable().log(Level.WARN, marker, "abc {}", supplierArray1);
        assertEquals(1, logger2.list.size());
        assertTrue(supplier.invoked);

        final LogEvent event = logger2.list.get(0);
        assertEquals(Level.WARN, event.level);
        assertSame(marker, event.marker);
        assertEquals("abc Hi", event.message.getFormattedMessage());
    }
