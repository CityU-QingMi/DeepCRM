    @Test
    public void testRemoveNullsOutRemovedSlot() throws Exception {
        final SortedArrayStringMap original = new SortedArrayStringMap();
        original.putValue("a", "avalue");
        original.putValue("b", "bvalue");
        original.putValue("c", "cvalue");
        original.putValue("d", "dvalue");
        original.remove("a");
        original.remove("b");
        original.remove("c");
        original.remove("d");
        assertNull(original.getValueAt(0));

        // ensure slots in the values array are nulled out
        final Field f = SortedArrayStringMap.class.getDeclaredField("values");
        f.setAccessible(true);
        final Object[] values = (Object[]) f.get(original);
        for (int i = 0; i < values.length; i++) {
            assertNull(values[i]);
        }
    }
