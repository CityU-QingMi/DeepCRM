    @Test
    public void testIndexOfKey() throws Exception {
        final SortedArrayStringMap original = new SortedArrayStringMap();
        original.putValue("a", "avalue");
        assertEquals(0, original.indexOfKey("a"));

        original.putValue("B", "Bvalue");
        assertEquals(1, original.indexOfKey("a"));
        assertEquals(0, original.indexOfKey("B"));

        original.putValue("3", "3value");
        assertEquals(2, original.indexOfKey("a"));
        assertEquals(1, original.indexOfKey("B"));
        assertEquals(0, original.indexOfKey("3"));

        original.putValue("A", "AAA");
        assertEquals(3, original.indexOfKey("a"));
        assertEquals(2, original.indexOfKey("B"));
        assertEquals(1, original.indexOfKey("A"));
        assertEquals(0, original.indexOfKey("3"));

        original.putValue("C", "CCC");
        assertEquals(4, original.indexOfKey("a"));
        assertEquals(3, original.indexOfKey("C"));
        assertEquals(2, original.indexOfKey("B"));
        assertEquals(1, original.indexOfKey("A"));
        assertEquals(0, original.indexOfKey("3"));

        original.putValue("2", "222");
        assertEquals(5, original.indexOfKey("a"));
        assertEquals(4, original.indexOfKey("C"));
        assertEquals(3, original.indexOfKey("B"));
        assertEquals(2, original.indexOfKey("A"));
        assertEquals(1, original.indexOfKey("3"));
        assertEquals(0, original.indexOfKey("2"));
    }
