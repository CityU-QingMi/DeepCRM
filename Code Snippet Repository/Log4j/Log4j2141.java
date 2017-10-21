    @Test
    public void testGet() throws Exception {
        final JdkMapAdapterStringMap original = new JdkMapAdapterStringMap();
        original.putValue("a", "avalue");
        original.putValue("B", "Bvalue");
        original.putValue("3", "3value");

        assertEquals("avalue", original.getValue("a"));
        assertEquals("Bvalue", original.getValue("B"));
        assertEquals("3value", original.getValue("3"));

        original.putValue("0", "0value");
        assertEquals("0value", original.getValue("0"));
        assertEquals("3value", original.getValue("3"));
        assertEquals("Bvalue", original.getValue("B"));
        assertEquals("avalue", original.getValue("a"));
    }
