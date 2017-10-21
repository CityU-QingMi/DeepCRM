    @Test
    public void testSerialization() throws Exception {
        final SortedArrayStringMap original = new SortedArrayStringMap();
        original.putValue("a", "avalue");
        original.putValue("B", "Bvalue");
        original.putValue("3", "3value");

        final byte[] binary = serialize(original);
        final SortedArrayStringMap copy = deserialize(binary);
        assertEquals(original, copy);
    }
