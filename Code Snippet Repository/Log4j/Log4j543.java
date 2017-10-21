    @Test
    public void testClear() throws Exception {
        final SortedArrayStringMap original = new SortedArrayStringMap();
        original.putValue("a", "avalue");
        original.putValue("B", "Bvalue");
        original.putValue("3", "3value");
        assertEquals(3, original.size());

        original.clear();
        assertEquals(0, original.size());

        // ensure slots in the values array are nulled out
        final Field f = SortedArrayStringMap.class.getDeclaredField("values");
        f.setAccessible(true);
        final Object[] values = (Object[]) f.get(original);
        for (int i = 0; i < values.length; i++) {
            assertNull(values[i]);
        }
    }
