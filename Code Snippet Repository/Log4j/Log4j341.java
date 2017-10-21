    @Test
    public void testIsInRangeErrorToDebug() {
        assertFalse(Level.OFF.isInRange(Level.ERROR, Level.DEBUG));
        assertFalse(Level.FATAL.isInRange(Level.ERROR, Level.DEBUG));
        assertTrue(Level.ERROR.isInRange(Level.ERROR, Level.DEBUG));
        assertTrue(Level.WARN.isInRange(Level.ERROR, Level.DEBUG));
        assertTrue(Level.INFO.isInRange(Level.ERROR, Level.DEBUG));
        assertTrue(Level.DEBUG.isInRange(Level.ERROR, Level.DEBUG));
        assertFalse(Level.TRACE.isInRange(Level.ERROR, Level.DEBUG));
        assertFalse(Level.ALL.isInRange(Level.ERROR, Level.DEBUG));
    }
