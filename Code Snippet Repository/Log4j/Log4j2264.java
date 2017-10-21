    @Test
    public void testMainMap() {
        MapLookup.setMainArguments(new String[] {
                "--file",
                "foo.txt" });
        final MapLookup lookup = MainMapLookup.MAIN_SINGLETON;
        assertEquals(null, lookup.lookup(null));
        assertEquals(null, lookup.lookup("X"));
        assertEquals("--file", lookup.lookup("0"));
        assertEquals("foo.txt", lookup.lookup("1"));
        assertEquals("foo.txt", lookup.lookup("--file"));
        assertEquals(null, lookup.lookup("foo.txt"));
    }
