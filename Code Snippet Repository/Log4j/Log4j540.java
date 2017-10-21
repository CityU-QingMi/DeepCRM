    @Test
    public void testNullValuesArePreserved() {
        final SortedArrayStringMap original = new SortedArrayStringMap();
        original.putValue("a", "avalue");
        assertEquals(1, original.size());
        assertEquals("avalue", original.getValue("a"));

        original.putValue("a", null);
        assertEquals(1, original.size());
        assertNull("no a val", original.getValue("a"));

        original.putValue("B", null);
        assertEquals(2, original.size());
        assertNull("no B val", original.getValue("B"));
    }
