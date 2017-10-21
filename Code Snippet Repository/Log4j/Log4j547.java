    @Test
    public void testGetValueAt() throws Exception {
        final SortedArrayStringMap original = new SortedArrayStringMap();
        original.putValue("a", "avalue");
        assertEquals("a", original.getKeyAt(0));
        assertEquals("avalue", original.getValueAt(0));

        original.putValue("B", "Bvalue");
        assertEquals("B", original.getKeyAt(0));
        assertEquals("Bvalue", original.getValueAt(0));
        assertEquals("a", original.getKeyAt(1));
        assertEquals("avalue", original.getValueAt(1));

        original.putValue("3", "3value");
        assertEquals("3", original.getKeyAt(0));
        assertEquals("3value", original.getValueAt(0));
        assertEquals("B", original.getKeyAt(1));
        assertEquals("Bvalue", original.getValueAt(1));
        assertEquals("a", original.getKeyAt(2));
        assertEquals("avalue", original.getValueAt(2));
    }
