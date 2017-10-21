    @Test
    public void testTraceMarkerStringParamSupplier() {
        logger2.disable().trace(marker, "abc {}", supplierArray1);
        assertTrue(logger2.list.isEmpty());
        assertFalse(supplier.invoked);

        logger2.enable().trace(marker, "abc {}", supplierArray1);
        assertEquals(1, logger2.list.size());
        assertTrue(supplier.invoked);

        final LogEvent event = logger2.list.get(0);
        assertEquals(Level.TRACE, event.level);
        assertSame(marker, event.marker);
        assertEquals("abc Hi", event.message.getFormattedMessage());
    }
