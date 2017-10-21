    @Test
    public void testNoConcurrentModificationTriConsumerPutValue() {
        final JdkMapAdapterStringMap original = new JdkMapAdapterStringMap();
        original.putValue("a", "aaa");
        original.putValue("b", "aaa");
        original.putValue("c", "aaa");
        original.putValue("d", "aaa");
        original.putValue("e", "aaa");
        original.forEach(new TriConsumer<String, Object, Object>() {
            @Override
            public void accept(final String s, final Object o, final Object o2) {
                original.putValue("c" + s, "other");
            }
        }, null);
    }
