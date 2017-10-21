    @Test
    public void testNullKeysCopiedToAsMap() {
        final SortedArrayStringMap original = new SortedArrayStringMap();
        original.putValue("a", "avalue");
        original.putValue("B", "Bvalue");
        original.putValue("3", "3value");
        original.putValue("c", "cvalue");
        original.putValue("d", "dvalue");
        assertEquals(5, original.size());

        final HashMap<String, String> expected = new HashMap<>();
        expected.put("a", "avalue");
        expected.put("B", "Bvalue");
        expected.put("3", "3value");
        expected.put("c", "cvalue");
        expected.put("d", "dvalue");
        assertEquals("initial", expected, original.toMap());

        original.putValue(null, "nullvalue");
        expected.put(null, "nullvalue");
        assertEquals(6, original.size());
        assertEquals("with null key", expected, original.toMap());

        original.putValue(null, "otherNullvalue");
        expected.put(null, "otherNullvalue");
        assertEquals(6, original.size());
        assertEquals("with null key value2", expected, original.toMap());

        original.putValue(null, "nullvalue");
        expected.put(null, "nullvalue");
        assertEquals(6, original.size());
        assertEquals("with null key value1 again", expected, original.toMap());

        original.putValue(null, "abc");
        expected.put(null, "abc");
        assertEquals(6, original.size());
        assertEquals("with null key value3", expected, original.toMap());
    }
