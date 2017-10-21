    @Test
    public void testForEachTriConsumer() throws Exception {
        final JdkMapAdapterStringMap original = new JdkMapAdapterStringMap();
        original.putValue("a", "avalue");
        original.putValue("B", "Bvalue");
        original.putValue("3", "3value");

        final JdkMapAdapterStringMapTest.State state = new JdkMapAdapterStringMapTest.State();
        state.data = original;
        original.forEach(COUNTER, state);
        assertEquals(state.count, original.size());
    }
