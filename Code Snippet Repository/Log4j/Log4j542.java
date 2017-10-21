    @Test
    public void testGetValue_GetValueAt() throws Exception {
        final SortedArrayStringMap original = new SortedArrayStringMap();
        original.putValue("a", "avalue");
        original.putValue("B", "Bvalue");
        original.putValue("3", "3value");

        assertEquals("avalue", original.getValue("a"));
        assertEquals("avalue", original.getValueAt(2));

        assertEquals("Bvalue", original.getValue("B"));
        assertEquals("Bvalue", original.getValueAt(1));

        assertEquals("3value", original.getValue("3"));
        assertEquals("3value", original.getValueAt(0));

        original.putValue("0", "0value");
        assertEquals("0value", original.getValue("0"));
        assertEquals("0value", original.getValueAt(0));
        assertEquals("3value", original.getValue("3"));
        assertEquals("3value", original.getValueAt(1));
        assertEquals("Bvalue", original.getValue("B"));
        assertEquals("Bvalue", original.getValueAt(2));
        assertEquals("avalue", original.getValue("a"));
        assertEquals("avalue", original.getValueAt(3));
    }
