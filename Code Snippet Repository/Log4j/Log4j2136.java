    @Test
    public void testNullKeysAllowed() {
        final JdkMapAdapterStringMap original = new JdkMapAdapterStringMap();
        original.putValue("a", "avalue");
        original.putValue("B", "Bvalue");
        original.putValue("3", "3value");
        original.putValue("c", "cvalue");
        original.putValue("d", "dvalue");
        assertEquals(5, original.size());

        original.putValue(null, "nullvalue");
        assertEquals(6, original.size());
        assertEquals("nullvalue", original.getValue(null));

        original.putValue(null, "otherNullvalue");
        assertEquals("otherNullvalue", original.getValue(null));
        assertEquals(6, original.size());

        original.putValue(null, "nullvalue");
        assertEquals(6, original.size());
        assertEquals("nullvalue", original.getValue(null));

        original.putValue(null, "abc");
        assertEquals(6, original.size());
        assertEquals("abc", original.getValue(null));

    }
