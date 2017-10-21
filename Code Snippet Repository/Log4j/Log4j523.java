    @Test
    public void testSerializationOfNonSerializableValue() throws Exception {
        final SortedArrayStringMap original = new SortedArrayStringMap();
        original.putValue("a", "avalue");
        original.putValue("B", "Bvalue");
        original.putValue("unserializable", new Object());

        final byte[] binary = serialize(original);
        final SortedArrayStringMap copy = deserialize(binary);

        final SortedArrayStringMap expected = new SortedArrayStringMap();
        expected.putValue("a", "avalue");
        expected.putValue("B", "Bvalue");
        expected.putValue("unserializable", null);
        assertEquals(expected, copy);
    }
