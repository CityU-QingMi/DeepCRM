    @Test
    public void testEquals() {
        final JdkMapAdapterStringMap original = new JdkMapAdapterStringMap();
        original.putValue("a", "avalue");
        original.putValue("B", "Bvalue");
        original.putValue("3", "3value");
        assertEquals(original, original); // equal to itself

        final JdkMapAdapterStringMap other = new JdkMapAdapterStringMap();
        other.putValue("a", "avalue");
        assertNotEquals(original, other);

        other.putValue("B", "Bvalue");
        assertNotEquals(original, other);

        other.putValue("3", "3value");
        assertEquals(original, other);

        other.putValue("3", "otherValue");
        assertNotEquals(original, other);

        other.putValue("3", null);
        assertNotEquals(original, other);

        other.putValue("3", "3value");
        assertEquals(original, other);
    }
