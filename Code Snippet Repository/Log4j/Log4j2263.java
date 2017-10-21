    public void callFromMain() {
        final JmxRuntimeInputArgumentsLookup lookup = JmxRuntimeInputArgumentsLookup.JMX_SINGLETON;
        assertEquals(null, lookup.lookup(null));
        assertEquals(null, lookup.lookup("X"));
        // Eclipse adds -Dfile.encoding=Cp1252
        // assertEquals("--file", lookup.lookup("0"));
        // assertEquals("foo.txt", lookup.lookup("1"));
        //
        // JMX does not include the main arguments.
        // assertEquals("foo.txt", lookup.lookup("--file"));
        // assertEquals(null, lookup.lookup("foo.txt"));
    }
