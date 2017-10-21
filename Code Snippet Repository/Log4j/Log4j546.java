    @Test
    public void testContainsKey() throws Exception {
        final SortedArrayStringMap original = new SortedArrayStringMap();
        assertFalse("a", original.containsKey("a"));
        assertFalse("B", original.containsKey("B"));
        assertFalse("3", original.containsKey("3"));
        assertFalse("A", original.containsKey("A"));

        original.putValue("a", "avalue");
        assertTrue("a", original.containsKey("a"));
        assertFalse("B", original.containsKey("B"));
        assertFalse("3", original.containsKey("3"));
        assertFalse("A", original.containsKey("A"));

        original.putValue("B", "Bvalue");
        assertTrue("a", original.containsKey("a"));
        assertTrue("B", original.containsKey("B"));
        assertFalse("3", original.containsKey("3"));
        assertFalse("A", original.containsKey("A"));

        original.putValue("3", "3value");
        assertTrue("a", original.containsKey("a"));
        assertTrue("B", original.containsKey("B"));
        assertTrue("3", original.containsKey("3"));
        assertFalse("A", original.containsKey("A"));

        original.putValue("A", "AAA");
        assertTrue("a", original.containsKey("a"));
        assertTrue("B", original.containsKey("B"));
        assertTrue("3", original.containsKey("3"));
        assertTrue("A", original.containsKey("A"));
    }
