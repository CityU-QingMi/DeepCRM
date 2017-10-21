    @Test
    public void testSizeAndIsEmpty() throws Exception {
        final JdkMapAdapterStringMap original = new JdkMapAdapterStringMap();
        assertEquals(0, original.size());
        assertTrue("initial", original.isEmpty());

        original.putValue("a", "avalue");
        assertEquals(1, original.size());
        assertFalse("size=" + original.size(), original.isEmpty());

        original.putValue("B", "Bvalue");
        assertEquals(2, original.size());
        assertFalse("size=" + original.size(), original.isEmpty());

        original.putValue("3", "3value");
        assertEquals(3, original.size());
        assertFalse("size=" + original.size(), original.isEmpty());

        original.remove("B");
        assertEquals(2, original.size());
        assertFalse("size=" + original.size(), original.isEmpty());

        original.remove("3");
        assertEquals(1, original.size());
        assertFalse("size=" + original.size(), original.isEmpty());

        original.remove("a");
        assertEquals(0, original.size());
        assertTrue("size=" + original.size(), original.isEmpty());
    }
