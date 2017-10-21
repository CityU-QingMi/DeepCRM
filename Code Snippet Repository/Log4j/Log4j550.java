    @Test
    public void testForEachTriConsumer() throws Exception {
        final SortedArrayStringMap original = new SortedArrayStringMap();
        original.putValue("a", "avalue");
        original.putValue("B", "Bvalue");
        original.putValue("3", "3value");

        final State state = new State();
        state.data = original;
        original.forEach(COUNTER, state);
        assertEquals(state.count, original.size());
    }
