    @Test
    public void testForNameEquals() {
        final String name = "Foo";
        final int intValue = 1;
        final Level level = Level.forName(name, intValue);
        assertNotNull(level);
        assertEquals(level, Level.forName(name, intValue));
        assertEquals(level, Level.getLevel(name));
        assertEquals(intValue, Level.getLevel(name).intLevel());
    }
