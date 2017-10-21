    @Test
    public void testIsInRangeWarnToInfo() {
        assertFalse(Level.OFF.isInRange(Level.WARN, Level.INFO));
        assertFalse(Level.FATAL.isInRange(Level.WARN, Level.INFO));
        assertFalse(Level.ERROR.isInRange(Level.WARN, Level.INFO));
        assertTrue(Level.WARN.isInRange(Level.WARN, Level.INFO));
        assertTrue(Level.INFO.isInRange(Level.WARN, Level.INFO));
        assertFalse(Level.DEBUG.isInRange(Level.WARN, Level.INFO));
        assertFalse(Level.TRACE.isInRange(Level.WARN, Level.INFO));
        assertFalse(Level.ALL.isInRange(Level.WARN, Level.INFO));
    }
