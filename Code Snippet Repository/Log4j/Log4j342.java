    @Test
    public void testIsInRangeFatalToTrace() {
        assertFalse(Level.OFF.isInRange(Level.FATAL, Level.TRACE));
        assertTrue(Level.FATAL.isInRange(Level.FATAL, Level.TRACE));
        assertTrue(Level.ERROR.isInRange(Level.FATAL, Level.TRACE));
        assertTrue(Level.WARN.isInRange(Level.FATAL, Level.TRACE));
        assertTrue(Level.INFO.isInRange(Level.FATAL, Level.TRACE));
        assertTrue(Level.DEBUG.isInRange(Level.FATAL, Level.TRACE));
        assertTrue(Level.TRACE.isInRange(Level.FATAL, Level.TRACE));
        assertFalse(Level.ALL.isInRange(Level.FATAL, Level.TRACE));
    }
