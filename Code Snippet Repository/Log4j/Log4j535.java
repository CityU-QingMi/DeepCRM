    @Test
    public void testPutValueInsertsInAlphabeticOrder() throws Exception {
        final SortedArrayStringMap original = new SortedArrayStringMap();
        original.putValue("a", "avalue");
        original.putValue("B", "Bvalue");
        original.putValue("3", "3value");
        original.putValue("c", "cvalue");
        original.putValue("d", "dvalue");

        assertEquals("avalue", original.getValue("a"));
        assertEquals("avalue", original.getValueAt(2));

        assertEquals("Bvalue", original.getValue("B"));
        assertEquals("Bvalue", original.getValueAt(1));

        assertEquals("3value", original.getValue("3"));
        assertEquals("3value", original.getValueAt(0));

        assertEquals("cvalue", original.getValue("c"));
        assertEquals("cvalue", original.getValueAt(3));

        assertEquals("dvalue", original.getValue("d"));
        assertEquals("dvalue", original.getValueAt(4));
    }
