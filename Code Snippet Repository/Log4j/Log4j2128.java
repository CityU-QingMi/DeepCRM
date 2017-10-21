    @Test
    public void testNoConcurrentModificationBiConsumerPutValue() {
        final JdkMapAdapterStringMap original = new JdkMapAdapterStringMap();
        original.putValue("a", "aaa");
        original.putValue("b", "aaa");
        original.putValue("c", "aaa");
        original.putValue("d", "aaa");
        original.putValue("e", "aaa");
        original.forEach(new BiConsumer<String, Object>() {
            @Override
            public void accept(final String s, final Object o) {
                original.putValue("c" + s, "other");
            }
        });
    }
