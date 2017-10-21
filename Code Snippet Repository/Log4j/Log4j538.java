    @Test
    public void testRemove() {
        final SortedArrayStringMap original = new SortedArrayStringMap();
        original.putValue("a", "avalue");
        assertEquals(1, original.size());
        assertEquals("avalue", original.getValue("a"));

        original.remove("a");
        assertEquals(0, original.size());
        assertNull("no a val", original.getValue("a"));

        original.remove("B");
        assertEquals(0, original.size());
        assertNull("no B val", original.getValue("B"));
    }
