    @Test
    public void testSerialization() throws Exception {
        final JdkMapAdapterStringMap original = new JdkMapAdapterStringMap();
        original.putValue("a", "avalue");
        original.putValue("B", "Bvalue");
        original.putValue("3", "3value");

        final byte[] binary = serialize(original);
        final JdkMapAdapterStringMap copy = deserialize(binary);
        assertEquals(original, copy);
    }
