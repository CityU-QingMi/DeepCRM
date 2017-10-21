    @Test
    public void testIsInRangeOffToAll() {
        assertTrue(Level.OFF.isInRange(Level.OFF, Level.ALL));
        assertTrue(Level.FATAL.isInRange(Level.OFF, Level.ALL));
        assertTrue(Level.ERROR.isInRange(Level.OFF, Level.ALL));
        assertTrue(Level.WARN.isInRange(Level.OFF, Level.ALL));
        assertTrue(Level.INFO.isInRange(Level.OFF, Level.ALL));
        assertTrue(Level.DEBUG.isInRange(Level.OFF, Level.ALL));
        assertTrue(Level.TRACE.isInRange(Level.OFF, Level.ALL));
        assertTrue(Level.ALL.isInRange(Level.OFF, Level.ALL));
    }
