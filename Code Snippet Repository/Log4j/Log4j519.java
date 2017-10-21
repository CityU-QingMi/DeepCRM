    @Test
    public void testToMap() throws Exception {
        final SortedArrayStringMap original = new SortedArrayStringMap();
        original.putValue("a", "avalue");
        original.putValue("B", "Bvalue");
        original.putValue("3", "3value");

        final Map<String, Object> expected = new HashMap<>();
        expected.put("a", "avalue");
        expected.put("B", "Bvalue");
        expected.put("3", "3value");

        assertEquals(expected, original.toMap());

        try {
            original.toMap().put("abc", "xyz");
        } catch (final UnsupportedOperationException ex) {
            fail("Expected map to be mutable, but " + ex);
        }
    }
